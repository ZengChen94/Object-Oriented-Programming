package cz39_zx18.game.utils;

import java.rmi.RemoteException;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.IReceiver;

import provided.mixedData.MixedDataKey;
import cz39_zx18.game.utils.IServer2GameAdapter;

public class GameResultCmd extends DataPacketCRAlgoCmd<GameResult> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9016442025991702050L;
	private transient ICRCmd2ModelAdapter cmd2ModelAdpt;
	private IReceiver receiverStub;

	public GameResultCmd(IReceiver receiverStub) {
		this.receiverStub = receiverStub;
	}

	@Override
	public String apply(Class<?> index, DataPacketCR<GameResult> host, String... params) {
		try {
			MixedDataKey<IServer2GameAdapter> key = new MixedDataKey<>(receiverStub.getUUID(), "IServer2GameAdapter",
					IServer2GameAdapter.class);
			System.out.println("GameResult: " + receiverStub.getUUID());

			//			while (cmd2ModelAdpt.get(key) == null);
			IServer2GameAdapter adpt = cmd2ModelAdpt.get(key);

			if (adpt == null) {
				System.out.println("Error: IServer2GameAdapter == null");
			} else {
				adpt.showResult(host.getData().getResult());
				adpt.setPosition(host.getData().getPositions());
				adpt.setActionInfo(host.getData().getTotalInfo());
				adpt.setSurvival(host.getData().getSurvivals());

				adpt.setPlayerMap(host.getData().getPlayerMap());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
