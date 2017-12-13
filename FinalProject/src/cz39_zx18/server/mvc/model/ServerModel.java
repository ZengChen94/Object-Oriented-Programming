package cz39_zx18.server.mvc.model;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import common.DataPacketCR;
import common.DataPacketUser;
import common.ICRMessageType;
import common.IChatRoom;
import common.IComponentFactory;
import common.IReceiver;
import common.IUser;
import common.IUserMessageType;
import common.datatype.user.IInvitationType;
import provided.mixedData.MixedDataKey;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;
import cz39_zx18.client.utils.ChatRoom;
import cz39_zx18.client.utils.IUser2ModelAdapter;
import cz39_zx18.game.mvc.model.Player;
import cz39_zx18.game.utils.GameMsg;
import cz39_zx18.game.utils.GameResult;
import cz39_zx18.server.mvc.model.cmd.ChooseTeamMsg;
import cz39_zx18.server.mvc.model.cmd.Invitation;
import cz39_zx18.server.utils.ServerCRCmd2ModelAdapter;
import cz39_zx18.server.utils.ServerReceiver;
import cz39_zx18.server.utils.ServerUser;

/**
 * MVC model
 */
public class ServerModel {

	private IServerModel2ViewAdapter model2ViewAdpt;
	private Consumer<String> consumer;
	private IRMIUtils rmiUtils;
	private Registry registry = null;
	private ServerUser user;
	private IUser userStub;
	private int receivePort = IRMI_Defs.STUB_PORT_EXTRA + 1;
	private List<IChatRoom> rooms = new LinkedList<>();
	//	private IServerUserCmd2ModelAdapter serverCmd2ModelAdpt;
	private List<ServerCRManager> managers = new LinkedList<>();
	private IChatRoom lobby;
	private ServerCRManager lobbyCr;
	//	private int timerMinute = 0;

	//	game related variable
	Map<String, Player> playerMap = new HashMap<String, Player>();
	Queue<Player> playerQueue = new LinkedList<Player>();
	int winner = -1;
	int alive = 0;

	private java.util.Timer timer = new java.util.Timer();

	/**
	 * @param model2ViewAdpt adapter from model to view
	 */
	public ServerModel(IServerModel2ViewAdapter model2ViewAdpt) {
		this.model2ViewAdpt = model2ViewAdpt;
	}

	private class ServerCRManager {
		ServerReceiver receiver;
		IReceiver receiverStub;
		IChatRoom room;

		ServerCRManager(IChatRoom room) {
			this.room = room;
			receiver = new ServerReceiver(room, user, userStub, new ServerCRCmd2ModelAdapter(room) {
				@Override
				public <T extends ICRMessageType> void sendTo(IReceiver target, Class<T> id, T data) {
					try {
						target.receive(new DataPacketCR<T>(id, data, receiverStub));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				@Override
				public List<IChatRoom> getChatRooms() {
					return rooms;
				}

				@Override
				public String getName() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void addPlayer(Player player) {
					// TODO Auto-generated method stub
					playerQueue.add(player);
					System.out.println("[playerQueue.size()]: " + playerQueue.size());
					//					System.out.println(player.getName() + " " + player.getAction() + " " + player.getDirection());
					System.out.println("alive: " + alive);
					//					if (playerQueue.size() == alive) {
					//						process();
					//					}
					process();
					//					broadcastPlayerMap();
				}
			});
			try {
				receiverStub = (IReceiver) UnicastRemoteObject.exportObject(receiver, receivePort);
				receiver.setReceiverStub(receiverStub);
				receiver.init();
				receivePort += 2;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			room.addIReceiverStub(receiverStub);
		}

		public <T extends ICRMessageType> void boardCast(Class<T> id, T data) {
			//			System.out.println("[ServerCRManager.broadCast] " + room.getName());
			room.sendPacket(new DataPacketCR<T>(id, data, receiverStub));
		}
	}

	/**
	 * Start the server models
	 */
	public void start() {
		consumer = new Consumer<String>() {
			@Override
			public void accept(String t) {
			}
		};
		rmiUtils = new RMIUtils(consumer);
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_SERVER);
		String addr = "localhost";
		try {
			addr = rmiUtils.getLocalAddress();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String userName = "Game Server@" + addr;
		user = new ServerUser(userName, new IUser2ModelAdapter() {
			@Override
			public void addConnected(IUser connectedUserStub) {
				model2ViewAdpt.addConnected(connectedUserStub);
				try {
					connectedUserStub.receive(new DataPacketUser<IInvitationType>(IInvitationType.class,
							new Invitation(lobby), userStub));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void joinChatRoom(IChatRoom room) {
			}

			@Override
			public void appendMsg(String text, String label) {
			}

			@Override
			public void buildScrollableComponent(IComponentFactory fac, String label) {
			}

			@Override
			public void buildNonScrollableComponent(IComponentFactory fac, String label) {
			}

			@Override
			public <T> T put(MixedDataKey<T> key, T value) {
				return null;
			}

			@Override
			public <T> T get(MixedDataKey<T> key) {
				return null;
			}

			@Override
			public <T extends IUserMessageType> void sendTo(IUser target, Class<T> id, T data) {
			}

			@Override
			public String getName() {
				return null;
			}
		});

		model2ViewAdpt.setServerName(addr);
		model2ViewAdpt.setUserName(userName);

		registry = rmiUtils.getLocalRegistry();
		try {
			userStub = (IUser) UnicastRemoteObject.exportObject(user, IRMI_Defs.STUB_PORT_SERVER);
			user.setSelfStub(userStub);
		} catch (RemoteException e) {
			consumer.accept("[ServerModel.init()]: Exception when export object " + e.getMessage());
			e.printStackTrace();
		}
		try {
			registry.rebind(IUser.BOUND_NAME, userStub);
			model2ViewAdpt.addConnected(userStub);
			consumer.accept("[ServerModel.init()]: Registry successfully bind " + IUser.BOUND_NAME + " To "
					+ IRMI_Defs.STUB_PORT_SERVER);
		} catch (RemoteException e) {
			consumer.accept("[ServerModel.init()]: Exception when rebinging user " + e.getMessage());
			e.printStackTrace();
		}

		lobby = user.createChatRoom("LobbyRoom");
		lobbyCr = new ServerCRManager(lobby);

		IChatRoom team1 = new ChatRoom("Team1");
		ServerCRManager cr1 = new ServerCRManager(team1);
		IChatRoom team2 = new ChatRoom("Team2");
		ServerCRManager cr2 = new ServerCRManager(team2);
		rooms.add(team1);
		rooms.add(team2);
		managers.add(cr1);
		managers.add(cr2);
	}

	/**
	 * Start the game
	 */
	public void startGame() {
		System.out.println("[ServerModel.sendGame()] --- ");
		for (ServerCRManager cr : managers) {
			cr.boardCast(GameMsg.class, new GameMsg());
		}
		initPlayerMap();

		//		Player player1 = new Player("testUser1", 49, -70, 2);
		//		Player player2 = new Player("testUser2", 55, -70, 1);
		//		playerMap.put(player1.getName(), player1);
		//		playerMap.put(player2.getName(), player2);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				for (ServerCRManager cr : managers) {
					cr.boardCast(GameResult.class, new GameResult(playerMap));
				}
			}
		}, 0, 10000);
	}

