package model;

import java.io.Serializable;

public class Option implements Serializable {
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
		if (price>=0)
			System.out.printf("%s: $%.2f\n",name,price );
		else
			System.out.printf("%s: -$%.2f\n",name,price*-1 );
	}

}
