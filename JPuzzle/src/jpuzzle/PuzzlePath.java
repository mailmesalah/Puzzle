/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpuzzle;

import java.util.ArrayList;
import java.util.List;

/*
Keeps a path of tile movements from starting to the end
*/
public class PuzzlePath implements Comparable<PuzzlePath> {

    int g;//G value
    int h;//heurisitc value
    List<PuzzleGrid> path;//Path so far this object has traversed

    public PuzzlePath() {
    }

    public PuzzlePath(int g, List<PuzzleGrid> path) {
        this.g = g;        
        this.h = 0;
        this.path = new ArrayList<>();
        //Grids are added to the newly created list
        path.stream().forEach((puzzleGrid) -> {
            this.path.add(puzzleGrid);
        });
    }

    public List<PuzzleGrid> getPath() {
        return path;
    }

    public void setPath(List<PuzzleGrid> path) {
        this.path = path;
    }

    /*
    Creates new path with the added new node, g is incremented and h value is updated 
    */
    public PuzzlePath createNewPath(PuzzleGrid node, int h) {
        PuzzlePath newPath = new PuzzlePath(g + 1, path);
        newPath.getPath().add(node);
        newPath.h = h;

        return newPath;
    }

    /*
    used for priority queue checking, compares two grids for it difference in displacement and g
    */
    @Override
    public int compareTo(PuzzlePath o) {
        return (this.g+h)-(o.g+o.h);
    }
   
}
