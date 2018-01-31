package main;

public class ObjectFactory {

	public Objectt getObject(String objectType) {
		if (objectType.equalsIgnoreCase("Bird")) {
			Bird bird = new Bird();
			return bird;
		} else if (objectType.equalsIgnoreCase("Pig")) {
			
			return new Pig();
		} else if (objectType.equalsIgnoreCase("BlackHole")) {
			return new BlackHole();
		}
		
		return null;
	}
}
