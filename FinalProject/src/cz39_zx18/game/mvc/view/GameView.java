package cz39_zx18.game.mvc.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Earth;
import map.IRightClickAction;
import map.MapLayer;
import map.MapPanel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.util.TimerTask;
import javax.swing.SwingConstants;

public class GameView<CBType> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3152423011683006755L;
	private JPanel contentPane;
	private final JPanel panel_control = new JPanel();
	private final JPanel panel_main = new JPanel();
	private final JScrollPane scrollPane_info = new JScrollPane();
	private final JTextArea textArea_info = new JTextArea();
	private final JLabel lblTeam1 = new JLabel("Team1 Alive Members: 0");
	private final JLabel lblTeam2 = new JLabel("Team2 Alive Members: 0");
	private IView2ModelAdapter<CBType> _adpt;

	private JComboBox<CBType> _places;

	private MapPanel _mapPanel;

	//	/**
	//	 * Launch the application.
	//	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					GameView frame = new GameView();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}
	private IAppStart2Controller controller;
	private final JPanel panel_status = new JPanel();
	private final JPanel panel_operations = new JPanel();
	private final JButton btnNorth = new JButton("North");
	private final JButton btnEast = new JButton("East");
	private final JButton btnWest = new JButton("West");
	private final JButton btnSouth = new JButton("South");
	private final JButton btnAttack = new JButton("Attack");
	private final JTextField textField1 = new JTextField();
	private final JTextField textField2 = new JTextField();
	private final JPanel panel = new JPanel();
	private final JLabel lblTimeOfGame = new JLabel("Time of Game: 00:00");

	private java.util.Timer timer = new java.util.Timer();
	private final JPanel panel_winner = new JPanel();
	private final JLabel lblWinner = new JLabel("Winner: -1");

	TimerUp timerUp = new TimerUp();
	private final JLabel lblChatroom = new JLabel("[Please chat in client]");

	/**
	 * Create the frame.
	 * @param adpt Adapter from View to Model
	 * @param rightClick The stub of clicking right button on mouth
	 */
	public GameView(IView2ModelAdapter<CBType> adpt, IRightClickAction rightClick) {
		super();
		setTitle("GAME: MOVE OR HIT");
		_adpt = adpt;
		initGUI(adpt, rightClick);
		//		this.controller = controller;
	}

	/**
	 * Initial the GUI.
	 * @param adpt Adapter from View to Model
	 * @param rightClick The stub of clicking right button on mouth
	 */
	private void initGUI(IView2ModelAdapter<CBType> adpt, IRightClickAction rightClick) {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1000, 700);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			contentPane.add(panel_control, BorderLayout.EAST);
			panel_control.setPreferredSize(new java.awt.Dimension(200, 700));
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Time Information",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

			panel_control.add(panel);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			lblTimeOfGame.setHorizontalAlignment(SwingConstants.LEFT);
			lblTimeOfGame.setPreferredSize(new Dimension(180, 30));

			panel.add(lblTimeOfGame);
			scrollPane_info.setBorder(new TitledBorder(null, "Operation of the Last Round", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));

			panel_control.add(scrollPane_info);
			scrollPane_info.setPreferredSize(new java.awt.Dimension(180, 120));

			scrollPane_info.setViewportView(textArea_info);
			panel_status.setBorder(
					new TitledBorder(null, "Game Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			panel_control.add(panel_status);
			panel_status.setLayout(new GridLayout(0, 1, 0, 0));
			panel_status.add(lblTeam1);
			lblTeam1.setPreferredSize(new Dimension(180, 30));
			panel_status.add(lblTeam2);
			lblTeam2.setPreferredSize(new Dimension(180, 30));
			panel_operations.setBorder(
					new TitledBorder(null, "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			panel_control.add(panel_operations);
			panel_operations.setLayout(new GridLayout(0, 1, 0, 0));
			btnNorth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("[GameView.btnNorth()] " + (_adpt == null) + " getPlayer() == null "
							+ (_adpt.getPlayer() == null));
					_adpt.getPlayer().setAction("move");
					_adpt.getPlayer().setDirection(0);
					//					adpt.getPlayer().move();
					_adpt.sendPlayer();
					//					adpt.updateAnnotation();
					//					textArea_info.append(adpt.getPlayer().getActionInfo());
					disableButton();
					//					textArea_info.append(adpt.getPlayer().getName()+"@Team"+adpt.getPlayer().getTeam()+" move North\n");
				}
			});
			btnNorth.setToolTipText("Move to North");

			panel_operations.add(btnNorth);
			btnNorth.setPreferredSize(new Dimension(180, 30));
			btnEast.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					adpt.getPlayer().setAction("move");
					adpt.getPlayer().setDirection(3);
					//					adpt.getPlayer().move();
					adpt.sendPlayer();
					//					adpt.updateAnnotation();
					//					textArea_info.append(adpt.getPlayer().getActionInfo());
					disableButton();
					//					textArea_info.append(adpt.getPlayer().getName()+"@Team"+adpt.getPlayer().getTeam()+" move East\n");
				}
			});
			btnEast.setToolTipText("Move to East");

			panel_operations.add(btnEast);
			btnEast.setPreferredSize(new Dimension(180, 30));
			btnWest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					adpt.getPlayer().setAction("move");
					adpt.getPlayer().setDirection(2);
					//					adpt.getPlayer().move();
					adpt.sendPlayer();
					//					adpt.updateAnnotation();
					//					textArea_info.append(adpt.getPlayer().getActionInfo());
					disableButton();
					//					textArea_info.append(adpt.getPlayer().getName()+"@Team"+adpt.getPlayer().getTeam()+" move West\n");
				}
			});
			btnWest.setToolTipText("Move to West");

			panel_operations.add(btnWest);
			btnWest.setPreferredSize(new Dimension(180, 30));
			btnSouth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					adpt.getPlayer().setAction("move");
					adpt.getPlayer().setDirection(1);
					//					adpt.getPlayer().move();
					adpt.sendPlayer();
					//					adpt.updateAnnotation();
					//					textArea_info.append(adpt.getPlayer().getActionInfo());
					disableButton();
					//					textArea_info.append(adpt.getPlayer().getName()+"@Team"+adpt.getPlayer().getTeam()+" move South\n");
				}
			});
			btnSouth.setToolTipText("Move to South");

			panel_operations.add(btnSouth);
			btnSouth.setPreferredSize(new Dimension(180, 30));

			panel_operations.add(textField1);
			textField1.setPreferredSize(new Dimension(180, 35));
			panel_operations.add(textField2);
			textField2.setPreferredSize(new Dimension(180, 35));
			btnAttack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					adpt.getPlayer().setAction("attack");
					String latitude = !textField1.getText().isEmpty() ? textField1.getText() : "0";
					String longitude = !textField2.getText().isEmpty() ? textField2.getText() : "0";
					adpt.getPlayer().setAttack(Double.parseDouble(latitude), Double.parseDouble(longitude));
					//					adpt.calSurvive();
					adpt.sendPlayer();
					//					adpt.updateAnnotation();
					//					textArea_info.append(adpt.getPlayer().getActionInfo());	
					disableButton();
					//					textArea_info.append(adpt.getPlayer().getName()+"@Team"+adpt.getPlayer().getTeam()+" attack at (" + latitude + "," + longitude + ")\n");
				}
			});
			btnAttack.setToolTipText("Attack to the above (x, y)");
			textField1.setToolTipText("Input the attacked latitude");
			textField2.setToolTipText("Input the attacked longitude");
			panel_operations.add(btnAttack);
			btnAttack.setPreferredSize(new Dimension(180, 30));

			//			contentPane.add(panel_main, BorderLayout.CENTER);
			//			panel_main.setPreferredSize(new java.awt.Dimension(180, 120));

			_mapPanel = new MapPanel(Earth.class);
			getContentPane().add(_mapPanel, BorderLayout.CENTER);
			//			_mapPanel.setPreferredSize(new java.awt.Dimension(600, 400));
			textField2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Attack Longitude (err<0.2)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField2.setColumns(10);
			textField1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Attack Latitude (err<0.2)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField1.setColumns(10);
			panel_winner.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Winner Information",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

			panel_control.add(panel_winner);
			panel_winner.setLayout(new GridLayout(0, 1, 0, 0));
			lblWinner.setPreferredSize(new Dimension(180, 30));

			panel_winner.add(lblWinner);
			lblChatroom.setBorder(null);
			lblChatroom.setPreferredSize(new Dimension(180, 30));

			panel_control.add(lblChatroom);

			_mapPanel.addRightClickAction(rightClick);

			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					//	                clock.setCurrentTime(); 
					//	                messageLabel.setText(clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond() + '\n');  
					lblTimeOfGame.setText("Time of This Round: " + timerUp.getTime());
					timerUp.updateTime();
					repaint();
				}
			}, 0, 1000);
		} catch (Exception e) {
			//add your error handling code here
			e.printStackTrace();
		}
	}

	public void start() {
		_mapPanel.start();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setPosition(Position pos) {
		_mapPanel.setPosition(pos, true);
	}

	public void addMapLayer(MapLayer layer) {
		_mapPanel.addLayer(layer);
	}

	public void removeMapLayer(MapLayer layer) {
		_mapPanel.removeLayer(layer);
	}

	public void addPlace(CBType p) {
		//		_places.insertItemAt(p, 0);
		//		_places.setSelectedIndex(0);
	}

	public void disableButton() {
		this.btnNorth.setEnabled(false);
		this.btnSouth.setEnabled(false);
		this.btnEast.setEnabled(false);
		this.btnWest.setEnabled(false);
		this.btnAttack.setEnabled(false);
	}

	public void enableButton() {
		this.btnNorth.setEnabled(true);
		this.btnSouth.setEnabled(true);
		this.btnEast.setEnabled(true);
		this.btnWest.setEnabled(true);
		this.btnAttack.setEnabled(true);
	}

	public void updateTeam(int team1, int team2) {
		lblTeam1.setText("Team1 Alive Members: " + team1);
		lblTeam2.setText("Team2 Alive Members: " + team2);
	}

	public void updateWinner(int winner) {
		// TODO Auto-generated method stub
		lblWinner.setText("Winner Team: " + winner);
	}

	public void clearTime() {
		timerUp.minute = 0;
		timerUp.second = 0;
	}

	public void append(String actionInfo) {
		textArea_info.append(actionInfo);
	}
}

class TimerUp {
	int minute;
	int second;

	TimerUp() {
		this.minute = 0;
		this.second = 0;
	}

	String getTime() {
		if (this.minute < 10 && this.second < 10) {
			return "0" + this.minute + ":" + "0" + this.second;
		} else if (this.minute < 10) {
			return "0" + this.minute + ":" + this.second;
		} else if (this.second < 10) {
			return this.minute + ":" + "0" + this.second;
		} else {
			return this.minute + ":" + this.second;
		}
	}

	void updateTime() {
		this.second += 1;
		if (this.second == 60) {
			this.minute += 1;
			this.second = 0;
		}
	}

	void clearTime() {
		this.second += 1;
		if (this.second == 31) {
			this.second = 0;
		}
	}
}
