package dam_45133.virusmaze.Model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;

import java.util.Random;

import dam_45133.virusmaze.GameConstants;
import dam_45133.virusmaze.R;

public class Boost {

    private Cell[][] maze;
    private int col,row,cellSize;
    private String type;
    private boolean pickedUp;
    public Boost(Cell[][] maze, int cellSize, int col, int row) {
        this.maze = maze;
        this.cellSize = cellSize;
        this.col = col;
        this.row = row;
        this.type = generateRandomType();
        this.pickedUp = false;

    }

    public void drawBoost(BitmapImages bitmapImages,Canvas canvas, Paint paint){
        float margin = cellSize / 10;
        canvas.drawBitmap(bitmapImages.getBoost(),this.col*cellSize + margin , this.row * cellSize + margin, paint);
    }

    public int getCol() { return col; }

    public int getRow() { return row; }

    public void setCol(int col) { this.col = col; }

    public void setRow(int row) { this.row = row; }

    public String getType(){ return type; }

    public boolean isPickedUp() { return pickedUp; }

    public void setPickedUp(boolean pickedUp) { this.pickedUp = pickedUp; }

    private String generateRandomType(){
        String[] TYPES = new String[]{"GOOD","BAD"};
        int rnd = new Random().nextInt(TYPES.length);
        return TYPES[rnd];
    }
}
