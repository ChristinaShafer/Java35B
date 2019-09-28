package adapter;
import exception.*;

public interface CreateAuto {
	public abstract void buildAuto(String fileName) throws AutoException;
	public abstract void printAuto(int year, String make, String model) throws AutoException;

}
