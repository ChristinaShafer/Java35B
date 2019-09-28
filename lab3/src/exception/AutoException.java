package exception;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Automobile;
import java.util.Arrays;

/*enum Errors {
	E101(101, "problem 101"), E102(102, "problem 102"), E103(103, "problem 103");

	private final Object[] values;

	Errors(Object... vals) {
		values = vals;
	}

	public int getEnumErrorNo() {
		return (int) values[0];
	}

	public String getEnumErrorMsg() {
		return (String) values[1];
	}
}
*/


public class AutoException extends Exception {

	private int errorno;
	private String errormsg;
	private Automobile a;
	private int index;

	public AutoException() {
		super();
		printmyproblem();
	}

	public AutoException(String errormsg) {
		super();
		this.errormsg = errormsg;
		printmyproblem();
	}

	public AutoException(int errorno) {
		super();
		this.errorno = errorno;
		printmyproblem();
	}

	public AutoException(int errorno, String errormsg) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		printmyproblem();
	}

	public AutoException(int errorno, String errormsg, int index) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		this.index = index;
		printmyproblem();
	}
	
	public AutoException(int errorno, String errormsg, Automobile a) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		this.a=a;
		printmyproblem();
	}
	
	public AutoException(int errorno, String errormsg, Automobile a, int index) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		this.a=a;
		this.index=index;
		printmyproblem();
	}
	
	public int getErrorno() {
		return errorno;
	}

	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public Automobile getAuto() {
		return a;
	}

	public void setAuto(Automobile a) {
		this.a = a;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void printmyproblem() {
		logException(errorno, errormsg);
	}

	/*public String fixProblemReadFromConsole() {
		String a = "Z:\\JavaPrograms\\SelfHealingSoftware\\bin\\abc.txt";
		System.out.println("got here --> fixProblemReadFromConsole");
		return a;
	}*/

	public void logException(int errorNo, String errorMsg) {
		try {
			if (Automobile.DEBUG)
				System.out.print("errorno: " + errorno + " errorMsg: " + errorMsg + "\n");
			String logfile = "AutoErrorLog.txt";
			SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String logMsg = "[" + d1.format(date) + "] " + errorNo + ": " + errorMsg + "\n";
			FileWriter fw = new FileWriter(logfile, true);
			PrintWriter writer = new PrintWriter(fw);
			writer.print(logMsg);
			writer.println();
			writer.close();
		} catch (Exception e) {
			System.out.print("Logging Error: " + e);
			System.exit(1);
		}
	}

	public String fix(int errorno) {
		FixHelpers fh1 = new FixHelpers();
		switch (errorno) {
			case 105:  return fh1.fix105();
			case 115: return fh1.fix115();
			case 125: return fh1.fix125();
			case 135: return fh1.fix135(index);
			case 145: return fh1.fix145(index);
			default:
				System.out.println("Your error has been logged.");
				return null;
		}
	}


}
