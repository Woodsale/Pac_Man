
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Main menu.
 *
 * @author Team 7
 * @version Fall 2018
 */
public class mainMenu extends JFrame implements ActionListener
{
    /** Buttons for the game */
    private JButton startGame, Exit;
    /** Menu for the game */
    private JMenuBar menu;
    /** Options for the game */
    private JMenu options;
    /** Option items for the game */
    private JMenuItem quit, help; 
    /** Difficulty label */
    private JLabel difficulty;
    /** Difficulty checkboxes*/
    private JCheckBox easy, medium, hard;
    /** Difficulty buttongroup*/
    private ButtonGroup difficultyChoice;
    /** Game object */
    private Game game;

    /*****************************************************************
    Constructor of a main menu 
    @return none
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
 	/*****************************************************************
    Creates a main menu 
    @param args
    @return none
    *****************************************************************/   
    public static void main(String[] args) {
		/*Main Menu Gui*/
		mainMenu w = new mainMenu();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.pack();
		w.setVisible(true);
		w.setTitle("Pac Man");

		/*One player GUI*/
		/*Game game = new Game();
			JFrame frame = new JFrame();
			frame.setTitle(Game.TITLE);
			frame.add(game);
			frame.setResizable(false);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.requestFocus();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			game.start();*/

		/*Two Player GUI*/
		/* Modifications should be in the Game class
			Game game = new Game();
			JFrame frame = new JFrame();
			frame.setTitle(Game.TITLE);
			frame.add(game);
			frame.setResizable(false);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			game.start();
		 */

	}
	/*****************************************************************
    Sets up an options menu 
    @return none
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
    Reads in button presses on the main menu
    @param e - Action event
    @return none
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
