package agh.sr.rmi.baszczyk.checkers.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry; 
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
 

import agh.sr.rmi.baszczyk.checkers.api.GameState;
import agh.sr.rmi.baszczyk.checkers.api.core.SizeImpl; 
import agh.sr.rmi.baszczyk.checkers.server.core.Game;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	private static String RMI_REGISTRY_ADDRESS = "rmi://127.0.0.1:1099";
	private static final String NOTEBOARD_REMOTE_OBJECT_NAME = "gamestate";

	public static void main(String[] args) {

		String port;
		String address;
		try {

			BasicConfigurator.configure();
			if(args.length!=2){
				logger.error("<RMI_REGISTRY_ADDRESS> <port>!");
				System.exit(-1);
			}
			address=args[0];
			port=args[1];
			RMI_REGISTRY_ADDRESS= "rmi://"+address+":"+port;
			Game model = new Game(8, new SizeImpl(75, 75));

			GameState state = (GameState) UnicastRemoteObject.exportObject(
					model, 0);
			LocateRegistry.createRegistry(Integer.parseInt(port));

			Naming.rebind(RMI_REGISTRY_ADDRESS + "/"
					+ NOTEBOARD_REMOTE_OBJECT_NAME, state);
			logger.debug("Server is running!");

		} catch (Exception e) {
			logger.error(e);
			logger.debug("Server has stopped!");
			System.exit(-1);
		}
		// View widok = new View(model);
		// Controller kontroler = new Controller(model, widok);

		// widok.setVisible(true);

	}

}
