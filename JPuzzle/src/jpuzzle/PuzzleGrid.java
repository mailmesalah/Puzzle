/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpuzzle;

/*
Represents the Grid state of a single move
*/
public class PuzzleGrid {
    //Width and Height of the grid is declaired
    public final static int WIDTH = 4;
    public final static int HEIGHT = 4;

    //string value of tiles
    private String tiles;
    //the index value of empty tile is kept in this variable
    private int emptyTileIndex;

    //Contructors
    public PuzzleGrid() {
    }

    public PuzzleGrid(String tiles) {
        this.tiles = tiles;
        emptyTileIndex = tiles.indexOf("_");
    }

    //Setter and getters
    public String getTiles() {
        return tiles;
    }

    public void setTiles(String tiles) {
        this.tiles = tiles;
    }

    public int getEmptyTileIndex() {
        return emptyTileIndex;
    }

    public void setEmptyTileIndex(int emptyTileIndex) {
        this.emptyTileIndex = emptyTileIndex;
    }

    /*
    Moves the empty tile one up by swaping the tile above it only if there is a movable tile.
    Returns the status if successfully moved or not
    */
    public boolean moveUp() {
        if (emptyTileIndex - WIDTH >= 0) {
            int tileIndex = emptyTileIndex - WIDTH;
            char tile = tiles.charAt(tileIndex);
            if (tile == '+') {
                //Immovable tile
                return false;
            }
            //swaps if its movable
            swapTiles(emptyTileIndex, tileIndex);
            emptyTileIndex = tileIndex;
            return true;
        }

        return false;

    }

    /*
    Moves the empty tile one down by swaping the tile below it, only if there is a movable tile.
    Returns the status if successfully moved or not
    */
    public boolean moveDown() {
        if (emptyTileIndex + WIDTH < (WIDTH * HEIGHT)) {
            int tileIndex = emptyTileIndex + WIDTH;
            char tile = tiles.charAt(tileIndex);
            if (tile == '+') {
                //Immovable tile
                return false;
            }
            //swaps if its movable
            swapTiles(emptyTileIndex, tileIndex);
            emptyTileIndex = tileIndex;
            return true;
        }

        return false;
    }

    /*
    Moves the empty tile left by swaping the tile left to it, only if there is a movable tile.
    Returns the status if successfully moved or not
    */
    public boolean moveLeft() {
        if (emptyTileIndex % WIDTH != 0 && emptyTileIndex != 0) {
            int tileIndex = emptyTileIndex - 1;
            char tile = tiles.charAt(tileIndex);
            if (tile == '+') {
                //Immovable tile
                return false;
            }
            //swaps if its movable
            swapTiles(emptyTileIndex, tileIndex);
            emptyTileIndex = tileIndex;
            return true;
        }

        return false;
    }

    /*
    Moves the empty tile right by swaping the tile at right to it, only if there is a movable tile.
    Returns the status if successfully moved or not
    */
    
    public boolean moveRight() {
        if ((emptyTileIndex + 1) % WIDTH != 0 && emptyTileIndex != (WIDTH * HEIGHT) - 1) {
            int tileIndex = emptyTileIndex + 1;
            char tile = tiles.charAt(tileIndex);
            if (tile == '+') {
                //Immovable tile
                return false;
            }
            //swaps if its movable
            swapTiles(emptyTileIndex, tileIndex);
            emptyTileIndex = tileIndex;
            return true;
        }

        return false;
    }

    /*
    Swaps the tiles with their index provided, one of the tile being emty tile
    */
    private void swapTiles(int i, int j) {
        char[] array = tiles.toCharArray();

        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;

        tiles = new String(array);
    }

    /*
    Returns the count of tiles that is out of space with the provided grid as argument
    */
    public int getDisplacedTilesCount(PuzzleGrid node){
        int count=0;
        for (int i = 0; i < tiles.length(); i++) {
            if(tiles.charAt(i)!=node.getTiles().charAt(i)){
                ++count;
            }
        }
        
        return count;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < HEIGHT; i++) {
            sb.append(tiles.substring(i * WIDTH, (i * WIDTH) + WIDTH)).append("\n");
        }

        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PuzzleGrid)) {
            return false;
        }

        PuzzleGrid other = (PuzzleGrid) obj;

        // Check the string		
        return other.getTiles().equals(this.getTiles());
    }
}
