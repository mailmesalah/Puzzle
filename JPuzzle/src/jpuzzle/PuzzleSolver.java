/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpuzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
This class is used to solve eleven tiles puzzle by traversing through all possible moves from the four moves a tile can have
last moved child nodes replaces parent nodes to check next, This continues until any of the node equals the end node.
*/
public class PuzzleSolver {
    /*
    Sarting from the start node the node travers through all possible moves each tiles can have. 
    */
    public static List<PuzzleGrid> getAllMoves(PuzzleGrid start, PuzzleGrid end){ 
        //Root node is set
        PuzzleMoveNode rootNode = new PuzzleMoveNode(null, start);
        //Queue list for keeping current non checked nodes
        Queue<PuzzleMoveNode> allMoves = new LinkedList<>();
        allMoves.add(rootNode);
        
        while(true){
            if(allMoves.isEmpty()){
                return null;
            }
            
            //top most queu item is returned to check for its possible next moves            
            PuzzleMoveNode currentNode= allMoves.poll();
            PuzzleGrid currentGrid=currentNode.getGrid();
            //gets the next moves the node can have
            for (PuzzleGrid puzzleGrid : nextMove(currentGrid)) {
                PuzzleMoveNode newMove = new PuzzleMoveNode(currentNode, puzzleGrid);
                //checks the new child node is equal to the end node, if yes traverse back through...
                //... the parent nodes to reach the root node
                if(puzzleGrid.getDisplacedTilesCount(end)==0){
                    //list is created to store the moves(grids)
                    List<PuzzleGrid> returnList = new ArrayList<>();
                    returnList.add(puzzleGrid);
                    PuzzleMoveNode temp = newMove.getParentNode();
                    //traverse through the nodes by changing to parent node
                    while(temp!=rootNode){
                        returnList.add(temp.getGrid());
                        temp=temp.getParentNode();
                    }
                    //adds th initial node for completion
                    returnList.add(start);
                    return returnList;
                }
                //checks if the same tile sequence already occur
                boolean contains = false;
                PuzzleMoveNode tempNode=currentNode;
                //iterates through current path to find if the same grid has come before
                while(tempNode!=rootNode){
                    if(tempNode.getGrid().equals(puzzleGrid)){
                        contains=true;
                    }
                    tempNode=tempNode.getParentNode();
                }
                //if not already added , add it
                if(!contains){
                    allMoves.add(newMove);                  
                }                
            }
        }
        
    }
    
    /*
    Does the next move for the current node, if any exists
    */
    private static List<PuzzleGrid> nextMove(PuzzleGrid node){
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
