package main;

import java.awt.Frame;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game implements Runnable {

	Drawing draw = new Drawing();
	String message;
	int score;
	double gravity;

	Image buffer;

	// calcule la distance entre deux points
	static double distance(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	// constructeur
	Game() {
		gravity = 0.1;
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
				draw.getOiseau().setVelocityY(draw.getOiseau().getVelocityY() + gravity);

				// conditions de victoire
				if (distance(draw.getOiseau().getBirdX(), draw.getOiseau().getBirdY(), draw.getCochon().getPigX(), draw.getCochon().getPigY()) < 35) {
					stop();
					message = "Gagné : cliquez pour recommencer.";
					draw.setMessage(message);
					score++;
					draw.setScore(score);
					draw.setPicPig();
					draw.repaint();
				} else if (draw.getOiseau().getBirdX() < 20 || draw.getOiseau().getBirdX() > 780 || draw.getOiseau().getBirdY() < 0
						|| draw.getOiseau().getBirdY() > 480) {
					stop();
					message = "Perdu : cliquez pour recommencer.";
					draw.setMessage(message);
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
		//final Drawing obj = new Drawing();
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
