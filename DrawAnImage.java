package TesterOne;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class DrawAnImage {

    public static void main(String[] args) throws IOException, URISyntaxException {

        new DrawAnImage();
    }

    public DrawAnImage() throws IOException, URISyntaxException {
    	int[][] board = {
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0},
    			{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
    			{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
    			{0,1,1,1,1,1,0,0,1,0,1,0,0,1,1,1,1,1,0},
    			{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
    			{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
    			{0,1,1,1,1,1,0,0,1,0,1,0,0,1,1,1,1,1,0},
    			{0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0},
    			{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0},
    			{0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0},
    			{0,1,0,1,0,0,0,0,1,0,1,1,0,0,0,1,0,1,0},
    			{0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0},
    			{0,1,1,1,1,1,1,0,0,2,0,0,1,1,1,1,1,1,0},
    			{0,1,0,0,0,0,1,0,2,2,2,0,1,0,0,0,0,1,0},
    			{0,1,0,0,0,0,1,0,2,2,2,0,1,0,0,0,0,1,0},
    			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
    			{0,1,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0},
    			{0,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,0},
    			{0,1,0,1,0,0,1,1,1,0,1,1,1,0,0,1,0,1,0},
    			{0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0},
    			{0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0},
    			{0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0},
    			{0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0},
    			{0,1,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0},
    			{0,1,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0},
    			{0,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,0},
    			{0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0},
    			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    	boolean gameRunning = true;
    	JFrame frame = buildFrame();

        BufferedImage imageWall = ImageIO.read(new File(getClass().getResource("/resources/Wall_Element.png").toURI()));
        BufferedImage imageBlank = ImageIO.read(new File(getClass().getResource("/resources/Wall_Element.png").toURI()));

        JPanel pane = new JPanel();

        frame.add(pane);
        frame.repaint();

        while(gameRunning == true) {
        	int x = board.length;
        	int y = board[0].length;
        	int a=0,b=0;
            long start = System.currentTimeMillis();
            int count = 0;
            while (System.currentTimeMillis() - start < 1000) {
            	//for (int times = 0; times < 10; times++) // Draw the screen 10 times to simulate multiple levels of graphics
                    /*for (int loopX = 0; loopX < 20 * 20; loopX += 20)
                        for (int loopY = 0; loopY < 10 * 20; loopY += 20)
                            frame.getGraphics().drawImage(imageWall, loopX, loopY, null);*/
            	for (int loopX = 0; loopX < x*20; loopX += 20) {
                    for (int loopY = 0; loopY < y*20; loopY += 20) {
                        if(board[a][y]==0) {
                        	frame.getGraphics().drawImage(imageWall, loopX, loopY, null);
                        }else {
                        	frame.getGraphics().drawImage(imageBlank, loopX, loopY, null);
                        }
                        a++;
                    }
                    a=0;
                    b++;
            	}
                count++;
            }
            System.out.println("Frames per second: " + count);

        }

        System.exit(0);
    }

    private JFrame buildFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        return frame;
    }
}
