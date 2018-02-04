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
	
	public int typeColision (Objectt object1, Objectt object2) {
		int type = 0;
		
		if ( (object1 instanceof Bird && object2 instanceof Pig) 
			|| (object1 instanceof Pig && object2 instanceof Bird ) ) {
			type = 1;
		}
		else if ( (object1 instanceof Bird && object2 instanceof BlackHole) 
			|| (object1 instanceof BlackHole && object2 instanceof Bird ) ){
			type = 2;
		}
		return type;
	}
	
	// 1 = oiseau*cochon
	// 2 = oiseau*trou
	public int detectColision(List<Objectt> objectList) {
		int result = 0;
		for (int i = 0 ; i < objectList.size() - 1 ; i++ ) {
			for (int j = i+1 ; j < objectList.size() ; j++) {
				if (checkColision(objectList.get(i), objectList.get(j))) {
					result = typeColision(objectList.get(i), objectList.get(j));
					onColision(objectList.get(i), objectList.get(j), result);
				}
			}
		}
		
		return result;
	}
	
	public void onColision(Objectt object1, Objectt object2 , int typeColision ) {
		
		if (typeColision == 1) {
			if ( object1 instanceof Bird) {
				object1.setIcon("resources/birdlaugh.png");
				object2.setIcon("resources/angrypig.png");
			}
			else {
				object1.setIcon("resources/angrypig.png");
				object2.setIcon("resources/birdlaugh.png");
			}
		}
		
		else if (typeColision == 2) {
			if ( object1 instanceof Bird) {
				object1.setIcon("resources/slowbird.png");
				object1.setVelocityX(0);
			}
			else { 
				object2.setIcon("resources/slowbird.png");
				object2.setVelocityX(0);
			}
		}
	}

	public List<Objectt> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Objectt> objectList) {
		this.objectList = objectList;
	}

}
