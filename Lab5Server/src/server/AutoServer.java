package server;
import model.*;
import exception.*;

public interface AutoServer {
	abstract void addAuto(Automobile auto) throws AutoException;
	abstract String listAutos();
	abstract Automobile getAuto(String fullAutoName);
}
