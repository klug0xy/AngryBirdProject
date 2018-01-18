package main;
import java.awt.Image;

public class Bird extends Character{

	public Bird() {
		super();
		
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

	double birdX, birdY, velocityX, velocityY;
	Image img;


	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Bird(double birdX, double birdY, double velocityX, double velocityY, Image img) {
		super();
		this.birdX = birdX;
		this.birdY = birdY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.img = img;
	}

	public void add_image(Image img) {
		this.setImg(img);
	}

	public void generate_abscisse_ordonne(double birdX, double birdY) {
		this.setBirdX (birdX);
		this.setBirdY (birdY);
	}

	

}
