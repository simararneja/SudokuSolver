package sudoku;

public class RuleBased {
	/*   
	 * searchColumn traverses the column and finds whether it can insert the value or not
	 *  
	 */
	
	private int[][] searchColumn(int value, int column, int row, Puzzle p) {					//column search function
		 int x, y,temp, tempCount1,count = 0, k = 0, tempCount = 0;
		 x= row;
		y = column;
		temp=y;
		
		for (y = column; (y < temp+3) ; y++) {
			if (p.grid[row][y] == -1) {
				count++;
			}
		}
		switch (count) {

		case 1:											//when only one empty space
			x = row;
			y=column;
			while (p.grid[row][y] != -1) {
				y++;
			}
			p.grid[row][y] = value;
			x=0;y=0;
			break;

		case 2:											//when two empty spaces
			x =0;
			y=column;
			temp=y;
			tempCount1=0;
			tempCount=0;
			tempCount = 0;
			k=0;
			for (y = column; y < temp + 3; y++) {
				tempCount1=tempCount;
				
				if (p.grid[row][y] == -1 ) {
					x = 0;
					for (x = 0; x < 9; x++) {
						if (p.grid[x][y] == value) {
							
							tempCount++;
							
							break;
						}
					}
					x=0;
					if(tempCount==tempCount1){
					k = y;
					}
				}
				//else{continue;}
			}

			if (tempCount == 1) {
				p.grid[row][k] = value;
				
				break;
			}
			break;

		case 3:										//when empty spaces is 3
			x = row;
			y=column;
			temp=y;
			tempCount1=0;
			tempCount=0;
			k=0;
			for (y = column; y < temp + 3; y++) {
				x = 0;
				tempCount1=tempCount;
				for (x = 0; x < 9; x++) {
					
					if (p.grid[x][y] == value) {
						tempCount++;
						
						break;
					}
				
				}
				if(tempCount1==tempCount){
					k = y;
				}
			}

			if (tempCount == 2) {
				p.grid[row][k] = value;
			}
			break;
		}

		return p.grid;
	}
	
//..................................................................................................................................
	/*The algorithm first divides the problem into three columns consisting three rows each.  
	 * consider first row of each column as TOP ROW
	 * consider second row of each column as MIDDLE ROW
	 * Consider third ow of each column as LAST ROW*/
	
	void solver(Puzzle p) {                         
		int tempRow = 0, tempCol = 0;
		int tempRow1 = 0, tempCol1 = 0;
		int status=0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int freq[] = p.freq(p);
				
				int value = p.grid[i][j];
				

			if (value != -1) { 										// considering filled cells
					if (freq[value] == 1 || freq[value] == 9) {
						continue; 									// that value has been filled all the 9 valid
																	// cells or filled just once
					}
					
					
//.................................................TOP ROW.................................................		
					
					

					if (i == 0 || i == 3 || i == 6) {               

						if (j == 0 || j == 1 || j == 2) {
							
							tempRow = i;
							tempCol = j;
							tempRow++; 										// Jump to next Row to search for the value
										
							for (tempCol = 3; tempCol < 9; tempCol++) {
								if (value == p.grid[tempRow][tempCol]) {
									status = 1;
									
									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1++;								//Jump to next Row
									if (tempCol == 3 || tempCol == 4
											|| tempCol == 5) {

										for (tempCol1 = 6; tempCol1 < 9; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) { 
												// 678
												
												break;
											}

											if (tempCol1 == 8) {
												
												p.grid = searchColumn(value, 6,
														tempRow1, p);
												break;
											}
										}
										// break;
									}

									else if (tempCol == 6 || tempCol == 7
											|| tempCol == 8) {

										for (tempCol1 = 3; tempCol1 < 6; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 345
												break;
											}
											if (tempCol1 == 5) {
												p.grid = searchColumn(value, 3,
														tempRow1, p); 					// put column search 
																		
																		
																		
												break;
											}

										}
										break;
									}

								}

							}

							if (status == 0) {
								
																			// when not found in next row
								tempRow++;									// jump to Bottom row
								for (tempCol = 3; tempCol < 9; tempCol++) {
									if (value == p.grid[tempRow][tempCol]) {

										tempCol1 = tempCol;
										tempRow1 = tempRow;
										tempRow1--;							//jump to prev row
										if (tempCol == 3 || tempCol == 4
												|| tempCol == 5) {
											
											p.grid = searchColumn(value, 6,
													tempRow1, p);
											
											break;
										} else if (tempCol == 6 || tempCol == 7
												|| tempCol == 8) {

											p.grid = searchColumn(value, 3,
													tempRow1, p);
											break;
										}

									}

								}
								// when value not found in both the rows
								break;

							}

						}
						
						
						
						
/*first row 3,4,5	*/		else if (j == 3 || j == 4 || j == 5) {
	
							status=0;
							tempRow = i;
							tempCol = j;
							tempRow++;			 // Jump to next Row to search for the value
										
							
							for (tempCol = 0; tempCol < 9; tempCol++) {
								if (value == p.grid[tempRow][tempCol]) {
									
									status=1;
									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1++;							//Jump to next Row
									if (tempCol == 0 || tempCol == 1
											|| tempCol == 2) {
										for (tempCol1 = 6; tempCol1 < 9; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) { 
																						// 678
												break;
											}

											if (tempCol1 == 8) {
												p.grid = searchColumn(value, 6,
														tempRow1, p);
												break;
											}
										}
										break;
									}

									
									else if (tempCol == 6 || tempCol == 7
											|| tempCol == 8) {
										for (tempCol1 = 0; tempCol1 < 3; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 345
												break;
											}
											if (tempCol1 == 2) {
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											}
											// put column search code
										}
										break;
									}
									
								}		
							}	 

