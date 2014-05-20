package agh.sr.rmi.baszczyk.checkers.server.core;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import agh.sr.rmi.baszczyk.checkers.api.Color;
import agh.sr.rmi.baszczyk.checkers.api.Direction;
import agh.sr.rmi.baszczyk.checkers.api.GameState;
import agh.sr.rmi.baszczyk.checkers.api.Pawn;
import agh.sr.rmi.baszczyk.checkers.api.Position;
import agh.sr.rmi.baszczyk.checkers.api.PositionBeat;
import agh.sr.rmi.baszczyk.checkers.api.Size;
import agh.sr.rmi.baszczyk.checkers.api.UserToken;
import agh.sr.rmi.baszczyk.checkers.api.core.PawnImpl;
import agh.sr.rmi.baszczyk.checkers.api.core.PositionImpl;
import agh.sr.rmi.baszczyk.checkers.api.core.PositonBeatImpl; 

public class Game extends Borad implements GameState {

	private List<Pawn> blackPawns;
	private List<Pawn> whitePawns;
	private Pawn activePawn=null;
	private UserToken activePlayer=null;
	private Move move;
	private Color whoseTurn;
	private UserToken player1=null;
	private UserToken player2=null;
	private static final Logger logger = Logger.getLogger(Game.class);

	public Game(int size, Size onePieceSize) throws RemoteException {
		super(size, onePieceSize);

		move=new Move();
		blackPawns = new ArrayList<Pawn>();
		whitePawns = new ArrayList<Pawn>();
		int numberOfPawns = 3 * size / 2;
		whoseTurn=Color.BLACK;

		for (int i = 0; i < getSize(); i++){
			for (int j = 0; j < getSize(); j++) {
				if (getBoard()[i][j].getColor() == Color.BLACK){
					blackPawns.add(new PawnImpl(Color.BLACK, new PositionImpl(j, i), Direction.Down));
					getBoard()[j][i].setEmpty(false);
				}
				if(blackPawns.size() == numberOfPawns)break;
			}
			if(blackPawns.size() == numberOfPawns)break;
		}
		for (int i = getSize()-1; i >-1; i--){
			for (int j = getSize()-1; j >-1; j--) {
				if (getBoard()[i][j].getColor() == Color.BLACK){
					whitePawns.add(new PawnImpl(Color.WHITE, new PositionImpl(j, i), Direction.Up));
					getBoard()[j][i].setEmpty(false);
				}
				
				if(whitePawns.size() == numberOfPawns)break;
			}
			if(whitePawns.size() == numberOfPawns)break;
		}

	}
	
	private boolean isCollision(Position newPos) throws RemoteException{
		
		return !getBoard()[newPos.getX()][newPos.getY()].isEmpty();
	}
	public boolean isBoundPossible(Position pos) throws RemoteException{
		
		return (pos.getX()<getSize() &&pos.getY()<getSize()&&pos.getX()>-1 &&
				pos.getY()>-1)?true:false;
	}
	//return null if there is no Pawn to beat
	private Map<Pawn,PositonBeatImpl> isHitPossible(Pawn p) throws RemoteException{

		Map<Pawn, PositonBeatImpl> hits=new HashMap<Pawn,PositonBeatImpl>();
		if(p.getSide()==Color.WHITE)
			for( Pawn toBeat : blackPawns){					
				for (Position pos : move.getNoBeatMoves(p.getDirectionOnBoard())){
					
					Position tmp=new PositionImpl(pos.getX()+p.getPosition().getX(),pos.getY()+p.getPosition().getY());
					Position isFree=new PositionImpl(2*pos.getX()+p.getPosition().getX(),2*pos.getY()+p.getPosition().getY());
					if(toBeat.getPosition().isEqual(tmp) && isBoundPossible(tmp) &&
							isCollision(tmp)&&isBoundPossible(isFree) && !isCollision(isFree)){
						hits.put(p,new PositonBeatImpl(tmp, isFree));
					}
				}
		}
		if(p.getSide()==Color.BLACK)
			for( Pawn toBeat : whitePawns){					
				for (Position pos : move.getNoBeatMoves(p.getDirectionOnBoard())){
					
					Position tmp=new PositionImpl(pos.getX()+p.getPosition().getX(),pos.getY()+p.getPosition().getY());
					Position isFree=new PositionImpl(2*pos.getX()+p.getPosition().getX(),2*pos.getY()+p.getPosition().getY());
					if(toBeat.getPosition().isEqual(tmp) && isBoundPossible(tmp) &&
							isCollision(tmp)&&isBoundPossible(isFree) && !isCollision(isFree)){
						hits.put(p,new PositonBeatImpl(tmp, isFree));
					}
				}
		}
		return hits;
	}
	
