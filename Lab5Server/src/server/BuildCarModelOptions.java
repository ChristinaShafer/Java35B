package server;
import java.io.ObjectInputStream;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import util.*;
import adapter.*;
import exception.*;
import model.*;
import adapter.*;

public class BuildCarModelOptions implements AutoServer {
	private int request;
	private static boolean DEBUG=false;
	
	public String setRequest(int request) {
		this.request=request;
		switch (request) {
		case 1:
			return ("Please enter the filename for the automobile you would like to upload.");

		case 2:
			return("Which car would you like to configure from the following list:\n"+ listAutos());
		default:{
			this.request=-1;
			return("Not a valid Menu Choice. Press any key to continue.");
			}
		}
	}
	
	public int getRequest() {
		return this.request;
	}
	
	public void addAuto(Automobile auto) throws AutoException{
		try {
			BuildAuto b = new BuildAuto();
			b.addAuto(auto);
		}
		 catch (Exception e) {
				if (Automobile.DEBUG)
					System.out.print("Error: " + e);
				// System.exit(1);
				String msg = "Server>BuildCarModel: adding auto to LHM:" + e;
				throw new AutoException(500, msg);
		 }
	};
	public String listAutos() {
		BuildAuto b = new BuildAuto();
		return b.listAutos();
	};
	

	
	public Automobile getAuto(String fullAutoName) {
		BuildAuto b = new BuildAuto();
		return b.getAuto(fullAutoName);
	};
	
	public String processRequest(Properties fromClient) {
		//Automobile a1 = new Automobile();
		//FileIO f1= new FileIO();
		BuildAuto b = new BuildAuto();
		//a1 = b.readPropData(fromClient);
		try {
		b.buildAuto(fromClient);	
		}
		 catch (Exception e) {
				if (Automobile.DEBUG)
					System.out.print("Error: " + e);
				return "There was an error uploading your automobile. Please try again. Press any key to continue.";
		 }
		String response = fromClient.getProperty("Year") + " " + fromClient.getProperty("Make") + " " + fromClient.getProperty("Model");
		response += " has been added to available automobiles. Press Enter to continue.\n";
		return response;
	}
	
	public Automobile processRequest(String fullAutoName) {
		Automobile a = null;
		try {
			a = getAuto(fullAutoName);
			if (DEBUG)
				System.out.println("Found " + a.getFullName());
		}
		catch (Exception e) {
			if (DEBUG)
				System.out.println("NOT Found: " + fullAutoName);
			
		}

		return a;
		
	}
	
	
	
	
	
	
}
