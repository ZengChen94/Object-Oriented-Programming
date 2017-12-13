package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import model.strategy.IUpdateStrategy;

/**
 * Ball class for defining the basic ball
 * 
 * @author cz39
 * @author ker7
 */
public class Ball implements Observer {
	/**
	 * A Ball's location on a x/y plane
	 **/
	protected Point location;

	/**
	 * Radius of the ball.
	 */
	protected int radius;

	/**
	 * A Ball's speed on an x/y plane
	 **/
	protected Point velocity;

	/**
	 * A Ball's color represented as a "Color" object
	 **/
	protected Color color;

	/**
	 * The Ball's canvas 
	 * The location in the application where a ball is drawn
	 **/
	protected Component canvas;

	/**
	 * The update strategy of this ball.
	 */
	private IUpdateStrategy strategy;

	/**
	 * The constructor for the basic Ball
	 * 
	 * @param location A Ball's location on a x/y plane
	 * @param radius The radius of the ball.
	 * @param velocity A Ball's directional speed on an x/y plane
	 * @param color A Ball's color represented as a "Color" object
	 * @param canvas The Ball's canvas where it will be drawn.
	 * @param strategy The (variant) strategy of the ball.
	 */
	public Ball(Point location, int radius, Point velocity, Color color, Component canvas, IUpdateStrategy strategy) {
		setLocation(location);
		setRadius(radius);
		setVelocity(velocity);
		setColor(color);
		setCanvas(canvas);
		setStrategy(strategy);
	}

	/**
	 * Set the location of the ball on x/y Plane
	 * 
	 * @param location Location of the ball.
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * Set the radius of the ball
	 * 
	 * @param radius Radius of the ball.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Set the velocity of the ball on x/y Plane
	 * 
	 * @param velocity Velocity of the ball.
	 */
	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}

	/**
	 * Set the color of the ball
	 * 
	 * @param color Color of the ball.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Set the canvas of where the ball will be drawn
	 * 
	 * @param canvas Canvas of where the ball will be drawn
	 */
	public void setCanvas(Component canvas) {
		this.canvas = canvas;
	}

	/**
	 * Get the strategy of this Ball.
	 * 
	 * @param strategy The strategy of this Ball.
	 */
	public void setStrategy(IUpdateStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Get the location of the ball on an x/y plane
	 * 
	 * @return location Location of the ball.
	 */
	public Point getLocation() {
		return this.location;
	}

	/**
	 * Get the radius of the ball
	 * 
	 * @return radius Radius of the ball.
	 */
	public int getRadius() {
		return this.radius;
	}

	/**
	 * Get the velocity of the ball on an x/y plane
	 * 
	 * @return velocity Velocity of the ball.
	 */
	public Point getVelocity() {
		return this.velocity;
	}

	/**
	 * Get the color of the ball
	 * 
	 * @return color Color of the ball.
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Get the canvas of where the ball will be drawn
	 * 
	 * @return canvas Canvas of where the ball will be drawn
	 */
	public Component getCanvas() {
		return this.canvas;
	}

	/**
	 * Get the strategy of this Ball.
	 * 
	 * @return The strategy of this Ball.
	 */
	public IUpdateStrategy getStrategy() {
		return strategy;
	}

	/**
	 * Paint the canvas.
	 * 
	 * @param g The graphics object for painting
	 */
	public void paint(Graphics g) {
		g.setColor(getColor());
		g.fillOval(location.x - radius, location.y - radius, radius * 2, radius * 2);
	}

	/**
	 * Move the ball.
	 */
	public void move() {
		location.x += velocity.x;
		location.y += velocity.y;
	}

	/**
	 * Bounce the ball.
	 */
	public void bounce() {
		if (location.y - radius < 0) {
			velocity.y = velocity.y * -1;
			location.y = radius;
		}
		if (location.x - radius < 0) {
			velocity.x = velocity.x * -1;
			location.x = radius;
		}
		if (location.y + radius > canvas.getHeight()) {
			velocity.y = velocity.y * -1;
			location.y = canvas.getHeight() - radius;
		}
		if (location.x + radius > canvas.getWidth()) {
			velocity.x = velocity.x * -1;
			location.x = canvas.getWidth() - radius;
		}
	}

	/**
	 * Override the method of Observer.
	 * 
	 * @param arg Graphics.
	 */
	@Override
	public void update(Observable o, Object arg) {
		strategy.updateState(this);
		move();
		bounce();
		paint((Graphics) arg);
	}
}
