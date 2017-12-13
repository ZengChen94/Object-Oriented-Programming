package cz39_zx18.game.mvc.view;

import java.io.Serializable;

public interface IAppStart2Controller extends Serializable {

	void makeMap();

	void startMap();

	void runJob(Runnable runnable);

}
