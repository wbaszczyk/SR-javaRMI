package agh.sr.rmi.baszczyk.checkers.api;

import java.io.Serializable; 

public interface Pawn  extends Serializable {

	Direction getDirectionOnBoard();

	Color getSide();

	Position getPosition();

	void setPosition(Position position);

}