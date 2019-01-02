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
import java.util.List;

//import the packages for using the classes in them into the program
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.bean.Members;
import model.bean.ThanhPho;
import model.dao.MembersDao;
import model.dao.QuanDao;
import model.dao.ThanhPhoDao;
import model.dao.XaDao;

public class AddMembers extends JPanel {

	private ThanhPhoDao thanhPhoDao;

	private QuanDao quanDao;

	private XaDao xaDao;

	private MembersDao membersDao;

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("THÔNG TIN BẠN ĐỌC");

	private JPanel centerPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[9];

	private String[] informationString = { " Mã bạn đọc: ", " Họ tên: ", " Điện thoại: ", " Email: ", " Số nhà: ",
			" Tên đường: ", " Tên tỉnh (thành phố): ", " Tên quận (huyện): ", " Tên xã (phường, thị trấn): " };

	private String[] textPromptString = { "000000001 - Mã có 9 chữ số ", "Họ và tên", "0123456789",
			"javadark@email.com", "307", "Tạ Quang Bửu" };

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[6];

	private TextPrompt[] informationTextPrompt = new TextPrompt[9];

	private JComboBox[] informationComboBox = new JComboBox[3];

	private JPanel insertInformationButtonPanel = new JPanel();

	private JButton insertInformationButton = new JButton("Thêm thông tin");

	private JPanel southPanel = new JPanel();

	private JButton OKButton = new JButton("Thoát");

	private String[] data = new String[10];

	public boolean isCorrect() {

		for (int i = 0; i < 6; i++) {

			if (!informationTextField[i].getText().equals("")) {

				data[i] = informationTextField[i].getText();

			} else {

				return false;
			}
		}

		membersDao = new MembersDao();

		if (!informationTextField[0].getText().matches(MainGui.PATTERN_ID_MEMBERS)
				|| (membersDao.checkIdExisted((String) informationTextField[0].getText())))
			return false;

		if (!informationTextField[1].getText().matches(MainGui.PATTERN_FULLNAME))
			return false;

		if (!informationTextField[2].getText().matches(MainGui.PATTERN_PHONENUMBER))
			return false;

		if (!informationTextField[3].getText().matches(MainGui.PATTERN_EMAIL))
			return false;

		if (informationComboBox[0].getSelectedItem().equals(""))
			return false;

		if (informationComboBox[1].getSelectedItem().equals(""))
			return false;

		if (informationComboBox[2].getSelectedItem().equals(""))
			return false;

		data[6] = (String) informationComboBox[0].getSelectedItem();

		data[7] = (String) informationComboBox[1].getSelectedItem();

		data[8] = (String) informationComboBox[2].getSelectedItem();

		return true;
	}

	public void clearTextField() {

		for (int i = 0; i < informationTextField.length; i++) {

			informationTextField[i].setText(null);
		}
	}

	public AddMembers() {

		setLayout(new BorderLayout());

		setPreferredSize(new Dimension(450, 400));

		setMaximumSize(new Dimension(450, 400));

		JPanel cp = this;

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		northPanel.add(northLabel);

		cp.add(northPanel, BorderLayout.NORTH);

		centerPanel.setLayout(new BorderLayout());

		centerPanel.setBorder(BorderFactory.createTitledBorder("Thêm thành viên"));

		((TitledBorder) centerPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		informationLabelPanel.setLayout(new GridLayout(9, 1, 1, 1));

		for (int i = 0; i < informationLabel.length; i++) {

			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));

			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		centerPanel.add(informationLabelPanel, BorderLayout.WEST);

		informationTextFieldPanel.setLayout(new GridLayout(9, 1, 1, 1));

		for (int i = 0; i < informationTextField.length; i++) {

			informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));

			informationTextPrompt[i] = new TextPrompt(textPromptString[i], informationTextField[i]);

			informationTextPrompt[i].setForeground(Color.BLACK);

			informationTextPrompt[i].changeAlpha(0.5f);

			informationTextPrompt[i].changeStyle(Font.BOLD + Font.ITALIC);

			informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
		}

		thanhPhoDao = new ThanhPhoDao();

		informationTextFieldPanel.add(informationComboBox[0] = new JComboBox());

		for (String tenThanhPho : thanhPhoDao.getAllNameThanhPho()) {

			informationComboBox[0].addItem(tenThanhPho);
		}

		informationTextFieldPanel.add(informationComboBox[1] = new JComboBox());

		informationComboBox[1].setFont(new Font("Tahoma", Font.PLAIN, 11));

		informationComboBox[0].setFont(new Font("Tahoma", Font.PLAIN, 11));

		informationComboBox[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				quanDao = new QuanDao();

				informationComboBox[1].removeAllItems();

				for (String tenQuan : quanDao
						.getAllNameQuanFromNameThanhPho((String) informationComboBox[0].getSelectedItem())) {
					informationComboBox[1].addItem(tenQuan);
				}
			}
		});

		informationComboBox[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				xaDao = new XaDao();

				informationComboBox[2].removeAllItems();

				for (String tenXa : xaDao.getAllNameXaFromNameQuan((String) informationComboBox[1].getSelectedItem())) {
					informationComboBox[2].addItem(tenXa);
				}
			}
		});

		informationTextFieldPanel.add(informationComboBox[2] = new JComboBox());

		informationComboBox[2].setFont(new Font("Tahoma", Font.PLAIN, 11));

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

					MembersDao membersDao = new MembersDao();

					Members members = new Members(data[0], data[1], data[2], data[3], data[4], data[5], data[8],
							data[7], data[6], 0);

					if (membersDao.addMembers(members)) {
						JOptionPane.showMessageDialog(null, "Thêm bạn đọc thành công", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập lại thông tin!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				JFrame frame = new JFrame("Thêm thông tin bạn đọc");

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				AddMembers newContentPane = new AddMembers();

				newContentPane.setOpaque(true);

				frame.setContentPane(newContentPane);

				frame.pack();

				frame.setVisible(true);

				frame.setResizable(true);
			}
		});
	}

}