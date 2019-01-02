package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.bean.Authors;
import model.bean.Publishers;
import model.dao.AuthorsDao;
import model.dao.PublishersDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.Flow.Publisher;

public class AddPublishers extends JPanel {

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("THÔNG TIN NHÀ XUẤT BẢN");

	private JPanel centerPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[2];

	private String[] informationString = { " Mã nhà xuất bản: ", " Tên nhà xuất bản: " };

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[2];

	private String[] textPromptString = { "000000001 - Mã có 9 chữ số ", "NXB Giáo dục - Tên nhà xuất bản" };

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
		PublishersDao publishersDao = new PublishersDao();
		if (publishersDao.checkIdExisted((String) informationTextField[0].getText())
				|| (!informationTextField[0].getText().matches(MainGui.PATTERN_ID_PUBLISHERS))) {
			return false;
		}
		return true;
	}

	public void clearTextField() {
		for (int i = 0; i < informationTextField.length; i++) {
			informationTextField[i].setText(null);
		}
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

	public AddPublishers() {
		setLayout(new BorderLayout());

		setPreferredSize(new Dimension(400, 170));

		setMaximumSize(new Dimension(400, 170));

		JPanel cp = this;

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		northPanel.add(northLabel);

		cp.add(northPanel, BorderLayout.NORTH);

		centerPanel.setLayout(new BorderLayout());

		centerPanel.setBorder(BorderFactory.createTitledBorder("Thêm nhà xuất bản"));
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
					Publishers publishers = new Publishers(data[0], data[1]);
					PublishersDao publishersDao = new PublishersDao();
					if (publishersDao.addPublishers(publishers)) {
						JOptionPane.showMessageDialog(null, "Thêm Nhà Xuất Bản Thành Công!", "Thông báo!",
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
				JFrame frame = new JFrame("Thêm thông tin loại sách");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				AddPublishers newContentPane = new AddPublishers();
				newContentPane.setOpaque(true);
				frame.setContentPane(newContentPane);
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}

}