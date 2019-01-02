package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.UpdatableResultSet;

import model.bean.Members;
import model.dao.MembersDao;
import model.dao.MuonTraDao;
import model.dao.QuanDao;
import model.dao.ThanhPhoDao;
import model.dao.XaDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class EditMembers extends JPanel {

	private ThanhPhoDao thanhPhoDao = new ThanhPhoDao();

	private QuanDao quanDao = new QuanDao();

	private XaDao xaDao = new XaDao();

	private MembersDao membersDao = new MembersDao();

	private JPanel northPanel = new JPanel();

	private JLabel northLabel = new JLabel("Thông tin bạn đọc");

	private JPanel centerPanel = new JPanel();

	private JPanel editPanel = new JPanel();

	private JPanel editInformationPanel = new JPanel();

	private JPanel editInformationLabelPanel = new JPanel();

	private JPanel editInformationTextFieldPanel = new JPanel();

	private JPanel editInformationButtonPanel = new JPanel();

	private JLabel editLabel = new JLabel("Mã bạn đọc: ");

	private JComboBox editComboBox = new JComboBox();

	private JButton editButton = new JButton("Sửa");

	private JButton deleteButton = new JButton("Xóa");

	private JPanel informationPanel = new JPanel();

	private JPanel informationLabelPanel = new JPanel();

	private JLabel[] informationLabel = new JLabel[8];

	private String[] informationString = { " Họ tên: ", " Điện thoại: ", " Email: ", " Số nhà: ", " Tên đường: ",
			" Tên tỉnh (thành phố): ", " Tên quận (huyện): ", " Tên xã (phường, thị trấn): " };

	private JPanel informationTextFieldPanel = new JPanel();

	private JTextField[] informationTextField = new JTextField[8];

	private JPanel updateInformationButtonPanel = new JPanel();

	private JButton updateInformationButton = new JButton("Cập nhật thông tin");

	private JPanel southPanel = new JPanel();

	private JButton exitButton = new JButton("Thoát");

	private String[] data = new String[20];

	private JComboBox[] informationComboBox = new JComboBox[3];

	public boolean isCorrect() {

		for (int i = 0; i < 5; i++) {

			if (!informationTextField[i].getText().equals("")) {

				data[i] = informationTextField[i].getText();
			} else {
				return false;
			}
		}

		if (!informationTextField[0].getText().matches(MainGui.PATTERN_FULLNAME))
			return false;

		if (!informationTextField[1].getText().matches(MainGui.PATTERN_PHONENUMBER))
			return false;

		if (!informationTextField[2].getText().matches(MainGui.PATTERN_EMAIL))
			return false;

		if (informationComboBox[0].getSelectedItem().equals(""))
			return false;

		if (informationComboBox[1].getSelectedItem().equals(""))
			return false;

		if (informationComboBox[2].getSelectedItem().equals(""))
			return false;

		data[5] = (String) informationComboBox[0].getSelectedItem();

		data[6] = (String) informationComboBox[1].getSelectedItem();

		data[7] = (String) informationComboBox[2].getSelectedItem();

		return true;
	}

	public EditMembers() {

		setPreferredSize(new Dimension(430, 400));

		setMaximumSize(new Dimension(430, 400));

		JPanel cp = this;

		setLayout(new BorderLayout());

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		northPanel.add(northLabel);

		cp.add("North", northPanel);

		centerPanel.setLayout(new BorderLayout());

		editPanel.setLayout(new BorderLayout());

		editPanel.setBorder(BorderFactory.createTitledBorder("Mã bạn đọc: "));
		((TitledBorder) editPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		editInformationPanel.setLayout(new BorderLayout());

		editInformationLabelPanel.setLayout(new GridLayout(1, 1, 1, 2));

		editInformationLabelPanel.add(editLabel);

		editLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		GridBagLayout gridbag = new GridBagLayout();

		GridBagConstraints c = new GridBagConstraints();

		editInformationPanel.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;

		c.weightx = 1;

		c.gridwidth = 1;

		gridbag.setConstraints(editInformationLabelPanel, c);

		editInformationPanel.add(editInformationLabelPanel);

		c.weightx = 1;

		c.gridwidth = GridBagConstraints.REMAINDER;

		gridbag.setConstraints(editComboBox, c);

		editInformationPanel.add(editComboBox);

		editPanel.add("North", editInformationPanel);

		editInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		editInformationButtonPanel.add(editButton);

		editButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		editInformationButtonPanel.add(deleteButton);

		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		editPanel.add("South", editInformationButtonPanel);

		centerPanel.add("North", editPanel);

		informationPanel.setLayout(new BorderLayout());

		informationPanel.setBorder(BorderFactory.createTitledBorder("Sửa thông tin bạn đọc: "));
		((TitledBorder) informationPanel.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 11));

		informationLabelPanel.setLayout(new GridLayout(8, 1, 1, 1));

		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		informationPanel.add("West", informationLabelPanel);

		informationTextFieldPanel.setLayout(new GridLayout(8, 1, 1, 1));

		for (int i = 0; i < 5; i++) {
			informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));
			informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
		}
		informationPanel.add("East", informationTextFieldPanel);

		// ----
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
				informationComboBox[2].removeAllItems();
				for (String tenXa : xaDao.getAllNameXaFromNameQuan((String) informationComboBox[1].getSelectedItem())) {
					informationComboBox[2].addItem(tenXa);
				}
			}
		});

		informationTextFieldPanel.add(informationComboBox[2] = new JComboBox());

		informationComboBox[2].setFont(new Font("Tahoma", Font.PLAIN, 11));

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

		for (String idBanDoc : membersDao.getAllMembersId()) {
			editComboBox.addItem(idBanDoc);
		}
		AutoCompletion.enable(editComboBox);
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Members members = membersDao.getMembersById((String) editComboBox.getSelectedItem());
				data[0] = members.getFullName();
				data[1] = members.getPhoneNumber();
				data[2] = members.getEmail();
				data[3] = members.getHomeNumber();
				data[4] = members.getStreetName();
				data[5] = members.getTenThanhPho();
				data[6] = members.getTenQuan();
				data[7] = members.getTenPhuong();
				for (int i = 0; i < 5; i++) {
					informationTextField[i].setText(data[i]);
				}
				informationComboBox[0].setSelectedItem(data[5]);
				informationComboBox[1].setSelectedItem(data[6]);
				informationComboBox[2].setSelectedItem(data[7]);
			}
		});
		updateInformationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isCorrect()) {
					MembersDao membersDao = new MembersDao();
					Members members = new Members((String) editComboBox.getSelectedItem(), data[0], data[1], data[2],
							data[3], data[4], data[7], data[6], data[5], 0);
					if (membersDao.updateMembers(members)) {
						JOptionPane.showMessageDialog(null, "Cập nhật bạn đọc thành công", "Thông báo!",
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
				MembersDao membersDao = new MembersDao();
				// editComboBox.getSelectedItem lay id cua ban doc
				MuonTraDao muonTraDao = new MuonTraDao();
				if (muonTraDao.returnable((String) editComboBox.getSelectedItem())) {
					membersDao.removeMembersById((String) editComboBox.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Xóa bạn đọc thành công!", "Thông báo!",
							JOptionPane.WARNING_MESSAGE);
					editComboBox.removeItem(editComboBox.getSelectedItem());
				} else {
					JOptionPane.showMessageDialog(null, "Bạn đọc chưa trả đủ sách! Không thể xóa", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		JButton jbutton = GetButtonSubComponent.getButtonSubComponent(editComboBox);
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
				editComboBox.removeAllItems();
				for (String idBanDoc : membersDao.getAllMembersId()) {
					editComboBox.addItem(idBanDoc);
				}

			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new JFrame("Sửa thông tin thành viên");

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				EditMembers newContentPane = new EditMembers();

				newContentPane.setOpaque(true);

				frame.setContentPane(newContentPane);

				frame.pack();

				frame.setVisible(true);

				frame.setResizable(true);
			}
		});
	}

}