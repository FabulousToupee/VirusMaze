package dam_45133.virusmaze;

import android.graphics.Color;

import java.util.Random;

public class GameConstants {


    public static String DIFFICULTY = "Medium";
    public static long PREPARATION_TIME = 0;
    public static int LEVEL_TIME = 60000;
    public static long TIME_LEFT = PREPARATION_TIME;
    public static int ROWS = 15;
    public static int COLS = 15;


    public final static int TOTAL_FRAMES = 4;
    public final static int WALL_THICKNESS = 4;
    public final static int GAME_SPEED = 20;

    public static int[] NUMBERS = new int[]{0,14};
    private static int rnd = new Random().nextInt(NUMBERS.length);
    public static int STARTER_X = NUMBERS[rnd];
    private static int rnd2 = new Random().nextInt(NUMBERS.length);
    public static int STARTER_Y = NUMBERS[rnd2];

    public static int VACCINE_X = (STARTER_X == 14) ? 0 : 14;
    public static int VACCINE_Y = (STARTER_Y == 14) ? 0 : 14;

    private static int[] NUMBERS_BOOST = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    private static int rnd3 = new Random().nextInt(NUMBERS_BOOST.length);
    public static int BOOST_X = NUMBERS_BOOST[rnd3];
    private static int rnd4 = new Random().nextInt(NUMBERS_BOOST.length);
    public static int BOOST_Y = NUMBERS_BOOST[rnd4];


    public static int BACKGROUND_COLOR = Color.BLACK;
    public static int MAZE_COLOR = Color.WHITE;

    public static int CURRENT_LEVEL = 1;
    public static int SCORE_MULTIPLIER = 2;
    public static int SCORE = 0;

    public static boolean PLAY_MUSIC = true;


    private static void setDifficultyEasy(){
        GameConstants.DIFFICULTY = "Easy";
        GameConstants.ROWS = 10;
        GameConstants.COLS = 10;
        GameConstants.LEVEL_TIME = 40000;
        GameConstants.SCORE_MULTIPLIER = 1;
    }
    private static void setDifficultyMedium(){
        GameConstants.DIFFICULTY = "Medium";
        GameConstants.ROWS = 15;
        GameConstants.COLS = 15;
        GameConstants.LEVEL_TIME = 60000;
        GameConstants.SCORE_MULTIPLIER = 2;
    }
    private static void setDifficultyHard(){
        GameConstants.DIFFICULTY = "Hard";
        GameConstants.ROWS = 20;
        GameConstants.COLS = 20;
        GameConstants.LEVEL_TIME = 90000;
        GameConstants.SCORE_MULTIPLIER = 3;
    }
    private static void setDifficultyExpert(){
        GameConstants.DIFFICULTY = "Expert";
        GameConstants.ROWS = 25;
        GameConstants.COLS = 25;
        GameConstants.LEVEL_TIME = 120000;
        GameConstants.SCORE_MULTIPLIER = 4;
    }

    public static void randomizeSpawn(){
        int[] NUMBERS = new int[]{0,14};
        int rnd = new Random().nextInt(NUMBERS.length);
        GameConstants.STARTER_X = NUMBERS[rnd];
        int rnd2 = new Random().nextInt(NUMBERS.length);
        GameConstants.STARTER_Y = NUMBERS[rnd2];

        GameConstants.VACCINE_X = (STARTER_X == 14) ? 0 : 14;
        GameConstants.VACCINE_Y = (STARTER_Y == 14) ? 0 : 14;
    }

    public static void randomizeBoostLocation(){
        int[] NUMBERS_BOOST = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        int rnd3 = new Random().nextInt(NUMBERS_BOOST.length);
        BOOST_X = NUMBERS_BOOST[rnd3];
        int rnd4 = new Random().nextInt(NUMBERS_BOOST.length);
        BOOST_Y = NUMBERS_BOOST[rnd4];

    }

    public static void resetGame(String difficulty){
        GameConstants.DIFFICULTY = difficulty;
        GameConstants.CURRENT_LEVEL = 1;
        GameConstants.SCORE = 0;
        GameConstants.PREPARATION_TIME = 20000;
        GameConstants.CURRENT_LEVEL = 1;
        switch (difficulty){
            case "Easy":
                setDifficultyEasy();
                break;
            case "Medium":
                setDifficultyMedium();
                break;
            case "Hard":
                setDifficultyHard();
                break;
            case "Expert":
                setDifficultyExpert();
                break;
        }
    }

    public static void resetScoreMultiplier(String difficulty){
        switch (difficulty){
            case "Easy":
                GameConstants.SCORE_MULTIPLIER = 1;
                break;
            case "Medium":
                GameConstants.SCORE_MULTIPLIER = 2;
                break;
            case "Hard":
                GameConstants.SCORE_MULTIPLIER = 3;
                break;
            case "Expert":
                GameConstants.SCORE_MULTIPLIER = 4;
                break;
        }
    }

    public static void changeColor(int backgroundColor){
        BACKGROUND_COLOR = backgroundColor;
        MAZE_COLOR = Color.rgb(255-Color.red(BACKGROUND_COLOR),
                255-Color.green(BACKGROUND_COLOR),
                255-Color.blue(BACKGROUND_COLOR));
    }
}
