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
import java.util.LinkedList;
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
	
	private ObjectFactory objectFactory = new ObjectFactory();
	private Objectt oiseau = objectFactory.getObject("Bird");
	private Objectt cochon = objectFactory.getObject("Pig");
	private Objectt cochon1 = objectFactory.getObject("Pig");
	private Objectt cochon2 = objectFactory.getObject("Pig");
	private Objectt trou = objectFactory.getObject("BlackHole");
	
	private int mouseX;
	private int mouseY;
	
	ColisionManager colisionManager = new ColisionManager();
	List<Objectt> objectList = new LinkedList<Objectt>();

	public void init() {
		gameOver = false;
		selecting = true;
		message = "Choisissez l'angle et la vitesse.";
		oiseau.setX(100);
		oiseau.setY(400);
		oiseau.setVelocityX(0);
		oiseau.setVelocityY(0);
		cochon.setX(Math.random() * 500 + 200);
		cochon.setY(480);
		cochon1.setX(Math.random() * 500 + 200);
		cochon1.setY(480);
		cochon2.setX(Math.random() * 500 + 200);
		cochon2.setY(480);
		trou.setX(Math.random()* 500);
		trou.setY(Math.random()* 500);

		colisionManager.addObject(cochon);
		colisionManager.addObject(cochon1);
		colisionManager.addObject(cochon2);
		colisionManager.addObject(oiseau);
		colisionManager.addObject(trou);
		
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
			g.drawLine((int) oiseau.getX(), (int) oiseau.getY(),
					mouseX, mouseY);
		
		// montre l'angle et la vitesse

//		g.drawImage(oiseau.getIcon(), (int) oiseau.getX() - 20,
//				(int) oiseau.getY() - 20, 40, 40, modalComp);
		
		for (int i = 0;i<colisionManager.getObjectList().size();i++){
			
			//System.out.println(colisionManager.getPigsList().get(i));
			
			g.drawImage(colisionManager.getObjectList().get(i).getIcon(), 
					(int) colisionManager.getObjectList().get(i).getX() - 20, 
					(int) colisionManager.getObjectList().get(i).getY() - 20, 40,
					40, modalComp);
		}
		
//		g.drawImage(cochon.getIcon(), (int) cochon.getPigX() - 20, (int) 
//		cochon.getPigY() - 20, 40, 40, modalComp);
//		
//		g.drawImage( cochon1.getIcon(), (int) cochon1.getPigX() - 20, (int)
//		cochon1.getPigY() - 20, 40, 40, modalComp);
//		
//		g.drawImage( cochon2.getIcon(), (int) cochon2.getPigX() - 20, (int)
//		cochon2.getPigY() - 20, 40, 40, modalComp);

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

	public Objectt getCochon() {
		return cochon;
	}

	public void setCochon(Objectt cochon) {
		this.cochon = cochon;
	}

	public Objectt getOiseau() {
		return oiseau;
	}

	public void setOiseau(Objectt oiseau) {
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
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (gameOver) {
			objectList.clear();
			init();
		} else if (selecting) {
			oiseau.setVelocityX((oiseau.getX() - mouseX) / 20.0);
			oiseau.setVelocityY((oiseau.getY() - mouseY) / 20.0);
			message = "L'oiseau prend sont envol";
			selecting = false;
		}
		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	public Objectt getCochon1() {
		return cochon1;
	}

	public void setCochon1(Pig cochon1) {
		this.cochon1 = cochon1;
	}

	public Objectt getCochon2() {
		return cochon2;
	}

	public void setCochon2(Pig cochon2) {
		this.cochon2 = cochon2;
	}

	public ColisionManager getColisionManager() {
		return colisionManager;
	}

	public void setColisionManager(ColisionManager colisionManager) {
		this.colisionManager = colisionManager;
	}

	public List<Objectt> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Objectt> objectList) {
		this.objectList = objectList;
	}
}
