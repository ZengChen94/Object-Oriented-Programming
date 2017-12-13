package cz39_zx18.client.utils;

import common.IReceiver;
import common.datatype.chatroom.IAddReceiverType;

/**
 * Concrete class implementing IAddReceiverType for transmitting message to add receiver
 * @author Chen Zeng, Zhihui Xie
 */
public class AddReceiverType implements IAddReceiverType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6767705932194343398L;
	private IReceiver receiverStub;

	/**
	 * Constructor for AddReceiverType
	 * @param receiverStub the receiver stub to add
	 */
	public AddReceiverType(IReceiver receiverStub) {
		this.receiverStub = receiverStub;
	}

	@Override
	public IReceiver getReceiverStub() {
		return receiverStub;
	}
}
