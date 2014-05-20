package agh.sr.rmi.baszczyk.checkers.api.core;

import java.rmi.RemoteException;

import agh.sr.rmi.baszczyk.checkers.api.Checker;
import agh.sr.rmi.baszczyk.checkers.api.Color;
import agh.sr.rmi.baszczyk.checkers.api.Size;

public class CheckerImpl implements Checker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private Size size;
	private boolean isEmpty;
	
	public CheckerImpl(Color fieldColor, Size s, boolean isEmpty) throws RemoteException{
		
		this.color=fieldColor;
		this.size=s;
		this.setEmpty(isEmpty);
	}
	
	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.server.core.Checker#getSize()
	 */
	public Size getSize(){
		
		return size;
	}
	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.server.core.Checker#getColor()
	 */
	public Color getColor(){
		
		return color;
	}

	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.server.core.Checker#isEmpty()
	 */
	public boolean isEmpty(){
		return isEmpty;
	}

	/* (non-Javadoc)
	 * @see agh.sr.rmi.baszczyk.checkers.server.core.Checker#setEmpty(boolean)
	 */
	public void setEmpty(boolean isEmpty){
		this.isEmpty = isEmpty;
	}
}
