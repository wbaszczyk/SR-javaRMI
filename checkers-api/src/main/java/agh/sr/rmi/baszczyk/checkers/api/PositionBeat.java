package agh.sr.rmi.baszczyk.checkers.api;

import java.io.Serializable; 


public interface PositionBeat  extends Serializable {

	Position getPawnBeating();

	Position getPawnJumping();

}