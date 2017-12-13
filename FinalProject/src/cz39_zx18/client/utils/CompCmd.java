package cz39_zx18.client.utils;

import java.awt.Component;
import java.rmi.RemoteException;

import common.DataPacketCRAlgoCmd;
import common.DataPacketCR;
import common.ICRCmd2ModelAdapter;
import common.IComponentFactory;

/**
 * CompCmd is a command for processing Component data type
 */
public class CompCmd extends DataPacketCRAlgoCmd<NarrowComponent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1458836024982756149L;
	private transient ICRCmd2ModelAdapter cmd2ModelAdpt;

	/**
	 * Constructor for CompCmd
	 * @param cmd2ModelAdpt ICmd2ModelAdapter for the command talking to local system
	 */
	public CompCmd(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	/**
	 * Set cmd2model adapter
	 * @param cmd2ModelAdpt ICmd2ModelAdapter for the command talking to local system
	 */
	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	/**
	 * Algorithm of sending narrow type component datapacket
	 * @param index Index
	 * @param host Host
	 * @param params Parameters
	 * @return string Algorithm result
	 */
	@Override
	public String apply(Class<?> index, DataPacketCR<NarrowComponent> host, String... params) {
		String name = "Unknown remote name ";
		try {
			name = host.getSender().getUserStub().getName();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		cmd2ModelAdpt.buildNonScrollableComponent(new IComponentFactory() {
			@Override
			public Component makeComponent() {
				return new MyPanel();
			}
		}, name);
		cmd2ModelAdpt.appendMsg("[CompCmd ] .... successfully installed a comp", name);
		return null;
	}
}
