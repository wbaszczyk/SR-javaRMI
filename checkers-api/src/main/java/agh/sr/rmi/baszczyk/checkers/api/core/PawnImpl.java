package agh.sr.rmi.baszczyk.checkers.api.core;
 
import java.rmi.RemoteException;

import agh.sr.rmi.baszczyk.checkers.api.Color;
import agh.sr.rmi.baszczyk.checkers.api.Direction;
import agh.sr.rmi.baszczyk.checkers.api.Pawn;
import agh.sr.rmi.baszczyk.checkers.api.Position;

public class PawnImpl implements Pawn {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color side;
	private Position position;
	
	private Direction directionOnBoard;

	
	public PawnImpl(Color side, Position pos, Direction directionOnBoard) throws RemoteException{
		
		this.side=side;
		this.setPosition(pos);
		this.directionOnBoard=directionOnBoard;
	}

	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.server.core.Pawn#getDirectionOnBoard()
	 */
	public Direction getDirectionOnBoard() {
		return directionOnBoard;
	}
	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.server.core.Pawn#getSide()
	 */
	public Color getSide()  {
		return side;
	}


	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.server.core.Pawn#getPosition()
	 */
	public Position getPosition()  {
		return position;
	}


	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.server.core.Pawn#setPosition(agh.sr.rmi.baszczyk.checkers.api.core.Position)
	 */
	public void setPosition(Position position)  {
		this.position = position;
	}
	
	@Override
	public String toString(){
		
		return side+" "+position.toString();
	}

	
}
