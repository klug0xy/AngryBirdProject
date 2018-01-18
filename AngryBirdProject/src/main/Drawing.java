package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Drawing extends Panel implements MouseListener, MouseMotionListener {

	public Drawing() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		init();
	}

	boolean gameOver;
	boolean selecting;
	String message;
	int score;
	Image buffer;
	Pig cochon = new Pig();
	Pig cochon1 = new Pig();
	Pig cochon2 = new Pig();
	List<Pig> pigs = null;
	Bird oiseau = new Bird();
	int mouseX;
	int mouseY;

	public void init() {
		gameOver = false;
		selecting = true;
		message = "Choisissez l'angle et la vitesse.";
		oiseau.setBirdX(100);
		oiseau.setBirdY(400);
		oiseau.setVelocityX(0);
		oiseau.setVelocityY(0);
		cochon.setPigX(Math.random() * 500 + 200);
		cochon.setPigY(480);
		// cochon1.setPigX(Math.random() * 500 + 200);
		// cochon1.setPigY(480);
		// cochon2.setPigX(Math.random() * 500 + 200);
		// cochon2.setPigY(480);
		// pigs.add(cochon);
		// pigs.add(cochon2);
		// pigs.add(cochon1);

	}

	void stop() {
		oiseau.setVelocityX(0);
		oiseau.setVelocityY(0);
		gameOver = true;
	}

	ImageObserver modalComp = null;
	Graphics2D g;

	public void paint(Graphics g2) {

		if (buffer == null)
			buffer = createImage(800, 600);
		g = (Graphics2D) buffer.getGraphics();

		// fond
		BufferedImage nature = null;
		try {
			nature = ImageIO.read(new File("resources/nature.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(nature, 0, 0, getWidth(), getHeight(), modalComp);
		// décor
		g.drawLine(100, 500, 100, 400);

		// oiseau
		if (selecting)
			g.drawLine((int) oiseau.getBirdX(), (int) oiseau.getBirdY(), mouseX, mouseY);
		
		// montre l'angle et la vitesse
		BufferedImage angrybird = null;
		try {
			angrybird = ImageIO.read(new File("resources/angrybird.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(angrybird, (int) oiseau.getBirdX() - 20, (int) oiseau.getBirdY() - 20, 40, 40, modalComp);

		// cochon
		BufferedImage pig = null;
		try {
			pig = ImageIO.read(new File("resources/pig2"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(pig, (int) cochon.getPigX() - 20, (int) cochon.getPigY() - 20, 40, 40, modalComp);
		// g.drawImage( pig, (int) cochon1.getPigX() - 20, (int)
		// cochon.getPigY() - 20, 40, 40, modalComp);
		// g.drawImage( pig, (int) cochon2.getPigX() - 20, (int)
		// cochon.getPigY() - 20, 40, 40, modalComp);

		// messages
		g.setColor(Color.BLACK);
		g.drawString(message, 300, 100);
		g.drawString("score: " + score, 20, 20);

		// affichage à l'écran sans scintillement
		g2.drawImage(buffer, 0, 0, null);
	}

	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	public void update(Graphics g) {
		this.paint(g);
	}

	public Image getBuffer() {
		return buffer;
	}

	public void setBuffer(Image buffer) {
		this.buffer = buffer;
	}

	public Pig getCochon() {
		return cochon;
	}

	public void setCochon(Pig cochon) {
		this.cochon = cochon;
	}

	public Bird getOiseau() {
		return oiseau;
	}

	public void setOiseau(Bird oiseau) {
		this.oiseau = oiseau;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isSelecting() {
		return selecting;
	}

	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (gameOver) {
			init();
		} else if (selecting) {
			oiseau.setVelocityX((oiseau.getBirdX() - mouseX) / 20.0);
			oiseau.setVelocityY((oiseau.getBirdY() - mouseY) / 20.0);
			System.out.println(oiseau.getVelocityX());
			System.out.println(oiseau.getVelocityY());
			message = "L'oiseau prend sont envol";
			selecting = false;
		}
		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setPicPig() {
		g = (Graphics2D) buffer.getGraphics();
		BufferedImage pig = null;
		try {
			pig = ImageIO.read(new File("resources/angrypig.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(pig, (int) cochon.getPigX() - 20, (int) cochon.getPigY() - 20, 40, 40, modalComp);
	}

}
