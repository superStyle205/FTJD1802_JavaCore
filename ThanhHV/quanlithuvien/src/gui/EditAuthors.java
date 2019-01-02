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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.bean.Authors;
import model.bean.Books;
import model.dao.AuthorsDao;
import model.dao.PublishersDao;

public class EditAuthors extends JPanel {
	private AuthorsDao authorsDao;

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("Thông tin tác giả");

	private JPanel centerPanel = new JPanel();

	private JPanel editPanel = new JPanel();

	private JPanel editInformationPanel = new JPanel();

	private JPanel editInformationLabelPanel = new JPanel();

	private JPanel editInformationTextFieldPanel = new JPanel();

	private JPanel editInformationButtonPanel = new JPanel();

	private JLabel editLabel = new JLabel("Mã tác giả: ");

	private JTextField editTextField = new JTextField(25);

	private JComboBox editComboBox;

	private JButton editButton = new JButton("Sửa");

	private JButton deleteButton = new JButton("Xóa");

	private JPanel informationPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[1];

	private String[] informationString = { " Tên tác giả: " };

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[1];

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

	public void setData() {
		data[0] = (String) editComboBox.getSelectedItem();
		data[1] = informationTextField[0].getText();
	}

	public EditAuthors() {
		setLayout(new BorderLayout());
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

		editPanel.setBorder(BorderFactory.createTitledBorder("Mã tác giả: "));
		((TitledBorder) editPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));
		editInformationLabelPanel.setLayout(new GridLayout(1, 1, 1, 1));
		editInformationLabelPanel.add(editLabel);
		editLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		// ---
		GridBagLayout gridbag = new GridBagLayout();

		GridBagConstraints c = new GridBagConstraints();

		editInformationPanel.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 3;
		gridbag.setConstraints(editInformationLabelPanel, c);

		editInformationPanel.add(editInformationLabelPanel);
		c.weightx = 7;

		c.gridwidth = GridBagConstraints.REMAINDER;

		authorsDao = new AuthorsDao();

		informationTextFieldPanel.add(editComboBox = new JComboBox());

		for (String idAuthors : authorsDao.getAllIdAuthors()) {
			editComboBox.addItem(idAuthors);
		}
		gridbag.setConstraints(editComboBox, c);

		editInformationPanel.add(editComboBox);

		editComboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));

		editPanel.add("North", editInformationPanel);

		editInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		editInformationButtonPanel.add(editButton);

		editButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		editInformationButtonPanel.add(deleteButton);

		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		editPanel.add("South", editInformationButtonPanel);

		centerPanel.add("North", editPanel);

		informationPanel.setLayout(new BorderLayout());

		informationPanel.setBorder(BorderFactory.createTitledBorder("Sửa thông tin tác giả: "));
		((TitledBorder) informationPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		informationLabelPanel.setLayout(new GridLayout(1, 1, 1, 1));

		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		informationPanel.add("West", informationLabelPanel);

		informationTextFieldPanel.setLayout(new GridLayout(1, 1, 1, 1));

		for (int i = 0; i < informationTextField.length; i++) {
			informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));
			informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
		}
		informationPanel.add("East", informationTextFieldPanel);
		updateInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		updateInformationButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateInformationButtonPanel.add(updateInformationButton);
		informationPanel.add("South", updateInformationButtonPanel);
		centerPanel.add("Center", informationPanel);
		cp.add("Center", centerPanel);

		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		southPanel.add(exitButton);
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		cp.add("South", southPanel);
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				authorsDao = new AuthorsDao();
				String nameAuthors = authorsDao.getNameById((String) editComboBox.getSelectedItem());
				informationTextField[0].setText(nameAuthors);
			}
		});
		updateInformationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isCorrect()) {
					setData();
					Authors authors = new Authors(data[0], data[1]);
					authorsDao = new AuthorsDao();
					if (authorsDao.updateAuthors(authors)) {
						JOptionPane.showMessageDialog(null, "Cập Nhật Tác Giả Thành Công", "Thông báo!",
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
				authorsDao.removeAuthorsById(data[0]);
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
				authorsDao = new AuthorsDao();
				for (String id : authorsDao.getAllIdAuthors()) {
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
				JFrame frame = new JFrame("Sửa thông tin tác giả");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				EditAuthors newContentPane = new EditAuthors();
				newContentPane.setOpaque(true);
				frame.setContentPane(newContentPane);

				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}

}