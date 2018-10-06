
/**
 * Write a description of class Image here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class mainMenu extends JFrame implements ActionListener
{
    private JLabel newGame;
    private JButton startGame, Exit;

    private JMenuBar menu;
    private JMenu options;
    private JMenuItem quit, help; 

    private JLabel difficulty;
    private JCheckBox onePlayer, twoPlayer,easy, medium, hard;
    
    public static void main(String args [])
    {
        mainMenu w = new mainMenu();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.pack();
        w.setVisible(true);
        w.setTitle("Pac Man");
        

    }

    public mainMenu()
    {   

        setLayout(new BorderLayout());
	setContentPane(new JLabel(new ImageIcon("pac-man-minimalist-featured-image-1024x576.png")));
	setLayout(new FlowLayout());
        

        /**set the layout to GridBag*/
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints(); 

        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 20;
        newGame = new JLabel("New Game");
        add(newGame, loc);

        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 1;
        loc.insets.bottom = 20;
        onePlayer = new JCheckBox("One Player");
        add(onePlayer, loc);

        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 1;
        loc.insets.bottom = 20;
        twoPlayer = new JCheckBox("Two Player");
        add(twoPlayer, loc);

        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 2;
        loc.insets.top = 20;
        loc.insets.bottom = 20;
        difficulty = new JLabel("Select Difficulty");
        add(difficulty, loc);

        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 3;
        easy = new JCheckBox("Easy");
        add(easy, loc);

        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 3;
        medium = new JCheckBox("Medium");
        add(medium, loc);

        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 3;
        loc.insets.bottom = 20;
        hard = new JCheckBox("Hard");
        add(hard, loc);
        
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 4;
        loc.insets.top = 20;
        startGame = new JButton("Start Game!");
        add(startGame, loc);

        onePlayer.addActionListener(this);
        twoPlayer.addActionListener(this);
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);
        startGame.addActionListener(this);

        setUpMenus();
    }

    private void setUpMenus(){
        options = new JMenu("Options");
        quit = new JMenuItem("Quit");
        help = new JMenuItem("Help");
        options.add(quit);
        options.add(help);
        menu = new JMenuBar();
        setJMenuBar(menu);
        menu.add(options);

        options.addActionListener(this);
        quit.addActionListener(this);
        help.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        // put your code here
        JComponent buttonPressed = (JComponent) e.getSource();
    }
}

