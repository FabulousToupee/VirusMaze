package dam_45133.virusmaze.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import dam_45133.virusmaze.Activities.PlayActivity;
import dam_45133.virusmaze.Model.BitmapImages;
import dam_45133.virusmaze.Model.Boost;
import dam_45133.virusmaze.Model.Cell;
import dam_45133.virusmaze.Model.CountDownTimerPausable;
import dam_45133.virusmaze.Model.Direction;
import dam_45133.virusmaze.Model.Player;
import dam_45133.virusmaze.Model.Vaccine;
import dam_45133.virusmaze.R;
import dam_45133.virusmaze.Utils.GameConstants;

public class DrawingView extends SurfaceView{
    private MazeGenerator mazeGenerator;
    private Cell[][] maze;
    private Player player;
    private Vaccine vaccine;
    private Boost boost;
    private BitmapImages bitmapImages;
    private Paint paint;
    SurfaceHolder holder;



    private int screenWidth;
    private int screenHeight;
    private int cellSize;

    private float x1,y1,x2,y2; //Initial and final positions

    public CountDownTimerPausable timer;

    public DrawingView(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        if(screenWidth/screenHeight < GameConstants.COLS / GameConstants.ROWS)
            cellSize = screenWidth / (GameConstants.COLS + 1);
        else
            cellSize = screenHeight/(GameConstants.ROWS + 1);

        mazeGenerator = new MazeGenerator(screenWidth,screenHeight,cellSize);
        maze = mazeGenerator.getMaze();

        player = new Player(maze, cellSize, GameConstants.STARTER_X, GameConstants.STARTER_Y,Direction.RIGHT);
        vaccine = new Vaccine(maze,cellSize,GameConstants.VACCINE_X,GameConstants.VACCINE_Y);
        boost = new Boost(maze, cellSize, GameConstants.BOOST_X, GameConstants.BOOST_Y);

        bitmapImages = new BitmapImages(cellSize, context);    //loads the bitmap images

        //Start game timers
        timer = new CountDownTimerPausable(GameConstants.PREPARATION_TIME + GameConstants.LEVEL_TIME, 1000) {
            public void onTick(long millisUntilFinished) {
                GameConstants.TIME_LEFT = (millisUntilFinished / 1000) + 1;
                if(GameConstants.TIME_LEFT <= GameConstants.LEVEL_TIME / 1000) player.setCanMove(true);
                invalidate(); // Force the View to redraw
            }
            public void onFinish() {
                PlayActivity.gameOver(getContext());
            }
        }.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1 = event.getX();
                y1 = event.getY();
                break;
            }
            case (MotionEvent.ACTION_UP): {
                x2 = event.getX();
                y2 = event.getY();
                if(player.isCanMove())
                    player.calculateSwipeDirection(x1, y1, x2, y2);
                break;
            }
        }
        return true;
    }


    public void draw(int currentPacmanFrame){
        Canvas canvas = holder.lockCanvas();
        if(canvas != null){
            canvas.drawColor(GameConstants.BACKGROUND_COLOR);
            drawUserInterface(canvas,paint);
            mazeGenerator.drawMaze(canvas,paint);
            player.drawPlayer(bitmapImages,canvas,paint,currentPacmanFrame);
            vaccine.drawVaccine(bitmapImages,canvas,paint);
            if(!boost.isPickedUp()) boost.drawBoost(bitmapImages,canvas,paint);
            holder.unlockCanvasAndPost(canvas);
        }

    }


    public void drawUserInterface(Canvas canvas, final Paint paint){
        paint.setTextSize(80f);
        paint.setColor(GameConstants.MAZE_COLOR);
        if(!player.isCanMove()) drawTextAtXCenter(canvas,paint,getResources().getString(R.string.preparationPhase),(screenHeight - GameConstants.ROWS * cellSize) / 12);
        else  drawTextAtXCenter(canvas,paint,getResources().getString(R.string.playingPhase),(screenHeight - GameConstants.ROWS * cellSize) / 12);

        paint.setTextSize(52f);
        canvas.drawText(getResources().getString(R.string.levelDifficulty) + ": " + GameConstants.getGameDifficultyInSystemLanguage(),
                (screenWidth - GameConstants.COLS * cellSize) / 2,(screenHeight - GameConstants.ROWS * cellSize) / 7f,paint);
        canvas.drawText(getResources().getString(R.string.timeLeft) + ": " + GameConstants.TIME_LEFT + " " + getResources().getString(R.string.timeUnits),
                (screenWidth - GameConstants.COLS * cellSize) / 2,(screenHeight - GameConstants.ROWS * cellSize) / 5f,paint);

        canvas.drawText(getResources().getString(R.string.level) + ": " + GameConstants.CURRENT_LEVEL,
                (screenWidth - GameConstants.COLS * cellSize) / 2,(screenHeight - GameConstants.ROWS * cellSize) / 4f,paint);
        paint.setTextSize(80f);
        canvas.drawText(getResources().getString(R.string.score) + ": " + GameConstants.SCORE,
                (screenWidth - GameConstants.COLS * cellSize) / 2,(screenHeight - GameConstants.ROWS * cellSize) / 2.5f,paint);



    }

    public void checkVictory(){
        if (player.getCol() == GameConstants.VACCINE_X && player.getRow() == GameConstants.VACCINE_Y) {
            GameConstants.adjustGameAndPreparationTimers();
            GameConstants.SCORE += GameConstants.TIME_LEFT * GameConstants.SCORE_MULTIPLIER * GameConstants.CURRENT_LEVEL;
            GameConstants.resetScoreMultiplier(GameConstants.DIFFICULTY);
            GameConstants.randomizeBoostLocation();
            boost.setRow(GameConstants.BOOST_X);
            boost.setCol(GameConstants.BOOST_Y);
            GameConstants.CURRENT_LEVEL += 1;
            PlayActivity.nextLevel(getContext());
        }
    }

    public void checkBoost(){
        if (player.getCol() == GameConstants.BOOST_X && player.getRow() == GameConstants.BOOST_Y) {
            if(!boost.isPickedUp()) {
                switch (boost.getType()) {
                    case "GOOD":
                        GameConstants.SCORE_MULTIPLIER *= 2;
                        this.post(new Runnable() {
                            @Override
                            public void run() {
                                showToast(getContext(),getResources().getString(R.string.doubleScore));
                            }
                        });
                        break;
                    case "BAD":
                        GameConstants.SCORE_MULTIPLIER /= 2;
                        this.post(new Runnable() {
                            @Override
                            public void run() {
                                showToast(getContext(),getResources().getString(R.string.halfScore));
                            }
                        });
                        break;
                }
                boost.setPickedUp(true);
            }
        }
    }

    private void drawTextAtXCenter(Canvas canvas, Paint paint, String text, float y) {
        Rect r = new Rect();
        canvas.getClipBounds(r);
        int cWidth = r.width();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        canvas.drawText(text, x, y, paint);
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
