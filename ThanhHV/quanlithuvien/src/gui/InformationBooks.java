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

import model.bean.Books;
import model.bean.Members;
import model.dao.BooksDao;
import model.dao.MembersDao;

public class InformationBooks extends JPanel {
	private boolean DEBUG = false;

	public InformationBooks() {
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

		dm.addColumn("Mã sách");
		dm.addColumn("Tên sách");
		dm.addColumn("Tác giả");
		dm.addColumn("Thể loại");
		dm.addColumn("Nhà xuất bản");
		dm.addColumn("Năm xuất bản");
		dm.addColumn("Số lượng");
		dm.addColumn("Số lượng hiện có");
		BooksDao booksDao = new BooksDao();
		for (String id : booksDao.getAllBooksId()) {
			Books books = booksDao.getBooksById(id);
			dm.addRow(new Object[] { id, books.getTitle(), books.getAuthor(), books.getGenre(), books.getPublisher(),
					books.getYear(), books.getNumberOfBooks(), books.getSoLuongHienCo() });
		}
		JTable table = new JTable(dm);
		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));

		// table.setFillsViewportHeight(true);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 1));

		TitledBorder titleCenterBorder = BorderFactory.createTitledBorder("Thông tin sách");
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
				BooksDao booksDao = new BooksDao();
				for (String id : booksDao.getAllBooksId()) {
					Books books = booksDao.getBooksById(id);
					dm.addRow(new Object[] { id, books.getTitle(), books.getAuthor(), books.getGenre(),
							books.getPublisher(), books.getYear(), books.getNumberOfBooks(),
							books.getSoLuongHienCo() });
				}

			}
		});

	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("InformationBooks");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new InformationBooks(), BorderLayout.CENTER);

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
