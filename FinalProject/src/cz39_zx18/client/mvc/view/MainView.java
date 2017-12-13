package cz39_zx18.client.mvc.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *Main mvc view
 * @param <TDropListItem> the type of item in the drop list
 */
public class MainView<TDropListItem> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4841444848022354702L;
	private JPanel contentPane;
	private final JPanel panel_top = new JPanel();
	private final JPanel panel_name = new JPanel();
	private final JLabel lblUsername = new JLabel("Username");
	private final JTextField textUsername = new JTextField();
	private final JLabel lblServerName = new JLabel("Server Name");
	private final JTextField textServerName = new JTextField();
	private final JPanel panel_start = new JPanel();
	private final JButton btnStart = new JButton("Start");
	private final JPanel panel_create = new JPanel();
	private final JTextField txtCRName = new JTextField();
	private final JButton btnCreateCR = new JButton("Create");
	private final JPanel panel_request = new JPanel();
	private final JPanel panel_ip = new JPanel();
	private final JTextField textField = new JTextField();
	private final JButton btnConnect = new JButton("Connect to");
	private final JComboBox<TDropListItem> comboBox = new JComboBox<TDropListItem>();
	private final JButton btnRequest = new JButton("Request");
	private final JPanel panel_leave = new JPanel();
	private final JButton btnQuit = new JButton("Leave All & Quit");
	private final JPanel panel_main = new JPanel();
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	private MainView2ModelAdapter<TDropListItem> mainView2ModelAdpt;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea txtDefaultInfo = new JTextArea();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
	private final JTextPane textPane = new JTextPane();
	private final JPanel panel_8 = new JPanel();
	private final JTextField textField_1 = new JTextField();
	private final JButton btnSendText = new JButton("Send Text");
	private final JButton btnSendImage = new JButton("Send Image");

	/**
	 * Create the frame.
	 * @param mainView2ModelAdpt adapter talking to main model
	 */
	public MainView(MainView2ModelAdapter<TDropListItem> mainView2ModelAdpt) {
		this.mainView2ModelAdpt = mainView2ModelAdpt;
		initGUI();
	}

	/**
	 * Create the frame
	 */
	public MainView() {
//		setTitle("CLIENT");
		initGUI();
	}

	/**
	 * Start the view, i.e. set visibility to true
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Add a tab in the tabbed panel
	 * @param title title of the tab
	 * @param component component to add
	 */
	public void addTab(String title, Component component) {
		tabbedPane.addTab(title, null, component, null);
	}

	/**
	 * Append a text in the view
	 * @param msg the text to append
	 */
	public void append(String msg) {
		txtDefaultInfo.append(msg + "\n");
	}

	/**
	 * Remote a tab in the tabbed panel
	 * @param component the component to remove
	 */
	public void removeTab(Component component) {
		tabbedPane.remove(component);
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
	}

	/**
	 * Remove an item in drop list
	 * @param item the item to remove
	 */
	public void removeConnected(TDropListItem item) {
		comboBox.removeItem(item);
	}

	/**
	 * Append a user level message
	 * @param str the message string
	 * @param name the label
	 */
	public void appendUserMsg(String str, String name) {
		textPane.add(new JLabel(name + ": " + str));
	}

	/**
	 * Add a user level scrollable component
	 * @param comp the component to add
	 * @param label the label
	 */
	public void addScroll(Component comp, String label) {
		textPane.add(comp);
	}

	/**
	 * Add a user level non-scrollable component
	 * @param comp the component to add
	 * @param label the label
	 */
	public void addNonScroll(Component comp, String label) {
		tabbedPane_1.add(label, comp);
	}

	private void initGUI() {
		setTitle("CLIENT");
		txtCRName.setToolTipText("Input chatroom name");
		txtCRName.setText("chatroom name");
		txtCRName.setColumns(10);
		textServerName.setToolTipText("IP address");
		textServerName.setEditable(false);
		textServerName.setColumns(10);
		textUsername.setToolTipText("Input your name");
		textUsername.setText("cz39");
		textUsername.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel_top.setForeground(Color.WHITE);
		//		panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));

		contentPane.add(panel_top, BorderLayout.NORTH);

		panel_top.add(panel_name);
		panel_name.setLayout(new GridLayout(2, 2, 0, 0));
		lblUsername.setToolTipText("username");

		panel_name.add(lblUsername);

		panel_name.add(textUsername);
		lblServerName.setToolTipText("server name");

		panel_name.add(lblServerName);

		panel_name.add(textServerName);

		panel_top.add(panel_start);
		btnStart.setToolTipText("Log In");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainView2ModelAdpt.startServer(textUsername.getText().trim(), textServerName.getText().trim());
			}
		});

		panel_start.add(btnStart);
		panel_create.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Create Chat Room",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panel_top.add(panel_create);
		panel_create.setLayout(new GridLayout(2, 1, 0, 0));

		panel_create.add(txtCRName);
		btnCreateCR.setToolTipText("Create chatroom");
		btnCreateCR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainView2ModelAdpt.createChatRoom(txtCRName.getText());
			}
		});

		panel_create.add(btnCreateCR);
		textField.setToolTipText("remote IP");
		textField.setColumns(12);
		panel_top.add(panel_ip);
		panel_ip.setBorder(new TitledBorder(null, "Remote IP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ip.setLayout(new GridLayout(2, 1, 0, 0));

		panel_ip.add(textField);
		btnConnect.setToolTipText("Connect to remote user");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem item = mainView2ModelAdpt.connectTo(textField.getText());
				if (item != null)
					comboBox.insertItemAt(item, 0);
			}
		});

		panel_ip.add(btnConnect);
		panel_request.setBorder(new TitledBorder(null, "Remote", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_top.add(panel_request);
		panel_request.setLayout(new GridLayout(0, 1, 0, 0));
		panel_request.add(comboBox);
		comboBox.setToolTipText("Connected users");
		comboBox.setMaximumRowCount(10);
		panel_request.add(btnRequest);
		btnRequest.setToolTipText("Request chatrooms from remote user");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainView2ModelAdpt.request(comboBox.getItemAt(comboBox.getSelectedIndex()));
			}
		});
		btnRequest.setPreferredSize(new Dimension(160, 28));

		panel_top.add(panel_leave);
		btnQuit.setToolTipText("Quit the app");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainView2ModelAdpt.stop();
			}
		});
		panel_leave.setLayout(new GridLayout(0, 1, 0, 0));

		panel_leave.add(btnQuit);

		contentPane.add(panel_main, BorderLayout.CENTER);
		panel_main.setLayout(new BorderLayout(0, 0));
		tabbedPane.setToolTipText("main panel");

		panel_main.add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab("Log Info", null, scrollPane, "log information");
		txtDefaultInfo.setToolTipText("Log information");

		scrollPane.setViewportView(txtDefaultInfo);

		//		tabbedPane.addTab("User Chat", null, scrollPane_1, null);

		scrollPane_1.setViewportView(tabbedPane_1);

		tabbedPane_1.addTab("ScrollMsg", null, textPane, null);

		//		contentPane.add(panel_8, BorderLayout.SOUTH);
		textField_1.setColumns(15);
		panel_8.add(textField_1);
		btnSendText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = comboBox.getSelectedIndex();
				idx = idx == -1 ? 0 : idx;
				mainView2ModelAdpt.sendMsg(textField_1.getText(), comboBox.getItemAt(idx));
				System.out.println("User send msg----");
			}
		});

		panel_8.add(btnSendText);

		panel_8.add(btnSendImage);
	}
}
