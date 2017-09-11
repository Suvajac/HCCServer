package net.etfbl.hcc;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import net.etfbl.hcc.model.Korisnik;
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
		try {
			while(ppin==null || !ppin.getTip().equals("Korisnik.logout")){

					ppin = (ProtokolPoruka) in.readObject();
					switch(ppin.getTip()){
						case "Korisnik.getKorisnik" :
							System.out.println("Korisnik.getKorisnik");
							Korisnik k=null;
							k=HCCUtil.getDAOFactory().getGostDAO().getKorisnik((String)ppin.getListaObjekata().get(0));
							if(k==null)
								k=HCCUtil.getDAOFactory().getRecepcionarDAO().getKorisnik((String)ppin.getListaObjekata().get(0));
							ArrayList<Object> rez=new ArrayList<Object>();
							rez.add(k);
							ppout=new ProtokolPoruka("response",rez);
							break;
						case "asdfg" :
							break;
						default :
							ppout = new ProtokolPoruka("greska");
					}
					out.writeObject(ppout);

			}
		}catch (EOFException e) {
			// TODO: handle exception al nikad
			System.out.println("PUKOOOO");
		}
		catch (ClassNotFoundException | IOException e) {
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
