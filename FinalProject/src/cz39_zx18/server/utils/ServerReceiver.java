package cz39_zx18.server.utils;

import java.rmi.RemoteException;
import common.*;
import common.datatype.user.IInvitationType;
//import cz39_zx18.server.demogame.DemoGameCmd;
//import cz39_zx18.server.demogame.GameMsg;
import cz39_zx18.game.utils.GameResult;
import cz39_zx18.game.utils.GameResultCmd;
import cz39_zx18.server.mvc.model.cmd.ChooseTeamCmd;
import cz39_zx18.server.mvc.model.cmd.ChooseTeamMsg;
import cz39_zx18.server.mvc.model.cmd.Invitation;
import cz39_zx18.server.mvc.model.cmd.SelectTeam;
import cz39_zx18.client.utils.*;
import cz39_zx18.game.utils.GameCmd;
import cz39_zx18.game.utils.GameAction;
import cz39_zx18.game.utils.GameMsg;

/**
 * Implementation of IReceiver, with more local methods
 */
public class ServerReceiver extends Receiver {
	private ServerUser user;
	private IServerCRCmd2ModelAdapter _cmd2ModelAdpt;

	/**
	 * Constructor for Receiver
	 * @param chatRoom the related chat room 
	 * @param user the user of server
	 * @param userStub the related user's stub
	 * @param cmd2ModelAdpt adapter for command talking with local model
	 */
	public ServerReceiver(IChatRoom chatRoom, ServerUser user, IUser userStub,
			IServerCRCmd2ModelAdapter cmd2ModelAdpt) {
		super(chatRoom, userStub, cmd2ModelAdpt);
		this.setUser(user);
		this._cmd2ModelAdpt = cmd2ModelAdpt;
	}

	/**
	 * 
	 */
	public void init() {
		algo.setCmd(ChooseTeamMsg.class,
				new ChooseTeamCmd(ServerReceiver.this, _cmd2ModelAdpt.getChatRooms(), _cmd2ModelAdpt));
		algo.setCmd(SelectTeam.class, new DataPacketCRAlgoCmd<SelectTeam>() {
			private static final long serialVersionUID = 7710578839212535098L;

			@Override
			public String apply(Class<?> index, DataPacketCR<SelectTeam> host, String... params) {
				IChatRoom cr = host.getData().getRoom();
				for (IChatRoom room : _cmd2ModelAdpt.getChatRooms()) {
					if (room.getUUID().equals(cr.getUUID())) {
						try {
							host.getSender().getUserStub().receive(new DataPacketUser<IInvitationType>(
									IInvitationType.class, new Invitation(room), userStub));
							break;
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		algo.setCmd(GameMsg.class, new GameCmd(_cmd2ModelAdpt, receiverStub));

		algo.setCmd(GameResult.class, new GameResultCmd(receiverStub));

		algo.setCmd(GameAction.class, new DataPacketCRAlgoCmd<GameAction>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4460351531975074680L;

			@Override
			public String apply(Class<?> index, DataPacketCR<GameAction> host, String... params) {
				System.out.println(host.getData().getPlayer().getName());
				_cmd2ModelAdpt.addPlayer(host.getData().getPlayer());
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
			}

		});

	}

	public ServerUser getUser() {
		return user;
	}

	public void setUser(ServerUser user) {
		this.user = user;
	}
}
