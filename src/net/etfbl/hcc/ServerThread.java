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
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - je logovan");
							rezLista.clear();
							rezLista.add(k);
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
						case "Popust.potvrdiPopust" :
							System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" ["+username+"] - Popust.potvrdiPopust");
							test = HCCUtil.getDAOFactory().getPopustDAO().potvrdiPopust((int)ppin.getListaObjekata().get(0),(Gost) ppin.getListaObjekata().get(1));
							rezLista.clear();
							rezLista.add(test);
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
							HCCUtil.getDAOFactory().getProizvodDAO().dodaj(proizvod);
							rezLista.clear();
							rezLista.add(new String("Proizvod dodan!"));
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
