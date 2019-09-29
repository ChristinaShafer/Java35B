package driver;

import server.DefaultServerSocket;
import exception.*;

public class Driver {

	public static void main(String[] args) throws AutoException {
		
		DefaultServerSocket dss1= new DefaultServerSocket(4445);
		dss1.start();
	}

		
		
}


