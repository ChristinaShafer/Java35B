package util;

import model.*;

import java.io.*;
import java.util.*;

import exception.*;

public class FileIO {

	public FileIO() {
		super();
		// TODO Auto-generated constructor stub
	}

	// readData() method which reads in a file and returns an Automotive object
	// called by buildAutoObject
	public Automobile readData(String fname) throws AutoException {
		Automobile a1 = new Automobile();

		// try block to catch exceptions
		try {
			//opens a file reader connection 
			FileReader file = new FileReader(fname);
			// opens a buffer and puts file contents in buffer
			BufferedReader buff = new BufferedReader(file);
			
			boolean eof = false;     // bool variable to keep track of whether we have reached the end of the file
			int nameLine = 11;        // nameLine used to test where option values should be read in
			int optionStartLine = 13;   // optionStartLine used to test where option values should be read in
			int opSetIndex = -1;       //used to hold current optionSet index
			int counter = 0;	//counts which line of the file we are on
			int numOptions = 0;  //updated to reflect how many optionSets there are and then how many options there are
								//used to reset nameLine and optionStartLine
			
			// do the following while you haven't reached the end of the file
			while (!eof) {
				String line = buff.readLine();
				// if we have reached the end of the file, set the eof bool to true
				if (line == null)
					eof = true;
				else {
					// increment line counter
					counter++;
					// printing out what line we just read in
					if (Automobile.DEBUG)
						System.out.println(counter + "Reading '" + line + "'\n");
					// for each line after the header line
					if (counter == 2) {
						String make = line;
						if (Automobile.DEBUG)
							System.out.print("length of make: " + line.length() + "\n");
						try {
							if (make.length() == 0) {
								if (Automobile.DEBUG)
									System.out.print("make: " + make + "\n");
								throw new AutoException(106, "FileIO>ReadData(): text file missing Make name.");
							}
						}

						catch (AutoException e1) {
							make = e1.fix(106);
							if (Automobile.DEBUG)
								System.out.print("make: " + make + "\n");
						}
						a1.setMake(make);
					}
					
					if (counter == 4) {
						String model = line;
						if (Automobile.DEBUG)
							System.out.print("length of model: " + line.length() + "\n");
						try {
							if (model.length() == 0) {
								if (Automobile.DEBUG)
									System.out.print("model: " + model + "\n");
								throw new AutoException(105, "FileIO>ReadData(): text file missing Model name.");
							}
						}

						catch (AutoException e1) {
							model = e1.fix(105);
							if (Automobile.DEBUG)
								System.out.print("model: " + model + "\n");
						}
						a1.setModel(model);
					}
					if (counter == 6) {
						String testLength = line;
						int base=0;
						try {
							if (testLength.length() == 0)
								throw new AutoException(115, "FileIO>ReadData(): text file missing Base price.");
							else
								 base = Integer.parseInt(testLength);
						} catch (AutoException e2) {
							 base = Integer.parseInt(e2.fix(115));
							if (Automobile.DEBUG)
								System.out.print("New base price: " + base + "\n");
						}
						a1.setBasePrice(base);

					}
					if (counter == 8) {
						String testLength = line;
						int year=0;
						try {
							if (testLength.length() == 0)
								throw new AutoException(155, "FileIO>ReadData(): text file missing year.");
							else
								 year = Integer.parseInt(testLength);
						} catch (AutoException e2) {
							 year=2018;
							if (Automobile.DEBUG)
								System.out.print("Year: " + year + "\n");
						}
						a1.setYear(year);

					}
					if (counter == 10) {
						String testLength = line;
						try {
							if (testLength.length() == 0)
								throw new AutoException(125, "FileIO>ReadData(): text file missing OptionSet size.");
							else
								numOptions = Integer.parseInt(testLength);
						} catch (AutoException e3) {
							numOptions = Integer.parseInt(e3.fix(125));
							if (Automobile.DEBUG)
								System.out.print("New optionSetSize: " + numOptions + "\n");
						}
						// build a Automotive object with modelname, base price and options
						if (Automobile.DEBUG) {
							System.out.print("make, model, base, numOptions: ");
							System.out.print(a1.getMake()+ a1.getModel() + "," + a1.getBasePrice() + "," + numOptions + "\n");
						}
						
					}
					if (counter > 10) {
						//if it's a line containing the name of the optionSet, create a new optionSet
						if (counter == nameLine) {
							String optionName = line;
							try {
								if (optionName.length() == 0)
									throw new AutoException(135, "FileIO>ReadData(): text file missing OptionSet name",opSetIndex + 1);
							} catch (AutoException e4) {
								optionName = e4.fix(135);
							}
							//add a new optionSet with the name read in
							a1.getOpset().add(new OptionSet(optionName));
							optionStartLine = counter + 1;
							opSetIndex++;
							if (Automobile.DEBUG)
								System.out.println(counter + "OptionName " + line + "\n");
						}
						//if it's the line after the name of the OptionSet which contains the number of options
						if (counter == (nameLine + 1)) {
							String testLength = line;
							try {
								if (testLength.length() == 0)
									throw new AutoException(145, "FileIO>ReadData(): text file missing Option Array size.",opSetIndex);
								else
									numOptions = Integer.parseInt(testLength);
							} catch (AutoException e5) {
								numOptions = Integer.parseInt(e5.fix(145));
								if (Automobile.DEBUG)
									System.out.print("New option Array : " + numOptions + "\n");
							}

							nameLine = counter + numOptions + 1; // set line of next option name
							if (Automobile.DEBUG) {
								System.out.println(counter + "NumOptions " + numOptions + "\n");
								System.out.println("Next nameLine: " + nameLine + "\n");
							}
							// build the optionSet to be populated
							//buildOpSet(a1, opSetIndex, optionName, numOptions);

						}
						// populate the optionSet
						if (counter > (optionStartLine)) {
							// tokenize the line to get option name value and option price value
							StringTokenizer st = new StringTokenizer(line);
							// set the option values
							String optionNameValue = st.nextToken(",");
							float price = Float.parseFloat(st.nextToken());
							// add a new option value to the optionSet
							a1.addOptionValue(opSetIndex,optionNameValue,price);
						}
					}

				}
			}
			// close the buffer
			buff.close();
		}

		// if something goes wrong, prints out the exception value
		catch (IOException e) {
			// System.out.println("Error -- " + e.toString());
			AutoException a = new AutoException(404, "FileIO>ReadData(): File Not Found: " + fname);
		} 
		return a1;
	}

