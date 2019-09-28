package model;

import java.io.*;
import java.util.ArrayList;

public class Automobile implements Serializable {
	public static final boolean DEBUG = false;
	private String make;
	private String model;
	private float basePrice;
	private int year;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> choice;

	// constructors
	public Automobile() {
		super();
		this.make = "";
		this.model = "";
		this.basePrice = 0;
		this.year=1900;
		this.opset = new ArrayList<OptionSet>();
		this.choice = new ArrayList<Option>();
	}

	/*
	 * public Automobile(String make, String Model, float basePrice, int
	 * totalOptions) { super(); this.make = make; this.model = Model; this.basePrice
	 * = basePrice; this.opset = new ArrayList<OptionSet>(); }
	 */

	public Automobile(String make, String Model, float basePrice) {
		super();
		this.make = make;
		this.model = Model;
		this.basePrice = basePrice;
		this.year=1900;
		this.opset = new ArrayList<OptionSet>();
		this.choice = new ArrayList<Option>();
	}
	// getters and setters

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// get all OptionSets
	public ArrayList<OptionSet> getOpset() {
		return opset;
	}

	public ArrayList<Option> getChoice() {
		return choice;
	}

	public void setChoice(ArrayList<Option> choice) {
		this.choice = choice;
	}

	// get OptionSet by index
	public OptionSet getOpset(int i) {
		return opset.get(i);
	}

	// get OptionSet name by index
	public String getOptionSetName(int index) {
		return opset.get(index).getName();
	}

	// get Options in option set by index
	public ArrayList<Option> getOptions(int index) {
		return opset.get(index).getOpt();
	}

	// get OptionSet by name
	public OptionSet getOpset(String s) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(s))
				return opset.get(i);
		}
		return null;
	}

	// get OptionSet index by name
	public int getOpsetIndex(String s) {
		for (int i = 0; i < opset.size(); i++) {
			//System.out.print("Autoline96:opset.get(i).getName(): "+ opset.get(i).getName()) ;
			if (opset.get(i).getName().equals(s))
				return i;
		}
		return -1;
	}

	// get option name value
	public String getOptionNameValue(int i, int j) {
		return this.opset.get(i).getOptNameValue(j);
	}

	// get option price value
	public float getOptionPriceValue(int i, int j) {
		return this.opset.get(i).getOptPriceValue(j);
	}

	public void setOpset(ArrayList<OptionSet> opset) {
		this.opset = opset;
	}

	public void setOpsetValue(int i, OptionSet os) {
		this.opset.set(i, os);
	}

	// set optionSet name value
	public void setOptionSetName(int i, String optionSetName) {
		this.opset.get(i).setName(optionSetName);

	}

	public void setOptionValue(int i, int j, String name, float price) {
		this.opset.get(i).setOptValue(j, name, price);
	}

	public void setOptionPrice(int i, String optionName, float newPrice) {
		int j = this.opset.get(i).getOptIndex(optionName);
		this.opset.get(i).setOptPrice(j, newPrice);

	}

	// add Option to OptionSet
	public void addOptionValue(int i, String name, float price) {
		this.opset.get(i).addOption(name, price);
	}

	// delete OptionSet by name
	public void deleteOptionSet(String opname) {
		int index = getOpsetIndex(opname);
		if (index > -1)
			deleteOptionSet(index);
	}

	// delete OptionSet by index
	public void deleteOptionSet(int index) {
		if ((index > -1) && (index < this.opset.size()))
			this.opset.remove(index);
	}

	// get the name of the Option chosen by user for a given OptionSet name
	public String getOptionChoice(String setName) {
		int opSetIndex = getOpsetIndex(setName);
		return opset.get(opSetIndex).getOptionChoice().getName();
	}
	public String getOptionChoice(int opSetIndex) {
		return opset.get(opSetIndex).getOptionChoice().getName();
	}
	
	
	// get the name of the Option chosen by user for a given OptionSet name
	public float getOptionChoicePrice(String setName) {
		int opSetIndex = getOpsetIndex(setName);
		return opset.get(opSetIndex).getOptionChoice().getPrice();
	}

	
	
	
	// set the Option chosen by user for a given Option Set
	public void setOptionChoice(String setName, String optionName) {
		int opSetIndex = getOpsetIndex(setName);
		Option chosen = opset.get(opSetIndex).getOption(optionName);
		opset.get(opSetIndex).setChoice(chosen);
		choice.add(chosen);
	}
	public void setOptionChoice(String setName, int optionIndex) {
		int opSetIndex = getOpsetIndex(setName);
		Option chosen = opset.get(opSetIndex).getOption(optionIndex);
		opset.get(opSetIndex).setChoice(chosen);
		choice.add(chosen);
	}
	public void setOptionChoice(int opSetIndex, int optionIndex) {
		Option chosen = opset.get(opSetIndex).getOption(optionIndex);
		opset.get(opSetIndex).setChoice(chosen);
		choice.add(chosen);
	}
	//clear out choice
	public void clearChoice() {
		this.choice.clear();
	}
	
	
	// get total including all option values
	public float getTotalPrice() {
		float total = this.getBasePrice();
		for (int i = 0; i < choice.size(); i++) {
			total += choice.get(i).getPrice();
		}
		return total;
	}

	public void print() {
		if (choice.size()==0) {
			System.out.println("Year: " + year);
			System.out.println("Make: " + make);
			System.out.println("Model: " + model);
			System.out.printf("Base Price: $%.2f\n",basePrice);
			
			System.out.println("Number of options: " + opset.size());
			System.out.print("\n");
			for (int i = 0; i < opset.size(); i++) {
				opset.get(i).print();
			}
		}
		else
			printTotal();
	}

	public void printTotal() {
		System.out.println("Year: " + year);
		System.out.println("Make: " + make);
		System.out.println("Model: " + model);
		System.out.printf("Base Price: $%.2f\n", basePrice);
		for (int i = 0; i < choice.size(); i++) {
			System.out.print(getOptionSetName(i) + ": ");
			System.out.printf(choice.get(i).getName() + ": $%.2f\n", choice.get(i).getPrice());
		}
		System.out.printf("Total with options selected: $%.2f\n", getTotalPrice());
	}

}