	/**
	 * Get a list of chat rooms
	 * @return a list of chat rooms
	 */
	public List<IChatRoom> getChatRooms() {
		return rooms;
	}

	/**
	 * Send choose team gui
	 */
	public void sendTeams() {
		System.out.println("[ServerModel.sendTeams()] --- ");
		lobbyCr.boardCast(ChooseTeamMsg.class, new ChooseTeamMsg());
	}

	public Map<String, Player> getPlayerMap() {
		return playerMap;
	}

	public void setPlayerMap(Map<String, Player> map) {
		playerMap = map;
	}

	public Queue<Player> getPlayerQueue() {
		return playerQueue;
	}

	public void setPlayerQueue(Queue<Player> queue) {
		playerQueue = queue;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int win) {
		winner = win;
	}

	public void initPlayerMap() {
		for (IChatRoom room : rooms) {
			Collection<IReceiver> receiverStubs = room.getIReceiverStubs();
			for (IReceiver receiverStub : receiverStubs) {
				try {
					int playerTeam = room.getName().equals("Team1") ? 1 : 2;
					String playerName = receiverStub.getUserStub().getName();
					if (playerName.startsWith("Game Server"))
						continue;
					System.out.println("[Name]: " + playerName);
					double latitude = ThreadLocalRandom.current().nextDouble(-90, 90);
					double longlitude = ThreadLocalRandom.current().nextDouble(-180, 180);
					System.out.println(playerName + "@Team" + playerTeam + " : " + latitude + ", " + longlitude);
					playerMap.put(playerName, new Player(playerName, latitude, longlitude, playerTeam));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		this.alive = playerMap.size();
	}

	public void process() {
		while (!playerQueue.isEmpty()) {
			Player player = playerQueue.poll();
			if (!player.getSurvive())
				continue;
			else {
				if (player.getAction().equals("move")) {
					//					System.out.println(player.getName() + "-" + player.getAction() + ":" + player.getPosLat() + "," + player.getPosLon());
					playerMap.get(player.getName()).setDirection(player.getDirection());
					;
					playerMap.get(player.getName()).move();
					//					if (Math.abs(playerMap.get(player.getName()).getPosLat()) < 0.2 && Math.abs(playerMap.get(player.getName()).getPosLon()) < 0.2) {
					//						winner = playerMap.get(player.getName()).getTeam();
					//						break;
					//					}
				} else {
					double attackLat = player.getAttackLat();
					double attackLon = player.getAttackLon();
					for (Player playerTarget : playerMap.values()) {
						double targetLat = playerTarget.getPosLat();
						double targetLon = playerTarget.getPosLon();
						if (Math.abs(attackLat - targetLat) < 0.2 && Math.abs(attackLon - targetLon) < 0.2) {
							playerTarget.setSurvive(false);
							this.alive -= 1;
						}
					}
					//					playerMap.get(player.getName()).setAction("move");
				}
			}
		}
	}

	//	public void broadcastPlayerMap() {
	//		for (ServerCRManager cr : managers) {
	//			cr.boardCast(GameResult.class, new GameResult(playerMap));
	//		}
	//	}
}