	public Automobile buildAutoObject(String filename) throws AutoException {
		Automobile a1 = readData(filename);
		if (Automobile.DEBUG) {
			System.out.print("Make: " + a1.getMake());
			System.out.print("Model: " + a1.getModel());
			System.out.print("Base Price: " + a1.getBasePrice());
			System.out.println("Automobile object sucessfully built. \n");
		}
		serializeAuto(a1);
		return a1;
	}

	public void serializeAuto(Automobile a1) throws AutoException {
		String filename = a1.getYear() + a1.getMake() + a1.getModel() + ".ser";
		if (Automobile.DEBUG)
			System.out.println(filename);
		try {
			FileOutputStream fo1 = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fo1);
			out.writeObject(a1);
			out.close();

		} catch (Exception e) {
			if (Automobile.DEBUG)
				System.out.print("Error: " + e);
			// System.exit(1);
			String msg = "SerializeAuto:" + e;
			throw new AutoException(205, msg);
		}
	}

	public Automobile deSerializeAuto(int year, String make, String model) throws AutoException {
		Automobile a2 = null;
		try {
			String yearStr = String.valueOf(year);
			// deserialize Automotive object
			String fileToBeRead = "C:\\Users\\cljsh\\OneDrive\\Desktop\\Java35B\\LAB3\\";
			fileToBeRead = fileToBeRead + yearStr + make + model + ".ser";
			// System.out.print("fileToBeRead: "+fileToBeRead);

			FileInputStream f2 = new FileInputStream(fileToBeRead);
			ObjectInputStream in = new ObjectInputStream(f2);

			a2 = (Automobile) in.readObject();
			f2.close();
		} catch (Exception e) {
			if (Automobile.DEBUG)
				System.out.print("Error: " + e);
			String msg = "deSerializeAuto:" + e;
			throw new AutoException(215, msg);
			// System.exit(1);
		}
		return a2;
	}

}
