package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import util.IDispatcher;
import util.IObserver;

/**
 * The concrete ball class
 * 
 * @author Ye Wang
 * @author Chen Zeng
 * @version 1.0
 * 
 */
public class Ball implements IObserver<IBallCmd> {

	/**
	 * The location of this ball, which is the center of the ball
	 */
	Point loc;

	/**
	 * The radius of the ball
	 */
	int rad;

	/**
	 * The color of the ball
	 */
	Color color;

	/**
	 * The vel of the ball
	 */
	Point vel;

	/**
	 * The canvas
	 */
	Component canvas;

	/**
	 * The new strategy of the ball.
	 */
	private IUpdateStrategy<IBallCmd> strategy;

	/**
	 * The paint strategy of this ball.
	 */

	private IPaintStrategy ipaint;

	public IInteractStrategy interactStrategy = IInteractStrategy.NULL_STRATEGY;

	/**
	 * Constructor for this Ball.
	 * @param _iniLoc The location of this Ball. The location represents the center point.
	 * @param _iniRad The radius of this Ball.
	 * @param _iniVel The velocity of this Ball.
	 * @param _iniColor The Color of this Ball.
	 * @param _canvas The Canvas related to this Ball
	 * @param _iUpdateStrategy The update strategy of the Ball.
	 * @param _ipaint The paint strategy of the Ball.
	 */
	public Ball(Point _iniLoc, int _iniRad, Point _iniVel, Color _iniColor, Component _canvas,
			IUpdateStrategy<IBallCmd> _iUpdateStrategy, IPaintStrategy _ipaint) {
		this.loc = _iniLoc;
		this.rad = _iniRad;
		this.vel = _iniVel;
		this.color = _iniColor;
		this.canvas = _canvas;
		this.strategy = _iUpdateStrategy;
		setPaint(_ipaint);
	}

	public void setLoc(Point loc) {
		this.loc = loc;
	}

	/**
	 *The bounce method when the ball reach the edge
	 */
	void bounce() {
		/*
		 * The ball reach the left
		 */
		if (loc.x - rad < 0) {
			vel.x *= -1;
			loc.x = rad * 2 - loc.x;
		}

		if (loc.y - rad < 0) {
			vel.y *= -1;
			loc.y = rad * 2 - loc.y;
		}

		if (loc.x + rad > canvas.getWidth()) {
			vel.x *= -1;
			loc.x = 2 * (canvas.getWidth() - rad) - loc.x;
		}

		if (loc.y + rad > canvas.getHeight()) {
			vel.y *= -1;
			loc.y = 2 * (canvas.getHeight() - rad) - loc.y;
		}
	}

	/**
	 * Update the properties of this ABall every time slice.
	 */
	@Override
	public void update(IDispatcher<IBallCmd> o, IBallCmd msg) {
		msg.apply(this, o);
	}

	/**
	 * Update the state of the ball.   Delegates to the update strategy.
	 * @param dispatcher The Dispatcher that sent the command that is calling this method.
	 */
	public void updateState(IDispatcher<IBallCmd> dispatcher) {
		strategy.updateState(this, dispatcher); // update this ball's state using the strategy.		
	}

	/**
	 * The movement of the ball
	 */
	void movingball() {
		move();
		bounce();
	}

	/**
	 * Change the loc of the ball
	 */
	void move() {
		loc.x += vel.x;
		loc.y += vel.y;
	}

	/**
	 * Paint this Ball.
	 * @param g The Graphics object to paint.
	 */
	public void paint(Graphics g) {
		ipaint.paint(g, this);
	}

	/**
	 * set the ball color
	 * @param color 
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Set the ball radius
	 * @param radius The radius of this Ball.
	 */
	public void setRadius(int radius) {
		this.rad = radius;
	}

	/**
	 * Get ball velocity which is used for update velocity
	 * @return velocity
	 */
	public Point getVel() {
		return this.vel;
	}

	/**
	 * Set ball velocity
	 * @param vel The velocity to be set.
	 */
	public void setVel(Point vel) {
		this.vel = vel;
	}

	/**
	 * @return rad
	 */
	public int getRadius() {
		// TODO Auto-generated method stub
		return this.rad;
	}

	/**
	 * @return location
	 */
	public Point getLocation() {
		// TODO Auto-generated method stub
		return this.loc;
	}

	/**
	 * @return color
	 */
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	/**
	 * @return paint strategy
	 */
	public IPaintStrategy getPaint() {
		return ipaint;
	}

	/**
	 * Set the paint strategy of this Ball.
	 * @param _ipaint The paint strategy of this ball.
	 */
	public void setPaint(IPaintStrategy _ipaint) {
		this.ipaint = _ipaint;
		ipaint.init(this);
	}

	/**
	 * Set the interact strategy of this Ball.
	 * @param interactStrategy The interact strategy of this ball.
	 */
	public void setInteractStrategy(IInteractStrategy interactStrategy) {
		this.interactStrategy = interactStrategy;
	}

	/**
	 * Get the interact strategy of this Ball.
	 * @return interactStrategy The interact strategy of this ball.
	 */
	public IInteractStrategy getInteractStrategy() {
		return interactStrategy;
	}

	/**
	 * @return canvas to paint
	 */
	public Component getCanvas() {
		// TODO Auto-generated method stub
		return canvas;
	}

	/**
	 * Interact between balls.
	 */
	public void interactWith(Ball target, IDispatcher<IBallCmd> dispatcher) {
		interactStrategy.interact(this, target, dispatcher);
	}

	/**
	 * Get the strategy of ball.
	 * @return strategy The strategy of the ball.
	 */
	public IUpdateStrategy<IBallCmd> getStrategy() {
		return strategy;
	}

	/**
	 * Set the strategy of ball.
	 * @param strategy The strategy of the ball.
	 */
	public void setStrategy(IUpdateStrategy<IBallCmd> strategy) {
		this.strategy = strategy;
		strategy.init(this);
	}
}
