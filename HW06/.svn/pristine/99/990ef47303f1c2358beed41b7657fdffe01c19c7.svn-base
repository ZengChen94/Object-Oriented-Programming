package controller;

import java.awt.EventQueue;

import model.IViewAdapter;
import model.Model;
import provided.util.ABCInstrument;
import view.IModelAdapter;
import view.View;

/**
 * 
 * MVC Controller for the app
 * Contain the main() method. 
 * @author Yuchang Shen
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class Controller {
	/**
	 * The Model of this Controller
	 */
	private Model _model;

	/**
	 * The View of this Controller
	 */
	private View<ABCInstrument> _view;

	/**
	 * Start method of this Controller.
	 */
	protected void start() {
		_model.start();
		_view.start();
	}

	/**
	 * The Controller constructor 
	 */
	public Controller() {
		_model = new Model(new IViewAdapter() {
			@Override
			public void addInstrument(ABCInstrument instrument) {
				_view.addInstrument(instrument);
			}
		});

		_view = new View<ABCInstrument>(new IModelAdapter<ABCInstrument>() {
			@Override
			public String loadMusic(String musicName) {
				return _model.loadMusic(musicName);
			}

			@Override
			public String parseMusic() {
				return _model.parseMusic();
			}

			@Override
			public void playMusic(ABCInstrument instrument) {
				_model.playMusic(instrument);
			}

			@Override
			public void stopMusic() {
				_model.stopMusic();
			}
		});
	}

	/**
	 * Controller Main function
	 * @param args Arguments by default
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