	public void deletePawn(Position pos) throws RemoteException{
		

		for (Iterator<Pawn> iter = blackPawns.listIterator(); iter.hasNext(); ) {
		    Pawn p = iter.next();
		    if (p.getPosition().isEqual(pos)) {
		        getBoard()[p.getPosition().getX()][p.getPosition().getY()].setEmpty(true);
		        iter.remove();
		        break;
		    }
		}


		for (Iterator<Pawn> iter = whitePawns.listIterator(); iter.hasNext(); ) {
		    Pawn p = iter.next();
		    if (p.getPosition().isEqual(pos)) {
		        getBoard()[p.getPosition().getX()][p.getPosition().getY()].setEmpty(true);
		        iter.remove();
		        break;
		    }
		}
	}

	private UserToken getPlayer(Pawn p){
		
		if(player1!=null)
			if(p.getSide()==player1.getSide()) return player1;
		return player2;
	}

	public void activationPawn(Position pos, UserToken token)  throws RemoteException {
		
		boolean isAvailable=false;
		if(whoseTurn==Color.BLACK && whoseTurn==token.getSide())
			for (Pawn p : blackPawns)
				if(p.getPosition().getX()==pos.getX() && p.getPosition().getY()==pos.getY()){
					activePawn=p;
					activePlayer=getPlayer(p);
					isAvailable=true;
					break;
				}
		

		if(whoseTurn==Color.WHITE && whoseTurn==token.getSide())
			for (Pawn p : whitePawns){
				
				if(p.getPosition().getX()==pos.getX() && p.getPosition().getY()==pos.getY()){
					activePawn=p;
					activePlayer=getPlayer(p);
					isAvailable=true;
					break;
				}
			}
		if(whoseTurn!=token.getSide())
			logger.debug(activePlayer.toString() +" it's not your turn!");
		if (!isAvailable) activePawn=null;


		
		
	}
	public boolean isActivePawn()  throws RemoteException {
		
		return activePawn==null?false:true;
	}
	public Pawn getActivePawn()  throws RemoteException {
		
		return activePawn;
	}
	public List<Pawn> getWhitePawns() throws RemoteException  {

		return whitePawns;
	}

	public void makeMove(Position newPos) throws RemoteException {

		if(activePawn.getSide()==whoseTurn){
			Map<Pawn, PositonBeatImpl> hits=new HashMap<Pawn,PositonBeatImpl>();
			if(whoseTurn==Color.BLACK)
				for (Pawn p : blackPawns)
					hits.putAll(isHitPossible(p));
	
			if(whoseTurn==Color.WHITE)
				for (Pawn p : whitePawns)
					hits.putAll(isHitPossible(p));
	
				
			boolean isMovePossible=false;
			boolean isBeating=false;
			if (hits.size()>0){
				logger.debug(activePlayer.toString() +" have to jump!");
				for (Map.Entry<Pawn, PositonBeatImpl> entry : hits.entrySet()) {
				    Pawn key = entry.getKey();
				    PositionBeat p = entry.getValue();
				    if (key.getPosition().isEqual(activePawn.getPosition()) && p.getPawnJumping().isEqual(newPos)){
						deletePawn(p.getPawnBeating());
						isMovePossible=true;
						isBeating=true;
						break;
					}
				}
			}
			else isMovePossible=true;
			
			if (isMovePossible && move.isMovePossible(activePawn.getPosition(), newPos, activePawn.getDirectionOnBoard(), isBeating)
					&& !isCollision(newPos)){

				logger.debug(activePlayer.toString() +" moved to: "+ newPos);
				whoseTurn=Color.reverse(whoseTurn);
				getBoard()[activePawn.getPosition().getX()][activePawn.getPosition().getY()].setEmpty(true);
				activePawn.setPosition(newPos);
				getBoard()[activePawn.getPosition().getX()][activePawn.getPosition().getY()].setEmpty(false);
			}
		}
		activePawn=null;
		//activePlayer=null;
		
	}

	public List<Pawn> getBlackPawns() throws RemoteException {

		return blackPawns;
		
	}

	public void register(UserToken token) throws RemoteException {

		if(player1==null){
			player1=token;
			logger.debug(token.toString() +" has been registered");
		}
		else if(player2==null){
			player2=token;
			logger.debug(token.toString() +" has been registered");
		}

		
	}

	public List<UserToken> getPlayers() throws RemoteException {

		List<UserToken> out=new ArrayList<UserToken>();

		if(player1!=null)out.add(player1);
		if(player2!=null)out.add(player2);
		
		return out;
	}

	public void unregister(UserToken token) throws RemoteException {
		
		if(player1!=null)
			if(token.getSide()==player1.getSide()) player1=null;
		else player2=null;

		logger.debug(token.toString() +" has been unregistered");
		
	}

}
