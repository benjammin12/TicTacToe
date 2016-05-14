import java.util.Scanner;

//Define class for TicTacToe game.
public class TicTacToeGame {
   private TicTacToeBoard board;
    private static Scanner sc = new Scanner(System.in);
    private int currRow, currCol; //the row and col the current player chooses.
    private final char HUMAN_ID = 'X';
    private final char COMPUTER_ID = 'O';

    // create a board.    
    public TicTacToeGame() {
    	
    	board = new TicTacToeBoard (3);
    }
   
    //create a TicTacToe board by size if it is positive, otherwise,
    //throw an exception. 
    public TicTacToeGame(int size) {
    	if (size > 0)
    		board = new TicTacToeBoard(size);
    	else
    		board = new TicTacToeBoard(3);
    }
    
    //start to play the game
    public void start() {
        System.out.println(board);
        
        int round = 1; //the next round
        while (true) {
            
        	System.out.print("Round " + round + ": ");
            humanPlay();
            System.out.println(board);
            checkStatus();
            round++; 
            System.out.print("Round " + round + ": ");
            computerPlay();
            System.out.println(board);
            checkStatus();
            
            round++;
        }   
    }
   
   //if there is a win by player, computer, or a tie then we will exit the game. 
    //Otherwise we return to caller. and go to next round
    public void checkStatus() {
    	
    	if(board.win(currRow, currCol)  && board.getValue(currRow, currCol) == HUMAN_ID ) {
    		System.out.println("Human wins!!! Alright Humans");
    		System.exit(0);
    	}
    	else if (board.win(currRow, currCol) && board.getValue(currRow, currCol) == COMPUTER_ID){
    		System.out.println("Computer Wins, booo Artifical Intelligence");
    		System.exit(0);
    	}
       
    	else if (board.tie()){
    		System.out.println("Tie Game");
    		System.exit(0);
    	}	
    	else
    		return;
    }
    
    

    
    //This is how the user plays:
    //Enter row and col such that
    //1. row is in [0, numRows-1]
    //2. col is in [0, numCols-1]
    //3. the corresponding cell in the board is available
    //   (hint: call board.getValue(row, col) to check the return is 0 or not).
    //As long as the input row or col is not valid
    //begin
    //   prompt what error(s) are, for example,
    //   * row is not in [0, numRows-1]
    //   * col is not in [0, numCols-1]
    //   * the corresponding cell in board is not available 
    //   prompt user to re-enter.
    //end
    //
    //Once we exit the above repetition loop,
    //row and col are valid,
    //set them to be the currRow and currCol,
    //mark the corresponding cell in the board by HUMAN_ID.
    public void humanPlay() {      
    	System.out.print("Enter position ");
    	int row, col;
    	row = sc.nextInt();
    	col = sc.nextInt();
    	int numRows = board.getNumRows();
    	int numCols = board.getNumCols();
        
    	while (!(board.isValidRow(row) && (board.isValidCol(col)) && (board.isAvailable(row, col))) ){ //updated
    		if (!board.isValidRow(row)){
    			System.out.println("Row number should be in [0, " + (numRows-1) + "]");
    			
    		}
    		
    		if (!board.isValidCol(col)){
    			System.out.println("Col should be in [0, " + (numCols -1) + "]");
    			
    		}
          
    		if ((board.isValidRow(row)) && (board.isValidCol(col))&& (!(board.isAvailable(row, col)))) //updated
    			System.out.println("That is not a valid position");
    		
    		System.out.print("Enter the row and col: ");
    		
    		row = sc.nextInt();
    		col = sc.nextInt();
    	}
    		currRow = row;
    		currCol = col;
    	board.mark(currRow, currCol, HUMAN_ID);
    	
    }
    
    //computer play
    //Computer checks the board from the first row to the last row. 
    //In each row, the computer checks from the first column to the last column.
    //The simplest placement is to find the first available cell.
    //A more sophisticated approach is to use "mark first; if unfit, then remove mark"
    //1. Try to win first.
    //   Mark an available cell by COMPUTER_ID, 
    //   if this leads to win by computer,
    //      take this cell and return, 
    //   otherwise, do not take this cell (that is, set this cell to be available).
    //2. Try to block the opponent from winning. 
    //   This approach is adopted after the try-to-win approach fails.
    //   Mark an available cell by HUMAN_ID (that is, suppose this cell is taken by HUMAN_ID),
    //   if this leads to win by user,
    //      mark this cell by computerId, and return.
    //   otherwise, do not take this cell (that is, set this cell to be available).
    //3. If neither one of the above two approaches works 
    //   (that is, the computer does not take a cell yet), 
    //   then mark the first available cell.
    public void computerPlay() {
        System.out.print("Computer places O at ");
        
        int numRows = board.getNumRows();
        int numCols = board.getNumCols();
        
        for (int row = 0; row < numRows ; row++){
        	for (int col = 0; col <numCols ; col++)
        	if (board.isAvailable(row, col)) {
        		board.mark(row, col, COMPUTER_ID);
        		if(board.win(row, col)) {
        			currRow = row;
        			currCol = col;
        			
        			System.out.println(row + " " + col);
        			
        			return;
        		}
        		else
        			board.mark(row, col, ' ');
        	}
        }
        
        //If we take an empty cell leads to win,
        //then we take that empty cell.

        for (int row = 0; row < numRows ; row++){
        	for (int col = 0; col <numCols ; col++)
        	if (board.isAvailable(row, col)) {
        		board.mark(row, col, HUMAN_ID);
        		if(board.win(row, col)) {
        			currRow = row;
        			currCol = col;
        			
        			System.out.println(row + " " + col);
        			board.mark(row, col, COMPUTER_ID);
        			return;
        		}
        		else
        			board.mark(row, col, ' ');
        	}
        } 
        
        for (int row = 0; row < numRows; row++){
        	for (int col= 0; col < numCols; col++)
        		if (board.isAvailable(row, col)) {
        			board.mark(row, col, COMPUTER_ID);
        			System.out.println(row + " " + col);
                    currRow = row;
                    currCol = col;
                    return; //once we find the first available item, return.
        		}
        }

     
    }
}
