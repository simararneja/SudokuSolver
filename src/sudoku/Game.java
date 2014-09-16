package sudoku;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puzzle p = new Puzzle();
		System.out.println("The sudoku problem:");
		p.display(p);
	long t1 = System.nanoTime();
	new RuleBased().solver(p);
		
	  
	
		BackTracking sudoku = new BackTracking(p.grid);
		      
		   
		      
		      if ( sudoku.solve(0) ) {
		        System.out.println("Solution found");
		        sudoku.print();
		      } else {
		        System.out.println("No solution has been found");
		      }
		   
		      long t2 = System.nanoTime() - t1;
				
		      
		      System.out.println("Time used: " + t2 + " ns / " + (t2 / 1000000) + " ms");
		      
		      //sudoku.createSudoku(20);
		
		
		
		
		
	}

}
