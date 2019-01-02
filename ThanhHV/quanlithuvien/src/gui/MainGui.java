package gui;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class MainGui extends JPanel {

	public static final String PATTERN_ID_MEMBERS = "\\b[0-9,a-z,A-Z]{9,9}\\b";
	public static final String PATTERN_ID_BOOKS = "\\b[0-9,a-z,A-Z]{9,9}\\b";
	public static final String PATTERN_ID_AUTHORS = "\\b[0-9,a-z,A-Z]{9,9}\\b";
	public static final String PATTERN_ID_PUBLISHERS = "\\b[0-9,a-z,A-Z]{9,9}\\b";
	public static final String PATTERN_ID_GENRES = "\\b[0-9,a-z,A-Z]{9,9}\\b";
	public static final String PATTERN_FULLNAME = "^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\\\s]+$";
	public static final String PATTERN_EMAIL = "^(.+)@(.+)$";
	public static final String PATTERN_PHONENUMBER = "^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$";

	public MainGui() {

		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = createImageIcon("");

		// tab panel ManagerMember
		JComponent panel1 = new ManagerMembers();
		tabbedPane.addTab("Quản lí bạn đọc", icon, panel1, "Manager Members");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);

		// tab panel ManagerBooks
		JComponent panel2 = new ManagerBooks();
		tabbedPane.addTab("Quản lí sách", icon, panel2, "Manager Books");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_S);

		// tab panel MangerAuthors
		JComponent panel3 = new ManagerAuthors();
		tabbedPane.addTab("Quản lí tác giả", icon, panel3, "Manager Authors");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_T);

		// tab panel MangerPublishers
		JComponent panel4 = new ManagerPublishers();
		tabbedPane.addTab("Quản lí nhà xuất bản", icon, panel4, "Manager Publishers");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_X);

		// tab panel ManagerGenres
		JComponent panel5 = new ManagerGenres();
		tabbedPane.addTab("Quản lí thể loại", icon, panel5, "Manager Book Genres");
		tabbedPane.setMnemonicAt(4, KeyEvent.VK_O);

		// tab panel ManagerBorrowedBooks
		JComponent panel6 = new ManagerBorrowedBooks();
		tabbedPane.addTab("Quản lí mượn trả", icon, panel6, "Manager Borrowed Books");
		tabbedPane.setMnemonicAt(5, KeyEvent.VK_K);

		// Thêm tab panel tại đây

		add(tabbedPane);

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
	}

	protected JComponent makeTextPanel(String text) {

		JPanel panel = new JPanel(false);

		JLabel filler = new JLabel(text);

		filler.setHorizontalAlignment(JLabel.CENTER);

		panel.setLayout(new GridLayout(1, 1));

		panel.add(filler);

		return panel;
	}

	protected static ImageIcon createImageIcon(String path) {

		java.net.URL imgURL = MainGui.class.getResource(path);

		if (imgURL != null) {

			return new ImageIcon(imgURL);

		} else {

			// System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("MainGui");

		frame.setPreferredSize(new Dimension(1200, 570));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new MainGui(), BorderLayout.CENTER);

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
