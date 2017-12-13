package cz39_zx18.game.mvc.controller;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import gov.nasa.worldwind.geom.Position;
import javax.swing.SwingUtilities;

import common.ICRCmd2ModelAdapter;
import common.ICRMessageType;
import common.IReceiver;
import common.IUserMessageType;
import cz39_zx18.game.utils.IServer2GameAdapter;
import map.IRightClickAction;
import map.MapLayer;
import provided.mixedData.MixedDataKey;
import cz39_zx18.game.mvc.model.GameModel;
import cz39_zx18.game.mvc.model.IModel2ViewAdapter;
import cz39_zx18.game.mvc.model.Place;
import cz39_zx18.game.mvc.model.Player;
import cz39_zx18.game.mvc.view.GameView;
import cz39_zx18.game.mvc.view.IView2ModelAdapter;

/**
 * Controller of Game
 */
public class GameController implements IUserMessageType, ICRMessageType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5858410373284466002L;
	private GameView<Place> _view;
	private GameModel _model;

	/**
	 * Constructor
	 * @param receiverStub receiver stub
	 * @param cmd2ModelAdpt adapter from command to model
	 */
	public GameController(IReceiver receiverStub, ICRCmd2ModelAdapter cmd2ModelAdpt) {
		System.out.println("[GameController] ReceiverStub == null " + receiverStub == null);
		makeMapMVC(receiverStub, cmd2ModelAdpt);

		//		System.out.println("[userName]: "+cmd2ModelAdpt.getName().split("@")[0]);

		try {
			MixedDataKey<IServer2GameAdapter> key = new MixedDataKey<>(receiverStub.getUUID(), "IServer2GameAdapter",
					IServer2GameAdapter.class);
			System.out.println("GameController: " + receiverStub.getUUID());
			cmd2ModelAdpt.put(key, new IServer2GameAdapter() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 3948708266640525508L;

				@Override
				public void showResult(String text) {
					//					_model.showResult(text);
				}

				@Override
				public void setPlayerMap(Map<String, Player> playerMap) {
					_model.setPlayerMap(playerMap);
				}

				@Override
				public void setPosition(ArrayList<Position> positions) {
					// TODO Auto-generated method stub

				}

				@Override
				public void setActionInfo(ArrayList<String> actionInfo) {
					// TODO Auto-generated method stub

				}

				@Override
				public void setSurvival(Map<String, Boolean> survive) {
					// TODO Auto-generated method stub

				}
			});
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Make the MVC
	 * @param receiverStub receiver stub
	 * @param cmd2ModelAdpt adapter from command to model
	 */
	public void makeMapMVC(IReceiver receiverStub, ICRCmd2ModelAdapter cmd2ModelAdpt) {
		_view = new GameView<Place>(new IView2ModelAdapter<Place>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2842134476443432839L;

			public void goPlace(Place p) {
				_view.setPosition(p.getPosition());
			}

			public void goLatLong(String latitude, String longitude) {
				try {
					_view.setPosition(
							Position.fromDegrees(Double.parseDouble(latitude), Double.parseDouble(longitude), 4000));
				} catch (Exception e) {
					System.out.println("Improper latitude, longitude: " + latitude + ", " + longitude);
				}
			}

			@Override
			public Player getPlayer() {
				return _model.getPlayer();
			}

			@Override
			public void updateAnnotation() {
				_model.updateAnnotation();
			}

			@Override
			public void calSurvive() {
				// TODO Auto-generated method stub
				_model.calSurvive();
			}

			@Override
			public void sendPlayer() {
				_model.action();

			}
		}, new IRightClickAction() {
			public void apply(Position p) {
				_model.click(p);
			}
		});
		_model = new GameModel(cmd2ModelAdpt.getName(), receiverStub, new IModel2ViewAdapter() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6736188158091964725L;

			public void addPlace(Place p) {
				_view.addPlace(p);
			}

			public void show(MapLayer layer) {
				_view.addMapLayer(layer);
			}

			public void hide(MapLayer layer) {
				_view.removeMapLayer(layer);
			}

			@Override
			public <T extends ICRMessageType> void sendTo(Class<T> class1, T g) {
				// TODO Auto-generated method stub
				cmd2ModelAdpt.sendTo(receiverStub, class1, g);
			}

			@Override
			public void enableButton() {
				// TODO Auto-generated method stub
				_view.enableButton();
			}

			@Override
			public void updateTeam(int team1, int team2) {
				// TODO Auto-generated method stub
				_view.updateTeam(team1, team2);
			}

			@Override
			public void updateWinner(int winner) {
				// TODO Auto-generated method stub
				_view.updateWinner(winner);
			}

			@Override
			public void clearTime() {
				// TODO Auto-generated method stub
			}

			@Override
			public void append(String actionInfo) {
				// TODO Auto-generated method stub
				_view.append(actionInfo);
			}
		});
	}

	/**
	 * start Controller
	 */
	public void startMap() {
		_view.start();
		_model.start();
	}

	/**
	 * start Controller
	 */
	public void start() {
		startMap();
	}

	/**
	 * Run the given Runnable job on the main thread.
	 * @param r   The Runnable job to run
	 */
	public void runJob(Runnable r) {
		try {
			bq.put(r); // Put job into the queue, blocking if out of space
		} catch (InterruptedException e) {
			System.out.println("runJob(): Exception putting job into blocking queue = " + e);
			e.printStackTrace();
		}
	}

	private BlockingQueue<Runnable> bq = new LinkedBlockingQueue<Runnable>(5); // May want larger or different type of blocking queue

	/**
	 * @param args parameters of main
	 */
	public static void main(String[] args) {

		final GameController[] c = new GameController[1]; // One-element array trick to get around the "final"
		try {
			SwingUtilities.invokeAndWait(new Runnable() { // Must use invokeAndWait, not invokeLater so that controller will be a valid instance when the job processing loop starts below.
				public void run() {
					c[0] = new GameController(null, null); // Controller, incl. GUI, is constructed on GUI thread
					c[0].start(); // Always show the GUI on the GUI thread.
				}
			});
		} catch (InvocationTargetException | InterruptedException e1) {
			System.err.println("main(): Exception in instantiating controller = " + e1);
			e1.printStackTrace();
		}

		// Go into infinite loop, waiting for Runnable jobs to perform on the main thread.
		while (true) {
			try {
				System.out.println("Waiting for main thread jobs..");
				Runnable r = c[0].bq.take(); // Pull the next available job out of the queue, otherwise block
				System.out.println("Found and now running job: " + r);
				r.run(); // Run the job.
			} catch (InterruptedException e) {
				System.err.println("Exception in blocking queue: " + e);
				e.printStackTrace();
			}
		}
	}

}
