package gui;

import javax.swing.JTabbedPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class ManagerBooks extends JPanel {
	public ManagerBooks() {

		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		ImageIcon icon = createImageIcon("images/middle.gif");

		JPanel wrapPanel1 = new JPanel();

		wrapPanel1.setLayout(new BoxLayout(wrapPanel1, BoxLayout.Y_AXIS));

		JPanel addBooks = new AddBooks();

		wrapPanel1.add(Box.createVerticalGlue());

		wrapPanel1.add(addBooks);

		wrapPanel1.add(Box.createVerticalGlue());

		tabbedPane.addTab("Thêm sách", icon, wrapPanel1, "Add Books");

		JPanel wrapPanel2 = new JPanel();

		wrapPanel2.setLayout(new BoxLayout(wrapPanel2, BoxLayout.Y_AXIS));

		JPanel editBooks = new EditBooks();

		wrapPanel2.add(Box.createVerticalGlue());

		wrapPanel2.add(editBooks);

		wrapPanel2.add(Box.createVerticalGlue());

		tabbedPane.addTab("Sửa sách", icon, wrapPanel2, "Edit Books");
		
		JPanel wrapPanel3 = new JPanel();

		wrapPanel3.setLayout(new BoxLayout(wrapPanel3, BoxLayout.Y_AXIS));

		JPanel informationBooks = new InformationBooks();

		wrapPanel3.add(Box.createVerticalGlue());

		wrapPanel3.add(informationBooks);

		wrapPanel3.add(Box.createVerticalGlue());

		tabbedPane.addTab("Thông tin sách", icon, wrapPanel3, "Information Books");
		
		

		add(tabbedPane);

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		tabbedPane.setTabPlacement(JTabbedPane.TOP);
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ManagerBooks.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			// System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("ManagerBooks");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new ManagerBooks(), BorderLayout.CENTER);

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