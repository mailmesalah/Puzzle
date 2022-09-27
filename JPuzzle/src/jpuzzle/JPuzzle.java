/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JPuzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Input
        Scanner sc = new Scanner(System.in);

        System.out.println("Please Enter the Input in xxxxxxxxxxxxxxxx2yyyyyyyyyyyyyyyy format:");
        String problem = sc.nextLine();
        
        String startGridTiles = problem.substring(0, 16);//"++dd+b_d+dabcbad";
        String endGridTiles = problem.substring(17, 17 + 16);//"++dd+b_d+dbacadb";
        //++dd+b_d+dabcbad?++dd+b_d+dbacadb
        sc.close();
        
        //initialising Start and end grids
        PuzzleGrid startG = new PuzzleGrid(startGridTiles);
        PuzzleGrid endG = new PuzzleGrid(endGridTiles);

        System.out.println("Start Grid");
        System.out.println(startG.toString());

        System.out.println("End Grid");
        System.out.println(endG.toString());

        long startTime =System.currentTimeMillis();        
        //Calls the PuzzleSolver getAllMove method to get the solution        
        List<PuzzleGrid> temp = PuzzleSolver.getAllMoves(startG, endG);
        List<PuzzleGrid> thePath = new ArrayList<>();
        for (int i = temp.size()-1; i >= 0; i--) {
            thePath.add(temp.get(i));
        }
        //Prints all the moves
        System.out.println("Puzzle Solver Output");
        printAllMoves(thePath);        
        
        System.out.println("Problem Solver Execution time: "+(System.currentTimeMillis()-startTime));
        System.out.println();
        
        startTime =System.currentTimeMillis();        
        //Calls the getAllMoves function of AStarAlgorithm class to get the solution
        thePath = AStarAlgorithm.getAllMoves(startG, endG);

        System.out.println("A* Algorithm Output");
        printAllMoves(thePath);
        
        System.out.println("A* Algorithm Execution time: "+(System.currentTimeMillis()-startTime));
        
    }

    /*
    Prints all the resulting moves with one space difference
     */
    private static void printAllMoves(List<PuzzleGrid> thePath) {

        for (int i = 0; i < PuzzleGrid.HEIGHT; i++) {
            for (PuzzleGrid grid : thePath) {
                String stiles = grid.getTiles();
                System.out.print(stiles.substring(i * PuzzleGrid.WIDTH, (i * PuzzleGrid.WIDTH) + PuzzleGrid.WIDTH) + " ");
            }
            System.out.println();
        }
    }

}
