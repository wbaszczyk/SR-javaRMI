package agh.sr.rmi.baszczyk.checkers.api.core;
 

import agh.sr.rmi.baszczyk.checkers.api.Position;
import agh.sr.rmi.baszczyk.checkers.api.PositionBeat;

public class PositonBeatImpl implements PositionBeat{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Position pawnBeating;
	private Position pawnJumping;
	
	public PositonBeatImpl(Position pawnBeating, Position pawnJumping){
		
		this.pawnBeating=pawnBeating;
		this.pawnJumping=pawnJumping;
	}
	
	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.api.core.PositionBeat#getPawnBeating()
	 */
	public Position getPawnBeating(){
		
		return pawnBeating;
	}
	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.api.core.PositionBeat#getPawnJumping()
	 */
	public Position getPawnJumping(){
		
		return pawnJumping;
	}
}
