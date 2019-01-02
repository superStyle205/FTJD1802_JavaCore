package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

//import the packages for using the classes in them into the program
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.bean.Books;
import model.dao.AuthorsDao;
import model.dao.BooksDao;
import model.dao.GenresDao;
import model.dao.PublishersDao;

/**
 * A public class
 */
public class AddBooks extends JPanel {

	private AuthorsDao authorsDao = new AuthorsDao();

	private GenresDao genresDao = new GenresDao();

	private PublishersDao publishersDao = new PublishersDao();

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("THÔNG TIN SÁCH");

	private JPanel centerPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[7];

	private String[] informationString = { " Mã sách: ", " Tên sách: ", " Tác giả: ", " Thể loại: ", " Nhà xuất bản: ",
			" Năm xuất bản: ", " Số lượng: " };

	private String[] textPromptString = { "000000001 - Mã có 9 chữ số ", "Lập Trình Java", "2008 - Năm",
			"10 - Số lượng" };

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[7];

	private JPanel insertInformationButtonPanel = new JPanel();

	private JButton insertInformationButton = new JButton("Thêm thông tin");

	private JPanel southPanel = new JPanel();

	private JButton OKButton = new JButton("Thoát");

	private String[] data;

	private JComboBox[] informationComboBox = new JComboBox[3];

	private TextPrompt[] informationTextPrompt = new TextPrompt[9];

	private boolean availble = true;

	public void createDataTable() {

		informationTextFieldPanel.add(informationTextField[0] = new JTextField(25));

		informationTextPrompt[0] = new TextPrompt(textPromptString[0], informationTextField[0]);

		informationTextPrompt[0].setForeground(Color.BLACK);

		informationTextPrompt[0].changeAlpha(0.5f);

		informationTextPrompt[0].changeStyle(Font.BOLD + Font.ITALIC);

		informationTextField[0].setFont(new Font("Tahoma", Font.PLAIN, 11));

		informationTextFieldPanel.add(informationTextField[1] = new JTextField(25));

		informationTextPrompt[1] = new TextPrompt(textPromptString[1], informationTextField[1]);

		informationTextPrompt[1].setForeground(Color.BLACK);

		informationTextPrompt[1].changeAlpha(0.5f);

		informationTextPrompt[1].changeStyle(Font.BOLD + Font.ITALIC);

		informationTextField[1].setFont(new Font("Tahoma", Font.PLAIN, 11));

		// ---

		informationTextFieldPanel.add(informationComboBox[0] = new JComboBox());

		for (String nameAuthors : authorsDao.getAllNameAuthors()) {
			informationComboBox[0].addItem(nameAuthors);
		}
		informationComboBox[0].setFont(new Font("Tahoma", Font.PLAIN, 11));

		informationTextFieldPanel.add(informationComboBox[1] = new JComboBox());

		for (String nameGenres : genresDao.getAllNameGenres()) {
			informationComboBox[1].addItem(nameGenres);
		}
		informationComboBox[1].setFont(new Font("Tahoma", Font.PLAIN, 11));

		informationTextFieldPanel.add(informationComboBox[2] = new JComboBox());

		for (String namePublishers : publishersDao.getAllNamePublishers()) {
			informationComboBox[2].addItem(namePublishers);
		}
		informationComboBox[2].setFont(new Font("Tahoma", Font.PLAIN, 11));

		// --

		informationTextFieldPanel.add(informationTextField[2] = new JTextField(25));
		informationTextPrompt[2] = new TextPrompt(textPromptString[2], informationTextField[2]);
		informationTextPrompt[2].setForeground(Color.BLACK);
		informationTextPrompt[2].changeAlpha(0.5f);
		informationTextPrompt[2].changeStyle(Font.BOLD + Font.ITALIC);

		informationTextField[2].setFont(new Font("Tahoma", Font.PLAIN, 11));

		informationTextFieldPanel.add(informationTextField[3] = new JTextField(25));
		informationTextPrompt[3] = new TextPrompt(textPromptString[3], informationTextField[3]);
		informationTextPrompt[3].setForeground(Color.BLACK);
		informationTextPrompt[3].changeAlpha(0.5f);
		informationTextPrompt[3].changeStyle(Font.BOLD + Font.ITALIC);

		informationTextField[3].setFont(new Font("Tahoma", Font.PLAIN, 11));
	}

	public boolean isCorrect() {
		data = new String[10];

		BooksDao booksDao = new BooksDao();

		if (informationTextField[0].getText().equals("")
				|| (booksDao.checkIdExisted((String) informationTextField[0].getText()))
				|| (!informationTextField[0].getText().matches(MainGui.PATTERN_ID_BOOKS))) {
			return false;
		}

		if (informationTextField[1].getText().equals("")) {
			return false;
		}
		if (informationTextField[2].getText().equals("")) {
			return false;
		}
		if (informationTextField[3].getText().equals("")) {
			return false;
		}
		if (informationComboBox[0].getSelectedItem().equals("")) {
			return false;
		}
		if (informationComboBox[1].getSelectedItem().equals("")) {
			return false;
		}
		if (informationComboBox[2].getSelectedItem().equals("")) {
			return false;
		}
		data[0] = informationTextField[0].getText();
		data[1] = informationTextField[1].getText();
		data[2] = (String) informationComboBox[0].getSelectedItem();
		data[3] = (String) informationComboBox[1].getSelectedItem();
		data[4] = (String) informationComboBox[2].getSelectedItem();
		data[5] = informationTextField[2].getText();
		data[6] = informationTextField[3].getText();

		return true;
	}

	public void clearTextField() {
		for (int i = 0; i < informationTextField.length; i++) {
			informationTextField[i].setText(null);
		}
	}

	public AddBooks() {

		setLayout(new BorderLayout());

		setPreferredSize(new Dimension(400, 300));

		setMaximumSize(new Dimension(400, 300));

		this.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JPanel cp = this;

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		northPanel.add(northLabel);

		cp.add(northPanel, BorderLayout.NORTH);

		centerPanel.setLayout(new BorderLayout());

		centerPanel.setBorder(BorderFactory.createTitledBorder("Thêm sách"));
		((TitledBorder) centerPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		informationLabelPanel.setLayout(new GridLayout(7, 1, 1, 1));

		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		centerPanel.add(informationLabelPanel, BorderLayout.WEST);

		informationTextFieldPanel.setLayout(new GridLayout(7, 1, 1, 1));

		createDataTable();

		centerPanel.add(informationTextFieldPanel, BorderLayout.EAST);

		insertInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		insertInformationButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		insertInformationButtonPanel.add(insertInformationButton);

		centerPanel.add("South", insertInformationButtonPanel);

		cp.add(centerPanel, BorderLayout.CENTER);

		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		OKButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		southPanel.add(OKButton);

		southPanel.setBorder(BorderFactory.createEtchedBorder());

		cp.add(southPanel, BorderLayout.SOUTH);

		setVisible(true);

		insertInformationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isCorrect()) {
					Books books = new Books(data[0], data[1], data[2], data[3], data[4], data[5],
							Integer.parseInt(data[6]), Integer.parseInt(data[6]));
					BooksDao booksDao = new BooksDao();
					if (booksDao.addBooks(books)) {
						JOptionPane.showMessageDialog(null, "Thêm Sách Thành Công", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập lại thông tin!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}

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

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("Thêm thông tin sách");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				AddBooks newContentPane = new AddBooks();
				newContentPane.setOpaque(true);
				frame.setContentPane(newContentPane);
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}

}