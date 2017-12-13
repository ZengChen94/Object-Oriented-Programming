package cz39_zx18.client.utils;

import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import common.DataPacketCRAlgoCmd;
import common.DataPacketCR;
import common.ICRCmd2ModelAdapter;
import common.IComponentFactory;

/**
 * ImageCmd is a command for processing ImageIcon data packet
 */
public class ImageCmd extends DataPacketCRAlgoCmd<NarrowImageIcon> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5401116048185495785L;
	private transient ICRCmd2ModelAdapter cmd2ModelAdpt;

	/**
	 * Constructor for ImageCmd
	 * @param cmd2ModelAdpt ICmd2ModelAdapter for the command talking to local system
	 */
	public ImageCmd(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	/**
	 * Algorithm of sending narrow type imageIcon datapacket
	 * @param index Index
	 * @param host Host
	 * @param params Parameters
	 * @return string Algorithm result
	 */
	@Override
	public String apply(Class<?> index, DataPacketCR<NarrowImageIcon> host, String... params) {
		String name = "Unknown remote name ";
		try {
			name = host.getSender().getUserStub().getName();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ImageIcon img = host.getData().getImage();
		cmd2ModelAdpt.buildScrollableComponent(new IComponentFactory() {
			@Override
			public Component makeComponent() {
				JLabel label = new JLabel(img);
				return label;
			}
		}, name);
		return null;
	}

	/**
	 * Set cmd2model adapter
	 * @param cmd2ModelAdpt ICmd2ModelAdapter for the command talking to local system
	 */
	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}
}
