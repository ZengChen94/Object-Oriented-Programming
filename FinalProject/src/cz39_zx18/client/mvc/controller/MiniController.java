package cz39_zx18.client.mvc.controller;

import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;

import common.IChatRoom;
import common.IReceiver;
import cz39_zx18.client.mvc.model.MiniModel;
import cz39_zx18.client.mvc.view.MiniView;
import cz39_zx18.client.mvc.view.MiniView2ModelAdapter;
import cz39_zx18.client.utils.ProxyUser;
import cz39_zx18.client.utils.Receiver;
import cz39_zx18.client.utils.User;

/**
 * mini mvc controller
 */
public class MiniController {

	private MiniModel model;
	private MiniView<ProxyUser> view;

	/**
	 * Constructor for MiniController, initialize mini model and mini view
	 * @param chatRoom the chat room object corresponding to this mini mvc
	 * @param user the local user
	 * @param mini2MainAdpt adapter talking to main mvc
	 * @param receiver the local receiver corresponding to this mini mvc
	 */
	public MiniController(IChatRoom chatRoom, User user, Mini2MainAdapter mini2MainAdpt, Receiver receiver) {

		model = new MiniModel(chatRoom, user, receiver);

		view = new MiniView<ProxyUser>(new MiniView2ModelAdapter() {
			@Override
			public void leave(Component comp) {
				mini2MainAdpt.remove(comp);
				model.leave();
			}

			@Override
			public void sendText(String msg) {
				model.sendMsg(msg);
			}

			@Override
			public void sendImage(ImageIcon imageIcon) {
				model.sendImage(imageIcon);

			}

			@Override
			public void sendComp() {
				model.sendComp();
			}
		});

		for (IReceiver u : chatRoom.getIReceiverStubs()) {
			try {
				view.addItem(new ProxyUser(u.getUserStub()));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		view.append("Successful create chat room " + chatRoom.getName() + " by " + user);
	}

	/**
	 * Get mini view
	 * @return mini view
	 */
	public MiniView<ProxyUser> getView() {
		return view;
	}

	/** Get mini model 
	 * @return mini model
	 */
	public MiniModel getModel() {
		return model;
	}
}
