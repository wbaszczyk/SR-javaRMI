package agh.sr.rmi.baszczyk.checkers.api;

import java.io.Serializable;

public interface Checker extends Serializable {

	Size getSize();

	Color getColor();

	boolean isEmpty();

	void setEmpty(boolean isEmpty);

}