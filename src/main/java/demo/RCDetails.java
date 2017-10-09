/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author rajanikant
 */
public class RCDetails {

    // The location of our server.
    private final static String server_url = "http://52.172.205.76/unifyv3/xmlRPC.do";

    public static void main(String[] args) {
        try {

            URL serverUrl = new URL(server_url);
            // Create an object to represent our server.
            XmlRpcClient server = new XmlRpcClient();
            XmlRpcClientConfigImpl conf = new XmlRpcClientConfigImpl();
            conf.setBasicUserName("oneeight");
            conf.setBasicPassword("!oneight@#");
            conf.setServerURL(serverUrl);

            Vector<Object> params = new Vector<Object>();
            params.add("OE-000003");
            params.add(false);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
            Date date = formatter.parse("2017-06-06");
			params.add(date);
			params.add(new Date());
              server.setConfig(conf);

            Object[] token = (Object[]) server.execute("unify.getRCsForAccount", params);
            for (Object ob : token) {
				HashMap<String, Object> hs = (HashMap<String, Object>) ob;

				Iterator it = hs.entrySet().iterator();

				System.out.println("==========================================================");
				System.out.println("================ RC ============================");
				System.out.println("==========================================================");

				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					System.out.println(pair.getKey() + " = " + pair.getValue());
					it.remove(); // avoids a ConcurrentModificationException
				}

				System.out.println("==========================================================");
			}


        } catch (MalformedURLException exception) {
            System.err.println("JavaClient: " + exception.toString());
        } catch (ParseException exception) {
            System.err.println("JavaClient: " + exception.toString());
        } catch (XmlRpcException exception) {
            System.err.println("JavaClient: " + exception.toString());
        }
    }

}
