package demo;

import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class testPackegeID {
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
			params.add("100Mbps10MBPP");

			server.setConfig(conf);

			HashMap<String, Object> token = (HashMap<String, Object>) server.execute("unify.getFUPForRatePlan", params);
			System.out.println(token.get("mbytesout").toString());

		} catch (XmlRpcException x) {
			System.err.println("JavaClient: " + x.toString());
		}

		catch (Exception exception) {
			System.err.println("JavaClient: " + exception.toString());
		}
	}
}
