package engine.controller;

import engine.model.EngineModel;
import engine.model.IEngineViewAdapter;
import engine.view.EngineView;
import engine.view.IEngineModelAdapter;

/**
 * 
 * MVC Controller for the app
 * Contain the main() method. 
 * @author Zhenwei Feng
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class EngineController {
	/**
	 * The View of this Controller
	 */
	private EngineView _view;
	/**
	 * The Model of this Controller
	 */
	private EngineModel _model;

	/**
	 * The constructor of this Controller
	 */
	public EngineController() {

		_view = new EngineView(new IEngineModelAdapter() {
			@Override
			public void quit() {
				_model.stop();
			}

			@Override
			public void sendMsgToRemote(String text) {
				_model.sendMsgToClient(text);

			}
		});

		_model = new EngineModel(new IEngineViewAdapter() {
			@Override
			public void append(String s) {
				_view.append(s);
			}

		});
	}

	/**
	 * Start method of this Controller.
	 */
	public void start() {
		_view.start();
		_model.start();
	}

	/**
	 * Controller Main function
	 * @param args ignored.
	 */
	public static void main(String[] args) {
		(new EngineController()).start();
	}
}
