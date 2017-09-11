package net.etfbl.hcc;

import java.io.*;
import java.net.Socket;

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
		ProtokolPoruka ppin = null;
		ProtokolPoruka ppout = null;
		String poruka = null;
		try {
			while(poruka==null || !poruka.equals("Korisnik.logout")){
				poruka = (String) in.readObject();
					switch(poruka){
						case "Korisnik.getKorisnik" :
							String username = (String) in.readObject();
							System.out.println("Korisnik.getKorisnik");
							Korisnik k=null;
							k=HCCUtil.getDAOFactory().getGostDAO().getKorisnik(username);
							if(k==null)
								k=HCCUtil.getDAOFactory().getRecepcionarDAO().getKorisnik(username);
							out.reset();
							out.writeObject(k);
							out.flush();
							break;
						case "Utisak.dodaj" :
							System.out.println("Utisak.dodaj");
							Utisak u=(Utisak)ppin.getObjekti()[0];
							HCCUtil.getDAOFactory().getUtisakDAO().dodaj(u);
						
							ppout=new ProtokolPoruka("response",new Object[]{u});
							break;
						case "asdfg" :
							break;
						default :
							ppout = new ProtokolPoruka("greska");
					}
			}
		}catch (EOFException e) {
			e.printStackTrace();
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
			System.out.println("Log out");
		}
	}
}
