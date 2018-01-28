package main;

import java.util.LinkedList;
import java.util.List;

public class ColisionManager {
	
	private List<Objectt> objectList = new LinkedList<Objectt>();

	public ColisionManager() {}

	// calcule la distance entre deux points
	private double distance(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public boolean checkColision(Objectt object1, Objectt object2) {
		boolean colision = false;
		if (this.distance(object1.getX(), object1.
				getY(), object2.getX(), 
				object2.getY()) < 35) {colision = true;}
		
		return colision;
		
	}
	
	public void onColision(Objectt object1, Objectt object2/*, int pigIndex*/) {
		
		object1.setIcon("resources/birdlaugh.png");
		object2.setIcon("resources/angrypig.png");
		//pigsList.remove(pigIndex);
	}
	
	public void addObject(Objectt object){
	
			objectList.add(object);
	}

	public List<Objectt> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Objectt> objectList) {
		this.objectList = objectList;
	}

}
