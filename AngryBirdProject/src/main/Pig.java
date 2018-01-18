package main;

import java.awt.Image;

public class Pig extends Character {

	public Pig() {
		super();
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

	double pigX, pigY;
	Image img;

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Pig(double pigX, double pigY, Image img) {
		super();
		this.pigX = pigX;
		this.pigY = pigY;
		this.img = img;
	}

	public void add_image(Image img) {
		this.setImg(img);
	}

	public void generate_abscisse_ordonne(double birdX, double birdY) {
		this.setPigX(birdX);
		this.setPigY(birdY);
	}
}
