package cz39_zx18.game.utils;

import javax.swing.SwingUtilities;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.IReceiver;
import cz39_zx18.game.mvc.controller.GameController;

public class GameCmd extends DataPacketCRAlgoCmd<GameController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7539143360598166354L;
	private transient ICRCmd2ModelAdapter cmd2ModelAdpt;
	private IReceiver receiverStub;

	/**
	 * @param cmd2ModelAdpt adapter from cmd to model
	 * @param receiverStub receiver stub
	 */
	public GameCmd(ICRCmd2ModelAdapter cmd2ModelAdpt, IReceiver receiverStub) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
		this.receiverStub = receiverStub;
	}

	@Override
	public String apply(Class<?> index, DataPacketCR<GameController> host, String... params) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					GameController game = new GameController(receiverStub, cmd2ModelAdpt);
					game.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;

	}

}
