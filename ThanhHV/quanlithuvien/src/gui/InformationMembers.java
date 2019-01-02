package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.bean.Members;
import model.dao.MembersDao;

public class InformationMembers extends JPanel {
	private boolean DEBUG = false;

	public InformationMembers() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridBagLayout);

		DefaultTableModel dm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		dm.addColumn("Mã bạn đọc");
		dm.addColumn("Họ tên");
		dm.addColumn("Điện thoại");
		dm.addColumn("Email");
		dm.addColumn("Số nhà");
		dm.addColumn("Tên đường");
		dm.addColumn("Tỉnh, thành phố");
		dm.addColumn("Quận, huyện");
		dm.addColumn("Xã, phường, thị trấn");
		dm.addColumn("Đang mượn");
		MembersDao membersDao = new MembersDao();
		for (String id : membersDao.getAllMembersId()) {
			Members members = membersDao.getMembersById(id);
			dm.addRow(new Object[] { id, members.getFullName(), members.getPhoneNumber(), members.getEmail(),
					members.getHomeNumber(), members.getStreetName(), members.getTenThanhPho(), members.getTenQuan(),
					members.getTenPhuong(), members.getNumberOfBooks() });
		}
		JTable table = new JTable(dm);
		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));

		// table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(20);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 1));

		TitledBorder titleCenterBorder = BorderFactory.createTitledBorder("Thông tin thành viên");
		centerPanel.setBorder(titleCenterBorder);
		centerPanel.setOpaque(true);

		JScrollPane scrollPane = new JScrollPane(table);

		centerPanel.add(scrollPane);

		// scrollPane.setPreferredSize(new Dimension(1200, 300));

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridBagLayout.setConstraints(centerPanel, c);
		add(centerPanel);
		c.weighty = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton refreshButton = new JButton("Refresh");
		gridBagLayout.setConstraints(southPanel, c);
		southPanel.add(refreshButton);
		add(southPanel);
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dm.setRowCount(0);
				MembersDao membersDao = new MembersDao();
				for (String id : membersDao.getAllMembersId()) {
					Members members = membersDao.getMembersById(id);
					dm.addRow(new Object[] { id, members.getFullName(), members.getPhoneNumber(), members.getEmail(),
							members.getHomeNumber(), members.getStreetName(), members.getTenThanhPho(),
							members.getTenQuan(), members.getTenPhuong(), members.getNumberOfBooks() });
				}

			}
		});

	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("InformationMembers");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new InformationMembers(), BorderLayout.CENTER);

		frame.pack();

		frame.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				UIManager.put("swing.boldMetal", Boolean.FALSE);

				createAndShowGUI();
			}
		});
	}
}
