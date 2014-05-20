package agh.sr.rmi.baszczyk.checkers.api.core;
 

import agh.sr.rmi.baszczyk.checkers.api.Position;

public class PositionImpl implements Position{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x,y;
	
	public PositionImpl(int x, int y){
		
		this.x=x;
		this.y=y;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	public boolean isEqual(Position p) {
		return (p.getX()==getX() && p.getY()==getY())? true:false;
	}



	@Override
	public String toString(){
		
		return getX()+":"+getY();
	}
}
