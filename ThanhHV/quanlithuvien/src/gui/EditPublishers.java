package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.bean.Publishers;
import model.dao.GenresDao;
import model.dao.PublishersDao;

public class EditPublishers extends JPanel {

	private PublishersDao publishersDao;

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("Thông tin nhà xuất bản");

	private JPanel centerPanel = new JPanel();

	private JPanel editPanel = new JPanel();

	private JPanel editInformationPanel = new JPanel();

	private JPanel editInformationLabelPanel = new JPanel();

	private JPanel editInformationTextFieldPanel = new JPanel();

	private JPanel editInformationButtonPanel = new JPanel();

	private JLabel editLabel = new JLabel("Mã nhà xuất bản: ");

	private JTextField editTextField = new JTextField(25);

	private JButton editButton = new JButton("Sửa");

	private JButton deleteButton = new JButton("Xóa");

	private JPanel informationPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[1];

	private String[] informationString = { " Tên nhà xuất bản: " };

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[1];

	private JComboBox editComboBox;

	private JPanel updateInformationButtonPanel = new JPanel();

	private JButton updateInformationButton = new JButton("Cập nhật thông tin");

	private JPanel southPanel = new JPanel();

	private JButton exitButton = new JButton("Thoát");

	private String[] data = new String[10];

	public boolean isCorrect() {
		setData();

		if (data[0].equals("")) {
			return false;
		}
		if (data[1].equals("")) {
			return false;
		}
		return true;
	}

	public void setData() {
		data[0] = (String) editComboBox.getSelectedItem();
		data[1] = informationTextField[0].getText();
	}

	public boolean isEditCorrect() {
		if (editTextField.getText().equals(""))
			return false;
		return true;
	}

	public void clearTextField() {
		for (int i = 0; i < informationTextField.length; i++) {
			editTextField.setText(null);
			informationTextField[i].setText(null);
		}
	}

	public EditPublishers() {
		setPreferredSize(new Dimension(400, 220));
		setMaximumSize(new Dimension(400, 220));
		JPanel cp = this;
		setLayout(new BorderLayout());

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		northPanel.add(northLabel);

		cp.add("North", northPanel);

		centerPanel.setLayout(new BorderLayout());

		editPanel.setLayout(new BorderLayout());

		editPanel.setBorder(BorderFactory.createTitledBorder("Mã nhà xuất bản: "));
		((TitledBorder) editPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		editInformationPanel.setLayout(new BorderLayout());

		editInformationLabelPanel.setLayout(new GridLayout(1, 1, 1, 2));

		editInformationLabelPanel.add(editLabel);

		editLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		// -----

		GridBagLayout gridbag = new GridBagLayout();

		GridBagConstraints c = new GridBagConstraints();

		editInformationPanel.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 2;
		gridbag.setConstraints(editInformationLabelPanel, c);

		editInformationPanel.add(editInformationLabelPanel);
		c.weightx = 10;
		c.gridwidth = GridBagConstraints.REMAINDER;
		publishersDao = new PublishersDao();
		List<String> listIdPublishers = publishersDao.getAllIdPublishers();
		informationTextFieldPanel.add(editComboBox = new JComboBox());
		for (String idPublishers : listIdPublishers) {
			editComboBox.addItem(idPublishers);
		}
		gridbag.setConstraints(editComboBox, c);
		editInformationPanel.add(editComboBox);

		editComboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		// -----

		editPanel.add("North", editInformationPanel);

		editInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		editInformationButtonPanel.add(editButton);

		editButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		editInformationButtonPanel.add(deleteButton);

		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		editPanel.add("South", editInformationButtonPanel);

		centerPanel.add("North", editPanel);

		informationPanel.setLayout(new BorderLayout());

		informationPanel.setBorder(BorderFactory.createTitledBorder("Sửa thông tin nhà xuất bản: "));
		((TitledBorder) informationPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		informationLabelPanel.setLayout(new GridLayout(1, 1, 1, 1));

		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		informationPanel.add("West", informationLabelPanel);

		// for setting the layout
		informationTextFieldPanel.setLayout(new GridLayout(1, 1, 1, 1));
		/***********************************************************************
		 * for adding the strings to the labels, for setting the font * and adding these
		 * labels to the panel. * finally adding the panel to the container *
		 ***********************************************************************/
		for (int i = 0; i < informationTextField.length; i++) {
			informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));
			informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
		}
		informationPanel.add("East", informationTextFieldPanel);

		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button* and
		 * adding the button to the panel. * finally adding the panel to the container *
		 ***********************************************************************/
		updateInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		updateInformationButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateInformationButtonPanel.add(updateInformationButton);
		informationPanel.add("South", updateInformationButtonPanel);
		centerPanel.add("Center", informationPanel);
		cp.add("Center", centerPanel);

		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button* adding
		 * the button to the panel & setting the border. * finally adding the panel to
		 * the container *
		 ***********************************************************************/
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		southPanel.add(exitButton);
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		cp.add("South", southPanel);

		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				publishersDao = new PublishersDao();
				String namePublishers = publishersDao.getNamePublishersById((String) editComboBox.getSelectedItem());
				informationTextField[0].setText(namePublishers);
			}
		});

		updateInformationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isCorrect()) {
					setData();
					Publishers publishers = new Publishers(data[0], data[1]);
					publishersDao = new PublishersDao();
					if (publishersDao.updatePublishers(publishers)) {
						JOptionPane.showMessageDialog(null, "Cập Nhật Thông Tin NXB", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập lại thông tin!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setData();
				publishersDao.removePublishersById(data[0]);
				editComboBox.removeItem(editComboBox.getSelectedItem());
			}
		});

		editComboBox.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				editComboBox.removeAllItems();
				publishersDao = new PublishersDao();
				List<String> listIdPublishers = publishersDao.getAllIdPublishers();
				for (String id : listIdPublishers) {
					editComboBox.addItem(id);
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new JFrame("Sửa thông tin nhà xuất bản");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Create and set up the content pane.
				EditPublishers newContentPane = new EditPublishers();
				newContentPane.setOpaque(true); // content panes must be opaque
				frame.setContentPane(newContentPane);

				// Display the window.
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}

}