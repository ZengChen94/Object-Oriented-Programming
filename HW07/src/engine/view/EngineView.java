package engine.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

/**
 * The view class for the Engine
 *
 * @author Zhenwei Feng
 * @author Chen Zeng
 * @version 1.0
 *
 */

public class EngineView extends JFrame {

	/**
	 * The serial version ID
	 */
	private static final long serialVersionUID = 5239601649704057738L;
	private JPanel contentPane;
	private JTextField txtMsg;

	/**
	 * Text area to display
	 */
	JTextArea textArea = new JTextArea();

	/**
	 * Adapter
	 */
	private IEngineModelAdapter _model;

	/**
	 * Start the view
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Construction of Engine
	 */
	public EngineView(IEngineModelAdapter modelAdpt) {
		super("Engine GUI");
		this._model = modelAdpt;
		initGUI();
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_top = new JPanel();
		contentPane.add(panel_top, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_top.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnQuit = new JButton("Quit");
		btnQuit.setToolTipText("Shutdown the RMI system and quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("quitBtn.actionPerformed, event=" + e);
				_model.quit();
			}
		});
		panel_1.add(btnQuit);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Send msg to remote client's view", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_top.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		txtMsg = new JTextField();
		txtMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_model.sendMsgToRemote(txtMsg.getText());
			}
		});
		txtMsg.setText("Hit Enter to send Msg...");
		txtMsg.setToolTipText("Hit Enter to send the string to the remote client to be displayed on its view");
		panel_2.add(txtMsg);
		txtMsg.setColumns(20);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(textArea);
	}

	/**
	 * Append text to textArea
	 */
	public void append(String s) {
		textArea.append(s);
		textArea.setCaretPosition(textArea.getText().length());
	}

}
