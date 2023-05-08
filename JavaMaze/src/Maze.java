import java.util.ArrayList;

/**
 Adapted from Jay Horstmann's "Java Concepts: Early Objects, 7th Edition"

 A maze has its walls marked by * characters and corridors
 by spaces. It can classify corridor points as dead ends,
 intersections, or exits, and it can extend paths from
 one intersection to another. The maze is assumed not to have
 any cycles (i.e., paths returning to their own start.)
 */

public class Maze {
    private String[] cells;

    /**
     * Constructs a maze from a string describing its contents.
     *
     * @param contents strings consisting of * and spaces
     */
    public Maze(String[] contents) {
        cells = contents;
    }

    /**
     Gets all paths emanating from a position in the maze.
     @param row the row of the position
     @param column the column of the position
     @return all paths emanating from the position
     */
    public ArrayList<Path> pathsFrom(int row, int column) {
    }

    /**
     Extends this path to the next exit, intersection, or dead end.
     @param p the path to extend
     */
    private void extend(Path p) {

    }

    /**
     Checks whether a position is an exit.
     @param row the row of the position
     @param column the column of the position
     @return true if the position is an exit
     */
    public boolean isExit(int row, int column) {
    }

    /**
     Checks whether a position is a dead end.
     @param row the row of the position
     @param column the column of the position
     @return true if the position is a dead end
     */
    public boolean isDeadEnd(int row, int column) {

    }

    /**
     Checks whether a position is within the maze and not a wall.
     @param row the row of the position
     @param column the column of the position
     @return true if the position is valid
     */
    private boolean isValid(int row, int column) {

    }


    /**
     Counts the neighbors of a position.
     @param row the row of the position
     @param column the column of the position
     @return the number of neighbors in the four compass directions
     that are within the maze and not walls.
     */
    private int countNeighbors(int row, int column) {

    }
}
