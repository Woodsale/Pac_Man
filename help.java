import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class help extends JFrame{
	FlowLayout layout;
	public help() {
		Dimension d = new Dimension(Game.WIDTH,Game.HEIGHT);
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);
		layout = new FlowLayout();
		setLayout(layout);
		JLabel title = new JLabel("How to Play:");
		JLabel body = new JLabel("");
		add(title);
		add(body);
	}
}
