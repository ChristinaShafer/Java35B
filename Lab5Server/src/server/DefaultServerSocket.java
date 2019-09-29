//this file sets up the server, receives the port info and sets up the socket
package server;
import model.*;
import adapter.*;
import java.io.*;
import java.net.*;

public class DefaultServerSocket extends Thread  {

	////////// PROPERTIES //////////

	private int port;
	private ServerSocket server;
	private boolean DEBUG=false;

	////////// CONSTRUCTORS //////////

	public DefaultServerSocket(int port) {
		this.port = port;
		try {
			this.server = new ServerSocket(port);
		}
		catch (IOException e) {
			System.err.println("Could not listen on port " + port + " ... ");
			System.exit(1);
		}
	}

	////////// INSTANCE METHODS //////////

	@Override
	public void run() {
		
		Socket clientSocket = null;
		//System.out.print("server>dss: starting server");
		while (true) {
			//Accept client
			try {
				clientSocket = server.accept();
				System.out.print("server>dss: server is open for business");
			}
			catch (IOException e) {
				System.err.println("Error establishing client connection ... ");
				System.exit(1);
			}

			//Handle client-server interaction
			if (DEBUG)
				System.out.println(clientSocket.getLocalAddress());
			new DefaultSocketClient(clientSocket).start();

		}
	}

}
