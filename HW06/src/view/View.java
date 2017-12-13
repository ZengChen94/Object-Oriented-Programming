package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

/**
 * The view class for the Music Player
 *
 * @author Yuchang Shen
 * @author Chen Zeng
 * @version 1.0
 * @param <TDropListItem> 
 *
 */
public class View<TDropListItem> extends JFrame {

	/**
	 * The serial version ID
	 */
	private static final long serialVersionUID = -1332591522166404893L;

	/**
	 * The components
	 */
	private JPanel contentPane;
	private JTextField txtJtextarea;
	private JTextArea textField2;
	private JTextArea textField1;

	/**
	 * The model adapter is initialized to a no-op to insure that system always has well-defined behavior
	 * This part is from https://canvas.rice.edu/courses/6768/external_tools/1599
	 */
	IModelAdapter<TDropListItem> _modelAdapter;

	/**
	 * The components
	 */
	JComboBox<TDropListItem> comboBox = new JComboBox<TDropListItem>();
	JButton btn_play = new JButton("Play");
	JButton btn_stop = new JButton("Stop");
	JButton btn_load = new JButton("Load");
	JButton btn_parse = new JButton("Parse");

	/**
	 * The View
	 */
	public View(IModelAdapter<TDropListItem> modelAdapter) {
		this._modelAdapter = modelAdapter;
		initGUI();
	}

	/**
	 * Start the app
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_top = new JPanel();
		contentPane.add(panel_top, BorderLayout.NORTH);
		panel_top.setLayout(new GridLayout(0, 7, 0, 0));

		JLabel lblFile = new JLabel("         File: ");
		panel_top.add(lblFile);

		txtJtextarea = new JTextField();
		txtJtextarea.setText("minuet");
		panel_top.add(txtJtextarea);
		txtJtextarea.setColumns(10);

		/**
		 * Load button
		 */
		btn_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField1.setText(_modelAdapter.loadMusic(txtJtextarea.getText()));
				btn_parse.setEnabled(true);
			}
		});
		panel_top.add(btn_load);

		/**
		 * Parse button
		 */
		btn_parse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField2.setText(_modelAdapter.parseMusic());
				btn_play.setEnabled(true);
			}
		});
		panel_top.add(btn_parse);

		comboBox.setPreferredSize(new Dimension(200, 21));
		panel_top.add(comboBox);

		/**
		 * Play button
		 */
		btn_play.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				_modelAdapter.playMusic((TDropListItem) comboBox.getSelectedItem());
				btn_stop.setEnabled(true);
			}
		});
		panel_top.add(btn_play);

		/**
		 * Stop button
		 */
		btn_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelAdapter.stopMusic();
				btn_stop.setEnabled(false);
			}
		});
		panel_top.add(btn_stop);

		JSplitPane panel_main = new JSplitPane();
		panel_main.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(panel_main, BorderLayout.CENTER);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setPreferredSize(new Dimension(2, 200));
		panel_main.setLeftComponent(scrollPane1);

		/**
		 * Field that includes the contents of music
		 */
		textField1 = new JTextArea();
		textField1
				.setBorder(new TitledBorder(null, "File Contents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane1.setViewportView(textField1);
		textField1.setColumns(10);

		JScrollPane scrollPane2 = new JScrollPane();
		panel_main.setRightComponent(scrollPane2);

		/**
		 * Field that includes the parsed contents of music
		 */
		textField2 = new JTextArea();
		textField2.setBorder(
				new TitledBorder(null, "Parsed IPhrase structure", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane2.setViewportView(textField2);
		textField2.setColumns(10);
	}

	public void addInstrument(TDropListItem instrument) {
		this.comboBox.addItem(instrument);
	}
}
