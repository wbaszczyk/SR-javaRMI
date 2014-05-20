package agh.sr.rmi.baszczyk.checkers.client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import agh.sr.rmi.baszczyk.checkers.api.Color;
import agh.sr.rmi.baszczyk.checkers.api.GameState;
import agh.sr.rmi.baszczyk.checkers.api.core.UserTokenImpl;
import agh.sr.rmi.baszczyk.checkers.client.core.View;

public class CheckersClient {
	private static final Logger logger = Logger.getLogger(CheckersClient.class);

	private static String RMI_REGISTRY_ADDRESS = "rmi://127.0.0.1:1099";
	private static final String NOTEBOARD_REMOTE_OBJECT_NAME = "gamestate";

	public static void main(String[] args) throws RemoteException {
		GameState state = null;
		UserTokenImpl player = null;
		String port;
		String address;
		try {
			BasicConfigurator.configure();

			if(args.length!=3){
				logger.error("<RMI_REGISTRY_ADDRESS> <port>!");
				System.exit(-1);
			}
			address=args[0];
			port=args[1];
			RMI_REGISTRY_ADDRESS= "rmi://"+address+":"+port;
			state = (GameState) Naming.lookup(RMI_REGISTRY_ADDRESS + "/" + NOTEBOARD_REMOTE_OBJECT_NAME);
			logger.debug("Mam referencje do obiektu zdalnego!");

			Color side = null;
			if (state.getPlayers().size() == 0)
				side = Color.BLACK;
			else if (state.getPlayers().size() == 1)
				side = Color.WHITE;
			else {
				logger.error("There are two players registered already!");
				System.exit(-1);
			}
			player = new UserTokenImpl(args[2], 1, side);

			state.register(player);
			View v = new View(state, player);
			v.setVisible(true);

			while (true) {
				if (v == null)
					logger.error("ASD");
			}

		} catch (Exception e) {
			logger.error(e);
			state.unregister(player);
			System.exit(-1);
		}

		System.exit(0);
	}
}
