

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*****************************************************************
mainMenu class contains Menu for Pac Man game
@author Team 7
@version Fall 2018
*****************************************************************/
public class mainMenu extends JFrame implements ActionListener
{
    /** Buttons on the main menu*/
    private JButton startGame, Exit;
    
    /** Dropdown menu on the main menu*/
    private JMenuBar menu;
    
    /** Dropdown menu the main menu*/
    private JMenu options;
    
    /** Option menu items*/
    private JMenuItem quit, help; 
    
    /** Difficulty label*/
    private JLabel difficulty;
    
    /**Difficulty options*/
    private JCheckBox easy, medium, hard;
    
    /** ButtonGroup for difficulty choices*/
    private ButtonGroup difficultyChoice;
    
    /** Game object*/
    private Game game;
    
    /*****************************************************************
Constructor for main menu
*****************************************************************/
    public mainMenu()
    {   
    	Dimension d = new Dimension(Game.WIDTH,Game.HEIGHT);
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);

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
    
    public static void main(String[] args) {
		/*Main Menu Gui*/
		mainMenu w = new mainMenu();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.pack();
		w.setVisible(true);
		w.setTitle("Pac Man");

	}

    /*****************************************************************
Sets up Options menu
@return None
*****************************************************************/
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

    /*****************************************************************
Reads in the performed action
@param e ActionEvent
@return None
*****************************************************************/
    public void actionPerformed(ActionEvent e)
    {
        
        JComponent buttonPressed = (JComponent) e.getSource();
        if (buttonPressed == startGame) {
		if(easy.isSelected()||medium.isSelected()||hard.isSelected()){
        	this.dispose();
        	Game game = new Game();
			JFrame frame = new JFrame();
			frame.setTitle(Game.TITLE);
			frame.add(game);
			frame.setResizable(false);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.requestFocus();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			game.start();
			}
		else JOptionPane.showMessageDialog(null, "Please select a difficulty");
        }
        
        else if(buttonPressed == quit){
            System.exit(0);
        }
        else if(buttonPressed == help) {
        	JFrame help = new JFrame();
        	help.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	help.setVisible(true);
        }
 
    }
}
