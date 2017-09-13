package net.etfbl.hcc;

import java.io.IOException;
import java.net.*;

import net.etfbl.hcc.util.ConnectionProperty;

public class Main {

	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(ConnectionProperty.getInstance().getServerTCPPort());
			System.out.println("Running...");
			while(true){
				Socket sock = ss.accept();
				new ServerThread(sock).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(ss!=null)
					ss.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
