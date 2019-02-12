public class QueenBoard {
  private int[][]board;
  public QueenBoard(int size){
    board = new int[size][size]; //Assuming that the board is always a square.
  }
  private boolean addQueen(int r, int c) {
    if (board[r][c] != 0) { //There is already a queen or the spot is threatened.
      return false;
    }
    board[r][c] = -1;
    if (c != size - 1) { //Only if the queen is not already at the end. Add the death rays.
      for(int i = 1; i < size - c; i = i + 1) { //Loop for horizontal travel.
        board[r][c + i] = board[r][c + i] + 1; //Horizontal.
        if (r - i > -1) { //Upwards diagonal.
          board[r - i][c + i] = board[r - i][c + i] + 1; //Death ray.
        }
        if (r + i > size - 1) { //Downwards diagonal.
          board[r + i][c + i] = board[r + i][c + i] + 1; //Death ray.
        }
      }
    }
  }
  private boolean removeQueen(int r, int c) {
    if (board[r][c] != 1) { //There is no queen.
      return false;
    }
    board[r][c] = 0;
    if (c != size - 1) {
      for(int i = 1; i < size - c; i = i + 1) { //Same setup as the add method.
        board[r][c + i] = board[r][c + i] + 1;
        if (r - i > -1) {
          board[r - i][c + i] = board[r - i][c + i] + 1; //Remove death ray.
        }
        if (r + i > size - 1) {
          board[r + i][c + i] = board[r + i][c + i] + 1; //Remove death ray.
        }
      }
    }
  }
  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString() {
    String display = "";
    for(int i = 0; i < size; i = i + 1) {
      for(int j = 0; j < size; j = j + 1) {
        if (board[i][j] == -1) {
          display = display + "Q ";
        } else {
          display = display + "_ ";
        }
      }
      display = dislay + "\n";
    }
    return display;
  }
  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  private boolean cleanBoard() { //Checks if the board only contains 0s.
    for(int i = 0; i < size; i = i + 1) {
      for(int j = 0; j < size; j = j + 1) {
        if (board[i][j] != 0) {
          return false;
        }
      }
    }
    return true;
  }
  public boolean solve() {
    if (!cleanBoard) {
      throw IllegalStateException("Board is not clean.");
    }
    return solver(0); //Calls helper function.
  }
  private boolean solver(int c) { //Takes the column number as the parameter.
    if (c == size) { //All the queens have been addded.
      return true;
    }
    for(int r = 0; r < size; r = r + 1) { //Moves down the row for the column.
      if (addQueen(r, c)) { //If queen can be added.
        return solver(c + 1); //Move to next column.
      }
      removeQueen(r, c)
    }
    return false;
  }  
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions() {
    if (!cleanBoard) {
      throw IllegalStateException("Board is not clean.");
    }
  }
}
