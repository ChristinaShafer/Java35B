package adapter;
import exception.*;
public interface UpdateAuto {
	public abstract void updateOptionSetName(int year,String make, String model, String optionSetName, String newName) throws AutoException;
	public abstract void updateOptionPrice(int year, String make, String model, String optionName, String option, float newPrice) throws AutoException;

}
