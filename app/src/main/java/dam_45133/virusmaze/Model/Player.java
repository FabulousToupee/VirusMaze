package dam_45133.virusmaze.Model;

import android.graphics.Canvas;
import android.graphics.Paint;

import dam_45133.virusmaze.GameConstants;

public class Player{
    private Cell[][] maze;
    private int col,row,cellSize;
    private Direction direction;
    private boolean canMove;

    public Player(Cell[][] maze,int cellSize, int col, int row, Direction direction) {
        this.maze = maze;
        this.cellSize = cellSize;
        this.col = col;
        this.row = row;
        this.direction = direction;
        this.canMove = false;
    }

    public void drawPlayer(BitmapImages bitmapImages,Canvas canvas, Paint paint,int currentFrame){
        float margin = cellSize / 10;
        switch (direction){
            case UP:
                canvas.drawBitmap(bitmapImages.getPacmanUp()[currentFrame],this.col*cellSize + margin , this.row * cellSize + margin, paint);
                break;
            case DOWN:
                canvas.drawBitmap(bitmapImages.getPacmanDown()[currentFrame],this.col*cellSize + margin , this.row * cellSize + margin, paint);
                break;
            case RIGHT:
                canvas.drawBitmap(bitmapImages.getPacmanRight()[currentFrame],this.col*cellSize + margin , this.row * cellSize + margin, paint);
                break;
            case LEFT:
                canvas.drawBitmap(bitmapImages.getPacmanLeft()[currentFrame],this.col*cellSize + margin , this.row * cellSize + margin, paint);
                break;
        }

    }



    public void calculateSwipeDirection(float x1, float y1,float x2, float y2){
        float dx = x2 - x1;
        float dy = y2 - y1;
        float absDx = Math.abs(dx);
        float absDy = Math.abs(dy);

        if(absDx > cellSize || absDy > cellSize){
            //Move in X axis
            if (absDx > absDy){
                //Move right
                if(dx > 0){
                    this.setDirection(Direction.RIGHT);
                    movePlayer(this.direction);
                }
                //Move left
                else{
                    this.setDirection(Direction.LEFT);
                    movePlayer(this.direction);
                }
            }
            //Move in Y axis
            else{
                //Move down
                if(dy > 0){
                    this.setDirection(Direction.DOWN);
                    movePlayer(this.direction);
                }
                //Move up
                else{
                    this.setDirection(Direction.UP);
                    movePlayer(this.direction);
                }
            }
        }
    }


    private void movePlayer(Direction direction) {
        switch (direction){
            case UP:
                if (!maze[this.getCol()][this.getRow()].isTopWall()) {
                    this.row -= 1;
                }
                break;
            case DOWN:
                if (!maze[this.getCol()][this.getRow()].isBottomWall()) {
                    this.row += 1;
                }
                break;
            case LEFT:
                if (!maze[this.getCol()][this.getRow()].isLeftWall()) {
                    this.col -= 1;
                }
                break;
            case RIGHT:
                if (!maze[this.getCol()][this.getRow()].isRightWall()) {
                    this.col += 1;
                }
                break;
        }
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Cell[][] getMaze() {
        return maze;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean move) {
        this.canMove = move;
    }
}
