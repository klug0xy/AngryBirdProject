package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pig {

	double pigX, pigY;
	Image icon;

	public Pig() {
		super();
		
		this.setIcon("resources/pig2");
	}

	public Pig(double pigX, double pigY, Image icon) {
		super();
		this.pigX = pigX;
		this.pigY = pigY;
		this.icon = icon;
	}

	public double getPigX() {
		return pigX;
	}

	public void setPigX(double pigX) {
		this.pigX = pigX;
	}

	public double getPigY() {
		return pigY;
	}

	public void setPigY(double pigY) {
		this.pigY = pigY;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(String iconPath) {
		BufferedImage updatedIcon = null;
		try {
			updatedIcon = ImageIO.read(new File(iconPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.icon = updatedIcon;
	}

	// public void setPicPig() {
	// g = (Graphics2D) buffer.getGraphics();
	// BufferedImage pig = null;
	// try {
	// pig = ImageIO.read(new File("resources/angrypig.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// g.drawImage(pig, (int) cochon.getPigX() - 20, (int) cochon.getPigY() -
	// 20, 40, 40, modalComp);
	// }

}
