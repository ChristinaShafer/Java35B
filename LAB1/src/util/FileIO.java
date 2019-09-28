package util;

import model.*;
import java.io.*;
import java.util.*;

public class FileIO {

	public FileIO() {
		super();
		// TODO Auto-generated constructor stub
	}

	// readData() method which reads in a file and returns an Automotive object
	// called by buildAutoObject
	private Automotive readData(String fname) {
		Automotive a1 = null;
		int counter = 0;
		// try block to catch exceptions
		try {

			FileReader file = new FileReader(fname);

			// opens a buffer and puts file contents in buffer
			BufferedReader buff = new BufferedReader(file);

			// bool variable to keep track of whether we have reached the end of the file
			// model, base and numOptions variables to store values to instantiate
			// Automotive object
			// nameLine, optionStartLine, numOptions used to test where option values should
			// be read in
			// optionName, opSetIndex used to populate used to populate OptionSet data
			boolean eof = false;
			String model = "";
			float base = 0;
			int numOptions = 0;
			int nameLine = 9;
			int optionStartLine = 11;
			String optionName = "";
			int opSetIndex = -1;

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
					if (Automotive.DEBUG)
						System.out.println(counter + "Reading " + line + "\n");
					// for each line after the header line
					if (counter == 4)
						model = line;
					if (counter == 6)
						base = Integer.parseInt(line);
					if (counter == 8) {
						numOptions = Integer.parseInt(line);
						// build a Automotive object with modelname, base price and options
						a1 = new Automotive(model, base, numOptions);
					}
					if (counter > 8) {
						if (counter == nameLine) {
							optionName = line;
							optionStartLine = counter + 1;
							opSetIndex++;
							if (Automotive.DEBUG)
								System.out.println(counter + "OptionName " + line + "\n");
						}
						if (counter == (nameLine + 1)) {
							numOptions = Integer.parseInt(line);
							nameLine = counter + numOptions + 1; // set line of next option name
							if (Automotive.DEBUG) {
								System.out.println(counter + "NumOptions " + numOptions + "\n");
								System.out.println("Next nameLine: " + nameLine + "\n");
							}
							// build the optionSet to be populated
							buildOpSet(a1, opSetIndex, optionName, numOptions);

						}
						// populate the optionSet
						if (counter > (optionStartLine)) {
							// tokenize the line to get option name value and option price value
							StringTokenizer st = new StringTokenizer(line);
							// set the option values
							String optionNameValue = st.nextToken(",");
							float price = Float.parseFloat(st.nextToken());
							// setOptValue
							int optionIndex = counter - (optionStartLine + 1);// gives the index of the optionSet that
																				// we are filling
							// System.out.println(currentOpSet+ "\t " + optionIndex + "\t " +
							// optionNameValue+ "\t " + price);
							a1.setOptionValue(opSetIndex, optionIndex, optionNameValue, price);
						}
					}

				}
			}
			// close the buffer
			buff.close();
		}
		// if something goes wrong, prints out the exception value
		catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}
		return a1;
	}

	private void buildOpSet(Automotive a, int opSetIndex, String name, int opSize) {
		a.setOpsetValue(opSetIndex, new OptionSet(name, opSize));
	}

	public Automotive buildAutoObject(String filename) {
		Automotive a1 = readData(filename);
		return a1;
	}

	public void serializeAuto(Automotive a1) {
		try {
			String filename = a1.getModelName() + ".ser";
			FileOutputStream fo1 = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fo1);
			out.writeObject(a1);
			out.close();
		} catch (Exception e) {
			System.out.print("Error: " + e);
			System.exit(1);
		}
	}

	public Automotive deSerializeAuto(String modelname) {
		Automotive a2 = null;
		try {
			// deserialize Automotive object
			String fileToBeRead = "C:\\Users\\cljsh\\OneDrive\\Desktop\\Java35B\\LAB1\\";
			fileToBeRead = fileToBeRead + modelname + ".ser";

			FileInputStream f2 = new FileInputStream(fileToBeRead);
			ObjectInputStream in = new ObjectInputStream(f2);

			a2 = (Automotive) in.readObject();
			f2.close();
		} catch (Exception e) {
			System.out.print("Error: " + e);
			System.exit(1);
		}
		return a2;
	}

}
