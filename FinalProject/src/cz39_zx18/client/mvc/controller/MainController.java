package cz39_zx18.client.mvc.controller;

import java.awt.Component;
import javax.swing.SwingUtilities;

import common.IChatRoom;
import common.IUser;
import cz39_zx18.client.mvc.model.*;
import cz39_zx18.client.mvc.view.*;
import cz39_zx18.client.utils.ProxyUser;
import cz39_zx18.client.utils.Receiver;
import cz39_zx18.client.utils.User;
import cz39_zx18.game.mvc.controller.GameController;

/**
 * main-mvc controller
 */
public class MainController {

	private MainModel mainModel;
	private MainView<ProxyUser> mainView;

	/**
	 * Constructor for MainController, initialize model and view
	 */
	public MainController() {

		mainModel = new MainModel(new MainModel2ViewAdapter() {
			@Override
			public MiniMVCAdapter<ProxyUser> makeMiniMVC(IChatRoom chatRoom, User user, Receiver receiver) {
				MiniController miniController = new MiniController(chatRoom, user, new Mini2MainAdapter() {
					@Override
					public void remove(Component miniView) {
						mainView.removeTab(miniView);
					}
				}, receiver);

				MiniView<ProxyUser> miniView = miniController.getView();
				mainView.addTab(chatRoom.getName(), miniView);

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
						//						miniView.addNonScrollableComponent(comp, label);
						System.out.println(
								" SwingUtilities.isEventDispatchThread() " + SwingUtilities.isEventDispatchThread());
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
			public void append(String text) {
				mainView.append(text);
			}

			@Override
			public void setServerName(String localAddr) {
				mainView.setServerName(localAddr);
			}

			@Override
			public void addConnected(IUser userStub) {
				mainView.addConnected(new ProxyUser(userStub));
			}

			@Override
			public void appendUserMsg(String text, String label) {
				// TODO Auto-generated method stub
				mainView.appendUserMsg(text, label);

			}

			@Override
			public void addScrollComp(Component comp, String label) {
				// TODO Auto-generated method stub
				mainView.addScroll(comp, label);
			}

			@Override
			public void addNonScrollComp(Component comp, String label) {
				// TODO Auto-generated method stub
				mainView.addNonScroll(comp, label);
			}
		});

		mainView = new MainView<ProxyUser>(new MainView2ModelAdapter<ProxyUser>() {
			@Override
			public void createChatRoom(String name) {
				mainModel.createChatRoom(name);
			}

			@Override
			public ProxyUser connectTo(String remoteHost) {
				return mainModel.connectToPeer(remoteHost);
			}

			@Override
			public void stop() {
				mainModel.stop();
			}

			@Override
			public void request(ProxyUser user) {
				mainModel.request(user);
			}

			@Override
			public void startServer(String name, String addr) {
				mainModel.startServer(name, addr);
			}

			@Override
			public void sendMsg(String text, ProxyUser target) {
				// TODO Auto-generated method stub
				//				target.receive(new DataPacketUser<String>(String.class, text, user));
				mainModel.sendMsg(text, target);
			}

			@Override
			public void showMap() {
				// TODO Auto-generated method stub
				(new Thread() {
					@Override
					public void run() {
						// TO DO
						new GameController(null, null).startMap();
					}
				}).start();
			}
		});
	}

	/**
	 * Start the application
	 */
	public void start() {
		mainModel.start();
		mainView.start();
	}

	/**
	 * Launch the application.
	 * @param args comand line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainController().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
