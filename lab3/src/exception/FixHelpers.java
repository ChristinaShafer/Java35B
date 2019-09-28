package exception;
import java.util.Scanner;

public class FixHelpers {
	private int errorno;

	public FixHelpers() {
		
	}
	public FixHelpers(int errorno) {
		this.errorno = errorno;
		
	}
	
	public String fix105() {
		System.out.println("Model name corrected.");
		return "Focus Wagon ZTW";
	}
	
	public String fix106() {
		System.out.println("Make corrected.");
		return "Ford";
	}
	
	
	public String fix115() {
		System.out.println("Base Price corrected.");
		return String.valueOf(18455);
	}
	
	public String fix125() {
		System.out.println("Number of sets of options corrected.");
		return String.valueOf(5);
	}
	
	public String fix135(int i) {
		System.out.println("Option set name corrected.");
		String [] names = {"Color","Transmission","Brakes/Traction Control","Side Impact Air Bags","Power Moonroof"};
		return names[i];
	}
	public String fix145(int i) {
		System.out.println("Number of options corrected.");
		int [] numbers = {10,2,3,2,2};
		
		return String.valueOf(numbers[i]);
	}
}
