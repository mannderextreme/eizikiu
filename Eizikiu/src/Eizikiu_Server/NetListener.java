package Eizikiu_Server;

import java.net.*;
import java.util.*;
import Eizikiu_Tools.*;

public class NetListener implements Runnable {
	
	LinkedList<ConnectionToClient> connectionList;
	LinkedList<User> userList;
	
	EZKlogger output;

	NetListener(LinkedList<ConnectionToClient> connectionList, LinkedList<User> userList){
		this.connectionList = connectionList;
		this.userList = userList;
		
		output = new EZKlogger();
		// Logging in Datei anschalten
		output.setFileOutput(true);
	}
	
	@Override
	public void run() {
		
		output.log("NetListener.run() --> ...succesfully!");

		while(true) {
			
			try {
				ServerSocket listener = new ServerSocket(1234);
				output.info("NetListener.run() --> ServerSocket aufgebaut\n");

				Socket socket = listener.accept(); // wait for connection
				
				Thread x = new Thread(new ConnectionToClient(socket, connectionList, userList));
				x.start();
				
				listener.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				}
		}
	}

}
