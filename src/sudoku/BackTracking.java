package sudoku;

public class BackTracking {
    
    
    private boolean [] [] row  = new boolean[9][10];    //gives true if row has the number: boolean[row][number]
    private boolean [] [] column = new boolean[9][10];	//gives true if column has the number: boolean[column][number]
  
   
    private int getBoxNumber(int row, int column) {		//relation of box with row and column
        return 3 * (row / 3) + (column / 3);
    }
    
    
    private boolean [] [] box = new boolean[9][10];  	//gives true if box has the number: boolean[box][number]
    
    private boolean isEmptyField(int number) {								//checks if field is empty
        return number <= 0 || number > 9;							
  }
    
    private boolean allowed(int row, int column, int number) {				//if number is allowed in field
        return  (partialSudoku[row][column] == number 
        			|| isEmptyField(partialSudoku[row][column])
                    && ! this.row[row][number]
                    && ! this.column[column][number]
                    && ! this.box[getBoxNumber(row, column)][number]);
      }
    
    
    
    private int [] [] partialSudoku ;										//question or updated solution after every try
    
    private int [] [] sudoku  = new int[9][9];								//final solution
    
    
    public BackTracking(int [] [] partialSudoku ) {
      this.partialSudoku = partialSudoku;
      initialize();
    }
  
    private void initialize() {
            for (int i = 0; i < 9; i++) {
                for (int value = 1; value < 10; value++) {
                  this.box[i][value] = false;								//makes box boolean zero
                }
        }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {		
                  this.row[i][j + 1] = false;								//makes row boolean false
                  this.column[i][j + 1] = false;							//makes column boolean false
                  this.sudoku[i][j] = -1;							//initializes all places in final solution to -1
  
                  if (! isEmptyField(partialSudoku[i][j])) {				//field not empty check
                      this.sudoku[i][j] = partialSudoku[i][j];				//fills partial solution into final and updates boolean
                      this.row[i][partialSudoku[i][j]] = true;			
                      this.column[j][partialSudoku[i][j]] = true;
                      this.box[getBoxNumber(i,j)][partialSudoku[i][j]] = true;
                  }
                }
              }
    }
  
  
  
  
    
	public boolean solve(int count) {
		int row = count / 9;
		int column = count % 9;

		for (int value = 1; value < 10; value++) {
			if (allowed(row, column, value)) {
				boolean r = this.row[row][value];
				boolean c = this.column[column][value];
				boolean b = this.box[getBoxNumber(row, column)][value];
				this.sudoku[row][column] = value;
				this.row[row][value] = true;
				this.column[column][value] = true;
				this.box[getBoxNumber(row, column)][value] = true;
				if (count < 80) {
					if (solve(count + 1)) { // recursively solving
						return true;
					} else {
						this.sudoku[row][column] = partialSudoku[row][column]; // updating solutions  and all booleans if not  allowed
																				 
																					
						this.row[row][value] = r;
						this.column[column][value] = c;
						this.box[getBoxNumber(row, column)][value] = b;
					}
				}
				else {
					return true;
				}
			}
		}
		return false; // when no solution is found
	}
    
	public void print() { // prints final solution
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				System.out.print(sudoku[row][column] + "\t");
			}
			System.out.println();
		}
	}

}