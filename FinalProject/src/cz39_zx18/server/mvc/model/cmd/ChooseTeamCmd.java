package cz39_zx18.server.mvc.model.cmd;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.IChatRoom;
import common.IComponentFactory;
import common.IReceiver;

/**
 * Choose team gui command
 */
public class ChooseTeamCmd extends DataPacketCRAlgoCmd<ChooseTeamMsg> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8670328469405608658L;
	private transient ICRCmd2ModelAdapter cmd2ModelAdpt;
	private List<IChatRoom> rooms = new LinkedList<IChatRoom>();
	private IReceiver receiverStub;

	/**
	 * Constructor 
	 * @param receiverStub the stub of receiver
	 * @param list the list of chatrooms
	 * @param cmd2ModelAdpt adapter from cmd to model
	 */
	public ChooseTeamCmd(IReceiver receiverStub, List<IChatRoom> list, ICRCmd2ModelAdapter cmd2ModelAdpt) {
		rooms = list;
		this.receiverStub = receiverStub;
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public String apply(Class<?> index, DataPacketCR<ChooseTeamMsg> host, String... params) {
		cmd2ModelAdpt.buildNonScrollableComponent(new IComponentFactory() {
			@Override
			public Component makeComponent() {
				return new ChooseTeamView(rooms, cmd2ModelAdpt, receiverStub);
			}
		}, "Choose Team");
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}
}
