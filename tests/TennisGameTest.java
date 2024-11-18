import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();					
	}		
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		// Act
		// This statement should cause an exception
		game.player2Scored();			
	}		
	
	@Test
	public void testTennisGame_Player1Victory() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("End score incorrect", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2Victory() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("End score incorrect", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_P1Advantage() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		
		// Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();	
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		
		game.player1Scored();
		
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Adv score incorrect", "player1 has advantage", score);		
	}
	
	@Test
	public void testTennisGame_P2Advantage() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		
		// Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Adv score incorrect", "player2 has advantage", score);		
	}
	
	@Test
	public void testPlayer1WinAfterAdvantage() throws TennisGameException {
	    // Arrange
		TennisGame game = new TennisGame();
		// Act
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    
	    game.player1Scored(); // player1 has advantage
	    game.player1Scored(); // player1 wins
	    
	    // Assert
	    assertEquals("player1 wins", game.getScore());
	}
	
	@Test
	public void testPlayer2WinAfterAdvantage() throws TennisGameException {
	    // Arrange
		TennisGame game = new TennisGame();
		// Act
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    
	    game.player2Scored(); 
	    game.player2Scored(); 
	    
	    // Assert
	    assertEquals("player2 wins", game.getScore());
	}
	
	@Test 
	public void testTennisGame_TestToAdvAndBack() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
			
		
		// Assert
		assertEquals("Tie score incorrect", "deuce", game.getScore());	
		
		// Act
		game.player1Scored();
		// Assert
		assertEquals("Adv score incorrect", "player1 has advantage", game.getScore());
		
		// Act 
		game.player2Scored();
		// Assert
		assertEquals("Tie score incorrect", "deuce", game.getScore());
		
		// Act
		game.player2Scored();
		// Assert
		assertEquals("Adv score incorrect", "player2 has advantage", game.getScore());
		
		// Act
		game.player1Scored();
		// Assert
		assertEquals("Tie score incorrect", "deuce", game.getScore());

	}	
	
	@Test
	public void testTennisGame_P1ScoreTransitions() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();

	    // Act Player 1 scores incrementally
	    game.player1Scored();
	    assertEquals("Score incorrect", "15 - love", game.getScore());

	    game.player1Scored();
	    assertEquals("Score incorrect", "30 - love", game.getScore());

	    game.player1Scored();
	    assertEquals("Score incorrect", "40 - love", game.getScore());
	    
	    game.player1Scored();
	    assertEquals("Score incorrect", "player1 wins", game.getScore());
	}
	
	@Test
	public void testTennisGame_P2ScoreTransitions() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();

	    // Act Player 2 scores incrementally
	    game.player2Scored();
	    assertEquals("Score incorrect", "love - 15", game.getScore());

	    game.player2Scored();
	    assertEquals("Score incorrect", "love - 30", game.getScore());

	    game.player2Scored();
	    assertEquals("Score incorrect", "love - 40", game.getScore());
	    
	    game.player2Scored();
	    assertEquals("Score incorrect", "player2 wins", game.getScore());
	}
	
	
}
