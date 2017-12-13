package cz39_zx18.game.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import cz39_zx18.game.mvc.model.Player;
import gov.nasa.worldwind.geom.Position;

public interface IServer2GameAdapter extends Serializable {
	void showResult(String text);

	void setPosition(ArrayList<Position> positions);

	void setActionInfo(ArrayList<String> actionInfo);

	void setSurvival(Map<String, Boolean> survive);

	void setPlayerMap(Map<String, Player> playerMap);
}
