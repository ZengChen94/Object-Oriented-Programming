package cz39_zx18.game.mvc.view;

import java.io.Serializable;

import cz39_zx18.game.mvc.model.Player;

public interface IView2ModelAdapter<CBType> extends Serializable {
	public void goLatLong(String latitude, String longitude);

	public void goPlace(CBType o);

	public Player getPlayer();

	public void updateAnnotation();

	public void calSurvive();

	public void sendPlayer();
}
