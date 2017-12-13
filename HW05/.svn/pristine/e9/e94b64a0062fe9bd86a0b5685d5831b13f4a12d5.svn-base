package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * A composite design pattern extension of APaintStrategy that paints a set of paint strategies. 
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class MultiPaintStrategy extends APaintStrategy {
	private APaintStrategy[] pstrats;

	/**
	 * The set of paint strategies to paint
	 * @param pstrats strategies set
	 */
	public MultiPaintStrategy(APaintStrategy... pstrats) {
		this(new AffineTransform(), pstrats);
	}

	/**
	 * create AffineTransform and ApaintStrategy instance
	 * @param at affineTransform object
	 * @param pstrats strategy set
	 */
	public MultiPaintStrategy(AffineTransform at, APaintStrategy... pstrats) {
		// TODO Auto-generated constructor stub
		super(at);
		this.pstrats = pstrats;
	}

	/* (non-Javadoc)
	 * @see model.paint.APaintStrategy#init(model.CBall)
	 */
	@Override
	public void init(Ball host) {
		for (APaintStrategy pstrat : pstrats) {
			pstrat.init(host);
		}
	}

	/* (non-Javadoc)
	 * @see model.paint.APaintStrategy#paintXfrm(java.awt.Graphics, model.CBall, java.awt.geom.AffineTransform)
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		// TODO Auto-generated method stub
		for (APaintStrategy pstrat : pstrats) {
			pstrat.paintXfrm(g, host, at);
		}
	}
}
