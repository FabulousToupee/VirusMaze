package dam_45133.virusmaze.View;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import dam_45133.virusmaze.Activities.PlayActivity;
import dam_45133.virusmaze.GameConstants;
import dam_45133.virusmaze.Model.Cell;


public class MazeGenerator {
    private  Cell[][] maze;
    private Stack<Cell> stack;
    private Random random;
    private int screenWidth, screenHeight,cellSize;
    public MazeGenerator(int screenWidth, int screenHeight, int cellSize){
        stack = new Stack<>();
        random = new Random();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.cellSize = cellSize;
        this.maze = createMaze();

    }

    /**
     * Creates a maze using the Recursive Backtracker Algorithm
     * @return Maze
     */
    private  Cell[][] createMaze(){
        Cell[][] tempMaze = new Cell[GameConstants.COLS][GameConstants.ROWS];

        //Randomize starter position for play and for vaccine every maze
        GameConstants.randomizeSpawn();
        GameConstants.randomizeBoostLocation();

        //Create a maze of Cells
        for (int x = 0; x < GameConstants.COLS; x++) {
            for (int y = 0; y < GameConstants.ROWS; y++) {
                tempMaze[x][y] = new Cell(x, y);
            }

        }
        return recursiveBacktrackerGeneration(tempMaze);
    }

    /**
     * Creates a maze using recursive backtracker algorithm
     * 1- Choose an arbitrary cell, make it currentCell and mark it as visited
     * 2- Check if there are neighboring cell that can be visited
     *  IF there are neighboring cells
     * 3- Choose a random cell from the neighboring
     * 4- Destroy wall between neighbor cell and currentCell
     * 5- Make neighbor cell as current cell and mark it as visited
     * 6- Go back to step 2.
     * ELSE
     * 4- Go back to previous cell and keep doing it until there's a cell with neighbors that haven't been visited.
     * @param gridMaze Maze in which all cells have all the walls
     * @return Returns a random maze generated
     */
    private Cell[][] recursiveBacktrackerGeneration(Cell[][] gridMaze){
        Cell currentCell, nextCell;
        currentCell = gridMaze[GameConstants.STARTER_X][GameConstants.STARTER_Y];
        currentCell.setVisited(true);
        do {
            nextCell = getNeighbour(gridMaze,currentCell);
            if (nextCell != null) {
                removeWall(currentCell, nextCell);
                stack.push(currentCell);
                currentCell = nextCell;
                currentCell.setVisited(true);
            } else {
                currentCell = stack.pop();
            }
        } while (!stack.empty());

        return gridMaze;
    }

    /**
     * Method that chooses a random neighbor from a cell
     * @param maze Maze where the Cell is inserted
     * @param currentCell cell from where to choose a neighbor
     * @return Neighbouring cell
     */
    private Cell getNeighbour(Cell[][] maze,Cell currentCell){
        ArrayList<Cell> neighbors = new ArrayList<>();

        //Left cell
        if(currentCell.getCol() > 0) {
            if (!maze[currentCell.getCol() - 1][currentCell.getRow()].isVisited())
                neighbors.add(maze[currentCell.getCol() - 1][currentCell.getRow()]);
        }

        //Right cell
        if(currentCell.getCol() < GameConstants.COLS - 1) {
            if (!maze[currentCell.getCol()+1][currentCell.getRow()].isVisited())
                neighbors.add(maze[currentCell.getCol()+1][currentCell.getRow()]);
        }

        //Top cell
        if(currentCell.getRow() > 0) {
            if (!maze[currentCell.getCol()][currentCell.getRow()-1].isVisited())
                neighbors.add(maze[currentCell.getCol()][currentCell.getRow()-1]);
        }
        //Bottom cell
        if(currentCell.getRow() < GameConstants.ROWS - 1) {
            if (!maze[currentCell.getCol()][currentCell.getRow() + 1].isVisited())
                neighbors.add(maze[currentCell.getCol()][currentCell.getRow() + 1]);
        }

        if(neighbors.size() > 0) {
            int index = random.nextInt(neighbors.size());
            return neighbors.get(index);
        }

        return null;
    }

    /**
     * Method that removes a wall between two neighbors
     * @param currentCell current cell
     * @param nextCell neighbor cell
     */

    private void removeWall(Cell currentCell, Cell nextCell){
        if (currentCell.getCol() == nextCell.getCol() && currentCell.getRow() == nextCell.getRow() + 1){
            currentCell.setTopWall(false);
            nextCell.setBottomWall(false);
        }

        if (currentCell.getCol() == nextCell.getCol() && currentCell.getRow() == nextCell.getRow() - 1){
            currentCell.setBottomWall(false);
            nextCell.setTopWall(false);
        }
        if (currentCell.getCol() == nextCell.getCol() + 1 && currentCell.getRow() == nextCell.getRow()){
            currentCell.setLeftWall(false);
            nextCell.setRightWall(false);
        }
        if (currentCell.getCol() == nextCell.getCol() - 1 && currentCell.getRow() == nextCell.getRow()){
            currentCell.setRightWall(false);
            nextCell.setLeftWall(false);
        }
    }


    /**
     * Paints the maze in a canvas dynamic to the screen
     * @param canvas canvas
     * @param paint paint
     */
    public void drawMaze(Canvas canvas, Paint paint) {
        paint.setColor(GameConstants.MAZE_COLOR);
        paint.setStrokeWidth(GameConstants.WALL_THICKNESS);


        // Leave a vertical and horizontal margin depending on the screen and the maze size
        int hMargin = (screenWidth - GameConstants.COLS * cellSize) / 2;
        int vMargin = (screenHeight - GameConstants.ROWS * cellSize) / 2;

        //Translate the origin of the drawing to start with a distance to the edge equal to the margins calculated
        canvas.translate(hMargin,vMargin);

        // Draw the lines depending on which wall there is to be drawn
        for (int x = 0; x < GameConstants.COLS; x++) {
            for (int y = 0; y < GameConstants.ROWS; y++) {

                if(maze[x][y].isTopWall())
                    canvas.drawLine(x* cellSize,
                            y * cellSize,
                            (x+1) * cellSize,
                            y*cellSize,
                            paint);

                if(maze[x][y].isLeftWall())
                    canvas.drawLine(x* cellSize,
                            y * cellSize,
                             x * cellSize,
                            (y+1)*cellSize,
                            paint);
                if(maze[x][y].isBottomWall())
                    canvas.drawLine(x* cellSize,
                            (y+1) * cellSize,
                            (x+1) * cellSize,
                            (y+1)*cellSize,
                            paint);


                if(maze[x][y].isRightWall())
                    canvas.drawLine((x+1)* cellSize,
                            y * cellSize,
                            (x+1) * cellSize,
                            (y+1)*cellSize,
                            paint);

            }

        }
    }

    public Cell[][] getMaze() {
        return maze;
    }


}
