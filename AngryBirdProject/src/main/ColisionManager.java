package main;

import java.util.LinkedList;
import java.util.List;

public class ColisionManager {
	
	List<Bird> birdsList = new LinkedList<Bird>();
	List<Pig> pigsList = new LinkedList<Pig>();

	public ColisionManager() {
	}

	// calcule la distance entre deux points
	double distance(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	boolean checkColision(Bird bird, Pig pig) {
		boolean colision = false;
		if (this.distance(bird.getBirdX(), bird.
				getBirdY(), pig.getPigX(), 
				pig.getPigY()) < 35) {colision = true;}
		
		return colision;
		
	}
	
	void onColision(Bird bird, Pig pig, int pigIndex) {
		
		bird.setIcon("resources/birdlaugh.png");
		pig.setIcon("resources/angrypig.png");
		//pigsList.remove(pigIndex);
	}
	
	void addObject(Object object){
		if (object instanceof Bird){
			Bird bird = (Bird) object;
			birdsList.add(bird);
		}
		else if (object instanceof Pig){
			Pig pig = (Pig) object;
			pigsList.add(pig);
		}
	}

	public List<Bird> getBirdsList() {
		return birdsList;
	}

	public void setBirdsList(List<Bird> birdsList) {
		this.birdsList = birdsList;
	}

	public List<Pig> getPigsList() {
		return pigsList;
	}

	public void setPigsList(List<Pig> pigsList) {
		this.pigsList = pigsList;
	}

}
