package agh.sr.rmi.baszczyk.checkers.server.core;

import java.rmi.RemoteException;

import agh.sr.rmi.baszczyk.checkers.api.Checker;
import agh.sr.rmi.baszczyk.checkers.api.Color;
import agh.sr.rmi.baszczyk.checkers.api.Size;
import agh.sr.rmi.baszczyk.checkers.api.core.CheckerImpl;

public class Borad {

	private Checker[][] table;
	private int size;
	private Size onePieceSize;

	public Borad(int size, Size onePieceSize) throws RemoteException {

		table = new Checker[size][size];
		this.onePieceSize = onePieceSize;
		this.size = size;

		Color tmp;
		for (int i = 0; i < size * size; i++) {

			tmp = (i / size) % 2 == 0 ? Color.BLACK : Color.WHITE;
			tmp = (i % size) % 2 == 0 ? Color.reverse(tmp) : tmp;
			table[i / size][i % size] = new CheckerImpl(tmp, onePieceSize, true);

		}
	}

	public Checker[][] getBoard()  throws RemoteException  {

		return table;
	}

	public int getSize()  throws RemoteException  {

		return size;
	}

	public Size getOnePieceSize()  throws RemoteException  {

		return onePieceSize;
	}
}
