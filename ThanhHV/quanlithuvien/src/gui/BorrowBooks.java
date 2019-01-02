package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.bean.Members;
import model.dao.BooksDao;
import model.dao.MembersDao;
import model.dao.MuonTraDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BorrowBooks extends JPanel {

	private MembersDao membersDao = new MembersDao();

	private BooksDao booksDao = new BooksDao();

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("MƯỢN SÁCH");

	private JPanel centerPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[5];

	private String[] informationString = { " Mã bạn đọc: ", " Mã sách 1: ", " Mã sách 2: ", " Mã sách 3: ",
			" Ngày mượn: " };

	private JComboBox[] informationComboBox = new JComboBox[4];

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[4];

	private JLabel informationDateLabel = new JLabel();

	private JPanel insertInformationButtonPanel = new JPanel();

	private JButton insertInformationButton = new JButton("Thêm thông tin");

	private JButton borrowBook1Button = new JButton("Mượn sách 1");

	private JButton borrowBook2Button = new JButton("Mượn sách 2");

	private JButton borrowBook3Button = new JButton("Mượn sách 3");

	private JPanel southPanel = new JPanel();

	private JButton OKButton = new JButton("Thoát");

	private String[] data;

	private String dateString;

	public boolean isCorrect() {
		data = new String[2];
		for (int i = 0; i < informationLabel.length; i++) {
			if (!informationTextField[i].getText().equals("")) {
				data[i] = informationTextField[i].getText();
			} else {
				return false;
			}
		}
		return true;
	}

	public void clearTextField() {
		for (int i = 0; i < informationTextField.length; i++) {
			informationTextField[i].setText(null);
		}
	}

	public void reformatDate() {
		String currentPattern = "yyyy-MM-dd";
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(currentPattern);
		try {
			dateString = formatter.format(today);
			informationDateLabel.setForeground(Color.black);
			informationDateLabel.setText(dateString);
		} catch (IllegalArgumentException iae) {
			informationDateLabel.setForeground(Color.red);
			informationDateLabel.setText("Error: " + iae.getMessage());
		}
	}

	public boolean isBorrowed(String idBook) {
		membersDao = new MembersDao();
		Members members = membersDao.getMembersById((String) informationComboBox[0].getSelectedItem());
		MuonTraDao muonTraDao = new MuonTraDao();
		if (!muonTraDao.isBorrowed((String) informationComboBox[0].getSelectedItem(), idBook)) {
			return false;
		}
		return (members.getNumberOfBooks() < 3);
	}

	public BorrowBooks() {
		reformatDate();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 300));
		setMaximumSize(new Dimension(400, 300));

		JPanel cp = this;

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		northPanel.add(northLabel);

		cp.add(northPanel, BorderLayout.NORTH);

		centerPanel.setLayout(new BorderLayout());

		centerPanel.setBorder(BorderFactory.createTitledBorder("Mượn sách"));
		((TitledBorder) centerPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		informationLabelPanel.setLayout(new GridLayout(6, 1, 1, 5));

		informationLabelPanel.add(new JLabel());
		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		centerPanel.add(informationLabelPanel, BorderLayout.WEST);

		informationTextFieldPanel.setLayout(new GridLayout(6, 1, 1, 5));

		JTextField temptJtextField = new JTextField(25);
		informationTextFieldPanel.add(temptJtextField);
		temptJtextField.setVisible(false);
		for (int i = 0; i < informationLabel.length - 1; i++) {
			informationTextFieldPanel.add(informationComboBox[i] = new JComboBox());
		}
		// 1 --------------------------

		for (String idBanDoc : membersDao.getAllMembersId()) {
			informationComboBox[0].addItem(idBanDoc);
		}
		JButton jbutton = GetButtonSubComponent.getButtonSubComponent(informationComboBox[0]);
		jbutton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

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
				informationComboBox[0].removeAllItems();
				for (String id : membersDao.getAllMembersId()) {
					informationComboBox[0].addItem(id);
				}

			}
		});

		AutoCompletion.enable(informationComboBox[0]);

		// reloadId(informationComboBox[0], membersDao.getAllMembersId());

		// 2 --------------------------

		for (String idBooks : booksDao.getAllBooksId()) {
			informationComboBox[1].addItem(idBooks);
		}

		jbutton = GetButtonSubComponent.getButtonSubComponent(informationComboBox[1]);
		jbutton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

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
				informationComboBox[1].removeAllItems();
				for (String id : booksDao.getAllBooksId()) {
					informationComboBox[1].addItem(id);
				}

			}
		});

		AutoCompletion.enable(informationComboBox[1]);

		// reloadId(informationComboBox[1], booksDao.getAllBooksId());

		// 3 --------------------------

		for (String idBooks : booksDao.getAllBooksId()) {
			informationComboBox[2].addItem(idBooks);
		}

		jbutton = GetButtonSubComponent.getButtonSubComponent(informationComboBox[2]);
		jbutton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

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
				informationComboBox[2].removeAllItems();
				for (String id : booksDao.getAllBooksId()) {
					informationComboBox[2].addItem(id);
				}

			}
		});
		AutoCompletion.enable(informationComboBox[2]);
		// reloadId(informationComboBox[2], booksDao.getAllBooksId());

		// 4 --------------------------

		for (String idBooks : booksDao.getAllBooksId()) {
			informationComboBox[3].addItem(idBooks);
		}

		jbutton = GetButtonSubComponent.getButtonSubComponent(informationComboBox[3]);
		jbutton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

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
				informationComboBox[3].removeAllItems();
				for (String id : booksDao.getAllBooksId()) {
					informationComboBox[3].addItem(id);
				}

			}
		});

		AutoCompletion.enable(informationComboBox[3]);
		// reloadId(informationComboBox[3], booksDao.getAllBooksId());

		informationTextFieldPanel.add(informationDateLabel);
		informationDateLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		centerPanel.add(informationTextFieldPanel, BorderLayout.EAST);

		insertInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		insertInformationButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		insertInformationButtonPanel.add(borrowBook1Button);
		insertInformationButtonPanel.add(borrowBook2Button);
		insertInformationButtonPanel.add(borrowBook3Button);
		centerPanel.add("South", insertInformationButtonPanel);
		cp.add(centerPanel, BorderLayout.CENTER);

		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		OKButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		southPanel.add(OKButton);
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		cp.add(southPanel, BorderLayout.SOUTH);

		borrowBook1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isBorrowed((String) informationComboBox[1].getSelectedItem())) {
					MuonTraDao muonTraDao = new MuonTraDao();
					MembersDao membersDao = new MembersDao();
					if (muonTraDao.borrowBooks((String) informationComboBox[0].getSelectedItem(),
							(String) informationComboBox[1].getSelectedItem(), (String) dateString)) {
						JOptionPane.showMessageDialog(null, "Mượn sách thành công!", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
						Members members = membersDao.getMembersById((String) informationComboBox[0].getSelectedItem());
						members.setNumberOfBooks(members.getNumberOfBooks() + 1);
						membersDao.updateMembers(members);
						BooksDao booksDao = new BooksDao();
						booksDao.borrowBooks((String) informationComboBox[1].getSelectedItem());
					} else {
						JOptionPane.showMessageDialog(null, "Mượn sách không thành công!", "Cảnh báo!",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Mượn sách không thành công!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		borrowBook2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isBorrowed((String) informationComboBox[2].getSelectedItem())) {
					MuonTraDao muonTraDao = new MuonTraDao();
					MembersDao membersDao = new MembersDao();
					if (muonTraDao.borrowBooks((String) informationComboBox[0].getSelectedItem(),
							(String) informationComboBox[2].getSelectedItem(), (String) dateString)) {
						JOptionPane.showMessageDialog(null, "Mượn sách thành công!", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
						Members members = membersDao.getMembersById((String) informationComboBox[0].getSelectedItem());
						members.setNumberOfBooks(members.getNumberOfBooks() + 1);
						membersDao.updateMembers(members);
						BooksDao booksDao = new BooksDao();
						booksDao.borrowBooks((String) informationComboBox[2].getSelectedItem());
					} else {
						JOptionPane.showMessageDialog(null, "Mượn sách không thành công!", "Cảnh báo!",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Mượn sách không thành công!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		borrowBook3Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isBorrowed((String) informationComboBox[3].getSelectedItem())) {
					MuonTraDao muonTraDao = new MuonTraDao();
					MembersDao membersDao = new MembersDao();
					if (muonTraDao.borrowBooks((String) informationComboBox[0].getSelectedItem(),
							(String) informationComboBox[3].getSelectedItem(), (String) dateString)) {
						JOptionPane.showMessageDialog(null, "Mượn sách thành công!", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
						Members members = membersDao.getMembersById((String) informationComboBox[0].getSelectedItem());
						members.setNumberOfBooks(members.getNumberOfBooks() + 1);
						membersDao.updateMembers(members);
						BooksDao booksDao = new BooksDao();
						booksDao.borrowBooks((String) informationComboBox[3].getSelectedItem());
					} else {
						JOptionPane.showMessageDialog(null, "Mượn sách không thành công!", "Cảnh báo!",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Mượn sách không thành công!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new JFrame("Mượn Sách");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				BorrowBooks newContentPane = new BorrowBooks();
				newContentPane.setOpaque(true);
				frame.setContentPane(newContentPane);
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}

}