package cz39_zx18.server.mvc.model;

import common.IChatRoom;
import common.IUser;
import cz39_zx18.client.mvc.model.MiniMVCAdapter;
import cz39_zx18.client.utils.ProxyUser;
import cz39_zx18.client.utils.User;
import cz39_zx18.server.utils.ServerReceiver;

/**
 * MVC model2view adapter 
 */
public interface IServerModel2ViewAdapter {

	/**
	 * Create a mini mvc corresponding to a chat room
	 * @param chatRoom the chat room corresponding to the mini mvc
	 * @param user local user
	 * @param receiver the local receiver corresponding to a chat room 
	 * @return MiniMVCAdapter talking to this mini mvc 
	 */
	MiniMVCAdapter<ProxyUser> makeMiniMVC(IChatRoom chatRoom, User user, ServerReceiver receiver);

	//	/**
	//	 * Append text to the view
	//	 * @param text text to add
	//	 */
	//	public void append(String text);

	/**
	 * Set user name in the view
	 * @param name name of user
	 */
	public void setUserName(String name);

	/**
	 * Set server name in the view
	 * @param localAddr server name(local ip address) to be set 
	 */
	public void setServerName(String localAddr);

	/**
	 * Add a connected user in the view
	 * @param userStub the user stub to add
	 */
	public void addConnected(IUser userStub);
}
