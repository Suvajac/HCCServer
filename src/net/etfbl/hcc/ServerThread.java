package net.etfbl.hcc;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import net.etfbl.hcc.model.*;
import net.etfbl.hcc.util.HCCUtil;
import net.etfbl.hcc.util.ProtokolPoruka;

public class ServerThread extends Thread{
	private Socket sock;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ServerThread(Socket sock){
		try{
			this.sock = sock;
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
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
							System.out.println("Korisnik.getKorisnik");
							String username = (String) ppin.getListaObjekata().get(0);
							Korisnik k=null;
							k=HCCUtil.getDAOFactory().getGostDAO().getKorisnik(username);
							if(k==null)
								k=HCCUtil.getDAOFactory().getRecepcionarDAO().getKorisnik(username);
							rezLista.clear();
							rezLista.add(k);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Gost.getKorisnike" :
							System.out.println("Gost.getKorisnike");
							ArrayList<Gost> gosti=null;
							gosti=HCCUtil.getDAOFactory().getGostDAO().getKorisnike();
							rezLista.clear();
							rezLista.add(gosti);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Utisak.dodaj" :
							System.out.println("Utisak.dodaj");
							Utisak u=(Utisak) ppin.getListaObjekata().get(0);
							HCCUtil.getDAOFactory().getUtisakDAO().dodaj(u);
							rezLista.clear();
							rezLista.add(new String("Utisak dodan!"));
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Utisak.getUtisci" :
							System.out.println("Utisak.getUtisci");
							ArrayList<Utisak> utisci = HCCUtil.getDAOFactory().getUtisakDAO().getUtisci();
							rezLista.clear();
							rezLista.add(utisci);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Utisak.obrisi" :
							System.out.println("Utisak.obrisi");
							test=HCCUtil.getDAOFactory().getUtisakDAO().obrisi((Utisak)ppin.getListaObjekata().get(0));
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Oglas.getOglasi" :
							System.out.println("Oglas.getOglasi");
							ArrayList<Oglas> oglasi = HCCUtil.getDAOFactory().getOglasDAO().getOglasi();
							rezLista.clear();
							rezLista.add(oglasi);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Popust.potvrdiPopust" :
							System.out.println("Popust.potvrdiPopust");
							test = HCCUtil.getDAOFactory().getPopustDAO().potvrdiPopust((int)ppin.getListaObjekata().get(0),(Gost) ppin.getListaObjekata().get(1));
							rezLista.clear();
							rezLista.add(test);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Popust.getPopusti" :
							System.out.println("Popust.getPopusti");
							ArrayList<Popust> popusti = HCCUtil.getDAOFactory().getPopustDAO().getPopuste();
							rezLista.clear();
							rezLista.add(popusti);
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Popust.dodaj" :
							System.out.println("Popust.dodaj");
							Popust p=(Popust) ppin.getListaObjekata().get(0);
							HCCUtil.getDAOFactory().getPopustDAO().dodaj(p);
							rezLista.clear();
							rezLista.add(new String("Popust dodan!"));
							ppout=new ProtokolPoruka("response");
							ppout.setListaObjekata(rezLista);
							break;
						case "Popust.obrisi" :
							System.out.println("Popust.obrisi");
							test=HCCUtil.getDAOFactory().getPopustDAO().obrisi((Popust) ppin.getListaObjekata().get(0));
							rezLista.clear();
							rezLista.add(test);
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
		System.out.println("Log out");
	}
}
