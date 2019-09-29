package adapter;
import exception.*;
import java.util.Properties;

public interface CreateAuto {
	public abstract void buildAuto(String fileName) throws AutoException;
	public abstract void buildAuto(String fileName, String fileType) throws AutoException;
	public abstract void buildAuto(Properties props) throws AutoException;
	public abstract void printAuto(int year, String make, String model) throws AutoException;

}
