import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class test {

	@Before
    public void setUp() throws Exception {
         Game game = new Game();
    }
	@Test
	void checkPausedStatus() {
		assertFalse(Game.isPaused);
	}
	@Test
	void checkInitialMap() {
		boolean ret = true;
		boolean ret1 = true;
		boolean ret2 = true;
		int[][] testBoard = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,4,4,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0},
				{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
				{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
				{0,1,1,1,1,1,0,0,1,0,1,0,0,1,1,1,1,1,0},
				{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
				{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
				{0,1,1,1,1,1,0,0,1,0,1,0,0,1,1,1,1,1,0},
				{0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0},
				{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0},
				{0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0},
				{0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0},
				{0,1,0,1,0,0,1,1,1,1,1,1,1,0,0,1,0,1,0},
				{0,1,1,1,1,1,1,0,0,5,0,0,1,1,1,1,1,1,0},
				{0,1,0,0,0,0,1,0,5,5,5,0,1,0,0,0,0,1,0},
				{0,1,0,0,0,0,1,0,5,5,5,0,1,0,0,0,0,1,0},
				{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
				{0,1,0,0,0,0,1,1,1,3,1,1,1,0,0,0,0,1,0},
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
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
		for(int i=0;i < Map.board.length; i++) {
			for(int j = 0; j < Map.board[0].length; j++) {
				if(Map.board[i][j] != testBoard[i][j])
					ret = false;
			}
		}
		for(int i=0;i < Map.getBoard().length; i++) {
			for(int j = 0; j < Map.getBoard()[0].length; j++) {
				if(Map.board[i][j] != testBoard[i][j])
					ret1 = false;
			}
		}
		for(int i=0;i < Map.getBoard().length; i++) {
			for(int j = 0; j < Map.getBoard()[0].length; j++) {
				if(Game.map.getType(i,j) != testBoard[i][j])
					ret2 = false;
			}
		}
		assertTrue(ret);
		assertTrue(ret1);
		assertTrue(ret2);
	}
	@Test
	void lives() {
		assertEquals(Game.p1LivesRemaining, 3);
	}
	@Test
	void startingScore() {
		assertEquals(Game.playerOneScore, 0);
	}
	@Test
	void gameOver() {
		assertFalse(Game.map.gameOver());
	}
}
