package cz39_zx18.game.utils;

import common.ICRMessageType;
import common.IUserMessageType;
import cz39_zx18.game.mvc.model.Player;

public class GameAction implements IUserMessageType, ICRMessageType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 749377557813092295L;
	private Player player;

	public GameAction(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
