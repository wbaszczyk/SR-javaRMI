package agh.sr.rmi.baszczyk.checkers.api;

import java.io.Serializable;

public interface UserToken extends Serializable {

	public String getName();
	public Integer getId();
	public Color getSide();
}
