package gui;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;

public class GetButtonSubComponent {
	static JButton getButtonSubComponent(Container container) {
		if (container instanceof JButton) {
			return (JButton) container;
		} else {
			Component[] components = container.getComponents();
			for (Component component : components) {
				if (component instanceof Container) {
					return getButtonSubComponent((Container) component);
				}
			}
		}
		return null;
	}
}
