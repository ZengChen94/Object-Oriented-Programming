package cz39_zx18.game.utils;

import java.util.ArrayList;
import java.util.Map;

import common.ICRMessageType;
import common.IUserMessageType;
import cz39_zx18.game.mvc.model.Player;
import gov.nasa.worldwind.geom.Position;

public class GameResult implements IUserMessageType, ICRMessageType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8498557945651486206L;
	private Map<String, Player> playerMap;
	private String result;
	private ArrayList<Position> positions;
	private ArrayList<String> totalInfo;
	private Map<String, Boolean> survivals;

	/**
	 * Constructor
	 * @param playerMap playerMap to be sent
	 */
	public GameResult(Map<String, Player> playerMap) {
		this.playerMap = playerMap;
	}

	/**
	 * Get the result string
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Set the result string
	 * @param result the result of the game
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}

	public ArrayList<String> getTotalInfo() {
		return totalInfo;
	}

	public void setTotalInfo(ArrayList<String> totalInfo) {
		this.totalInfo = totalInfo;
	}

	public Map<String, Boolean> getSurvivals() {
		return survivals;
	}

	public void setSurvivals(Map<String, Boolean> survivals) {
		this.survivals = survivals;
	}

	public Map<String, Player> getPlayerMap() {
		return playerMap;
	}

	public void setPlayerMap(Map<String, Player> playerMap) {
		this.playerMap = playerMap;
	}
}
