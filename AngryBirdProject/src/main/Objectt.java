package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Objectt {
	
	private double x;
	private double y;
	private double velocityX;
	private double velocityY;
	private Image icon;
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
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
}
