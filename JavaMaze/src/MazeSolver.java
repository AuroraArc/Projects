import java.util.Stack;

public class MazeSolver {
    public static void main(String[] args)
    {
        Maze maze = new Maze(
                new String[] {
                        "*****************************",
                        "** ***                      *",
                        "** *** * ********************",
                        "** *** *         *          *",
                        "** *** * *******   **** *****",
                        "**     * ************** *****",
                        "****** ******* *******  *****",
                        "******         ******* ******",
                        "*      ******* ******* ******",
                        "* **** ******* **           *",
                        "*    ********* ******* ******",
                        "* ****         ***     ******",
                        "************** **************"});

        solve(maze, 5, 8);
    }

    /**
     Traverses a maze, printing out a path to the exit.
     @param maze the maze
     @param param the row of the starting position
     @param param the column of the starting position
     */
    public static void solve(Maze maze, int row, int column) {

    }
}


