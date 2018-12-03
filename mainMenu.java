/**
 * Write a description of class Image here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainMenu extends JFrame implements ActionListener
{
    private JButton startGame, Exit;

    private JMenuBar menu;
    private JMenu options;
    private JMenuItem quit, help; 

    private JLabel difficulty;
    private JCheckBox easy, medium, hard;
    
    private ButtonGroup difficultyChoice;
    
    private Game game;


    public mainMenu()
    {   

        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("pac-man-minimalist-featured-image-1024x576.png")));
        setLayout(new FlowLayout());

        /**set the layout to GridBag*/
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints(); 

        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 0;
        loc.insets.top = 20;
        loc.insets.bottom = 20;
        difficulty = new JLabel("Select Difficulty");
        add(difficulty, loc);

        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 1;
        loc.insets.bottom = 20;
        easy = new JCheckBox("Easy");
        add(easy, loc);

        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 1;
        loc.insets.bottom = 20;
        medium = new JCheckBox("Medium");
        add(medium, loc);

        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 1;
        loc.insets.bottom = 20;
        hard = new JCheckBox("Hard");
        add(hard, loc);
        
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 2;
        loc.insets.top = 20;
        startGame = new JButton("Start Game!");
        add(startGame, loc);
        
        /**Allows for only one difficulty to 
         * be chosen*/
        difficultyChoice = new ButtonGroup();
        difficultyChoice.add(easy);
        difficultyChoice.add(medium);
        difficultyChoice.add(hard);

        
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
        if (buttonPressed == startGame) {
        	game = new Game();
        	this.setTitle(Game.TITLE);
    		this.add(game);
    		this.setResizable(false);
    		this.pack();
    		this.setLocationRelativeTo(null);
    		this.setVisible(true);
    		this.requestFocus();
    		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		game.start();
        }
        
        else if(buttonPressed == quit){
            System.exit(0);
        }
 
    }
}
