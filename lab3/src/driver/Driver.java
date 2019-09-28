package driver;

import java.util.Scanner;
import adapter.*;
import exception.*;
import model.*;

public class Driver {

	public static void main(String[] args) throws AutoException {
		// attempt to print Auto before it is built
		/*System.out.print("-------------------------------------------------\n");
		System.out.print("Attempt to print Ford Focus Wagon ZTW before it is built.\n");
		System.out.print("--------------------------------------------------\n");
		BuildAuto myCar=new BuildAuto();
		myCar.printAuto("Ford", "Focus Wagon ZTW");
		System.out.println();
		System.out.println();
		*/
		// build (and print) an Automotive object with missing model name
		
		System.out.print("-------------------------------------------------\n");
		System.out.print("Ford Focus Wagon ZTW object\n");
		System.out.print("--------------------------------------------------\n");
		BuildAuto myCar=new BuildAuto();
		myCar.buildAuto("C:\\Users\\cljsh\\OneDrive\\Desktop\\Java35B\\Lab3\\src\\2018FordFocusWagonZTW.txt");
		myCar.printAuto(2018,"Ford", "Focus Wagon ZTW");
		System.out.println();
		
		myCar.chooseOptions();
		myCar.printAuto(2018, "Ford", "Focus Wagon ZTW");
	}

}
