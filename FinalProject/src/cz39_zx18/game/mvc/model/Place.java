package cz39_zx18.game.mvc.model;

import java.io.Serializable;

import gov.nasa.worldwind.geom.Position;

public class Place implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3331739339351515868L;
	private String _name;
	private Position _pos;

	/**
	 * Constructor
	 * @param name the name of place
	 * @param pos the position of place
	 */
	public Place(String name, Position pos) {
		_name = name;
		_pos = pos;
	}

	/**
	 * Get the position
	 * @return _pos the position of place
	 */
	public Position getPosition() {
		return _pos;
	}

	/**
	 * Get the name
	 * @return _name the name of place
	 */
	public String toString() {
		return _name;
	}
}
