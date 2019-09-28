package scale;

import exception.AutoException;

public interface EditThread  {
	//these are the methods to be implemented
	public abstract void updateOptionSetName(int threadNum, int year,String make, String model, String optionSetName, String newOptionSetName) throws AutoException;
	public abstract void updateOptionPrice(int threadNum, int year,String make, String model, String optionSetName, String option, float newPrice) throws AutoException;
	public abstract void updateOptionName(int threadNum, int year,String make, String model, String optionSetName, String optionName, String newOptionName) throws AutoException;

}
