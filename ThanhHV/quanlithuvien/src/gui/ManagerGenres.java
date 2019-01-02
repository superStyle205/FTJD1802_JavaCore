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

public class ManagerGenres extends JPanel {
	public ManagerGenres() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = createImageIcon("images/middle.gif");

		JPanel wrapPanel1 = new JPanel();
		wrapPanel1.setLayout(new BoxLayout(wrapPanel1, BoxLayout.Y_AXIS));
		JPanel addGenres = new AddKindOfBooks();
		wrapPanel1.add(Box.createVerticalGlue());
		wrapPanel1.add(addGenres);
		wrapPanel1.add(Box.createVerticalGlue());
		JComponent panel1 = makeTextPanel("Panel #1", wrapPanel1);
		tabbedPane.addTab("Thêm Thể Loại Sách", icon, panel1, "Add Genres");

		// tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		// panel1.setLayout( new BoxLayout(panel1, BoxLayout.Y_AXIS));
		// panel1.setAlignmentX(CENTER_ALIGNMENT);

		JPanel wrapPanel2 = new JPanel();
		wrapPanel2.setLayout(new BoxLayout(wrapPanel2, BoxLayout.Y_AXIS));
		JPanel editGenres = new EditKindOfBooks();
		wrapPanel2.add(Box.createVerticalGlue());
		wrapPanel2.add(editGenres);
		wrapPanel2.add(Box.createVerticalGlue());
		// panel2.setPreferredSize(new Dimension(400,300));
		JComponent panel2 = makeTextPanel("Panel #2", wrapPanel2);
		tabbedPane.addTab("Cập nhật thể loại sách", icon, panel2, "Edit Genres");
		// tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		int location = JTabbedPane.LEFT; // or TOP, BOTTOM, RIGHT
		// pane = new JTabbedPane(location);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
	}

	protected JComponent makeTextPanel(String text, JPanel panel) {
		// JPanel panel = new JPanel(false);
		// JLabel filler = new JLabel(text);
		// filler.setHorizontalAlignment(JLabel.CENTER);
		// panel.setLayout(new GridLayout(1, 1));
		// panel.add(filler);
		return panel;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ManagerGenres.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			//System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("ManagerGenres");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new ManagerGenres(), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}