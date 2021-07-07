# Sliding-Puzzle
Efficient Solver for N*N Sliding puzzle problem implemented in Java


Example:  
Enter N:  
3  
Enter intial matrix:  
0 8 7   
6 5 4  
3 2 1  
Solution is Possible  
Moves is 28  

0 8 7  
6 5 4  
3 2 1  

8 0 7  
6 5 4  
3 2 1  

8 7 0  
6 5 4  
3 2 1  

8 7 4  
6 5 0  
3 2 1  

8 7 4  
6 5 1  
3 2 0  

8 7 4  
6 5 1  
3 0 2  

8 7 4  
6 5 1  
0 3 2  

8 7 4  
0 5 1  
6 3 2  

0 7 4  
8 5 1  
6 3 2  

7 0 4  
8 5 1  
6 3 2  

7 4 0  
8 5 1  
6 3 2  

7 4 1  
8 5 0  
6 3 2  

7 4 1  
8 5 2  
6 3 0  

7 4 1  
8 5 2  
6 0 3  

7 4 1  
8 5 2  
0 6 3  

7 4 1  
0 5 2  
8 6 3  

0 4 1  
7 5 2  
8 6 3  

4 0 1  
7 5 2  
8 6 3  

4 1 0  
7 5 2  
8 6 3  

4 1 2  
7 5 0  
8 6 3  

4 1 2  
7 5 3  
8 6 0  

4 1 2  
7 5 3  
8 0 6  

4 1 2  
7 5 3  
0 8 6  

4 1 2  
0 5 3  
7 8 6  

0 1 2  
4 5 3  
7 8 6  

1 0 2  
4 5 3  
7 8 6  

1 2 0  
4 5 3  
7 8 6  

1 2 3  
4 5 0  
7 8 6  

1 2 3  
4 5 6  
7 8 0  

Execution time for IDAstar is 0.25600 seconds  
Execution time for Astar is 0.34300 seconds  
Execution time for IDDFS is 35.04600 seconds  
Execution time for BFS is 0.75100 seconds  

