package cz39_zx18.game.mvc.model;

import map.MapLayer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

import common.IReceiver;
import cz39_zx18.game.mvc.model.IModel2ViewAdapter;
import cz39_zx18.game.utils.GameAction;
import gov.nasa.worldwind.geom.Position;

/**
 * Model of Game
 */
public class GameModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -139562435551956418L;
	IModel2ViewAdapter _adpt;
	MapLayer _layer = new MapLayer();
	LinkedList<Player> playerList = new LinkedList<Player>();
	private Map<String, Player> playerMap;
	String _userName;

	/**
	 * Constructor
	 * @param userName the user name of game
	 * @param receiverStub the receiver stub
	 * @param adpt the adapter from model to view
	 */
	public GameModel(String userName, IReceiver receiverStub, IModel2ViewAdapter adpt) {
		_adpt = adpt;
		_userName = userName;
	}

	/**
	 * Click display on map
	 * @param p the position clicked
	 */
	public void click(Position p) {
		System.out.println("Mouse clicked at: " + p + " altitude = " + p.getAltitude());
	}

	//	Player playerMyself = new Player("cz39", 45, -70, 1);

	/**
	 * Start model
	 */
	public void start() {
		updateAnnotation();
	}

	/**
	 * update annotation
	 */
	public void updateAnnotation() {
		_layer.removeAllAnnotations();
		int team1 = 0;
		int team2 = 0;
		int winner = -1;
		try {
			for (Player player : playerMap.values()) {
				if (player.getSurvive()) {
					System.out.println(player.getName() + ": " + player.getPosLat() + ", " + player.getPosLon());
					_layer.addToggleAnnotation(player.getName() + "@Team" + player.getTeam(), player.getPosText(),
							Position.fromDegrees(player.getPosLat(), player.getPosLon(), 10000));
					if (player.getTeam() == 1)
						team1 += 1;
					else
						team2 += 1;
					if (Math.abs(player.getPosLat()) < 4 && Math.abs(player.getPosLon()) < 4) {
						winner = player.getTeam();
					}
					_adpt.append(player.getActionInfo());
				}
			}
		} catch (NullPointerException e) {
			//            System.out.print("Caught the NullPointerException\n");
		}
		_layer.addToggleAnnotation("DESTINATION", "0, 0", Position.fromDegrees(0, 0, 10000));
		_adpt.show(_layer);
		_adpt.enableButton();
		_adpt.updateTeam(team1, team2);
		_adpt.updateWinner(winner);
	}

	/**
	 * Get player
	 * @return player the player of game
	 */
	public Player getPlayer() {
		return this.playerMap.get(_userName);
	}

	/**
	 * Judge whether die or not
	 */
	public void calSurvive() {
		double latitudeAttack = this.getPlayer().getAttackLat();
		double longlitudeAttack = this.getPlayer().getAttackLon();
		for (Player player : playerList) {
			if (player.getSurvive()) {
				double latitude = player.getPosLat();
				double longlitude = player.getPosLon();
				if (Math.abs(latitudeAttack - latitude) < 0.2 && Math.abs(longlitudeAttack - longlitude) < 0.2) {
					player.setSurvive(false);
				}
			}
		}
	}

	/**
	 * Get the playermap
	 * @return playerMap the map of player, with username as key and player as value
	 */
	public Map<String, Player> getPlayerMap() {
		return playerMap;
	}

	/**
	 * Set the playermap
	 * @param playerMap the map of player, with username as key and player as value
	 */
	public void setPlayerMap(Map<String, Player> playerMap) {
		this.playerMap = playerMap;
		//		System.out.println("----------------------------------");
		//		System.out.println(this.playerMap.size());
		updateAnnotation();
		//		_adpt.clearTime();
	}

	/**
	 * Take action
	 */
	public void action() {
		System.out.println("Action");
		_adpt.sendTo(GameAction.class, new GameAction(this.getPlayer()));
	}

}
