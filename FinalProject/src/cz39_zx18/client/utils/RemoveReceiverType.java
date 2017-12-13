package cz39_zx18.client.utils;

import common.IReceiver;
import common.datatype.chatroom.IRemoveReceiverType;

/**
 * Implementation of IRemoveReceiverType for sending packet to remove receiver
 */
public class RemoveReceiverType implements IRemoveReceiverType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6086738333169971973L;
	private IReceiver receiverStub;

	/**
	 * Constructor for RemoveReceiverType
	 * @param receiverStub the receiver to remove
	 */
	public RemoveReceiverType(IReceiver receiverStub) {
		this.receiverStub = receiverStub;
	}

	@Override
	public IReceiver getReceiverStub() {
		return receiverStub;
	}

}
