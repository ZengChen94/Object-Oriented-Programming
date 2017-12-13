package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class SpecificShape implements a shape of arc.
 * Class SpecificShape is a class extended from Class AShape.
 * 
 * @author Chen Zeng, cz39@rice.edu
 * @author Yiqing Lu, yl128@rice.edu
 * @version 1.0.20170826.1
 * @since 1.0.20170826.1
 *
 */

public class SpecificShape extends AShape {
	/**
	 * The start angle and end (end-start) of the Arc.
	 */
	private int startAngle, arcAngle;

	/**
	 * Constructor of Arc.
	 * @param x_position The x-coordinate of this Arc.
	 * @param y_position The y-coordinate of this Arc.
	 * @param width The width in pixels of this Arc.
	 * @param height The height in pixels of this Arc.
	 * @param color The color of this Arc.
	 * @param startAngle The start angle of this Arc
	 * @param arcAngle The (end-start) angle of this Arc
	 */
	public SpecificShape(int x_position, int y_position, int width, int height, int startAngle, int arcAngle,
			Color color) {
		this.x_position = x_position;
		this.y_position = y_position;
		this.color = color;
		this.width = width;
		this.height = height;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
	}

	/**
	 * Set the value of startAngle of this AShape.
	 * @param value The startAngle of this AShape
	 */
	public void set_startAngle(int value) {
		this.startAngle = value;
	}

	/**
	 * Set the value of srcAngle of this AShape.
	 * @param value The srcAngle of this AShape
	 */
	public void set_arcAngle(int value) {
		this.arcAngle = value;
	}

	/**
	 * @return Get the value of startAngle of this AShape.
	 */
	public int get_startAngle() {
		return this.startAngle;
	}

	/**
	 * @return Get the value of arcAngle of this AShape.
	 */
	public int get_arcAngle() {
		return this.arcAngle;
	}

	/**
	 * Override the paint method.
	 * Paint a width*height arc with degree in (startAngle, startAngle+arcAngle) at (x_position, y_position) and fill the arc in its color.
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillArc(x_position, y_position, width, height, startAngle, arcAngle);
	}
}
