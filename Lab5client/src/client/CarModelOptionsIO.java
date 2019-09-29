package client;


import java.io.*;
import java.util.*;
import adapter.*;
import model.*;

public class CarModelOptionsIO {

	////////// PROPERTIES //////////


	////////// CONSTRUCTORS //////////

	public CarModelOptionsIO() {

	}

	////////// INSTANCE METHODS //////////

	public Object loadPropsFile(String fname) {
		Properties props = new Properties();
		int checkpath = fname.indexOf("\\"); 
		if (checkpath == -1) 
			fname="C:\\Users\\cljsh\\OneDrive\\Desktop\\Java35B\\Lab5Client\\src\\" + fname;
		try {
			props.load(new FileInputStream(fname));
		}
		catch (FileNotFoundException e) {
			System.err.println(fname + " not found. ");
			//System.exit(1);
			props=null;
		}
		catch (IOException e) {
			System.err.println("Error reading file from directory ... ");
			System.exit(1);
		}

		return props;
	}

	

}
