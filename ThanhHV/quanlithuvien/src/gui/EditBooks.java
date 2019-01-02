package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.bean.Books;
import model.bean.Members;
import model.dao.AuthorsDao;
import model.dao.BooksDao;
import model.dao.GenresDao;
import model.dao.MembersDao;
import model.dao.MuonTraDao;
import model.dao.PublishersDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditBooks extends JPanel {
	private AuthorsDao authorsDao;

	private GenresDao genresDao;

	private PublishersDao publishersDao;

	private BooksDao booksDao = new BooksDao();

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("Thông tin sách");

	private JPanel centerPanel = new JPanel();

	private JPanel editPanel = new JPanel();

	private JPanel editInformationPanel = new JPanel();

	private JPanel editInformationLabelPanel = new JPanel();

	private JPanel editInformationTextFieldPanel = new JPanel();

	private JPanel editInformationButtonPanel = new JPanel();

	private JLabel editLabel = new JLabel("Mã sách: ");

	private JTextField editTextField = new JTextField(25);

	private JButton editButton = new JButton("Sửa");

	private JButton deleteButton = new JButton("Xóa");

	private JPanel informationPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[6];

	private String[] informationString = { " Tên sách: ", " Tác giả: ", " Thể loại: ", " Nhà xuất bản: ",
			" Năm xuất bản: ", " Số lượng: " };

	private JComboBox editComboBox = new JComboBox();

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[6];

	private JPanel updateInformationButtonPanel = new JPanel();

	private JButton updateInformationButton = new JButton("Cập nhật thông tin");

	private JPanel southPanel = new JPanel();

	private JButton exitButton = new JButton("Thoát");

	private String[] data = new String[10];

	private JComboBox[] informationComboBox = new JComboBox[3];

	public boolean isCorrect() {
		for (int i = 0; i < informationLabel.length; i++) {
			if (!informationTextField[i].getText().equals("")) {
				data[i] = informationTextField[i].getText();
			} else
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

	public void createInformationTextFieldPanel() {

		informationTextFieldPanel.add(informationTextField[0] = new JTextField(25));

		informationTextField[0].setFont(new Font("Tahoma", Font.PLAIN, 11));

		// ---
		authorsDao = new AuthorsDao();
		informationTextFieldPanel.add(informationComboBox[0] = new JComboBox());
		for (String nameAuthors : authorsDao.getAllNameAuthors()) {
			informationComboBox[0].addItem(nameAuthors);
		}
		informationComboBox[0].setFont(new Font("Tahoma", Font.PLAIN, 11));

		genresDao = new GenresDao();
		informationTextFieldPanel.add(informationComboBox[1] = new JComboBox());
		for (String nameGenres : genresDao.getAllNameGenres()) {
			informationComboBox[1].addItem(nameGenres);
		}
		informationComboBox[1].setFont(new Font("Tahoma", Font.PLAIN, 11));

		publishersDao = new PublishersDao();
		informationTextFieldPanel.add(informationComboBox[2] = new JComboBox());
		for (String namePublishers : publishersDao.getAllNamePublishers()) {
			informationComboBox[2].addItem(namePublishers);
		}
		informationComboBox[2].setFont(new Font("Tahoma", Font.PLAIN, 11));

		// --

		informationTextFieldPanel.add(informationTextField[1] = new JTextField(25));

		informationTextField[1].setFont(new Font("Tahoma", Font.PLAIN, 11));

		informationTextFieldPanel.add(informationTextField[2] = new JTextField(25));

		informationTextField[2].setFont(new Font("Tahoma", Font.PLAIN, 11));

	}

	public void createDataTable() {
		Books books = new Books();
		books = booksDao.getBooksById((String) editComboBox.getSelectedItem());
		informationTextField[0].setText(books.getTitle());
		informationComboBox[0].setSelectedItem(books.getAuthor());
		informationComboBox[1].setSelectedItem(books.getGenre());
		informationComboBox[2].setSelectedItem(books.getPublisher());
		informationTextField[1].setText(books.getYear());
		informationTextField[2].setText(String.valueOf(books.getNumberOfBooks()));
	}

	public void setDataTable() {
		data[0] = (String) editComboBox.getSelectedItem();
		data[1] = informationTextField[0].getText();
		data[2] = (String) informationComboBox[0].getSelectedItem();
		data[3] = (String) informationComboBox[1].getSelectedItem();
		data[4] = (String) informationComboBox[2].getSelectedItem();
		data[5] = informationTextField[1].getText();
		data[6] = informationTextField[2].getText();
	}

	public EditBooks() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(390, 350));
		setMaximumSize(new Dimension(390, 350));
		JPanel cp = this;

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		northPanel.add(northLabel);

		cp.add("North", northPanel);

		centerPanel.setLayout(new BorderLayout());

		editPanel.setLayout(new BorderLayout());

		editPanel.setBorder(BorderFactory.createTitledBorder("Mã sách: "));

		((TitledBorder) editPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		editInformationPanel.setLayout(new BorderLayout());

		editInformationLabelPanel.setLayout(new GridLayout(1, 1, 1, 2));

		editInformationLabelPanel.add(editLabel);

		editLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		// ----

		GridBagLayout gridbag = new GridBagLayout();

		GridBagConstraints c = new GridBagConstraints();

		editInformationPanel.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 4;
		gridbag.setConstraints(editInformationLabelPanel, c);

		editInformationPanel.add(editInformationLabelPanel);
		c.weightx = 9;
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(editComboBox, c);
		editInformationPanel.add(editComboBox);

		// ----
		editPanel.add("North", editInformationPanel);

		editInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		editInformationButtonPanel.add(editButton);

		editButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		editInformationButtonPanel.add(deleteButton);

		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		editPanel.add("South", editInformationButtonPanel);

		centerPanel.add("North", editPanel);

		informationPanel.setLayout(new BorderLayout());

		informationPanel.setBorder(BorderFactory.createTitledBorder("Sửa thông tin sách: "));
		((TitledBorder) informationPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		informationLabelPanel.setLayout(new GridLayout(6, 1, 1, 1));

		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}

		informationPanel.add("West", informationLabelPanel);

		informationTextFieldPanel.setLayout(new GridLayout(6, 1, 1, 1));

		createInformationTextFieldPanel();

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

		for (String idBooks : booksDao.getAllBooksId()) {
			editComboBox.addItem(idBooks);
		}
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createDataTable();
			}
		});
		updateInformationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isCorrect()) {
					setDataTable();
					Books books = new Books(data[0], data[1], data[2], data[3], data[4], data[5],
							Integer.parseInt(data[6]), Integer.parseInt(data[6]));
					if (booksDao.updateBooks(books)) {
						JOptionPane.showMessageDialog(null, "Cập Nhật Sách Thành Công", "Thông báo!",
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
				setDataTable();
				BooksDao booksDao = new BooksDao();
				// editComboBox.getSelectedItem lay id cua sach
				MuonTraDao muonTraDao = new MuonTraDao();
				if (muonTraDao.canDeletedBooks(data[0])) {
					booksDao.removeBooksById(data[0]);
					JOptionPane.showMessageDialog(null, "Xóa sách thành công!", "Thông báo!",
							JOptionPane.WARNING_MESSAGE);
					editComboBox.removeItem(editComboBox.getSelectedItem());
				} else {
					JOptionPane.showMessageDialog(null, "Sách vẫn đang được mượn! Không thể xóa", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
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
				booksDao = new BooksDao();
				for (String idBooks : booksDao.getAllBooksId()) {
					editComboBox.addItem(idBooks);
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

		informationComboBox[0].addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				informationComboBox[0].removeAllItems();
				for (String nameAuthors : authorsDao.getAllNameAuthors()) {
					informationComboBox[0].addItem(nameAuthors);
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
		informationComboBox[1].addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				informationComboBox[1].removeAllItems();
				for (String nameGenres : genresDao.getAllNameGenres()) {
					informationComboBox[1].addItem(nameGenres);
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
		informationComboBox[2].addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				informationComboBox[2].removeAllItems();
				for (String namePublishers : publishersDao.getAllNamePublishers()) {
					informationComboBox[2].addItem(namePublishers);
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
				JFrame frame = new JFrame("Sửa thông tin Sách");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				EditBooks newContentPane = new EditBooks();
				newContentPane.setOpaque(true);
				frame.setContentPane(newContentPane);

				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}

}