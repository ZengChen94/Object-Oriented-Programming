package model;

import java.awt.Graphics;

/**
 * The IPaintStrategy class for painting
 * 
 * @author Ye Wang
 * @author Chen Zeng
 * @version 1.0
 * 
 */
public interface IPaintStrategy {
	/**
	 * @param host ball object
	 */
	public void init(Ball host);

	/**
	 * @param g paint
	 * @param host ball object
	 */
	public void paint(Graphics g, Ball host);

	/**
	 * does nothing
	 */
	public static final IPaintStrategy NULL_OBJECT = new IPaintStrategy() {
		public void init(Ball host) {
		}

		public void paint(Graphics g, Ball host) {
		}
	};

}
