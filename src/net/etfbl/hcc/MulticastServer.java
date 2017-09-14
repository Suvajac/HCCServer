package net.etfbl.hcc;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;

import net.etfbl.hcc.model.Obavjestenje;
import net.etfbl.hcc.util.ConnectionProperty;
import net.etfbl.hcc.util.TemporalStringConverters;

public class MulticastServer {

	public MulticastServer() {
		// TODO Auto-generated constructor stub
	}

	public static void posaljiObavjestenje(Obavjestenje obavjestenje){
		 InetAddress addr = null;
		 try (DatagramSocket serverSocket = new DatagramSocket()) {
	                addr=InetAddress.getByName(ConnectionProperty.getInstance().getMulticastIpAdress());
			 		String msg = obavjestenje.getTekst()+"#"+obavjestenje.getDatum();
	                DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),
	                        msg.getBytes().length, addr, ConnectionProperty.getInstance().getMulticastPort());
	                serverSocket.send(msgPacket);
	                System.out.println(TemporalStringConverters.toString(LocalDateTime.now())+" [Server] - "+msg);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
}
