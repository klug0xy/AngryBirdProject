package main;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game implements Runnable {

	private Drawing draw = new Drawing();
	private String message;
	private int score;
	private Gravity gravity = new Gravity();
	private ColisionManager colisionManager = draw.getColisionManager();

	// constructeur
	Game() {
		draw.setScore(0);
		init();
		new Thread(this).start();
	}

	// début de partie
	public void init() {}

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
				draw.getOiseau().setX(draw.getOiseau().getX() + draw.getOiseau().getVelocityX());
				draw.getOiseau().setY(draw.getOiseau().getY() + draw.getOiseau().getVelocityY());
				draw.getOiseau().setVelocityY(draw.getOiseau().getVelocityY() + gravity.getValue());
				
				// bird velocity feedback
				if (draw.getOiseau().getVelocityX() < 2) {
					draw.getOiseau().setIcon("resources/slowbird.png");
					draw.repaint();
				} else if (draw.getOiseau().getVelocityX() > 5) {
					draw.getOiseau().setIcon("resources/bird.png");
					draw.repaint();
				}

				// conditions de victoire
				int res = colisionManager.detectColision(draw.getObjectList());
				if ( res == 1 ){
					stop();
					message = "Gagné : cliquez pour recommencer.";
					draw.setMessage(message);
					score++;
					draw.setScore(score);
					draw.repaint();
				} 
				
				else if (res == 2) {
					stop();
					message = "Oups : Visez bien.";
					draw.setMessage(message);
					draw.repaint();
				}

				else if (draw.getOiseau().getX() < 20 || draw.getOiseau().getX() > 780
						|| draw.getOiseau().getY() < 0 || draw.getOiseau().getY() > 480) {
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
		final Game theGame = new Game();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(theGame.draw);
		frame.pack();
		frame.setVisible(true);
	}

}
