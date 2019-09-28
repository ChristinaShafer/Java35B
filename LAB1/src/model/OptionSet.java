package model;

import java.io.*;

public class OptionSet implements Serializable {
	private String name;
	private Option opt[];

	// constructor
	public OptionSet(String name, int size) {
		super();
		this.name = name;
		this.opt = new Option[size];
		for (int i = 0; i < size; i++)
			this.opt[i] = new Option();
	}

	// getters and setters
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected Option[] getOpt() {
		return opt;
	}

	protected void setOpt(Option[] opt) {
		this.opt = opt;
	}

	protected int getOptSize() {
		return this.opt.length;
	}

	protected String getOptNameValue(int index) {
		return opt[index].getName();
	}

	protected float getOptPriceValue(int index) {
		return opt[index].getPrice();
	}

	protected Option getOption(String s) {
		for (int i = 0; i < opt.length; i++) {
			if (opt[i].getName().equals(s))
				return opt[i];
		}
		return null;
	}

	protected int getOptIndex(String s) {
		for (int i = 0; i < opt.length; i++) {
			if (opt[i].getName().equals(s))
				return i;
		}
		return -1;
	}

	protected void setOptValue(int index, String s, float price) {
		this.opt[index].setName(s);
		this.opt[index].setPrice(price);
	}

	protected void deleteOption(int index) {
		int newSize = opt.length - 1;
		// create a new array of options 1 size smaller
		Option[] temp = new Option[newSize];
		for (int i = 0; i < newSize; i++)
			temp[i] = new Option();
		// copy options until you reach the one to be deleted.
		for (int i = 0; i < index; i++) {
			temp[i] = opt[i];
		}
		// skip the one to be deleted and copy the rest
		for (int i = index; i < newSize; i++) {
			temp[i] = opt[i + 1];
		}
		this.opt = temp;
	}

	protected void addOption(String name, float price) {
		int newSize = opt.length + 1;
		// create a new array of options 1 size larger
		Option[] temp = new Option[newSize];
		for (int i = 0; i < newSize; i++)
			temp[i] = new Option();
		// copy options
		for (int i = 0; i < opt.length; i++) {
			temp[i] = opt[i];
		}
		// add new values
		temp[newSize - 1].setName(name);
		temp[newSize - 1].setPrice(price);
		this.opt = temp;

	}

	protected void print() {
		System.out.println("OptionSet name: " + getName());
		// System.out.println("OptionSet size: "+ getOpt().length);
		for (int i = 0; i < opt.length; i++) {
			opt[i].print();
		}
		System.out.print("\n");
	}

	// innerclass
	private class Option implements Serializable {
		private String name;
		private float price;

		protected Option() {
			this.name = "";
			this.price = 0;
			// TODO Auto-generated constructor stub
		}

		protected Option(String name, float price) {
			super();
			this.name = name;
			this.price = price;
		}

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected float getPrice() {
			return price;
		}

		protected void setPrice(float price) {
			this.price = price;
		}

		// methods
		protected void print() {
			System.out.print(name + ": " + price + "\n");
		}

	}

}
