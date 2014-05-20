package agh.sr.rmi.baszczyk.checkers.api.core;

 

import agh.sr.rmi.baszczyk.checkers.api.Size;


public class SizeImpl implements Size {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int height, width ;
	
	public SizeImpl(int height, int width){
		
		this.height=height;
		this.width=width;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

}
