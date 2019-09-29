package model;

import java.io.*;
import java.util.ArrayList;

public class OptionSet implements Serializable {
	private String name;
	private ArrayList<Option> opt;
	private Option optionChoice;

	// constructor
	public OptionSet(String name) {
		super();
		this.name = name;
		this.opt= new ArrayList<Option>();
		this.optionChoice = null;
	}

	public OptionSet(String name, int size) {
		super();
		this.name = name;
		this.opt= new ArrayList<Option>();
		this.optionChoice = null;
	}
	// getters and setters
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected ArrayList<Option> getOpt() {
		return opt;
	}


	protected void setOpt(ArrayList<Option> opt) {
		this.opt = opt;
	}
	
	protected Option getOptionChoice() {
		return optionChoice;
	}
	
	protected void setChoice(Option optionChoice) {
		this.optionChoice = optionChoice;
	}
	
	protected void setChoice(String optionChoiceName) {
		this.optionChoice = getOption(optionChoiceName);
	}

	protected int getOptSize() {
		return this.opt.size();
	}

	protected String getOptNameValue(int index) {
		return opt.get(index).getName();
	}

	protected float getOptPriceValue(int index) {
		return opt.get(index).getPrice();
	}

	protected Option getOption(String s) {
		for (int i = 0; i < opt.size(); i++) {
			if (opt.get(i).getName().equals(s))
				return opt.get(i);
		}
		return null;
	}

	protected Option getOption(int index) {
				return opt.get(index);
		}
		
	protected int getOptIndex(String s) {
		for (int i = 0; i < opt.size(); i++) {
			if (opt.get(i).getName().equals(s))
				return i;
		}
		return -1;
	}

	protected void setOptValue(int index, String s, float price) {
		this.opt.get(index).setName(s);
		this.opt.get(index).setPrice(price);
	}

	protected void setOptPrice(int index, float price) {
		this.opt.get(index).setPrice(price);
	}

	protected void setOptName(int index, String s) {
		this.opt.get(index).setName(s);
	}

	protected void deleteOption(int index) {
		opt.remove(index);
		return;
	}

	protected void addOption(String name, float price) {
		Option toBeAdded = new Option();
		toBeAdded.setName(name);
		toBeAdded.setPrice(price);
		this.opt.add(toBeAdded);
	}

	protected void print() {
		System.out.println("OptionSet name: " + getName());
		// System.out.println("OptionSet size: "+ getOpt().length);
		for (int i = 0; i < opt.size(); i++) {
			opt.get(i).print();
		}
		System.out.print("\n");
	}

	
}
