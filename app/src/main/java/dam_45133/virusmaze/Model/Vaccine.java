package dam_45133.virusmaze.Model;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Vaccine {
    private Cell[][] maze;
    private int col,row,cellSize;
    public Vaccine(Cell[][] maze,int cellSize, int col, int row) {
        this.maze = maze;
        this.cellSize = cellSize;
        this.col = col;
        this.row = row;
    }

    public void drawVaccine(BitmapImages bitmapImages, Canvas canvas, Paint paint){
        float margin = cellSize / 10;
        canvas.drawBitmap(bitmapImages.getVaccine(),this.col*cellSize + margin , this.row * cellSize + margin, paint);
    }
}
