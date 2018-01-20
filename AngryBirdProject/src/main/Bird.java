package main;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
	
	double birdX, birdY, velocityX, velocityY;
	Image icon;

	public Bird() {
		super();
		this.setIcon("resources/angrybird.png");
	}
	
	public double getBirdX() {
		return birdX;
	}

	public void setBirdX(double birdX) {
		this.birdX = birdX;
	}

	public double getBirdY() {
		return birdY;
	}

	public void setBirdY(double birdY) {
		this.birdY = birdY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;

	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(String iconPath) {
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(new File(iconPath));
		} catch (IOException e) {
			//e.printStackTrace();
		}
		this.icon = icon;
	}

	public Bird(double birdX, double birdY, double velocityX, double velocityY, Image img) {
		super();
		this.birdX = birdX;
		this.birdY = birdY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.icon = icon;
	}
}
