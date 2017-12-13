package cz39_zx18.client.utils;

import java.rmi.RemoteException;

import common.DataPacketCRAlgoCmd;
import common.DataPacketCR;
import common.ICRCmd2ModelAdapter;

/**
 * StringCmd is a command for processing String data packet
 */
public class StringCmd extends DataPacketCRAlgoCmd<NarrowString> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9034847292223147641L;
	private transient ICRCmd2ModelAdapter cmd2ModelAdpt;

	/**
	 * Constructor for StringCmd
	 * @param cmd2ModelAdpt adapter for the command talking with local system
	 */
	public StringCmd(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public String apply(Class<?> index, DataPacketCR<NarrowString> host, String... params) {
		String name = "Unknown remote name ";
		try {
			name = host.getSender().getUserStub().getName();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		cmd2ModelAdpt.appendMsg(host.getData().getData(), name);
		return null;
	}

}
