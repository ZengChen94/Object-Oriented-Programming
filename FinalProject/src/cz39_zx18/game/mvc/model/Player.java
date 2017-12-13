package cz39_zx18.game.mvc.model;

import java.io.Serializable;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3603574217425717666L;
	private double posLat;
	private double posLon;
	private String name;
	private int team;
	private String action;
	private String actionInfo;
	private double attackLat;
	private double attackLon;
	private boolean survive;
	private int direction;

	/**
	 * Constructor
	 * @param name the name of player
	 * @param posLat the latitude of player
	 * @param posLon the longitude of player
	 * @param team the team of player
	 */
	public Player(String name, double posLat, double posLon, int team) {
		this.name = name;
		this.posLat = posLat;
		this.posLon = posLon;
		this.team = team;
		this.survive = true;
	}

	/**
	 * Get the action of player
	 * @return action the action of player
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Set the action of player
	 * @param action the action of player
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Get the direction of player
	 * @return direction the direction of player
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Set the direction of player
	 * @param direction the direction of player
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Get the name of player
	 * @return name the name of player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of player
	 * @param name the name of player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the latitude of position
	 * @return posLat the latitude of position
	 */
	public double getPosLat() {
		return this.posLat;
	}

	/**
	 * Set the latitude of position
	 * @param posLat the latitude of position
	 */
	public void setPosLat(double posLat) {
		this.posLat = posLat;
	}

	/**
	 * Get the longitude of position
	 * @return posLon the longitude of position
	 */
	public double getPosLon() {
		return this.posLon;
	}

	/**
	 * Set the longitude of position
	 * @param posLon the longitude of position
	 */
	public void setPosLon(double posLon) {
		this.posLon = posLon;
	}

	/**
	 * Get the position text
	 * @return string the position text
	 */
	public String getPosText() {
		return this.posLat + "," + this.posLon;
	}

	/**
	 * Get the team
	 * @return team the team
	 */
	public int getTeam() {
		return this.team;
	}

	/**
	 * Set the team
	 * @param team the team
	 */
	public void setTeam(int team) {
		this.team = team;
	}

	/**
	 * Get the action information
	 * @return actionInfo the action information
	 */
	public String getActionInfo() {
		return this.actionInfo;
	}

	/**
	 * Set the action information
	 * @param actionInfo the action information
	 */
	public void setActionInfo(String actionInfo) {
		this.actionInfo = actionInfo;
	}

	/**
	 * move
	 */
	public void move() {
		//		String info;
		double latitude = this.getPosLat(), longitude = this.getPosLon();
		switch (this.direction) {
		case 0:
			//			info = "North";
			latitude += 4;
			break;
		case 1:
			//			info = "South";
			latitude -= 4;
			break;
		case 2:
			//			info = "West";
			longitude -= 4;
			break;
		default:
			//			info = "East";
			longitude += 4;
			break;
		}
		if (latitude > 90)
			latitude = latitude - 180;
		if (latitude < -90)
			latitude = latitude + 180;
		if (longitude > 180)
			longitude = longitude - 360;
		if (longitude < -180)
			longitude = longitude + 360;
		//		setPosition(new Position(this.pos.getLatitude().add(Angle.fromDegrees(latitude)), this.pos.getLongitude().add(Angle.fromDegrees(longitude)), 0));
		setPosLat(latitude);
		setPosLon(longitude);
		System.out.println(latitude + "," + longitude);
		setActionInfo(this.name + "@Team" + this.team + " moves to " + latitude + "," + longitude + "\n");
	}

	/**
	 * Set the latitude and longitude of attack
	 * @param attackLat the latitude of attack
	 * @param attackLon the longitude of attack
	 */
	public void setAttack(double attackLat, double attackLon) {
		this.attackLat = attackLat;
		this.attackLon = attackLon;
		setActionInfo(this.name + "@Team" + this.team + " attacks " + this.attackLat + ", " + this.attackLon + "\n");
	}

	/**
	 * Get the latitude of attack
	 * @return latitude the latitude of attack
	 */
	public double getAttackLat() {
		return this.attackLat;
	}

	/**
	 * Get the longitude of attack
	 * @return longitude the longitude of attack
	 */
	public double getAttackLon() {
		return this.attackLon;
	}

	/**
	 * Set the survival
	 * @param survive the survival of player
	 */
	public void setSurvive(boolean survive) {
		this.survive = survive;
	}

	/**
	 * Get the survival
	 * @return survival the survival of player
	 */
	public boolean getSurvive() {
		return this.survive;
	}

}
