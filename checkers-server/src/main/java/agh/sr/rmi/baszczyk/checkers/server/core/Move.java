package agh.sr.rmi.baszczyk.checkers.server.core;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import agh.sr.rmi.baszczyk.checkers.api.Direction;
import agh.sr.rmi.baszczyk.checkers.api.Position;
import agh.sr.rmi.baszczyk.checkers.api.core.PositionImpl;

public class Move {
	
	
	private List<PositionImpl> possibleMoveDown;
	private List<PositionImpl> possibleMoveUp;
	public Move(){
		
		possibleMoveDown=new ArrayList<PositionImpl>();
		possibleMoveDown.add(new PositionImpl(-1,1));
		possibleMoveDown.add(new PositionImpl(1,1));
		possibleMoveDown.add(new PositionImpl(-2,2));
		possibleMoveDown.add(new PositionImpl(2,2));
		

		possibleMoveUp=new ArrayList<PositionImpl>();
		possibleMoveUp.add(new PositionImpl(-1,-1));
		possibleMoveUp.add(new PositionImpl(1,-1));
		possibleMoveUp.add(new PositionImpl(-2,-2));
		possibleMoveUp.add(new PositionImpl(2,-2));
	}
	
	public boolean isMovePossible(Position oldPos, Position newPos, Direction dir, boolean isBeating) throws RemoteException{
		
		Position normalPos;
		//if(dir == Direction.Down){
			//oldPos < newPos
			normalPos =new PositionImpl(newPos.getX()-oldPos.getX(), newPos.getY()-oldPos.getY());
			if(isBeating==true)
				for (Position p : getBeatMoves(dir)){
					if(p.getX()==normalPos.getX() && p.getY()==normalPos.getY())
						return true;
				}
			else 		
				for (Position p : getNoBeatMoves(dir)){
					if(p.getX()==normalPos.getX() && p.getY()==normalPos.getY())
						return true;
			}
				
		//}
		/*if(dir == Direction.Up){
			//oldPos > newPos
			normalPos =new Position(newPos.getX()-oldPos.getX(), newPos.getY()-oldPos.getY());
			for (Position p : possibleMoveUp)
				if(p.getX()==normalPos.getX() && p.getY()==normalPos.getY())
					return true;
		}*/
		return false;
	}
	
	public List<PositionImpl> getBeatMoves(Direction dir){
		
		List<PositionImpl> out=new ArrayList<PositionImpl>();

		if(dir == Direction.Down){

			out.add(new PositionImpl(-2,2));
			out.add(new PositionImpl(2,2));
		}
		if(dir == Direction.Up){

			out.add(new PositionImpl(-2,-2));
			out.add(new PositionImpl(2,-2));
		}
		
		return out;
	}
	public List<Position> getNoBeatMoves(Direction dir){
		
		List<Position> out=new ArrayList<Position>();

		if(dir == Direction.Down){

			out.add(new PositionImpl(-1,1));
			out.add(new PositionImpl(1,1));
		}
		if(dir == Direction.Up){

			out.add(new PositionImpl(-1,-1));
			out.add(new PositionImpl(1,-1));
		}
		
		return out;
	}

}
