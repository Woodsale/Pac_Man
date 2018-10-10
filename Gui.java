import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame{
	private JTextField st; //Score Text
	private JButton pm_one,pm_two,pm_three;
	
	private JMenuBar menu;
    	private JMenu options;
    	private JMenuItem quit, help, newGame; 
	
	public Gui() {
		super("Pac Man");
		setLayout(new FlowLayout());
		
		setLayout(new GridBagLayout());
		GridBagConstraints position = new GridBagConstraints();
		
		st = new JTextField("Score: 0",15);
		st.setFont(new Font("Serif",Font.PLAIN,14));
		st.setEditable(false);
		position.gridx = 0;
		position.gridy = 0;
		position.gridwidth = 2;
		position.gridheight = 1;
		add(st,position);
		
		Icon pm = new ImageIcon(getClass().getResource("Pac_Man_Pic.png"));
		pm_one = new JButton(pm);
		position.gridx = 2;
		position.gridy = 0;
		position.gridwidth = 1;
		position.gridheight = 1;
		add(pm_one,position);
		
		pm_two = new JButton(pm);
		position.gridx = 3;
		position.gridy = 0;
		position.gridwidth = 1;
		position.gridheight = 1;
		add(pm_two,position);
		
		pm_three = new JButton(pm);
		position.gridx = 4;
		position.gridy = 0;
		position.gridwidth = 1;
		position.gridheight = 1;
		add(pm_three,position);
		
		setupMenus();
	}
	private void setupMenus(){
        options = new JMenu("Options");
        newGame = new JMenuItem("New Game");
        help = new JMenuItem("Help");
        quit = new JMenuItem("Quit");
        options.add(newGame);
        options.add(help);
        options.add(quit);
        menu = new JMenuBar();
        setJMenuBar(menu);
        menu.add(options);

        /*options.addActionListener(this);
        quit.addActionListener(this);
        help.addActionListener(this);
    	*/
    }
	
	public void actionPerformed(ActionEvent e)
    {
        
    }
	
}
