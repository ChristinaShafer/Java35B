
package client;

import model.*;
import adapter.*;
import java.util.*;

public class SelectCarOptions {
	private static boolean DEBUG=false;
	////////// PROPERTIES //////////
	private Scanner in = new Scanner(System.in);

	////////// CONSTRUCTORS //////////

	public SelectCarOptions() {

	}

	////////// INSTANCE METHODS //////////

	public void configureAuto(Object obj) {
		if (DEBUG)
			System.out.println("It's configuration time! In SelectCarOptions!\n");
		Automobile a = (Automobile)obj;
		BuildAuto b = new BuildAuto();
		b.chooseOptions(a);
		System.out.print("You have configured the following:\n");
		a.printTotal();
		System.out.println("");
		System.out.println("Press any key to continue.");
		

	}

	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;
		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
			if (DEBUG)
				System.out.println("It's an auto!");
		}
		catch (ClassCastException e) {
			isAutomobile = false;
			if (DEBUG)
				System.out.println("That's NOT an auto!");
		}

		return isAutomobile;
	}

}
