package main;

import java.awt.Image;
import java.awt.Toolkit;


public class CharacterFactory {
	Image img;

	public Character getCharacter(String characterType) {
		if (characterType.equalsIgnoreCase("Bird")) {
			img = Toolkit.getDefaultToolkit().getImage("resources/images/oiseau.gif");
			return new Bird(100.0, 400.0, 0.0, 0.0, img);
		} else if (characterType.equalsIgnoreCase("Pig")) {
			img = Toolkit.getDefaultToolkit().getImage("resources/images/cochon.gif");
			return new Pig((Math.random() * 500 + 200), 480.0, img);
		}
		return null;
	}
}
