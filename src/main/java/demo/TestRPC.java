package demo;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class TestRPC {

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
                    params.add("NED_Ops");

                    server.setConfig(conf);

                    HashMap<String, Object> token = (HashMap) server.execute("unify.getAccountDetails", params);
                    System.out.println(token.keySet());

                    Iterator it = token.entrySet().iterator();
                    while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            System.out.println(pair.getKey() + " = " + pair.getValue());
                            it.remove(); // avoids a ConcurrentModificationException
                    }

            } catch (Exception exception) {
                    System.err.println("JavaClient: " + exception.toString());
            }
	}
}
