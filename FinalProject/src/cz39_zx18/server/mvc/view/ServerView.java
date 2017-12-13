package cz39_zx18.server.mvc.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 *Main mvc view
 * @param <TDropListItem> the type of item in the drop list
 */
public class ServerView<TDropListItem> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5223656255106422726L;
	private JPanel contentPane;
	private final JPanel panel_top = new JPanel();
	private final JPanel panel_name = new JPanel();
	private final JLabel lblUsername = new JLabel("User Name");
	private final JTextField textUsername = new JTextField();
	private final JLabel lblServerName = new JLabel("Server Addr");
	private final JTextField textServerName = new JTextField();
	private final JPanel panel_team = new JPanel();
	private final JButton btnStart = new JButton("Send Team");
	private final JPanel panel_7 = new JPanel();
	private final JPanel panel_main = new JPanel();
	private final JTabbedPane panel_tab = new JTabbedPane(JTabbedPane.TOP);

	private IServerView2ModelAdapter view2ModelAdpt;
	private final JScrollPane panel_log = new JScrollPane();
	private final JTextArea txtDefaultInfo = new JTextArea();
	private final JComboBox<TDropListItem> comboBox = new JComboBox<>();
	private final JPanel panel_connected = new JPanel();
	DefaultListModel<TDropListItem> listModel = new DefaultListModel<>();
	private final JList<TDropListItem> list = new JList<>(listModel);
	private final JPanel panel_game = new JPanel();
	private final JButton btnGame = new JButton("Send Game");

	/**
	 * Create the frame.
	 * @param view2ModelAdpt adapter talking to main model
	 */
	public ServerView(IServerView2ModelAdapter view2ModelAdpt) {
		setTitle("SERVER");
		this.view2ModelAdpt = view2ModelAdpt;
		initGUI();
	}

	/**
	 * Start the view, i.e. set visibility to true
	 */
	public void start() {
		setVisible(true);
	}

	/** Set user name
	 * @param name the user name
	 */
	public void setUserName(String name) {
		textUsername.setText(name);
	}

	/**
	 * Set the server name
	 * @param localIP the servername(local ip address) to set
	 */
	public void setServerName(String localIP) {
		textServerName.setText(localIP);
	}

	/**
	 * Add an item in drop list
	 * @param item the item to add
	 */
	public void addConnected(TDropListItem item) {
		comboBox.insertItemAt(item, 0);
		listModel.addElement(item);
	}

	/**
	 * Remove an item in drop list
	 * @param item the item to remove
	 */
	public void removeConnected(TDropListItem item) {
		comboBox.removeItem(item);
		for (int i = 0; i < listModel.size(); i++) {
			if (listModel.getElementAt(i).equals(item)) {
				listModel.remove(i);
				break;
			}
		}
	}

	private void initGUI() {
		textServerName.setToolTipText("IP address");
		textServerName.setEditable(false);
		textServerName.setColumns(15);
		textUsername.setEditable(false);
		textUsername.setToolTipText("Input your name");
		textUsername.setText("name...");
		textUsername.setColumns(15);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		setBounds(100, 100, 1200, 457);
		setBounds(100, 100, 700, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel_top.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		//		contentPane.add(panel_top, BorderLayout.NORTH);
		contentPane.add(panel_top, BorderLayout.CENTER);

		panel_top.add(panel_name);
		panel_name.setLayout(new GridLayout(2, 2, 0, 0));
		lblUsername.setToolTipText("username");

		panel_name.add(lblUsername);

		panel_name.add(textUsername);
		lblServerName.setToolTipText("server name");

		panel_name.add(lblServerName);

		panel_name.add(textServerName);

		panel_top.add(panel_team);
		btnStart.setToolTipText("Send Team");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				view2ModelAdpt.startGame();
				view2ModelAdpt.sendTeams();
			}
		});

		panel_team.add(btnStart);

		panel_top.add(panel_game);
		btnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.startGame();
			}
		});

		panel_game.add(btnGame);
		btnGame.setToolTipText("Send Game");

		//		contentPane.add(panel_main, BorderLayout.CENTER);
		//		panel_main.setLayout(new BorderLayout(0, 0));
		//		panel_tab.setToolTipText("main panel");
		//		
		//		panel_main.add(panel_tab, BorderLayout.CENTER);
		//		
		//		panel_tab.addTab("Log Record", null, panel_log, "log information");
		//		txtDefaultInfo.setToolTipText("Log information");
		//		
		//		panel_log.setViewportView(txtDefaultInfo);
		//		
		//		panel_tab.addTab("Connected User", null, panel_connected, null);
		//		panel_connected.setLayout(new BorderLayout(0, 0));
		//		
		//		panel_connected.add(list, BorderLayout.CENTER);
	}
}
