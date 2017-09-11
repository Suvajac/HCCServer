package net.etfbl.hcc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
		while(ppin==null || !ppin.getTip().equals("quit")){
			try {
				ppin = (ProtokolPoruka) in.readObject();
				switch(ppin.getTip()){
					case "radili" : 
//						HCCUtil.getDAOFactory().getRacunDAO().getRacun(ppin.getListaObjekata().get(0));
						break;
					case "asdfg" :
						break;
					default :
						ppout = new ProtokolPoruka("greska");
				}
				out.writeObject(ppout);
			} 
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}		
		}

		try {
			in.close();
			out.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
