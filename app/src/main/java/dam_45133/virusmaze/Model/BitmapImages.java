package dam_45133.virusmaze.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dam_45133.virusmaze.R;

public class BitmapImages {

    private int spriteSize;
    private Bitmap vaccine, boost;
    private Bitmap[] pacmanRight, pacmanDown, pacmanLeft, pacmanUp;

    public BitmapImages(int cellSize, Context context){
        this.spriteSize = cellSize - cellSize / 5;

        // Add bitmap images of pacman facing right
        pacmanRight = new Bitmap[4];
        pacmanRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),R.drawable.pacman_right1), spriteSize, spriteSize, false);
        pacmanRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_right2), spriteSize, spriteSize, false);
        pacmanRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_right3), spriteSize, spriteSize, false);
        pacmanRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_right), spriteSize, spriteSize, false);


        // Add bitmap images of pacman facing down
        pacmanDown = new Bitmap[4];
        pacmanDown[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_down1), spriteSize, spriteSize, false);
        pacmanDown[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_down2), spriteSize, spriteSize, false);
        pacmanDown[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_down3), spriteSize, spriteSize, false);
        pacmanDown[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_down), spriteSize, spriteSize, false);


        // Add bitmap images of pacman facing left
        pacmanLeft = new Bitmap[4];
        pacmanLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_left1), spriteSize, spriteSize, false);
        pacmanLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_left2), spriteSize, spriteSize, false);
        pacmanLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_left3), spriteSize, spriteSize, false);
        pacmanLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_left), spriteSize, spriteSize, false);


        // Add bitmap images of pacman facing up
        pacmanUp = new Bitmap[4];
        pacmanUp[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_up1), spriteSize, spriteSize, false);
        pacmanUp[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_up2), spriteSize, spriteSize, false);
        pacmanUp[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_up3), spriteSize, spriteSize, false);
        pacmanUp[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pacman_up), spriteSize, spriteSize, false);


        // Add bitmap image of vaccine
        vaccine = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.vaccine), spriteSize, spriteSize, false);


        // Add bitmap image of boost
        boost = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.boost), spriteSize, spriteSize, false);

    }

    public Bitmap[] getPacmanRight() {
        return pacmanRight;
    }

    public Bitmap[] getPacmanDown() {
        return pacmanDown;
    }

    public Bitmap[] getPacmanLeft() {
        return pacmanLeft;
    }

    public Bitmap[] getPacmanUp() {
        return pacmanUp;
    }

    public Bitmap getVaccine() { return vaccine;}

    public Bitmap getBoost(){ return boost;}

}
