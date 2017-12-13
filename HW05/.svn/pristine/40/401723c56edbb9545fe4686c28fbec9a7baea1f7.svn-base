package model.paint;

import model.CBall;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

/**
 * Image paint strategy
 * This APaintStrategy subclass adds specialized init and paintXfrm methods to load the image and 
 * calculate a secondary affine transform that is used to help create the the required additional scaling needed convert 
 * the image file into a prototype.  
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class ImagePaintStrategy extends APaintStrategy {

	/**
	 * The percentage of the average of the width and height of the image
	 * that defines a unit radius for the image.
	 */
	private double fillFactor;
	/**
	 * The image to paint
	 */
	private Image image;
	/**
	 * Ratio of the unit radius circle to the effective radius size of the image.
	 */
	private double scaleFactor;
	/**
	 * ImageObserver needed for some image operations
	 */
	private ImageObserver imageObs;
	/**
	 * A local affine transform used to transform the image into its unit size and location.
	 */
	protected AffineTransform localAT = new AffineTransform();
	/**
	 * 
	 */
	protected AffineTransform tempAT;

	/**
	 * construct with an AffineTransform instance
	 * @param at AffineTransform object
	 * @param filename to load
	 * @param fillFactor scale
	 */
	public ImagePaintStrategy(AffineTransform at, String filename, double fillFactor) {
		super(at);
		this.fillFactor = fillFactor;
		try {
			this.image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(filename));

		} catch (Exception e) {
			System.err.println("ImagePaintStrategy: Error reading file: " + filename + "\n" + e);
		}
	}

	/**
	 * construct without an AffineTransform instance
	 * @param filename image filename to load
	 * @param fillFactor he ratio of the desired average radius of the image
	 * 		  to the actual average of the image's width and height.
	 */
	public ImagePaintStrategy(String filename, double fillFactor) {
		this(new AffineTransform(), filename, fillFactor);
	}

	/* (non-Javadoc)
	 * @see model.paint.APaintStrategy#init(model.CBall)
	 */
	@SuppressWarnings("javadoc")
	@Override
	public void init(CBall host) {
		imageObs = host.getCanvas();
		MediaTracker mt = new MediaTracker(host.getCanvas());
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			System.out.println("ImagePaintStrategy.init(): Error waiting for image.  Exception = " + e);
		}

		scaleFactor = 2.0 / (fillFactor * (image.getWidth(imageObs) + image.getHeight(imageObs)) / 2.0);
	}

	/* (non-Javadoc)
	 * @see model.paint.APaintStrategy#paintXfrm(java.awt.Graphics, model.CBall, java.awt.geom.AffineTransform)
	 */
	@SuppressWarnings("javadoc")
	@Override
	public void paintXfrm(Graphics g, CBall host, AffineTransform at) {
		localAT.setToScale(scaleFactor, scaleFactor);

		localAT.translate(-image.getWidth(imageObs) / 2.0, -image.getHeight(imageObs) / 2.0);
		localAT.preConcatenate(at);
		((Graphics2D) g).drawImage(image, localAT, imageObs);
	}

}
