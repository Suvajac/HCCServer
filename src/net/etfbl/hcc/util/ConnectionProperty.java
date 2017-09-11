package net.etfbl.hcc.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionProperty {
    private static ConnectionProperty instance;

    public static ConnectionProperty getInstance(){
        if(instance==null){
            instance = new ConnectionProperty();
        }
        return instance;
    }

    private ConnectionProperty(){
        //Constructor must be private
    }

    public String getServerIpAddress(){
        return readValue("ServerIpAdress");
    }
    public int getServerTCPPort(){
        return Integer.parseInt(readValue("ServerTCPPort"));
    }
    public String getMulticastIpAdress(){
        return readValue("MulticastIpAdress");
    }
    public int getMulticastPort(){
        return Integer.parseInt(readValue("MulticastPort"));
    }
    
    public void setServerIpAddress(String value){
    	writeValue("ServerIpAdress", value);
    }
    public void setServerTCPPort(String value){
    	writeValue("ServerTCPPort", value);
    }
    public void setMulticastIpAdress(String value){
    	writeValue("MulticastIpAdress", value);
    }
    public void setMulticastPort(String value){
    	writeValue("MulticastPort", value);
    }

    private String readValue(String key){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/net/etfbl/hcc/util/ConnectionProperties.txt"));
            String line = null;
            while((line=reader.readLine())!=null){
                if(line.startsWith(key)){
                    return line.replaceAll(key+"=","");
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    private void writeValue(String key,String value){
    	try{
            BufferedReader reader = new BufferedReader(new FileReader("src/net/etfbl/hcc/util/ConnectionProperties.txt"));
            String l = null;
            List<String> lines = new ArrayList<>();
            while((l=reader.readLine())!=null){
                lines.add(l);
            }
            reader.close();
            
            for(int i=0;i<lines.size();i++){
            	String line = lines.get(i);
            	if(line.startsWith(key)){
            		lines.set(i, new String(key+"="+value));
            	}
            }
            
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/etfbl/mdp/res/ConnectionProperties.txt")));
            for(String line : lines){
            	writer.println(line);
            }
            writer.flush();
            writer.close();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
}

