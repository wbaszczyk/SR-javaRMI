package agh.sr.rmi.baszczyk.checkers.api;

import java.io.Serializable; 

public interface Position extends Serializable  {

	int getX();

	int getY();

	boolean isEqual(Position p);

}