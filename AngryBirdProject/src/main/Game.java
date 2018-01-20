package main;

import java.awt.Frame;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class Game implements Runnable {

	Drawing draw = new Drawing();
	String message;
	int score;
	Gravity gravity = new Gravity();
	ColisionManager colisionManager = draw.getColisionManager();
	List<Pig> pigsList = colisionManager.getPigsList();

	Image buffer;

	// constructeur
	Game() {
		draw.setScore(0);
		init();
		new Thread(this).start();
	}

	// début de partie
	public void init() {
		draw.init();
	}

	// fin de partie
	void stop() {
		draw.stop();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

			if (!draw.isGameOver() && !draw.isSelecting()) {

				// moteur physique
				draw.getOiseau().setBirdX(draw.getOiseau().getBirdX() + draw.getOiseau().getVelocityX());
				draw.getOiseau().setBirdY(draw.getOiseau().getBirdY() + draw.getOiseau().getVelocityY());
				draw.getOiseau().setVelocityY(draw.getOiseau().getVelocityY() + gravity.getValue());
				if (draw.getOiseau().getVelocityX() < 2) {
					draw.getOiseau().setIcon("resources/slowbird.png");
					draw.repaint();
				} else if (draw.getOiseau().getVelocityX() > 5) {
					draw.getOiseau().setIcon("resources/bird.png");
					draw.repaint();
				}

				// conditions de victoire
				if (colisionManager.checkColision(draw.getOiseau(), draw.getCochon())) {
					stop();
					message = "Gagné : cliquez pour recommencer.";
					draw.setMessage(message);
					score++;
					draw.setScore(score);

					//System.out.println(draw.getColisionManager().getPigsList().indexOf(draw.getCochon()));
					colisionManager.onColision(draw.getOiseau(), draw.getCochon(),
							draw.getColisionManager().getPigsList().indexOf(draw.getCochon()));
					
					//draw.getColisionManager().getPigsList().remove(draw.getColisionManager().getPigsList().indexOf(draw.getCochon()));
					pigsList.remove(pigsList.indexOf(1));
					draw.cochon.setIcon("");

					draw.repaint();

				} else if (colisionManager.checkColision(draw.getOiseau(), draw.getCochon1())) {
					stop();
					message = "Gagné : cliquez pour recommencer.";
					draw.setMessage(message);
					score++;
					draw.setScore(score);

					colisionManager.onColision(draw.getOiseau(), draw.getCochon1(),
							draw.getColisionManager().getPigsList().indexOf(draw.getCochon1()));
					//draw.getColisionManager().getPigsList().remove(draw.getColisionManager().getPigsList().indexOf(draw.getCochon1()));

					pigsList.remove(pigsList.indexOf(2));
					draw.cochon1.setIcon("");
					draw.repaint();

				} else if (colisionManager.checkColision(draw.getOiseau(), draw.getCochon2())) {
					stop();
					message = "Gagné : cliquez pour recommencer.";
					draw.setMessage(message);
					score++;
					draw.setScore(score);

					colisionManager.onColision(draw.getOiseau(), draw.getCochon2(),
							draw.getColisionManager().getPigsList().indexOf(draw.getCochon2()));
					
					//draw.getColisionManager().getPigsList().remove(draw.getColisionManager().getPigsList().indexOf(draw.getCochon2()));

					pigsList.remove(pigsList.indexOf(3));
					draw.cochon2.setIcon("");
					draw.repaint();
				}

				else if (draw.getOiseau().getBirdX() < 20 || draw.getOiseau().getBirdX() > 780
						|| draw.getOiseau().getBirdY() < 0 || draw.getOiseau().getBirdY() > 480) {
					stop();
					message = "Perdu : cliquez pour recommencer.";
					draw.setMessage(message);
					draw.getCochon().setIcon("resources/pig2");
					draw.getCochon1().setIcon("resources/pig2");
					draw.getCochon2().setIcon("resources/pig2");
					draw.getOiseau().setIcon("resources/angrybird.png");
				}

				// redessine
				draw.repaint();
			}
		}

	}

	// met le jeu dans une fenêtre
	public static void main(String args[]) {
		Frame frame = new Frame("Oiseau pas content");
		final Game the_game = new Game();
		// final Drawing obj = new Drawing();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(the_game.draw);
		frame.pack();
		frame.setVisible(true);
	}

}
