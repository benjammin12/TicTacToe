public class TicTacToeBoard {
    private char[][] board; //use integer array, interpret int(player id) by char if necessary
   
    //a default TicTacToe takes 3x3 integer array
    public TicTacToeBoard() {
    	this(3);
    }
    
    //Non-default constructor of TicTacToe.
    //You may have TicTacToe board with size bigger than 3.
   
     public TicTacToeBoard(int givenSize) {
    	if (givenSize > 0)
    		board = new char[givenSize][givenSize];
    	else
    		board = new char[3][3];
    		
    	clear(); // you clear the board after creating the array so every cell starts with a ' '
    }
    
    //Put a space in every cell of the board.
    public void clear() {
    	for (int i = 0; i < board.length; i++)
    		for(int j = 0; j < board[i].length; j++)
    			board[i][j] = ' ';
    		
    }
   
    //Return the value of the square at (row)th row and (col)th column. 
    public char getValue(int row, int col) {
    	return board[row][col];
   
    }
    
    //Check whether the cell at (row, col) of board
    //is available or not. A cell is available is if its value is a space.
    public boolean isAvailable(int row, int col) {
    	if (board[row][col] == ' ')
    		return true;
    	else
    		return false;
    }
    
    //Check whether the given parameter row is valid or not.
    //A row is valid if it is between 0 and the number of rows of the board.
    public boolean isValidRow(int row) {
    	return (row >= 0 && row < board.length);
    	//if (board[row][0] < board.length && board[row][0] >= 0)
    	//	return true;
    	//else return false;
    }

    //Check whether the given parameter col is valid or not.
    //a col is valid if it is between 0 and the number of columns of the board.
    public boolean isValidCol(int col) {
       //ERROR:
       return (col >= 0 && col < board[0].length);   
    	//if (board[0][col] < board.length && board[0][col] >= 0)
    //		return true;
    //	else return false;
    }
    
    //Return number of rows of TicTacToe board
    public int getNumRows() {
    	return board.length;
    }
 
    //Return number of columns of TicTacToe board
    public int getNumCols() {
    	return board[0].length;
    }
    
    //Place playerId at board[row][col].
    public void mark(int row, int col, char playerId) {
    	board[row][col] = playerId;
    }
  
    //Get a string representation of the board. 
    @Override 
    public String toString() {
        String str = "    ";
        for (int i = 0; i < board.length; i++)
            str += " " + i + "  "; 
        str += "\n";

        str += separateLine();

        for (int row = 0; row < board.length; row++) {
            str += " " + row + " |";
            for (int col = 0; col < board[row].length; col++) {
                str += " " + board[row][col] + " ";
                
                str += "|";
            }
            str += "\n";

            str += separateLine();
        }
        return str;
    }

    //separate lines of data in board
    //This method is declared as private since
    //clients of TicTacToeBoard class do not need to need to use it.
    //Only method toString needs to use it.
    private String separateLine() {
        String str = "   +";
        for (int i = 0; i < board[0].length; i++)
            str += "---+";
            str += "\n";
        return str;
    } 
   
    // find out whether there is a tie.
    //If every cell is occupied with either 'X' or 'O', 
    //return true.
    //If any cell is not occupied (that is, with value ' '), return false.
    public boolean tie() {
    	
            for (int i = 0; i < board.length ; i++){
    			for (int j = 0; j < board[i].length; j++){
    				if (board[i][j] == ' ')
    					return false;
    		}
    	}
    	return true;
    		}
    		
    		
/*
     for (int i = 0; i < board.length ; i++){
    	 for (int j =0; j < board[i].length ; j++)
    		 if(!(win(i,j)) && isAvailable(i,j))
    			 return true;
     }
    	return false;
    	
    }
    */
    
    
    
    //Check whether the player at row and col wins.
    //The player can winner by row, by col, or by diagonal.
    public boolean win(int row, int col) {
        //UPDATE: cleaner code
        return winByRow(row, col) || winByCol(row, col) ||
               (getNumRows() == getNumCols() && winByDiagonal(row, col)); 
        /*
    	if (winByRow(row,col))
    		return true;
    	else if(winByCol(row,col))
    		return true;
    	else if(winByDiagonal(row, col))
    		return true;
    	
    	return false;
        */
    	
    	//return win by row or win by column or win by diagonal

    }
    
    //Check whether the player at (row, col) can win that row or not.
    //If the player at (row, col) can win, then return the corresponding id,
    //otherwise, return ' '.
    public boolean winByRow(int row, int col) {
    	char val = board[row][col];
    	
    	for (int i = 0 ; i < board[row].length; i++)
    		if(board[row][i] != val)
    				return false;
    	return true;
    	//idea:
    		
    }
    
    // Check whether the player at (row, col) can win that col or not
    public boolean winByCol(int row, int col) {
    	char val = board[row][col];
        //UPDATE:	
        for (int i = 0; i < board.length; i++)
            if (board[i][col] != val)
               return false;

        return true; 

        
    }
    
    //Check whether (row, col) is in the diagonal, if not, return ' '.
    //Otherwise, check whether the player at (row, col) can win the diagonal.
    public boolean winByDiagonal(int row, int col) {
    	return winByDiagonalTRtoBL(row, col) || winByDiagonalTLtoBR(row, col);
    	/*
    		if (winByDiagonalTRtoBL (row, col))
    			return true;
    		
    		if (winByDiagonalTLtoBR(row,col))
    			return true;
    		
    		return false;
    		*/
    }
    
    public boolean winByDiagonalTRtoBL (int row, int col) {
    	char val = board[row][col];
    	if (row + col != board.length-1)
    		return false;
    	
    	 for(int i = 0; i < board.length; i++)
    		if (board[i][board.length-1-i] != val)
    		  return false;
    			
    	return true; 	
    }
    
    public boolean winByDiagonalTLtoBR(int row, int col){
    	char val = board[row][col];
    	if (row != col)
    		return false;
    		
    	for (int i = 0; i < board.length; i++)
    		if (board[i][i] != val)
    			return false;
    		
    	return true;
    }
}
