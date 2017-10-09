package demo;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class Invoices 
{
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

				Date sd=Invoices.parseDate("2017-08-01");
				Date ed=Invoices.parseDate("2017-08-10");
				Vector params = new Vector();
				params.add(11);
				/*params.add(sd);
				params.add(ed);*/

				server.setConfig(conf);

				Object[] token = (Object[]) server.execute("unify.getInvoices", params);

				System.out.println(token);
				for (Object ob : token) {
					HashMap<String, Object> hs = (HashMap<String, Object>) ob;
					System.out.println(hs.get("invoiceno"));
					System.out.println("start date"+hs.get("startdt"));
					System.out.println(hs.get("enddt"));
					System.out.println(hs.get("openingBalance"));
					System.out.println(hs.get("amount"));
					System.out.println("due date"+hs.get("duedt"));
					System.out.println("invoice date"+hs.get("invoicedt"));
					System.out.println(hs.get("pendingamount"));
					System.out.println(hs.get("content"));
					
					/*
					Iterator it = hs.entrySet().iterator();

					System.out.println("================ Invoices ============================");
					while (it.hasNext()) {
						Map.Entry pair = (Map.Entry) it.next();
						if(pair.getKey().equals("Content"))
							continue;
						System.out.println(pair.getKey() + " = " + pair.getValue());
						it.remove(); // avoids a ConcurrentModificationException
					}

					*/
					System.out.println("==========================================================");
				}

			} catch (XmlRpcException x) {
				System.err.println("JavaClient: " + x.toString());
			}

			catch (Exception exception) {
				System.err.println("JavaClient: " + exception.toString());
			}
		}
	
		public static Date parseDate(String date) {
		     try {
		         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		     } catch (Exception e) {
		         return null;
		     }
		  }
}
