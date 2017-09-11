package net.etfbl.hcc;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import net.etfbl.hcc.model.*;
import net.etfbl.hcc.util.HCCUtil;
import net.etfbl.hcc.util.ProtokolPoruka;

public class ServerThread extends Thread{
	private Socket sock;
	private PrintWriter out;
	private BufferedReader in;

	public ServerThread(Socket sock){
		try{
			this.sock = sock;
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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

					JSONObject json = new JSONObject(in.readLine());
					ArrayList<Object> rez=new ArrayList<Object>();
					String tip = (String) json.getString("tip");
					switch(tip){
						case "Korisnik.getKorisnik" :
							System.out.println("Korisnik.getKorisnik");
							String username = (String) ((JSONObject)json.getJSONArray("listaObjekata").get(0)).get("username");
							Korisnik k=null;
							k=HCCUtil.getDAOFactory().getGostDAO().getKorisnik(username);
							if(k==null)
								k=HCCUtil.getDAOFactory().getRecepcionarDAO().getKorisnik(username);
							rez.clear();
							rez.add(k);
							ppout=new ProtokolPoruka("response",rez);
							break;
						case "Utisak.dodaj" :
							System.out.println("Utisak.dodaj");
							Utisak u=(Utisak)ppin.getListaObjekata().get(0);
							HCCUtil.getDAOFactory().getUtisakDAO().dodaj(u);
							rez.clear();
							ppout=new ProtokolPoruka("response",rez);
							break;
						case "asdfg" :
							break;
						default :
							ppout = new ProtokolPoruka("greska");
					}
					JSONObject jsonOut = new JSONObject(ppout);
					out.println(jsonOut.toString());
			}
		}catch (EOFException e) {
			e.printStackTrace();
		}
		catch (IOException  | JSONException e) {
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
