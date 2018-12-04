import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
/*****************************************************************
Help creates the display for the Help main menu screen
@author Team 7 
@version Fall 2018
*****************************************************************/
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
