package cz39_zx18.server.utils;

import java.util.List;

import common.IChatRoom;
import cz39_zx18.client.utils.ILocalCmd2ModelAdapter;
import cz39_zx18.game.mvc.model.Player;

/**
 * IServerCRCmd2ModelAdapter
 */
public interface IServerCRCmd2ModelAdapter extends ILocalCmd2ModelAdapter {
	/**
	 * A list of chat rooms
	 * @return a list of chat rooms
	 */
	public List<IChatRoom> getChatRooms();

	/**
	 * Add Player
	 * @param player the object of player to be sent
	 */
	public void addPlayer(Player player);
}