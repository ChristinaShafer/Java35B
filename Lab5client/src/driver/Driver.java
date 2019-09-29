package driver;
import client.*;

import exception.*;


public class Driver {

	public static void main(String[] args) throws AutoException {
		DefaultSocketClient dsc1 = new DefaultSocketClient("192.168.1.90" ,4445);
		//DefaultSocketClient dsc1 = new DefaultSocketClient("10.41.29.96" ,4445);
		dsc1.start();
		
	}

}
