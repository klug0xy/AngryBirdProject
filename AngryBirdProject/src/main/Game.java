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
	List<Objectt> objectList = colisionManager.getObjectList();

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
				draw.getOiseau().setX(draw.getOiseau().getX() + draw.getOiseau().getVelocityX());
				draw.getOiseau().setY(draw.getOiseau().getY() + draw.getOiseau().getVelocityY());
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
					colisionManager.onColision(draw.getOiseau(), draw.getCochon()/*,
							pigsList.indexOf(draw.getCochon())*/);
					
					//draw.getColisionManager().getPigsList().remove(draw.getColisionManager().getPigsList().indexOf(draw.getCochon()));
					System.out.println("L'index du premier cochon est : "+objectList.indexOf(draw.getCochon()));
					objectList.remove(objectList.indexOf(draw.getCochon()));
					draw.setObjectList(objectList);
					//draw.getCochon().setIcon("");

					draw.repaint();

				} else if (colisionManager.checkColision(draw.getOiseau(), draw.getCochon1())) {
					stop();
					message = "Gagné : cliquez pour recommencer.";
					draw.setMessage(message);
					score++;
					draw.setScore(score);

					colisionManager.onColision(draw.getOiseau(), draw.getCochon1()/*,
							draw.getColisionManager().getPigsList().indexOf(draw.getCochon1())*/);
					//draw.getColisionManager().getPigsList().remove(draw.getColisionManager().getPigsList().indexOf(draw.getCochon1()));
					
					System.out.println("L'index du deuxieme cochon est : "+objectList.indexOf(draw.getCochon1()));

					objectList.remove(objectList.indexOf(draw.getCochon1()));
					draw.setObjectList(objectList);
					//draw.getCochon1().setIcon("");
					draw.repaint();

				} else if (colisionManager.checkColision(draw.getOiseau(), draw.getCochon2())) {
					stop();
					message = "Gagné : cliquez pour recommencer.";
					draw.setMessage(message);
					score++;
					draw.setScore(score);

					colisionManager.onColision(draw.getOiseau(), draw.getCochon2()/*,
							pigsList.indexOf(draw.getCochon2())*/);
					
					//draw.getColisionManager().getPigsList().remove(draw.getColisionManager().getPigsList().indexOf(draw.getCochon2()));

					System.out.println("L'index du troisieme cochon est : "+objectList.indexOf(draw.getCochon2()));
					objectList.remove(objectList.indexOf(draw.getCochon2()));
					draw.setObjectList(objectList);
					//draw.getCochon2().setIcon("");
					draw.repaint();
				}

				else if (draw.getOiseau().getX() < 20 || draw.getOiseau().getX() > 780
						|| draw.getOiseau().getY() < 0 || draw.getOiseau().getY() > 480) {
					stop();
					message = "Perdu : cliquez pour recommencer.";
					draw.setMessage(message);
					
//					for (int i = 0;i<objectList.size();i++){
//						
//						objectList.get(i).setIcon("resources/pig2"); 
//					}
//					
//					draw.getOiseau().setIcon("resources/angrybird.png");
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