								if(status==0) { 		// when not found in next Row
									tempRow++;			// jump to lower row
									
									for (tempCol = 0; tempCol < 9; tempCol++) {
										if (value == p.grid[tempRow][tempCol]) {

											tempCol1 = tempCol;
											tempRow1 = tempRow;
											tempRow1--;
											if (tempCol == 0 || tempCol == 1
													|| tempCol == 2) {

												p.grid = searchColumn(value, 6,
														tempRow1, p);
												break;

											} else if (tempCol == 6
													|| tempCol == 7
													|| tempCol == 8) {
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											}

										}

									}
									// when value not found in both the rows
									break;
									
								}
									
				}					
									
/*first row 678 */							
						
						else { // j= 6 or 7 or 8
							
							status=0;
							tempRow = i;
							tempCol = j;
							tempRow++; // Jump to next Row to search for the
										// value
							for (tempCol = 0; tempCol < 6; tempCol++) {
								if (value == p.grid[tempRow][tempCol]) { 
									status=1;
									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1++;
									if (tempCol == 3 || tempCol == 4
											|| tempCol == 5) {
										for (tempCol1 = 0; tempCol1 < 3; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 678
												break;
											}
											if (tempCol1 == 2) {
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											}
										}

									} else if (tempCol == 0 || tempCol == 1
											|| tempCol == 2) {
										for (tempCol1 = 3; tempCol1 < 6; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 345
												break;
											}
											if (tempCol1 == 5) {
												p.grid = searchColumn(value, 3,
														tempRow1, p);

												break;
											}

										}
										//break;
									}
									
								}
								
								}
							if(status==0) { // when not found in next Row
									tempRow++;// jump to next row
									for (tempCol = 0; tempCol < 6; tempCol++) {
										if (value == p.grid[tempRow][tempCol]) {

											tempCol1 = tempCol;
											tempRow1 = tempRow;
											tempRow1--;

											if (tempCol == 3 || tempCol == 4
													|| tempCol == 5) {
												
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											} else if (tempCol == 0
													|| tempCol == 1
													|| tempCol == 2) {
												p.grid = searchColumn(value, 3,
														tempRow1, p);

												break;
											}

										}

									}
									// when value not found in both the rows
									//break;
								}
							
						}
						
					
					}

