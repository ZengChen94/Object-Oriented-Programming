package cz39_zx18.server.mvc.view;

/**
 * view2model adapter
 */
public interface IServerView2ModelAdapter {
	/**
	 * Start the game
	 */
	public void startGame();

	/**
	 * Send choose team gui
	 */
	public void sendTeams();
}
