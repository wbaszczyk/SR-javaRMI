package agh.sr.rmi.baszczyk.checkers.client.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import agh.sr.rmi.baszczyk.checkers.api.GameState;
import agh.sr.rmi.baszczyk.checkers.api.Pawn;
import agh.sr.rmi.baszczyk.checkers.api.UserToken;
import agh.sr.rmi.baszczyk.checkers.api.core.PositionImpl;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage blackPawn, whitePawn;
	private UserToken player;


	public View(final GameState model, final UserToken player) throws RemoteException {
		try {
			blackPawn = ImageIO.read(new File("black.png"));
			whitePawn = ImageIO.read(new File("green.png"));
		} catch (IOException ex) {
		}
		this.player=player;

		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					model.unregister(player);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		ActionListener taskPerformer = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				repaint();
			}

            };
        Timer timer = new Timer( 1000 , taskPerformer);
        timer.setRepeats(true);
        timer.start();
		Panel panel = new Panel(model);

		panel.setBackground(new java.awt.Color(255, 255, 255));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		// add the component to the frame to see it!
		this.setContentPane(panel);
		// be nice to testers..
		this.setTitle("Checkers-"+player.getName());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

	}




	class Panel extends JPanel {
		private static final long serialVersionUID = 1L;

		GameState game;

		Panel(GameState g) throws RemoteException {
			// set a preferred size for the custom panel.
			setPreferredSize(new Dimension(g.getSize()
					* g.getOnePieceSize().getHeight(), g.getSize()
					* g.getOnePieceSize().getWidth()));
			this.game = g;

			addMouseMotionListener(new MyMouseAdapter());
			addMouseListener(new MyMouseAdapter());
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			try {
				for (int i = 0; i < game.getSize(); i++) {
					for (int j = 0; j < game.getSize(); j++) {
						g.setColor(Color.BLUE);
						g.drawRect(i * game.getOnePieceSize().getHeight(), j
								* game.getOnePieceSize().getWidth(), game
								.getOnePieceSize().getHeight(), game
								.getOnePieceSize().getWidth());
						if (game.getBoard()[i][j].getColor() == agh.sr.rmi.baszczyk.checkers.api.Color.BLACK)
							g.setColor(Color.GRAY);
						else
							g.setColor(Color.WHITE);
						g.fillRect(i * game.getOnePieceSize().getHeight(), j
								* game.getOnePieceSize().getWidth(), game
								.getOnePieceSize().getHeight(), game
								.getOnePieceSize().getWidth());
					}

				}

				int sep = 5;
				for (Pawn p : game.getWhitePawns()) {
					g.drawImage(whitePawn, sep + p.getPosition().getX()
							* game.getOnePieceSize().getHeight(), sep
							+ p.getPosition().getY()
							* game.getOnePieceSize().getWidth(), game
							.getOnePieceSize().getHeight() - 2 * sep, game
							.getOnePieceSize().getWidth() - 2 * sep, null);
				}
				for (Pawn p : game.getBlackPawns()) {

					g.drawImage(blackPawn, sep + p.getPosition().getX()
							* game.getOnePieceSize().getHeight(), sep
							+ p.getPosition().getY()
							* game.getOnePieceSize().getWidth(), game
							.getOnePieceSize().getHeight() - 2 * sep, game
							.getOnePieceSize().getWidth() - 2 * sep, null);
				}
				if (game.isActivePawn()) {
					g.setColor(Color.RED);
					Graphics2D g2 = (Graphics2D) g;
					Stroke oldStroke = g2.getStroke();
					g2.setStroke(new BasicStroke(5));
					g.drawRect(game.getActivePawn().getPosition().getX()
							* game.getOnePieceSize().getHeight(), game
							.getActivePawn().getPosition().getY()
							* game.getOnePieceSize().getWidth(), game
							.getOnePieceSize().getHeight(), game
							.getOnePieceSize().getWidth());
					g2.setStroke(oldStroke);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private class MyMouseAdapter extends MouseAdapter {

			@Override
			public void mouseReleased(MouseEvent e) {

				int i;
				try {
					i = (e.getX() / game.getOnePieceSize().getHeight());

					int j = (e.getY() / game.getOnePieceSize().getWidth());
					if (!game.isActivePawn())
						game.activationPawn(new PositionImpl(i, j), player);
					else
						game.makeMove(new PositionImpl(i, j));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				repaint();
			}
		}
	}

}