// ----------------------------MIDDLE ROW--------------------------------------------------------------------------------------------------------------------

					
					
					
					else if (i == 1 || i == 4 || i == 7) {
						

/* Middle row j=0,1,2*/	if (j == 0 || j == 1 || j == 2) {
							status=0;
							tempRow = i;
							tempCol = j;
							tempRow++; // Jump to next Row to search for the
										// value

							for (tempCol = 3; tempCol < 9; tempCol++) {
								if (value == p.grid[tempRow][tempCol]) {
									status=1;
									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1 = tempRow1 - 2;// go to uppermost
															// row in unit

									if (tempCol == 3 || tempCol == 4 || tempCol == 5){
										for (tempCol1 = 6; tempCol1 < 9; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the uppermost
												// row 678
												break;
											}
											if (tempCol1 == 8) {
												p.grid = searchColumn(value, 6,
														tempRow1, p);
												break;
											}
										}
										break;
									} 
									
									else if (tempCol == 6 || tempCol == 7
											|| tempCol == 8) {
										for (tempCol1 = 3; tempCol1 < 6; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the uppermost
												// row 345
												break;
											}
											if (tempCol1 == 5) {
												p.grid = searchColumn(value, 3,
														tempRow1, p);
												break;
											}
											// put column search code
										}
										break;
									}
								} 
								
							}	
								
								
								
								if(status==0) { // when not found in next Row
									tempRow = tempRow - 2;// jump to uppermost
															// row
									for (tempCol = 3; tempCol < 9; tempCol++) {
										if (value == p.grid[tempRow][tempCol]) { 
											tempCol1 = tempCol;
											tempRow1 = tempRow;
											tempRow1 = tempRow1 + 2;

											if (tempCol == 3 || tempCol == 4
													|| tempCol == 5) {
												for (tempCol1 = 6; tempCol1 < 9; tempCol1++) {
													if (value == p.grid[tempRow][tempCol]) {
														break;
													}
													if (tempCol1 == 8) {
														p.grid = searchColumn(
																value, 6,
																tempRow1, p);
														break;
													}
												}
												break;
											}
											else if (tempCol == 6 || tempCol == 7 || tempCol == 8) {
													
													
												for (tempCol1 = 3; tempCol1 < 6; tempCol1++) {
													if (value == p.grid[tempRow][tempCol]) {

														break;
													}
													if (tempCol1 == 5) {
														p.grid = searchColumn(
																value, 3,
																tempRow1, p);
														break;
													}

												}
												break;
											}
											break;
										}

									}
									break;
									// when value not found in both the rows
									
								}
							                             
						}

						
						
