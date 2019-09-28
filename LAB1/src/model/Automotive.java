package model;

import java.io.*;

public class Automotive implements Serializable {
	public static final boolean DEBUG = false;
	private String modelName;
	private float basePrice;
	private OptionSet opset[];

	// constructors
	public Automotive() {

	}

	public Automotive(String modelName, float basePrice, int totalOptions) {
		super();
		this.modelName = modelName;
		this.basePrice = basePrice;
		this.opset = new OptionSet[totalOptions];
	}

	// getters and setters
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	// get all OptionSets
	public OptionSet[] getOpset() {
		return opset;
	}

	// get OptionSet by index
	public OptionSet getOpset(int i) {
		return opset[i];
	}

	// get OptionSet by name
	public OptionSet getOpset(String s) {
		for (int i = 0; i < opset.length; i++) {
			if (opset[i].getName().equals(s))
				return opset[i];
		}
		return null;
	}

	// get OptionSet index by name
	public int getOpsetIndex(String s) {
		for (int i = 0; i < opset.length; i++) {
			if (opset[i].getName().equals(s))
				return i;
		}
		return -1;
	}

	// get option name value
	public String getOptionNameValue(int i, int j) {
		return this.opset[i].getOptNameValue(j);
	}

	// get option price value
	public float getOptionPriceValue(int i, int j) {
		return this.opset[i].getOptPriceValue(j);
	}

	public void setOpset(OptionSet[] opset) {
		this.opset = opset;
	}

	public void setOpsetValue(int i, OptionSet os) {
		this.opset[i] = os;
	}

	public void setOptionValue(int i, int j, String name, float price) {
		this.opset[i].setOptValue(j, name, price);
	}

	// delete OptionSet by name
	public void deleteOptionSet(String opname) {
		int index = getOpsetIndex(opname);
		if (index > -1)
			deleteOptionSet(index);
	}

	// delete OptionSet by index
	public void deleteOptionSet(int index) {
		int newSize = opset.length - 1;
		// create a new array of optionSet 1 size smaller
		// copy optionSets until you reach the one to be deleted.
		OptionSet[] temp = new OptionSet[newSize];
		for (int i = 0; i < index; i++) {
			temp[i] = new OptionSet(opset[i].getName(), opset[i].getOptSize());
			temp[i].setOpt(opset[i].getOpt());
		}
		// copy optionsets from deleted one to end
		for (int i = index; i < newSize; i++) {
			temp[i] = new OptionSet(opset[i + 1].getName(), opset[i + 1].getOptSize());
			temp[i].setOpt(opset[i + 1].getOpt());
		}
		// set the opset to the new smaller optionset
		this.opset = temp;
	}

	public void print() {
		System.out.println("Model: " + modelName);
		System.out.println("Base Price: $" + basePrice);
		System.out.println("Number of options: " + opset.length);
		System.out.print("\n");
		for (int i = 0; i < opset.length; i++) {
			opset[i].print();
		}
	}

}
