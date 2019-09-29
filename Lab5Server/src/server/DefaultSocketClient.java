

package server;

import java.net.*;
import java.io.*;
import java.util.Properties;
import adapter.*;
import model.*;

public class DefaultSocketClient extends Thread  {

	////////// PROPERTIES //////////


	private static final boolean DEBUG = false;
	private static final int WAITING = 0;
    private static final int SENTFIRSTMENU = 1;
	private static final int REQUESTSET = 2;

    private int state = WAITING;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket clientSocket;
	private BuildCarModelOptions protocol;

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	////////// INSTANCE METHODS //////////

	public void handleConnection(Socket sock) {
		if (DEBUG)
			System.out.println("Creating server object streams ... ");
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		}
		catch (IOException e) {
			System.err.println("Error creating server object streams ... ");
			System.exit(1);
		}


		protocol = new BuildCarModelOptions();
		String menu = "\nEnter 1 to upload a new Automobile\n"
				+ "Enter 2 to configure an Automobile\n"
				+ "Enter 0 to terminate connection\n";

		try {
			do {
				
				if (state == WAITING) {
					if (DEBUG)
						System.out.println("Sending client interaction choices ... ");
					state = SENTFIRSTMENU;
					sendOutput(menu);
					
				}

				if (state == SENTFIRSTMENU) {
					int request;
					if (DEBUG)
						System.out.println("Reading client request ... ");
					try {
					request = Integer.parseInt(in.readObject().toString());
					}
					catch (Exception e){
						request = -1;
						state=WAITING;
						String response="Invalid menu choice. Press any key to continue.";		
						sendOutput(response);
					}
					if (DEBUG)
						System.out.println(request);
					if (request == 0)
						break;
					//tests for invalid menu choice
					if (request <0 || request > 2){
						state=WAITING;
						String response="Invalid menu choice. Press any key to continue";		
						sendOutput(response);
					}
					else {
						if (DEBUG) {
							System.out.println("Sending client request follow-up ... ");
							System.out.println(protocol.setRequest(request));
							}
						state=REQUESTSET;
						sendOutput(protocol.setRequest(request));
					}
				}
				if (state == REQUESTSET) {
					if (DEBUG)
						System.out.println("Sent request");
					int request = protocol.getRequest();
					if (request >= 1 && request <= 2)
						handleInput(request);
					else
						state=WAITING;
				}
				
				
			} while (in.readObject() != null);

			if (DEBUG)
				System.out.println("Closing server input stream for client " + sock.getInetAddress() + " ... ");
			in.close();
		}
		catch (IOException e) {
			System.err.println("Error handling client connection ... " + e.getMessage());
			System.exit(1);
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, object may be corrupted ... ");
			System.exit(1);
		}
	}

	public void sendOutput(Object obj) {
		try {
			out.flush();
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.err.println("Error returning output to client ...  "+ e.getMessage());
			System.exit(1);
		}
	}

	public void handleInput(int request) {
		//Object fromClient = null;
		Object toClient = null;

		try {
			switch (request) {
				case 1: //Client request to build Automobile
					if (DEBUG)
						System.out.println("Waiting for client to upload file ... ");
					try {
						Properties fromClient = (Properties)in.readObject();
						toClient = (String)protocol.processRequest(fromClient);
						if (DEBUG) {
							System.out.println("Adding new Automobile to database ... ");
						}
					}
					catch (Exception e) {
							toClient ="There was a problem with your upload. Please try again.  Press any key to continue.";
			
						}
					finally {
						state=WAITING;
						sendOutput(toClient);
					}
					break;

				case 2: //Client request to configure Automobile
					if (DEBUG)
						System.out.println("Waiting for client to select Automobile ... ");
					String fromClientInt = in.readObject().toString();
					if (DEBUG)
						System.out.println("Sending Automobile object to client ... ");
					toClient = (Automobile)protocol.processRequest(fromClientInt);
					state=WAITING;
					if (toClient ==null) {
						toClient="Not able to find your vehicle. Please press any key to continue.";
					}
					sendOutput(toClient);
					break;

				default: //Invalid requests
					state=WAITING;
					toClient="Invalid . Please press any key to continue.";
					sendOutput(toClient);
					break;
					
			}
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, file may be corrupted ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error in retrieving object from client ... ");
			System.exit(1);
		}
	}

	@Override
	public void run() {
		handleConnection(clientSocket);

		//Close client output stream
		if (DEBUG)
			System.out.println("Closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		try {
			out.close();
		}
		catch (IOException e) {
			System.err.println("Error closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		}

		//Close client socket
		if (DEBUG)
			System.out.println("Closing client socket " + clientSocket.getInetAddress() + " ... ");
		try {
			clientSocket.close();
		}
		catch (IOException e) {
			System.err.println("Error closing client socket " + clientSocket.getInetAddress() + " ... ");
		}
	}

}
