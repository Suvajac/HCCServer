package net.etfbl.hcc.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

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

    private String readValue(String key){
    	ResourceBundle bundle = PropertyResourceBundle
                .getBundle("net.etfbl.hcc.util.ConnectionProperties");
    	return bundle.getString(key);
    }

}

