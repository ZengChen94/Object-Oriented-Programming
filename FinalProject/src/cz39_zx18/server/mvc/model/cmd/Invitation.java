package cz39_zx18.server.mvc.model.cmd;

import common.IChatRoom;
import common.datatype.user.IInvitationType;

/**
 * Invitation type
 */
public class Invitation implements IInvitationType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7139520298433388531L;
	private IChatRoom chatRoom;

	/**
	 * Constructor for Invitation
	 * @param chatRoom the chat room in the invitation
	 */
	public Invitation(IChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

	@Override
	public IChatRoom getChatRoom() {
		return chatRoom;
	}

}
