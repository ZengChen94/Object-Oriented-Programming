package cz39_zx18.server.mvc.model.cmd;

import javax.swing.JOptionPane;

import common.ICRMessageType;
import common.IChatRoom;
import common.IUserMessageType;

/**
 * This class is used for selecting team
 */

public class SelectTeam implements ICRMessageType, IUserMessageType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6897483871246849540L;
	private IChatRoom room;
	private Object[] chatRooms;

	/**
	 * Select team which is also a chatroom
	 * @param room the team to be joined in
	 */
	public SelectTeam(IChatRoom room) {
		this.room = room;
	}

	/**
	 * Get team which is also a chatroom
	 * @return room the team to be joined in
	 */
	public IChatRoom getRoom() {
		return room;
	}

	/**
	 * Set team which is also a chatroom
	 * @param room the team to be joined in
	 */
	public void setRoom(IChatRoom room) {
		this.room = room;
	}

}
