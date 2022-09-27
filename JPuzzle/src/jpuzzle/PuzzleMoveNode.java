/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpuzzle;

/*
Node class which keeps the state of each movement of the puzzle.
Each movement nodes are linked to its parent node while creating.
once any node is matched with the end node, it traverse back to the root node through these parent nodes to get the path
 */
public class PuzzleMoveNode {

    //parent node for traversing
    private PuzzleMoveNode parentNode;
    private PuzzleGrid grid;

    //Constructors
    public PuzzleMoveNode() {
    }

    public PuzzleMoveNode(PuzzleMoveNode parentNode) {
        this.parentNode = parentNode;
    }

    public PuzzleMoveNode(PuzzleMoveNode parentNode, PuzzleGrid grid) {
        this.parentNode = parentNode;
        this.grid = grid;
    }

    //getters and setters
    public PuzzleMoveNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(PuzzleMoveNode parentNode) {
        this.parentNode = parentNode;
    }

    public PuzzleGrid getGrid() {
        return grid;
    }

    public void setGrid(PuzzleGrid grid) {
        this.grid = grid;
    }    

}
