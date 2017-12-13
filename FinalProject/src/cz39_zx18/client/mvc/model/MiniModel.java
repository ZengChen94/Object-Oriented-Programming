package cz39_zx18.client.mvc.model;

import java.rmi.RemoteException;

import javax.swing.ImageIcon;

import common.DataPacketCR;
import common.IChatRoom;
import common.IReceiver;
import common.datatype.chatroom.IRemoveReceiverType;
import cz39_zx18.client.utils.MyPanel;
import cz39_zx18.client.utils.NarrowComponent;
import cz39_zx18.client.utils.NarrowImageIcon;
import cz39_zx18.client.utils.NarrowString;
import cz39_zx18.client.utils.Receiver;
import cz39_zx18.client.utils.RemoveReceiverType;
import cz39_zx18.client.utils.User;

/**
 * MiniModel, related to only one chat room
 */
public class MiniModel {
	private IChatRoom chatRoom;
	private User user;
	private Receiver receiver;

	/**
	 * Constructor for MiniModel
	 * @param chatRoom the corresponding chat room
	 * @param user local user
	 * @param receiver local receiver corresponded to the chat room
	 */
	public MiniModel(IChatRoom chatRoom, User user, Receiver receiver) {
		this.chatRoom = chatRoom;
		this.user = user;
		this.receiver = receiver;
	}

	/**
	 * Leave the chat room
	 */
	public void leave() {
		user.leaveChatRoom(chatRoom);
		for (IReceiver receiverStub : chatRoom.getIReceiverStubs())
			try {
				if (receiverStub.getUserStub().getUUID().equals(user.getUUID())) {
					chatRoom.removeIReceiverStub(receiver);
					chatRoom.sendPacket(new DataPacketCR<IRemoveReceiverType>(IRemoveReceiverType.class,
							new RemoveReceiverType(receiver), receiver));
					return;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Send a message in the chat room
	 * @param msg the message to send
	 */
	public void sendMsg(String msg) {
		DataPacketCR<NarrowString> data = new DataPacketCR<NarrowString>(NarrowString.class, new NarrowString(msg),
				receiver);
		receiver.sendPacket(data, chatRoom);
	}

	/**
	 * Send a image in the chat room
	 * @param imageIcon the image to send
	 */
	public void sendImage(ImageIcon imageIcon) {
		DataPacketCR<NarrowImageIcon> data = new DataPacketCR<NarrowImageIcon>(NarrowImageIcon.class,
				new NarrowImageIcon(imageIcon), receiver);
		receiver.sendPacket(data, chatRoom);
	}

	/**
	 * Send a component in a chat room
	 */
	public void sendComp() {
		DataPacketCR<NarrowComponent> comp = new DataPacketCR<NarrowComponent>(NarrowComponent.class,
				new NarrowComponent(new MyPanel()), receiver);
		receiver.sendPacket(comp, chatRoom);
	}
}
