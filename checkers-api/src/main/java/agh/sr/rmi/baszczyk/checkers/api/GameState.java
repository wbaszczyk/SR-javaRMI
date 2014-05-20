package agh.sr.rmi.baszczyk.checkers.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GameState extends Remote {

	
	void makeMove(Position newPos)throws RemoteException;
	boolean isActivePawn()throws RemoteException;
	void activationPawn(Position pos, UserToken token)throws RemoteException;
	int getSize()throws RemoteException;
	Size getOnePieceSize()throws RemoteException;
	Checker[][] getBoard()throws RemoteException;
	List<Pawn> getBlackPawns()throws RemoteException;
	List<Pawn> getWhitePawns()throws RemoteException;
	Pawn getActivePawn() throws RemoteException;
	void register(UserToken token)throws RemoteException;
	void unregister(UserToken token)throws RemoteException;
	List<UserToken> getPlayers()throws RemoteException;
}