/*Middle row j=3,4,5 */

						else if (j == 3 || j == 4 || j == 5) {
							status=0;
							tempRow = i;
							tempCol = j;
							tempRow++; // Jump to next Row to search for the
										// value
							for (tempCol = 0; tempCol < 9; tempCol++) {
								if (value == p.grid[tempRow][tempCol]) { 
									
									status =1;
									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1 = tempRow1 - 2;
									
									if (tempCol == 0 || tempCol == 1
											|| tempCol == 2) {
										
										for (tempCol1 = 6; tempCol1 < 9; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												
												// when found in the uppermost
												// row 678
												break;
											}
											if (tempCol1 == 8) {
												
												p.grid = searchColumn(value, 6,
														tempRow1, p);
												break;
											}
										}
										break;
										
									} 
									else if (tempCol == 6 || tempCol == 7
											|| tempCol == 8) {
										
										for (tempCol1 = 0; tempCol1 < 3; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 345
												
												break;
											}
											if (tempCol1 == 2) {
												
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;

											}
										}
										break;
									}
									
								} 
								
							}
							
							
								if(status==0) { 									// when not found in next Row
									tempRow = tempRow - 2;							// jump to uppermost
																					// row
									for (tempCol = 0; tempCol < 9; tempCol++) {// search
																			
										// row
										if (value == p.grid[tempRow][tempCol]) { 
											tempCol1 = tempCol;
											tempRow1 = tempRow;
											tempRow1 = tempRow1 + 2;
											if (tempCol == 0 || tempCol == 1
													|| tempCol == 2) {

												
													p.grid = searchColumn(
															value, 6, tempRow1,
															p);
													break;
												

											} else if (tempCol == 6
													|| tempCol == 7
													|| tempCol == 8) {

												
													p.grid = searchColumn(
															value, 0, tempRow1,
															p);
													break;
												
												// put column search code

											}

										}

									}
									break;
									// when value not found in both the rows
								}
							
						
						} 
						
						
	/*middle row j=6,7,8 */					
						
						else { // j= 6 or 7 or 8
							status=0;
							tempRow = i;
							tempCol = j;
							tempRow++; 						// Jump to next Row to search for the
															// value
							for (tempCol = 0; tempCol < 6; tempCol++) {
								if (value == p.grid[tempRow][tempCol]) { 
									status=1;

									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1 = tempRow1 - 2;

									if (tempCol == 3 || tempCol == 4
											|| tempCol == 5) {
										for (tempCol1 = 0; tempCol1 < 3; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
																		// when found in the upper row
																		// 678
												break;
											}
											if (tempCol1 == 2) {
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											}
										}
										break;

									} else if (tempCol == 0 || tempCol == 1
											|| tempCol == 2) {
										for (tempCol1 = 3; tempCol1 < 6; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
																						// when found in the upper row
																						// 345
												break;
											}
											if (tempCol1 == 5) {
												p.grid = searchColumn(value, 3,
														tempRow1, p);
												break;
											}

											// put column search code
										}
										break;
									}
									
								} 
								
							}	
								
							
							if(status==0) { 							// when not found in next Row
									tempRow = tempRow - 2;				// jump to uppermost row
									for (tempCol = 0; tempCol < 6; tempCol++) {
										if (value == p.grid[tempRow][tempCol]) { 
											
											tempCol1 = tempCol;
											tempRow1 = tempRow;
											tempRow1 = tempRow1 + 2;

											if (tempCol == 3 || tempCol == 4
													|| tempCol == 5) {

												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;

											} else if (tempCol == 0
													|| tempCol == 1
													|| tempCol == 2) {

												p.grid = searchColumn(value, 3,
														tempRow1, p);
												break;

											}

										}

									}
									break;
									// when value not found in both the rows
								}
							
						}	

					}
					
					
// --------------------------------------------------LAST ROW----------------------------------------------------------------------------------------------------------
/*Last Row j=0,1,2 *//// 
					
					
					else if (i == 2 || i == 5 || i == 8) {
						
						if (j == 0 || j == 1 || j == 2) {
							tempRow = i;
							tempCol = j;
							tempRow--; 				// Jump to previous Row to search for the // value
										
							for (tempCol = 3; tempCol < 9; tempCol++) {
																
								if (value == p.grid[tempRow][tempCol]) {
									
									status = 1;
									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1--;        // Jump to previous row when finds the VALUE in current row
									if (tempCol == 3 || tempCol == 4
											|| tempCol == 5) {

										for (tempCol1 = 6; tempCol1 < 9; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) { // 678         //
																								
												break;
											}

											if (tempCol1 == 8) {
												
												p.grid = searchColumn(value, 6,  
														tempRow1, p);
												break;
											}
										}
										// break;
									}

									else if (tempCol == 6 || tempCol == 7
											|| tempCol == 8) {

										for (tempCol1 = 3; tempCol1 < 6; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 345
												break;
											}
											if (tempCol1 == 5) {
												p.grid = searchColumn(value, 3,
														tempRow1, p); // put
																		// column
																		// search
																		// code
												break;
											}

										}
										break;
									}

								}

							}

							if (status == 0) {
																		// when not found jumps to Previous row
								tempRow--;
								for (tempCol = 3; tempCol < 9; tempCol++) {
									
									if (value == p.grid[tempRow][tempCol]) {   // when found in current row it jumps back to next row

										tempCol1 = tempCol;
										tempRow1 = tempRow;
										tempRow1++;
										if (tempCol == 3 || tempCol == 4
												|| tempCol == 5) {
											
											p.grid = searchColumn(value, 6,
													tempRow1, p);
											break;
										} else if (tempCol == 6 || tempCol == 7
												|| tempCol == 8) {
											
											p.grid = searchColumn(value, 3,
													tempRow1, p);
											break;
										}

									}

								}
								// when value not found in both the rows
								break;

							}

						}
						
						
						
						
