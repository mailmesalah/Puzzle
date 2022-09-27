/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
A* Algorithm to solve eleven tile puzzle
*/
public class AStarAlgorithm {
    
    /*
    The method implements A* algorithm to deduct the possible path
    */
    public static List<PuzzleGrid> getAllMoves(PuzzleGrid start, PuzzleGrid end){
        //creates the initial grid with start grid        
        PuzzleGrid initialGrid = new PuzzleGrid(start.getTiles());
        //Used to keep all the current moves
        PriorityQueue<PuzzlePath> listOfMoves = new PriorityQueue <>();
        List<PuzzleGrid> firstGridList = new ArrayList<>();
        firstGridList.add(initialGrid);
        //first path is added with the G value by calculating the total number of displacement
        PuzzlePath firstPath = new PuzzlePath(initialGrid.getDisplacedTilesCount(end), firstGridList);
        //adds th first puzzle path object to the list of moves
        listOfMoves.add(firstPath);
        
        while(true){
            if(!listOfMoves.isEmpty()){
                //first element in the priority queue is taken to find its new possible moves
                PuzzlePath path = listOfMoves.poll();
                //last grid in that path is selected 
                PuzzleGrid lastNode = path.getPath().get(path.getPath().size()-1);
                
                for (PuzzleGrid puzzleGrid: nextMove(lastNode)) {
                    //Checks for possible moves in the last grid of the selected path
                    PuzzlePath newPath=path.createNewPath(puzzleGrid, puzzleGrid.getDisplacedTilesCount(end));
                    //checks if the current new node is same as the end node, if yes the path has been found and return the path
                    if(puzzleGrid.getDisplacedTilesCount(end)==0){
                        return newPath.getPath();
                    }
                    //new path is added to the priority queue if it doesnt exist already
                    boolean contains=false;
                    for (PuzzlePath puzzlePath : listOfMoves) {
                        if(puzzlePath.getPath().contains(puzzleGrid)){
                            contains=true;
                        }
                    }
                    //if not already added, add it
                    if(!contains){
                        listOfMoves.add(newPath);
                    }
                }
                
            }else{
                return null;
            }
        }
    }
    
    /*
    Does the next move for the current node, if any exists
    */
    private static List<PuzzleGrid> nextMove(PuzzleGrid node){
        //Left/Right/Up/Down moves are done, if successful the nesly created node is added to the list to return
        List<PuzzleGrid> returnNodes = new ArrayList<>();
        PuzzleGrid downNode = new PuzzleGrid(node.getTiles());
        if(downNode.moveDown()){
            returnNodes.add(downNode);
        }
        
        PuzzleGrid upNode = new PuzzleGrid(node.getTiles());
        if(upNode.moveUp()){
            returnNodes.add(upNode);
        }
        
        PuzzleGrid leftNode = new PuzzleGrid(node.getTiles());
        if(leftNode.moveLeft()){
            returnNodes.add(leftNode);
        }
        
        PuzzleGrid rightNode = new PuzzleGrid(node.getTiles());
        if(rightNode.moveRight()){
            returnNodes.add(rightNode);
        }
        
        return returnNodes;
    }
}
