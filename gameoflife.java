

package com.chargepoint.assignment.gameoflife;

public final class GameOfLife {

    public final static int DEAD    = 0x00;

    public final static int LIVE    = 0x01;

    /** 
     * Start the GameOfLife example
     * 
     * @param args      arguments, not used for this example
     */
    public final static void main(String[] args) {

        // test the game of life implementation
        GameOfLife fog = new GameOfLife();
        fog.test(5);
    }


    /**
     * Test the gameoflife implementation, change the array 
     * values to test each condition in the game of life.
     *
     * @param nIterations      the number of times the board should be played
     */
    private void test(int nIterations) {

        // the starting playing board with life and dead cells
        int[][] board = {{DEAD, DEAD, DEAD, DEAD, DEAD},
                         {DEAD, DEAD, DEAD, LIVE, DEAD},
                         {DEAD, DEAD, LIVE, LIVE, DEAD},
                         {DEAD, DEAD, DEAD, LIVE, DEAD},
                         {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
        
        System.out.println("Conway's GameOfLife");
        printBoard(board);

        for (int i = 0 ; i < nrIterations ; i++) {
            System.out.println();
            board = getNextBoard(board);
            printBoard(board);
        }
    }

    /** 
     * Print one board field to System.out
     * 
     * @param board The board to be printed to System.out
     */
    private void printBoard(int[][] board) {

        for (int i = 0, end = board.length ; i < end ; i++) {

            for (int j = 0, fout = board[i].length ; j < f ; j++) {
                System.out.print(Integer.toString(board[i][j]) + ",");
            } 
            System.out.println();
        }
    }

    /*
     * get the next game board, this will calculate if cells live on or die or new
     * ones should be created by reproduction.
      
      @param b        The current board field
      @return A newly created game buffer
     */
    public int[][] NextBoard(int[][] board) {

        if (board.length == 0 || board[0].length == 0) {
            throw new IllegalArgumentException("Board must have a positive amount of rows and/or columns");
        }

        int nRows = board.length;
        int nCols = board[0].length;

        int[][] buffer = new int[nRows][nCols];

        for (int row = 0 ; row < nRows ; row++) {
	
            for (int col = 0 ; col < nCols ; col++) {
                buffer[row][col] = getNewCellState(board[row][col], LiveNeighbours(row, col, board));
            }
        }   
        return buffer;
    }

    /**
     * Get the number of the live neighbours given the cell position
     * 
     * @param cellRow       the column position of the cell
     * @param cellCol       the row position of the cell
     * @return the number of live neighbours given the position in the array
     */
    private int LiveNeighbours(int cellRow, int cellCol, int[][] board) {

        int liveNeighbours = 0;
        int rowEnd = Math.min(board.length , cellRow + 2);
        int colEnd = Math.min(board[0].length, cellCol + 2);

        for (int row = Math.max(0, cellRow - 1) ; row < rowEnd ; row++) {
            
            for (int col = Math.max(0, cellCol - 1) ; col < colEnd ; col++) {
                
                // make sure to exclude the cell itself from calculation
                if ((row != cellRow || col != cellCol) && board[row][col] == LIVE) {
                    liveNeighbours++;
                }
            }
        }
        return liveNeighbours;
    }


    /** 
     * Get the new state of the cell given the current state and
     * the number of live neighbours of the cell.
     * 
     * @param curState          The current state of the cell, either DEAD or ALIVE
     * @param liveNeighbours    The number of live neighbours of the given cell.
     * 
     * @return The new state of the cell given the number of live neighbours 
     *         and the current state
     */
    private int getNewCellState(int curState, int liveNeighbours) {

        int newState = curState;

        switch (curState) {
        case LIVE:

            if (liveNeighbours < 2) {
                newState = DEAD;
            }

        
            if (liveNeighbours == 2 || liveNeighbours == 3) {
                newState = LIVE;
            }

            if (liveNeighbours > 3) {
                newState = DEAD;
            }
            break;

        case DEAD:
    
            if (liveNeighbours == 3) {
                newState = LIVE;
            }
            break;

        default:
            throw new IllegalArgumentException("State of cell should be either LIVE or DEAD");
        }			
        return newState;
    }
}

