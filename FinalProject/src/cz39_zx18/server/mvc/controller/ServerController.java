package cz39_zx18.server.mvc.controller;

import java.awt.Component;

import javax.swing.SwingUtilities;

import common.IChatRoom;
import common.IUser;
import cz39_zx18.client.mvc.controller.Mini2MainAdapter;
import cz39_zx18.client.mvc.controller.MiniController;
import cz39_zx18.client.mvc.model.MiniMVCAdapter;
import cz39_zx18.client.mvc.view.MiniView;
import cz39_zx18.client.utils.ProxyUser;
import cz39_zx18.client.utils.User;
import cz39_zx18.server.mvc.model.IServerModel2ViewAdapter;
import cz39_zx18.server.mvc.model.ServerModel;
import cz39_zx18.server.mvc.view.IServerView2ModelAdapter;
import cz39_zx18.server.mvc.view.ServerView;
import cz39_zx18.server.utils.ServerReceiver;

/**
 * MVC controller
 */
public class ServerController {

	private ServerView<ProxyUser> serverView;
	private ServerModel serverModel;

	/**
	 * Constructor for ServerController
	 */
	public ServerController() {
		serverView = new ServerView<ProxyUser>(new IServerView2ModelAdapter() {
			@Override
			public void startGame() {
				serverModel.startGame();
			}

			@Override
			public void sendTeams() {
				serverModel.sendTeams();
			}
		});

		serverModel = new ServerModel(new IServerModel2ViewAdapter() {
			@Override
			public MiniMVCAdapter<ProxyUser> makeMiniMVC(IChatRoom chatRoom, User user, ServerReceiver receiver) {
				MiniController miniController = new MiniController(chatRoom, user, new Mini2MainAdapter() {
					@Override
					public void remove(Component miniView) {
					}
				}, receiver);

				MiniView<ProxyUser> miniView = miniController.getView();

				return new MiniMVCAdapter<ProxyUser>() {
					@Override
					public void addItem(ProxyUser t) {
						miniView.addItem(t);
					}

					@Override
					public void removeItem(ProxyUser t) {
						miniView.removeItem(t);
					}

					@Override
					public void append(String text) {
						miniView.append(text);
					}

					@Override
					public void addScrollableComponent(Component comp, String label) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								miniView.addScrollableComponent(comp, label);
							}
						});
					}

					@Override
					public void quit() {
						miniController.getModel().leave();
					}

					@Override
					public void addNonScrollableComponent(Component comp, String label) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								miniView.addNonScrollableComponent(comp, label);
							}
						});
					}
				};
			}

			@Override
			public void setServerName(String localAddr) {
				serverView.setServerName(localAddr);
			}

			@Override
			public void addConnected(IUser userStub) {
				serverView.addConnected(new ProxyUser(userStub));
			}

			@Override
			public void setUserName(String name) {
				serverView.setUserName(name);
			}
		});

	}

	/**
	 * Start the application
	 */
	public void start() {
		serverModel.start();
		serverView.start();
	}

	/**
	 * Launch the application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new ServerController().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
