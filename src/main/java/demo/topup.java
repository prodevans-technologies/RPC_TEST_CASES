package demo;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class topup {
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

			Vector params = new Vector();
			params.add(30);

			server.setConfig(conf);

			Object[] token = (Object[]) server.execute("unify.getTopUpsForFUP", params);

			for (Object o : token) {
				HashMap<String, Object> hs = (HashMap<String, Object>) o;
				System.out.println("----------------top up -------------------------");

				Iterator it = hs.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					System.out.println(pair.getKey() + " = " + pair.getValue());
					it.remove(); // avoids a ConcurrentModificationException
				}
			}

		} catch (XmlRpcException x) {
			System.err.println("JavaClient: " + x.toString());
		}

		catch (Exception exception) {
			System.err.println("JavaClient: " + exception.toString());
		}
	}
}
