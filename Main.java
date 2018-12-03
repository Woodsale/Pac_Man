import javax.swing.JFrame;

public class Main {

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
}
