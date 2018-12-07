//Brandi Werner
// Description: This is the peg game writen in java. 
// 		It runs through the first 5 games and the rest
//		works the same way if you rotait the board.

import java.util.*;
import java.lang.*;

public class PegGame
{
	public static GamePlay game = new GamePlay();
	
    public static void main(String args[])
    {
        // Start the game through leaving the 0th peg out 
		System.out.println("___________ 0 ___________\n");
        Board startBoard = new Board(0);
        PlayGame(startBoard);
    }

    public static void PlayGame(Board playboard)
    {
		// Run through the different plays and if they work save them for printing out
		int i=0;
        do{	
			// Create new board
            Board newBoard = new Board();

			// Copy old board to new board
			newBoard.boardPos = playboard.boardPos.clone();
			newBoard.prePlays.addAll(playboard.prePlays);
			newBoard.startP = playboard.startP;
			newBoard.piecesLeft = playboard.piecesLeft;
			
			// Set up temp array
			int[] temp;
			/*if(i>17){
				temp = game.Moves.get(i%18);
				int ptemp = temp[0];
				temp[0] = temp[2];
				temp[2] = ptemp;
			}else{
			*/	temp = game.play.get(i);	// Get the current play to try
			//}
			
			// Check for change
            if(newBoard.boardPos[temp[0]] == 'x')
            {
				if(newBoard.boardPos[temp[1]] == 'x')
				{
					if(newBoard.boardPos[temp[2]] == '.')
					{
						// Doing the actual play
						newBoard.boardPos[temp[0]] = '.';
						newBoard.boardPos[temp[1]] = '.';
						newBoard.boardPos[temp[2]] = 'x';
						
						// Subtracting 1 from the peices
						newBoard.piecesLeft = newBoard.piecesLeft - 1;
						
						// Add this successful play to the plays
						newBoard.prePlays.add(temp);
						
						// If the game is won, print game
						if(newBoard.piecesLeft <= 1)
						{
							//Reset the board
							newBoard.resetBoard();
							int count=0;
						   
						   // Go through and print the board
							do{
								System.out.printf("\t     %c\n", newBoard.boardPos[0]);
								System.out.printf("\t    %c %c\n", newBoard.boardPos[1], newBoard.boardPos[2]);
								System.out.printf("\t   %c %c %c\n", newBoard.boardPos[3], newBoard.boardPos[4], newBoard.boardPos[5]);
								System.out.printf("\t  %c %c %c %c\n", newBoard.boardPos[6], newBoard.boardPos[7], newBoard.boardPos[8], newBoard.boardPos[9]);
								System.out.printf("\t %c %c %c %c %c\n\n", newBoard.boardPos[10], newBoard.boardPos[11], newBoard.boardPos[12], newBoard.boardPos[13], newBoard.boardPos[14]);

								// Get play positions
								int[] temp1 = newBoard.prePlays.get(count==newBoard.prePlays.size()?1:count);
								
								// Do play
								newBoard.boardPos[temp1[0]] = '.';
								newBoard.boardPos[temp1[1]] = '.';
								newBoard.boardPos[temp1[2]] = 'x';
								
								count++;
							}while(count<=newBoard.prePlays.size());

							int startprint = newBoard.startP + 1;
							
							// See if all boards have been solved
							if(startprint >= 5) 
							{
								System.exit(1); //exit success
							}
							
							// Print start of new game
							System.out.printf("___________ %d ___________\n", startprint);

							// Set up new board with new starting position
							Board boardNew = new Board(startprint);
							
							// Restart finding solution this new starting position
							PlayGame(boardNew);
						}
						PlayGame(newBoard);					
					}					
				}
            }
			i++;
        }while(i<game.play.size());
    }
}

// Board setup and plays during game
class Board {
    public char boardPos[] = new char[15];							// Peg Positions
    public int piecesLeft;											// How many places still have pegs
	public ArrayList<int[]> prePlays = new ArrayList<int[]>(); 		// List of all plays to win game
    public int startP;						              			// Initial start position for current game
    
	// Empty constructor
	public Board(){}
	
	// Constructor for setting up new starting position
    public Board(int s)
    {
		piecesLeft = 14;
        startP = s;
		resetBoard();
    }

	// Setting the board to all full except for starting peg
    public void resetBoard()
    {
		// Setting the board to all full of pegs
		int temp=0;
		do{
			boardPos[temp] = 'x';
			temp++;
		}while(temp<15);
		
		// Setting the starting piece to emtpy
        boardPos[startP] = '.';
    }
}

// Class that holds all possible plays for the game
class GamePlay
{
	// Hashmap that holds all of the user plays
	public static HashMap<Integer, int[]> play = new HashMap<Integer, int[]>();
	
	// Constructor which calls adds plays to the hashmap
	public GamePlay(){
		putMoves();
	}
	
	// Adding plays to the hashmap
	private void putMoves(){
		play.put(0, new int[]{0,1,3});
		play.put(1, new int[]{0,2,5});
		play.put(2 , new int[]{1,3,6});
		play.put(3, new int[]{1,4,8});
		play.put(4, new int[]{2,4,7});
		play.put(5, new int[]{2,5,9});
		play.put(6, new int[]{3,6,10});
		play.put(7, new int[]{3,7,12});
		play.put(8, new int[]{4,7,11});
		play.put(9, new int[]{4,8,13});
		play.put(10, new int[]{5,8,12});
		play.put(11, new int[]{5,9,14});
		play.put(12, new int[]{3,4,5});
		play.put(13, new int[]{6,7,8});
		play.put(14, new int[]{7,8,9});
		play.put(15, new int[]{10,11,12});
		play.put(16, new int[]{11,12,13});
		play.put(17, new int[]{12,13,14});
		play.put(18, new int[]{3,1,0});
		play.put(19, new int[]{5,2,0});
		play.put(20, new int[]{6,3,1});
		play.put(21, new int[]{8,4,1});
		play.put(22, new int[]{7,4,2});
		play.put(23, new int[]{9,5,2});
		play.put(24, new int[]{10,6,3});
		play.put(25, new int[]{12,7,3});
		play.put(26, new int[]{11,7,4});
		play.put(27, new int[]{13,8,4});
		play.put(28, new int[]{12,8,5});
		play.put(29, new int[]{14,9,5});
		play.put(30, new int[]{5,4,3});
		play.put(31, new int[]{8,7,6});
		play.put(32, new int[]{9,8,7});
		play.put(33, new int[]{12,11,10});
		play.put(34, new int[]{13,12,11});
		play.put(35, new int[]{14,13,12});	
	}
}