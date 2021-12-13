package org.example.ocsf.server.src.main.java.il.cshaifasweng.OCSFMediatorExample.server;

import org.example.ocsf.entities.src.main.java.il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import org.example.ocsf.server.src.main.java.il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import org.example.ocsf.server.src.main.java.il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;


public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);
		
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
