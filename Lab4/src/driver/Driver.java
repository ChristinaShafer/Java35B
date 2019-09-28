package driver;

import java.util.Scanner;

import adapter.*;
import exception.*;
import model.*;
import scale.*;

public class Driver {

	public static void main(String[] args) throws AutoException {

		
		System.out.print("-------------------------------------------------\n");
		System.out.print("2018 Ford Focus Wagon ZTW object\n");
		System.out.print("--------------------------------------------------\n");
		BuildAuto myCar=new BuildAuto();
		myCar.buildAuto("C:\\Users\\cljsh\\OneDrive\\Desktop\\Java35B\\Lab4\\src\\2018FordFocusWagonZTW.txt");
		myCar.printAuto(2018,"Ford", "Focus Wagon ZTW");
		System.out.println();
		
		
        // Create two EditOption Threads to update the color "Infra-Red Clearcoat" to different names
        EditOptions eo1 = new EditOptions(1,2018,"Ford", "Focus Wagon ZTW", "Color", "Infra-Red Clearcoat", "Red");   
        EditOptions eo2 = new EditOptions(2,2018,"Ford", "Focus Wagon ZTW", "Color", "Infra-Red Clearcoat", "Cherry Apple");
  
		
        
            System.out.println("Update Color option: Infra-Red Clearcoat  to Red");
            eo1.start();
            System.out.println("Update Color option: Infra-Red Clearcoat  to Cherry Apple");
            eo2.start();     
            

		System.out.println();
		
		
		
	}

}
