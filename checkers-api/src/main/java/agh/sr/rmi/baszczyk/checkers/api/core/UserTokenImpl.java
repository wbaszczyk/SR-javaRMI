package agh.sr.rmi.baszczyk.checkers.api.core;

import agh.sr.rmi.baszczyk.checkers.api.Color;
import agh.sr.rmi.baszczyk.checkers.api.UserToken;

public class UserTokenImpl implements UserToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	private Color side;

	
	public UserTokenImpl(String name, int id, Color side) {
		this.name = name;
		this.id = id;
		this.side=side;
	}

	@Override
	public String toString() {
		return "PLAYER: [name=" + name +  ", color=" + side + "]";
	}

	public Color getSide() {
		return side;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

}
