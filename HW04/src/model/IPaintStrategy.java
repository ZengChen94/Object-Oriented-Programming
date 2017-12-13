package model;

import java.awt.Graphics;

/**
 * The IPaintStrategy class for painting
 * 
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 * 
 */
public interface IPaintStrategy {
	/**
	 * @param host ball object
	 */
	public void init(CBall host);

	/**
	 * @param g paint
	 * @param host ball object
	 */
	public void paint(Graphics g, CBall host);

	/**
	 * does nothing
	 */
	public static final IPaintStrategy NULL_OBJECT = new IPaintStrategy() {
		public void init(CBall host) {
		}

		public void paint(Graphics g, CBall host) {
		}
	};

}
