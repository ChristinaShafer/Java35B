package adapter;

import model.*;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.io.IOException;

import exception.*;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> automobiles = new LinkedHashMap<>();
	private  Automobile a1;

	// constructor
	public ProxyAutomobile() {
		a1 = new Automobile();
	}

	public void updateOptionSetName(int year,String make, String model, String optionSetName, String newName)
			throws AutoException {
		FileIO f1 = new FileIO();
		try {
			a1 = f1.deSerializeAuto(year,make, model);
		} catch (Exception e) {
			AutoException a = new AutoException(215,
					"ProxyAutomobile>updateOptionSetName(): DeSerialize problem: " + make + " " + model);
		}
		int opSetIndex = a1.getOpsetIndex(optionSetName);
		try {
			a1.setOptionSetName(opSetIndex, newName);
		} catch (Exception e) {
			AutoException a1 = new AutoException(225, "ProxyAutomobile>updateOptionSetName()>setOptionName problem.",
					opSetIndex);
		}
		try {
			f1.serializeAuto(a1);
		} catch (Exception e) {
			AutoException a2 = new AutoException(205,
					"ProxyAutomobile>updateOptionSetName(): Serialize problem: " + make + " " + model);
		}
	}

	public void updateOptionPrice(int year,String make, String model, String optionSetName, String option, float newPrice)
			throws AutoException {
		FileIO f1 = new FileIO();
		try {
			a1 = f1.deSerializeAuto(year, make, model);
		} catch (Exception e) {
			AutoException a = new AutoException(215,
					"ProxyAutomobile>updateOptionPrice(): DeSerialize problem: " + make + " " + model);
		}
		int opSetIndex = a1.getOpsetIndex(optionSetName);
		try {
			a1.setOptionPrice(opSetIndex, option, newPrice);
		} catch (Exception e) {
			AutoException a2 = new AutoException(225, "ProxyAutomobile>updateOptionPrice()>setOptionPrice problem.",
					opSetIndex);
		}
		try {
			f1.serializeAuto(a1);
		} catch (Exception e) {
			AutoException a2 = new AutoException(205,
					"ProxyAutomobile>updateOptionSetName(): Serialize problem: " + make + " " + model);
		}
	}

	public void buildAuto(String fileName) throws AutoException {

		FileIO f1 = new FileIO();
		a1 = f1.buildAutoObject(fileName);
		String keyValue = String.valueOf(a1.getYear())+a1.getMake()+a1.getModel();
		automobiles.put(keyValue, a1);
	}

	public void printAuto(Automobile a) throws AutoException {
		try {
		a.print();
		}
		catch (Exception e) {
			System.out.println("Error printing " + a.getYear() + " " +a.getMake() + " " + a.getModel());
			AutoException ae = new AutoException(215,
					"ProxyAutomobile>updateOptionPrice(): LinkedHashMap lookup problem: " + a.getYear() + " " +a.getMake() + " " + a.getModel());
		}
	}
	
	
	public void printAuto(int year, String make, String model) throws AutoException {
		FileIO f1 = new FileIO();
		try {
			//a1 = f1.deSerializeAuto(year,make, model);
			String keyValue= String.valueOf(year)+make+model;
			a1=automobiles.get(keyValue);
			a1.print();
		} catch (Exception e) {
			System.out.println(make + " " + model + " is not available to be printed.");
			AutoException a = new AutoException(215,
					"ProxyAutomobile>updateOptionPrice(): LinkedHashMap lookup problem: " + String.valueOf(year) + make + model);
		}

	}

	public String fix(AutoException e) {
		FixHelpers fh1 = new FixHelpers();
		switch (e.getErrorno()) {
		case 105:
			return fh1.fix105();
		case 115:
			return fh1.fix115();
		case 125:
			return fh1.fix125();
		case 135:
			return fh1.fix135(e.getIndex());
		case 145:
			return fh1.fix145(e.getIndex());
		default:
			System.out.println("Your error has been logged.");
			return null;
		}
	}

	public void chooseOptions() {
		if (a1.getChoice().size() > 0) {
			a1.clearChoice();
		}
		Scanner sc1 = new Scanner(System.in);
		for (int i = 0; i < a1.getOpset().size(); i++) {
			boolean done=false;
			while (done== false) {
			String setName = a1.getOptionSetName(i);
			System.out.println("Please enter number for choice of " + setName);
			int num = 1;
			int answer;
			// iterate over options and print option name & price
			for (int j = 0; j < a1.getOptions(i).size(); j++) {
				num = j + 1;
				System.out.printf(num + ". " + a1.getOptionNameValue(i, j) + ": $%.2f\n" ,a1.getOptionPriceValue(i, j));
			}
			answer = sc1.nextInt();
			if (answer>=1 && answer<=a1.getOptions(i).size()) {
				String optionName = a1.getOptionNameValue(i, answer - 1);
				a1.setOptionChoice(i, answer - 1);
				done=true;
			}
			else {
				System.out.println("Error: Choice number not in range.\n");
			}
			}
		}
		System.out.println();
		//FileIO f1 = new FileIO();
		try {
			//f1.serializeAuto(a1);
			String keyValue= String.valueOf(a1.getYear())+a1.getMake()+a1.getModel();
			automobiles.put(keyValue, a1);
		} catch (Exception e) {
			AutoException a2 = new AutoException(205,
					"ProxyAutomobile>ChooseOptions(): LinkedHashMap: " + String.valueOf(a1.getYear()) + a1.getMake() + " " + a1.getModel());
		}
	}

}