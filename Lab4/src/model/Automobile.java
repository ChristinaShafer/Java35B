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

	public Automobile(String make, String Model, float basePrice) {
		super();
		this.make = make;
		this.model = Model;
		this.basePrice = basePrice;
		this.year=1900;
		this.opset = new ArrayList<OptionSet>();
		this.choice = new ArrayList<Option>();
	}
	// ----------------------------getters----------------------------------
	//-------------------------------------------------------------------------
	public  String getMake() {
		return make;
	}

	public  String getModel() {
		return model;
	}
	
	public  float getBasePrice() {
		return basePrice;
	}

	public  int getYear() {
		return year;
	}
	
	public  ArrayList<OptionSet> getOpset() {
		return opset;
	}

	public  ArrayList<Option> getChoice() {
		return choice;
	}
	
	//---------------------OptionSet getters----------------------------------
	// get OptionSet by index
	public  OptionSet getOpset(int i) {
		return opset.get(i);
	}

	// get OptionSet by name
		public  OptionSet getOpset(String s) {
			for (int i = 0; i < opset.size(); i++) {
				if (opset.get(i).getName().equals(s))
					return opset.get(i);
			}
			return null;
		}
	// get OptionSet index by name
		public  int getOpsetIndex(String s) {
			for (int i = 0; i < opset.size(); i++) {
				//System.out.print("Autoline96:opset.get(i).getName(): "+ opset.get(i).getName()) ;
				if (opset.get(i).getName().equals(s))
					return i;
			}
			return -1;
		}
	
	// get OptionSet name by index
	public  String getOptionSetName(int index) {
		return opset.get(index).getName();
	}
	//---------------------End OptionSet getters----------------------------------
	
	//---------------------Option getters----------------------------------
	// get Options in option set by index
	public  ArrayList<Option> getOptions(int index) {
		return opset.get(index).getOpt();
	}

	// get option name value by index
	public  String getOptionNameValue(int i, int j) {
		return this.opset.get(i).getOptNameValue(j);
	}
	
	// get option price value by index
	public  float getOptionPriceValue(int i, int j) {
		return this.opset.get(i).getOptPriceValue(j);
	}
	//---------------------End Option getters----------------------------------
	//---------------------End getters
	
	
	// ----------------------------Setters----------------------------------
	//-------------------------------------------------------------------------
	
	public  void setMake(String make) {
		this.make = make;
	}

	public  void setModel(String model) {
		this.model = model;
	}
	
	public  void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	
	public  void setYear(int year) {
		this.year = year;
	}
/*
	public  void setChoice(ArrayList<Option> choice) {
		this.choice = choice;
	}
*/	
	// set optionSet name value
	public  void setOptionSetName(int i, String optionSetName) {
		this.opset.get(i).setName(optionSetName);

	}
	// set optionName by name
	public  void setOptionName(int i, String optionName,String newName){
		int j = this.opset.get(i).getOptIndex(optionName);
		this.opset.get(i).setOptName(j, newName);
	}
	// set both option values by index
	public  void setOptionValue(int i, int j, String name, float price) {
		this.opset.get(i).setOptValue(j, name, price);
	}
	//set optionPrice by option name
	public  void setOptionPrice(int i, String optionName, float newPrice) {
		int j = this.opset.get(i).getOptIndex(optionName);
		this.opset.get(i).setOptPrice(j, newPrice);

	}
	//------------------------Add/Delete OptionSets(not in use)-----------------------------------
	// add Option to OptionSet
	public  void addOptionValue(int i, String name, float price) {
		this.opset.get(i).addOption(name, price);
	}

	// delete OptionSet by name
	public  void deleteOptionSet(String opname) {
		int index = getOpsetIndex(opname);
		if (index > -1)
			deleteOptionSet(index);
	}

	// delete OptionSet by index
	public  void deleteOptionSet(int index) {
		if ((index > -1) && (index < this.opset.size()))
			this.opset.remove(index);
	}
	//------------------------End Add/Delete OptionSets-----------------------------------
	
	//----------------------------------choice methods--------------------------------------
		// get the name of the Option chosen by user by OptionSet name
	public  String getOptionChoice(String setName) {
		int opSetIndex = getOpsetIndex(setName);
		return opset.get(opSetIndex).getOptionChoice().getName();
	}
		// get the name of the Option chosen by user by OptionSet index
	public  String getOptionChoice(int opSetIndex) {
		return opset.get(opSetIndex).getOptionChoice().getName();
	}
	
		// get the price of the Option chosen by user for a given OptionSet name
	public  float getOptionChoicePrice(String setName) {
		int opSetIndex = getOpsetIndex(setName);
		return opset.get(opSetIndex).getOptionChoice().getPrice();
	}

	// set the Option chosen by user for a given Option Set
		//by  OptionSetName, optionName
	public  void setOptionChoice(String setName, String optionName) {
		int opSetIndex = getOpsetIndex(setName);
		Option chosen = opset.get(opSetIndex).getOption(optionName);
		opset.get(opSetIndex).setChoice(chosen);
		choice.add(chosen);
	}
		//by OptionSetName, option index
	public  void setOptionChoice(String setName, int optionIndex) {
		int opSetIndex = getOpsetIndex(setName);
		Option chosen = opset.get(opSetIndex).getOption(optionIndex);
		opset.get(opSetIndex).setChoice(chosen);
		choice.add(chosen);
	}
		//by optionSet index, option index
	public  void setOptionChoice(int opSetIndex, int optionIndex) {
		Option chosen = opset.get(opSetIndex).getOption(optionIndex);
		opset.get(opSetIndex).setChoice(chosen);
		choice.add(chosen);
	}
	//clear out choice
	public  void clearChoice() {
		this.choice.clear();
	}
	//----------------------------------end choice methods--------------------------------------
	
	//----------------------------------print methods--------------------------------------
	// get total including all option values
	public  float getTotalPrice() {
		float total = this.getBasePrice();
		for (int i = 0; i < choice.size(); i++) {
			total += choice.get(i).getPrice();
		}
		return total;
	}

	public  void print() {
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
	//----------------------------------end print methods--------------------------------------
}
