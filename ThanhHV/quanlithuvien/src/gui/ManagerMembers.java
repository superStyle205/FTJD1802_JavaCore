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

public class ManagerMembers extends JPanel {

	public ManagerMembers() {

		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		ImageIcon icon = createImageIcon("");

		// Cac tab con cua tab panel ManagerMembers

		// tab con 1 Add Members

		JPanel wrapPanel1 = new JPanel();

		wrapPanel1.setLayout(new BoxLayout(wrapPanel1, BoxLayout.Y_AXIS));

		JPanel addMembers = new AddMembers();

		wrapPanel1.add(Box.createVerticalGlue());

		wrapPanel1.add(addMembers);

		wrapPanel1.add(Box.createVerticalGlue());

		tabbedPane.addTab("Thêm thành viên", icon, wrapPanel1, "Add Members");

		// tab con 2 Edit Members

		JPanel wrapPanel2 = new JPanel();

		wrapPanel2.setLayout(new BoxLayout(wrapPanel2, BoxLayout.Y_AXIS));

		JPanel editMembers = new EditMembers();

		wrapPanel2.add(Box.createVerticalGlue());

		wrapPanel2.add(editMembers);

		wrapPanel2.add(Box.createVerticalGlue());

		tabbedPane.addTab("Cập nhật thành viên", icon, wrapPanel2, "Edit Members");
		
		// tab con 3 
		JPanel wrapPanel3 = new JPanel();

		wrapPanel3.setLayout(new BoxLayout(wrapPanel3, BoxLayout.Y_AXIS));

		JPanel informationMembers = new InformationMembers();

		wrapPanel3.add(Box.createVerticalGlue());

		wrapPanel3.add(informationMembers);

		wrapPanel3.add(Box.createVerticalGlue());

		tabbedPane.addTab("Thông tin thành viên", icon, wrapPanel3, "Information Members");

		add(tabbedPane);

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		tabbedPane.setTabPlacement(JTabbedPane.TOP);
	}

	protected static ImageIcon createImageIcon(String path) {

		java.net.URL imgURL = ManagerMembers.class.getResource(path);

		if (imgURL != null) {

			return new ImageIcon(imgURL);
		} else {

			// System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("ManagerMembers");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new ManagerMembers(), BorderLayout.CENTER);

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