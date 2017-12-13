package model.paint.strategy;

import model.paint.ImagePaintStrategy;
import util.Randomizer;

/**
 * Generate a planet image randomly
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class PlanetImagePaintStrategy extends ImagePaintStrategy {

	/**
	 * @return a random planet image
	 */
	private static String randomImg() {
		switch (Randomizer.Singleton.randomInt(1, 5)) {
		case 1:
			return "../image/Earth.png";
		case 2:
			return "../image/Mars.png";
		case 3:
			return "../image/Jupiter.png";
		case 4:
			return "../image/Venus.png";
		case 5:
			return "../image/Saturn.png";
		default:
			return "../image/Earth.png";
		}
	}

	/**
	 * The percentage of the average of the width and 
	 * height of the image that defines a unit radius for the image.
	 */
	private static double fillFactor = 0.75;

	/**
	 * default constructor
	 */
	public PlanetImagePaintStrategy() {
		super(randomImg(), fillFactor);
	}
}
