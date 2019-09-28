package driver;

import util.FileIO;
import model.*;

public class Driver {

	public static void main(String[] args) {
		FileIO f1 = new FileIO();
		// build an Automotive object
		Automotive fordFocusWagonZTW = f1
				.buildAutoObject("C:\\Users\\cljsh\\OneDrive\\Desktop\\Java35B\\LAB1\\src\\FordFocusWagonZTW.txt");
		// print the new Automotive object
		System.out.print("Original Ford Focus Wagon ZTW object\n");
		System.out.print("------------------------------------\n");
		fordFocusWagonZTW.print();
		// serialize the Automotive object
		f1.serializeAuto(fordFocusWagonZTW);
		// create a new object by deserializing file
		Automotive newFordFocusWagonZTW = f1.deSerializeAuto(fordFocusWagonZTW.getModelName());
		// print the new Automotive object
		System.out.print("New Ford Focus Wagon ZTW object from Serialization/Deserialization\n");
		System.out.print("------------------------------------------------------------------\n");
		newFordFocusWagonZTW.print();
	}

}
