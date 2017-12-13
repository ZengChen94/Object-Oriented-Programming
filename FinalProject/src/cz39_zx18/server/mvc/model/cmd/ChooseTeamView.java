package cz39_zx18.server.mvc.model.cmd;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import common.ICRCmd2ModelAdapter;
import common.IChatRoom;
import common.IReceiver;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Choose team gui
 */
public class ChooseTeamView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5496100901654596099L;
	private final JPanel panel = new JPanel();
	private final JComboBox<IChatRoom> comboBox = new JComboBox<IChatRoom>();
	private final JButton btnNewButton = new JButton("Confirm");
	private final JTextField textField = new JTextField();
	private final JLabel lblSelectTeam = new JLabel("Select Team");
	private ICRCmd2ModelAdapter cmd2ModelAdpt;
	private IReceiver receiverStub;

	private ArrayList<IChatRoom> chatrooms = new ArrayList<IChatRoom>();

	/**
	 * Create the panel.
	 * @param items the chatrooms
	 * @param cmd2ModelAdpt adapter from cmd to model 
	 * @param receiverStub receiver stub
	 */
	public ChooseTeamView(Iterable<IChatRoom> items, ICRCmd2ModelAdapter cmd2ModelAdpt, IReceiver receiverStub) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
		this.receiverStub = receiverStub;
		for (IChatRoom t : items) {
			this.chatrooms.add(t);
		}

		initGUI();

		for (IChatRoom t : items)
			comboBox.insertItemAt(t, 0);
	}

	private void initGUI() {
		textField.setColumns(10);
		setLayout(new BorderLayout(0, 0));
		add(panel, BorderLayout.NORTH);
		panel.add(lblSelectTeam);
		panel.add(comboBox);
		comboBox.setPreferredSize(new Dimension(150, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmd2ModelAdpt.sendTo(receiverStub, SelectTeam.class,
						new SelectTeam(comboBox.getItemAt(comboBox.getSelectedIndex())));
			}
		});
		panel.add(btnNewButton);

		//		Object[] chatRooms = this.chatrooms.toArray();
		//		Object choose = JOptionPane.showInputDialog(null, "Choose one team", "Select a team", JOptionPane.INFORMATION_MESSAGE, null, chatRooms, chatRooms.length > 0 ? chatRooms[0] : null);
		//		if (choose == null) {
		//			return;
		//		}
		//		cmd2ModelAdpt.sendTo(receiverStub, SelectTeam.class, new SelectTeam((IChatRoom)choose));

	}
}