/*last row row 3,4,5	*/		else if (j == 3 || j == 4 || j == 5) {    // Jump to previous Row to search for the // value
							status=0;
							tempRow = i;
							tempCol = j;
							tempRow--;                            
										
							for (tempCol = 0; tempCol < 9; tempCol++) {
								if (value == p.grid[tempRow][tempCol]) {    // Jump to previous row when finds the VALUE in current row
									status=1;
									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1--;
									if (tempCol == 0 || tempCol == 1
											|| tempCol == 2) {
										for (tempCol1 = 6; tempCol1 < 9; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) { 
																						// 678
												break;
											}

											if (tempCol1 == 8) {
												
												p.grid = searchColumn(value, 6,
														tempRow1, p);
												break;
											}
										}
										//break;
									}

									
									else if (tempCol == 6 || tempCol == 7
											|| tempCol == 8) {
										for (tempCol1 = 0; tempCol1 < 3; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 345
												break;
											}
											if (tempCol1 == 2) {
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											}
											// put column search code
										}
										break;
									}
									
								}		
							}	 

								if(status==0) { 
									tempRow--;
									for (tempCol = 0; tempCol < 9; tempCol++) {
										if (value == p.grid[tempRow][tempCol]) {   // when not found jumps to Previous row

											tempCol1 = tempCol;
											tempRow1 = tempRow;
											tempRow1++;                    // when found in current row it jumps back to next row
											if (tempCol == 0 || tempCol == 1
													|| tempCol == 2) {

												p.grid = searchColumn(value, 6,
														tempRow1, p);
												
												break;

											} else if (tempCol == 6
													|| tempCol == 7
													|| tempCol == 8) {
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											}

										}

									}
									// when value not found in both the rows
									break;
									
								}
									
				}					
									
/*last row 678 */							
						
						else if(j==6 || j==7 || j== 8){ // j= 6 or 7 or 8 
							tempRow = i;
							tempCol = j;      // Jump to previous Row to search for the // value
							tempRow--; 
							for (tempCol = 0; tempCol < 6; tempCol++) {
								if (value == p.grid[tempRow][tempCol]) {     // Jump to previous row when finds the VALUE in current row
									status=1;
									tempCol1 = tempCol;
									tempRow1 = tempRow;
									tempRow1--;
									if (tempCol == 3 || tempCol == 4
											|| tempCol == 5) {
										for (tempCol1 = 0; tempCol1 < 3; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 678
												break;
											}
											if (tempCol1 == 2) {
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											}
										}

									} else if (tempCol == 0 || tempCol == 1
											|| tempCol == 2) {
										for (tempCol1 = 3; tempCol1 < 6; tempCol1++) {
											if (value == p.grid[tempRow1][tempCol1]) {
												// when found in the third row
												// 345
												break;
											}
											if (tempCol1 == 5) {
												p.grid = searchColumn(value, 3,
														tempRow1, p);

												break;
											}

										}
										//break;
									}
									
								}
								
								}
							if(status==0) { 
								tempRow--;                       // when not found jumps to Previous row
									for (tempCol = 0; tempCol < 6; tempCol++) {
										if (value == p.grid[tempRow][tempCol]) {   // when found in current row it jumps back to next row

											tempCol1 = tempCol;
											tempRow1 = tempRow;
											tempRow1++;

											if (tempCol == 3 || tempCol == 4
													|| tempCol == 5) {
												
												p.grid = searchColumn(value, 0,
														tempRow1, p);
												break;
											} else if (tempCol == 0
													|| tempCol == 1
													|| tempCol == 2) {
												p.grid = searchColumn(value, 3,
														tempRow1, p);

												break;
											}

										}

									}
									// when value not found in both the rows
									//break;
								}
							
						}
						
					
					}

				
			}
		}

		
	}
		System.out.println("The Partial solution from rule Based is:: \n");
		p.display(p);

}
	}
