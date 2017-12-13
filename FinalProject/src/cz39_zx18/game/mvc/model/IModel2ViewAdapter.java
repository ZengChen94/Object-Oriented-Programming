package cz39_zx18.game.mvc.model;

import java.io.Serializable;

import common.ICRMessageType;
import map.MapLayer;

public interface IModel2ViewAdapter extends Serializable {
	/**
	 * Add place
	 * @param p the place to be added
	 */
	public void addPlace(Place p);

	/**
	 * Show mapLayer
	 * @param layer the layer to be showed
	 */
	public void show(MapLayer layer);

	/**
	 * Hide mapLayer
	 * @param layer the layer to be hided
	 */
	public void hide(MapLayer layer);

	public <T extends ICRMessageType> void sendTo(Class<T> class1, T g);

	/**
	 * Enable button to be clickable
	 */
	public void enableButton();

	/**
	 * update team information
	 * @param team1 Alive number of team1
	 * @param team2 Alive number of team2
	 */
	public void updateTeam(int team1, int team2);

	/**
	 * Update winner
	 * @param winner the winner of game
	 */
	public void updateWinner(int winner);

	/**
	 * Clear time
	 */
	public void clearTime();

	public void append(String actionInfo);
}
