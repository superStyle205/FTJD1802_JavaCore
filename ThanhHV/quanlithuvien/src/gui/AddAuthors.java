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

//import the packages for using the classes in them into the program
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.bean.Authors;
import model.dao.AuthorsDao;
import model.dao.GenresDao;
import model.dao.PublishersDao;

public class AddAuthors extends JPanel {

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("THÔNG TIN TÁC GIẢ");

	private JPanel centerPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[2];

	private JTextField txtShelfNo = new JTextField();

	private String[] informationString = { " Mã tác giả: ", " Tên tác giả: " };

	private String[] textPromptString = { "000000001 - Mã có 9 chữ số ", "James Dasher - Tên tác giả" };

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[2];

	private JPanel insertInformationButtonPanel = new JPanel();

	private JButton insertInformationButton = new JButton("Thêm thông tin");

	private JPanel southPanel = new JPanel();

	private JButton OKButton = new JButton("Thoát");

	private String[] data;

	private TextPrompt[] informationTextPrompt = new TextPrompt[9];

	public boolean isCorrect() {
		data = new String[2];
		for (int i = 0; i < informationLabel.length; i++) {
			if (!informationTextField[i].getText().equals("")) {
				data[i] = informationTextField[i].getText();
			} else {
				return false;
			}
		}
		AuthorsDao authorsDao = new AuthorsDao();
		if (authorsDao.checkIdExisted((String) informationTextField[0].getText())
				|| (!informationTextField[0].getText().matches(MainGui.PATTERN_ID_AUTHORS))) {
			return false;
		}
		return true;
	}

	public void clearTextField() {
		for (int i = 0; i < informationTextField.length; i++) {
			informationTextField[i].setText(null);
		}
		txtShelfNo.setText(null);
	}

	public void createDataTable() {
		for (int i = 0; i < informationTextField.length; i++) {
			informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));
			informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
			informationTextPrompt[i] = new TextPrompt(textPromptString[i], informationTextField[i]);
			informationTextPrompt[i].setForeground(Color.BLACK);
			informationTextPrompt[i].changeAlpha(0.5f);
			informationTextPrompt[i].changeStyle(Font.BOLD + Font.ITALIC);

		}
	}

	public AddAuthors() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(350, 170));
		setMaximumSize(new Dimension(350, 170));
		JPanel cp = this;

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		northPanel.add(northLabel);

		cp.add(northPanel, BorderLayout.NORTH);

		centerPanel.setLayout(new BorderLayout());

		centerPanel.setBorder(BorderFactory.createTitledBorder("Thêm thành viên"));
		((TitledBorder) centerPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		informationLabelPanel.setLayout(new GridLayout(2, 1, 1, 1));

		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		centerPanel.add(informationLabelPanel, BorderLayout.WEST);

		informationTextFieldPanel.setLayout(new GridLayout(2, 1, 1, 1));

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

		insertInformationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isCorrect()) {
					Authors authors = new Authors(data[0], data[1]);
					AuthorsDao authorsDao = new AuthorsDao();
					if (authorsDao.addAuthors(authors)) {
						JOptionPane.showMessageDialog(null, "Thêm Tác Giả Thành Công", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập lại thông tin!", "Cảnh báo!",
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
				JFrame frame = new JFrame("Thêm thông tin bạn đọc");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				AddAuthors newContentPane = new AddAuthors();
				newContentPane.setOpaque(true);
				frame.setContentPane(newContentPane);

				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}

}