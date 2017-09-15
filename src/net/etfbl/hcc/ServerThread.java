package net.etfbl.hcc;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

import net.etfbl.hcc.model.*;
import net.etfbl.hcc.util.HCCUtil;
import net.etfbl.hcc.util.ProtokolPoruka;
import net.etfbl.hcc.util.TemporalStringConverters;

public class ServerThread extends Thread{
	private Socket sock;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String username;

	public ServerThread(Socket sock){
		try{
			this.sock = sock;
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			username=" ";
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run(){
		ProtokolPoruka ppin=null;
		ProtokolPoruka ppout=null;
		ArrayList<Object> rezLista=new ArrayList<Object>();
		boolean test=false;
		try {
			while(ppin==null || !ppin.getTip().equals("Korisnik.logout")){
				ppin = (ProtokolPoruka) in.readObject();
					switch(ppin.getTip()){
						case "Korisnik.getKorisnik" :
							username = (String) ppin.getListaObjekata().get(0);
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Korisnik.getKorisnik");
							Korisnik k=null;
							k=HCCUtil.getDAOFactory().getGostDAO().getKorisnik(username);
							if(k==null)
								k=HCCUtil.getDAOFactory().getRecepcionarDAO().getKorisnik(username);
							if(k!=null)
								System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - je logovan");
							rezLista.clear();
							rezLista.add(k);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Registracija.getRegistracije" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Registracija.getRegistracije");
							ArrayList<Registracija> registracije=null;
							registracije=HCCUtil.getDAOFactory().getRegistracijaDAO().getRegistracije();
							rezLista.clear();
							rezLista.add(registracije);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Registracija.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Registracija.dodaj");
							Registracija registracija=(Registracija)ppin.getListaObjekata().get(0);
							Gost oldGost=HCCUtil.getDAOFactory().getGostDAO().getKorisnikOld(registracija.getGost().getUsername());
							if(oldGost==null){    //provjeri se da li dobijeni gost vec postoji u bazi
								Gost tempGRacun=registracija.getGost(); //ako ne onda se pokupe podaci tog gosta
								tempGRacun.setRacun(HCCUtil.getDAOFactory().getRacunDAO().kreiraj()); //novi racun mu se doda
								HCCUtil.getDAOFactory().getGostDAO().dodaj(tempGRacun);  //doda se i taj gost u bazu
								registracija.setGost(tempGRacun);  //registracija dobije gosta koji je u bazi i koji ima racun
								//dodaj novog gosta sa racunom
							}else{
								Gost tempGRacun=registracija.getGost();//uzme se taj gost koji vec postoji
								tempGRacun.setRacun(HCCUtil.getDAOFactory().getRacunDAO().kreiraj()); //kreira mu se novi racun
								HCCUtil.getDAOFactory().getGostDAO().noviRacun(tempGRacun); //u bazu se setuje novi racun za tog gosta
								registracija.setGost(tempGRacun); //registracija.gost se doda taj novi racun
								//dodaj novi racun na novog gosta
							}

							//sobu dobijam dobru
							HCCUtil.getDAOFactory().getRegistracijaDAO().dodaj(registracija); //upise se registracija u bazu
							rezLista.clear();
							rezLista.add(registracija);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Gost.getKorisnike" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Gost.getKorisnike");
							ArrayList<Gost> gosti=null;
							gosti=HCCUtil.getDAOFactory().getGostDAO().getKorisnike();
							rezLista.clear();
							rezLista.add(gosti);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						/*case "Gost.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Gost.dodaj");
							Gost g=(Gost) ppin.getListaObjekata().get(0);
							HCCUtil.getDAOFactory().getGostDAO().dodaj(g);
							Gost gResponse=HCCUtil.getDAOFactory().getGostDAO().getKorisnik(g.getUsername());
							rezLista.clear();
							rezLista.add(gResponse);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;*/
						case "Gost.azuriraj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Gost.promijeniSifru");
							Gost newgost=(Gost) ppin.getListaObjekata().get(0);
							test=HCCUtil.getDAOFactory().getGostDAO().azuriraj(newgost);
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "SportUsluga.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - SportUsluga.dodaj");
							SportUsluga spuslg=(SportUsluga) ppin.getListaObjekata().get(0);
							test=HCCUtil.getDAOFactory().getSportTerminDAO().provjeriTermin(spuslg.getSportTermin());
							int rezultatIdSpUslg=0;
							boolean daLiSetovoOpremu=false;
							boolean daLiSetovoStavkuSport=false;
							if(test){
								int idTerminaSport=HCCUtil.getDAOFactory().getSportTerminDAO().dodaj(spuslg.getSportTermin());
								SportTermin tempSpTer=spuslg.getSportTermin();
								tempSpTer.setIdTermina(idTerminaSport);
								spuslg.setSportTermin(tempSpTer);
								rezultatIdSpUslg=HCCUtil.getDAOFactory().getSportUslugaDAO().dodaj(spuslg);
								spuslg.setIdUsluge(rezultatIdSpUslg);
								daLiSetovoOpremu=HCCUtil.getDAOFactory().getOpremaSportUslugaDAO().setOprema(spuslg);
								Stavka stavkaSpUslg=new Stavka(0,LocalDateTime.now(),spuslg);
								daLiSetovoStavkuSport=HCCUtil.getDAOFactory().getStavkaDAO().dodaj(stavkaSpUslg, (Racun) ppin.getListaObjekata().get(1));
							}
							rezLista.clear();
							rezLista.add(test);
							rezLista.add(rezultatIdSpUslg);
							rezLista.add(daLiSetovoOpremu);
							rezLista.add(daLiSetovoStavkuSport);
							if(test && rezultatIdSpUslg>0 && daLiSetovoStavkuSport){
								Obavjestenje obsu=new Obavjestenje(0,"Korisnik "+username+" je narucio:\n"+spuslg.toString(),
										LocalDateTime.now(),false);
								MulticastServer.posaljiObavjestenje(obsu);
								HCCUtil.getDAOFactory().getObavjestenjeDAO().dodaj(obsu);
							}
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "WellnessUsluga.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - WellnessUsluga.dodaj");
							WellnessUsluga welluslg=(WellnessUsluga) ppin.getListaObjekata().get(0);
							test=HCCUtil.getDAOFactory().getWellnessTerminDAO().provjeriTermin(welluslg.getWellnessTermin());
							int rezultatIdWellUslg=0;
							boolean daLiSetovoStavkuWell=false;
							if(test){
								int idTerminaWellness=HCCUtil.getDAOFactory().getWellnessTerminDAO().dodaj(welluslg.getWellnessTermin());
								WellnessTermin tempWellTer=welluslg.getWellnessTermin();
								tempWellTer.setIdTermina(idTerminaWellness);
								welluslg.setWellnessTermin(tempWellTer);
								rezultatIdWellUslg=HCCUtil.getDAOFactory().getWellnessUslugaDAO().dodaj(welluslg);
								welluslg.setIdUsluge(rezultatIdWellUslg);
								Stavka stavkaWellUslg=new Stavka(0,LocalDateTime.now(),welluslg);
								daLiSetovoStavkuWell=HCCUtil.getDAOFactory().getStavkaDAO().dodaj(stavkaWellUslg, (Racun) ppin.getListaObjekata().get(1));
							}
							rezLista.clear();
							rezLista.add(test);
							rezLista.add(rezultatIdWellUslg);
							rezLista.add(daLiSetovoStavkuWell);
							if(test && rezultatIdWellUslg>0 && daLiSetovoStavkuWell){
								Obavjestenje obwu=new Obavjestenje(0,"Korisnik "+username+" je narucio:\n"+welluslg.toString(),
										LocalDateTime.now(),false);
								MulticastServer.posaljiObavjestenje(obwu);
								HCCUtil.getDAOFactory().getObavjestenjeDAO().dodaj(obwu);
							}
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "UslugaRestorana.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - UslugaRestorana.dodaj");
							UslugaRestorana uslgrest=(UslugaRestorana) ppin.getListaObjekata().get(0);
							int rezIdRestUslg=HCCUtil.getDAOFactory().getUslugaRestoranaDAO().dodaj(uslgrest);
							uslgrest.setIdUsluge(rezIdRestUslg);
							boolean daLiSetovoProizvodeRestoran=HCCUtil.getDAOFactory().getProizvodiRestoranDAO().setProizvodi(uslgrest);
							Stavka stavkaUslgRest=new Stavka(0,LocalDateTime.now(),uslgrest);
							boolean daLiSetovoStavkuRest=HCCUtil.getDAOFactory().getStavkaDAO().dodaj(stavkaUslgRest, (Racun) ppin.getListaObjekata().get(1));
							rezLista.clear();
							rezLista.add(true);
							rezLista.add(rezIdRestUslg);
							rezLista.add(daLiSetovoProizvodeRestoran);
							rezLista.add(daLiSetovoStavkuRest);
							if(rezIdRestUslg>0 && daLiSetovoStavkuRest){
								Obavjestenje obur=new Obavjestenje(0,"Korisnik "+username+" je narucio:\n"+uslgrest.toString(),
										LocalDateTime.now(),false);
								MulticastServer.posaljiObavjestenje(obur);
								HCCUtil.getDAOFactory().getObavjestenjeDAO().dodaj(obur);
							}
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "SobnaUsluga.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - SobnaUsluga.dodaj");
							SobnaUsluga sobuslg=(SobnaUsluga) ppin.getListaObjekata().get(0);
							int rezultatIdSobnaUslg=HCCUtil.getDAOFactory().getSobnaUslugaDAO().dodaj(sobuslg);
							sobuslg.setIdUsluge(rezultatIdSobnaUslg);
							boolean daLiSetovoProizvode=HCCUtil.getDAOFactory().getProizvodiSobaDAO().setProizvodi(sobuslg);
							Stavka stavkaSobUslg=new Stavka(0,LocalDateTime.now(),sobuslg);
							boolean daLiSetovoStavkuSoba=HCCUtil.getDAOFactory().getStavkaDAO().dodaj(stavkaSobUslg, (Racun) ppin.getListaObjekata().get(1));
							rezLista.clear();
							rezLista.add(true);
							rezLista.add(rezultatIdSobnaUslg);
							rezLista.add(daLiSetovoProizvode);
							rezLista.add(daLiSetovoStavkuSoba);
							if(rezultatIdSobnaUslg>0 && daLiSetovoStavkuSoba){
								Obavjestenje obsu=new Obavjestenje(0,"Korisnik "+username+" je narucio:\n"+sobuslg.toString(),
										LocalDateTime.now(),false);
								MulticastServer.posaljiObavjestenje(obsu);
								HCCUtil.getDAOFactory().getObavjestenjeDAO().dodaj(obsu);
							}
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Utisak.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Utisak.dodaj");
							Utisak u=(Utisak) ppin.getListaObjekata().get(0);
							HCCUtil.getDAOFactory().getUtisakDAO().dodaj(u);
							rezLista.clear();
							rezLista.add(new String("Utisak dodan!"));
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Utisak.getUtisci" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Utisak.getUtisci");
							ArrayList<Utisak> utisci = HCCUtil.getDAOFactory().getUtisakDAO().getUtisci();
							rezLista.clear();
							rezLista.add(utisci);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Utisak.obrisi" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Utisak.obrisi");
							test=HCCUtil.getDAOFactory().getUtisakDAO().obrisi((Utisak)ppin.getListaObjekata().get(0));
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Oglas.getOglasi" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Oglas.getOglasi");
							ArrayList<Oglas> oglasi = HCCUtil.getDAOFactory().getOglasDAO().getOglasi();
							rezLista.clear();
							rezLista.add(oglasi);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Oglas.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Oglas.dodaj");
							Oglas o=(Oglas) ppin.getListaObjekata().get(0);
							int rezDodavanja=HCCUtil.getDAOFactory().getOglasDAO().dodaj(o);
							rezLista.clear();
							rezLista.add(rezDodavanja);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Oglas.obrisi" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Oglas.obrisi");
							test=HCCUtil.getDAOFactory().getOglasDAO().obrisi((Oglas) ppin.getListaObjekata().get(0));
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Popust.potvrdiPopust" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Popust.potvrdiPopust");
							int kodPopusta=(int)ppin.getListaObjekata().get(0);
							boolean popustAktivan=HCCUtil.getDAOFactory().getPopustDAO().provjeriPopust(kodPopusta);
							boolean iskoristenPopust=false;
							if(popustAktivan){
								test = HCCUtil.getDAOFactory().getPopustDAO().potvrdiPopust(kodPopusta,(Gost) ppin.getListaObjekata().get(1));
								iskoristenPopust=HCCUtil.getDAOFactory().getPopustDAO().popustIskoristi(kodPopusta);
							}
							rezLista.clear();
							rezLista.add(popustAktivan);
							rezLista.add(iskoristenPopust);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Popust.getPopusti" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Popust.getPopusti");
							ArrayList<Popust> popusti = HCCUtil.getDAOFactory().getPopustDAO().getPopuste();
							rezLista.clear();
							rezLista.add(popusti);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Popust.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Popust.dodaj");
							Popust p=(Popust) ppin.getListaObjekata().get(0);
							HCCUtil.getDAOFactory().getPopustDAO().dodaj(p);
							rezLista.clear();
							rezLista.add(new String("Popust dodan!"));
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Popust.obrisi" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Popust.obrisi");
							test=HCCUtil.getDAOFactory().getPopustDAO().obrisi((Popust) ppin.getListaObjekata().get(0));
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Obavjestenje.getObavjestenja" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Obavjestenje.getObavjestenja");
							ArrayList<Obavjestenje> obav = HCCUtil.getDAOFactory().getObavjestenjeDAO().getObavjestenja();
							rezLista.clear();
							rezLista.add(obav);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Obavjestenje.procitajObavjestenje" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Obavjestenje.procitajObavjestenje");
							test=HCCUtil.getDAOFactory().getObavjestenjeDAO().procitajObavjestenje((Obavjestenje) ppin.getListaObjekata().get(0));
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Proizvod.getProizvodi" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Proizvod.getProizvodi");
							ArrayList<Proizvod> proiz = HCCUtil.getDAOFactory().getProizvodDAO().getProizvodi();
							rezLista.clear();
							rezLista.add(proiz);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Proizvod.dodaj" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Proizvod.dodaj");
							Proizvod proizvod=(Proizvod) ppin.getListaObjekata().get(0);
							int rezIdProiz=HCCUtil.getDAOFactory().getProizvodDAO().dodaj(proizvod);
							rezLista.clear();
							rezLista.add(rezIdProiz);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Proizvod.obrisi" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Proizvod.obrisi");
							test=HCCUtil.getDAOFactory().getProizvodDAO().obrisi((Proizvod) ppin.getListaObjekata().get(0));
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "SportskaOprema.getOprema" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - SportskaOprema.getOprema");
							ArrayList<SportskaOprema> oprema = HCCUtil.getDAOFactory().getSportskaOpremaDAO().getOprema();
							rezLista.clear();
							rezLista.add(oprema);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Racun.plati" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Racun.plati");
							Racun rac=(Racun) ppin.getListaObjekata().get(0);
							test=HCCUtil.getDAOFactory().getRacunDAO().azuriraj(rac);
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Soba.getSlobodneSobe" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Soba.getSlobodneSobe");
							ArrayList<Soba> sobe = HCCUtil.getDAOFactory().getSobaDAO().getSlobodneSobe();
							rezLista.clear();
							rezLista.add(sobe);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						default :
					}
				out.reset();
				out.writeObject(ppout);
				out.flush();

			}
		}
		catch (IOException  | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Log out");
	}
}
