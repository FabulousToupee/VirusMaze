package dam_45133.virusmaze.Utils;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

import dam_45133.virusmaze.Activities.PlayActivity;
import dam_45133.virusmaze.R;

public class GameConstants {


    public static String DIFFICULTY = "Medium";
    public static long PREPARATION_TIME = 20000;
    public static int LEVEL_TIME = 60000;
    public static long TIME_LEFT = PREPARATION_TIME + LEVEL_TIME;
    public static int ROWS = 15;
    public static int COLS = 15;

    public final static int TOTAL_FRAMES = 4;
    public final static int WALL_THICKNESS = 4;
    public final static int GAME_SPEED = 20;

    private static int[] NUMBERS = new int[]{0,14};
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
        randomizeSpawn();
        randomizeBoostLocation();
    }
    private static void setDifficultyMedium(){
        GameConstants.DIFFICULTY = "Medium";
        GameConstants.ROWS = 15;
        GameConstants.COLS = 15;
        GameConstants.LEVEL_TIME = 60000;
        GameConstants.SCORE_MULTIPLIER = 2;
        randomizeSpawn();
        randomizeBoostLocation();
    }
    private static void setDifficultyHard(){
        GameConstants.DIFFICULTY = "Hard";
        GameConstants.ROWS = 20;
        GameConstants.COLS = 20;
        GameConstants.LEVEL_TIME = 90000;
        GameConstants.SCORE_MULTIPLIER = 3;
        randomizeSpawn();
        randomizeBoostLocation();
    }
    private static void setDifficultyExpert(){
        GameConstants.DIFFICULTY = "Expert";
        GameConstants.ROWS = 25;
        GameConstants.COLS = 25;
        GameConstants.LEVEL_TIME = 120000;
        GameConstants.SCORE_MULTIPLIER = 4;
        randomizeSpawn();
        randomizeBoostLocation();
    }

    public static void randomizeSpawn(){
        int[] NUMBERS = new int[]{0,GameConstants.ROWS - 1};
        int rnd = new Random().nextInt(NUMBERS.length);
        GameConstants.STARTER_X = NUMBERS[rnd];
        int rnd2 = new Random().nextInt(NUMBERS.length);
        GameConstants.STARTER_Y = NUMBERS[rnd2];

        GameConstants.VACCINE_X = (STARTER_X == GameConstants.ROWS - 1) ? 0 : GameConstants.ROWS - 1;
        GameConstants.VACCINE_Y = (STARTER_Y == GameConstants.ROWS - 1) ? 0 : GameConstants.ROWS - 1;
    }

    public static void randomizeBoostLocation(){
        ArrayList<Integer> NUMBERS_BOOST = new ArrayList<>();
        for(int i = 0; i< GameConstants.ROWS - 1; i++){
            NUMBERS_BOOST.add(i);
        }
        int rnd = new Random().nextInt(NUMBERS_BOOST.size());
        BOOST_X = NUMBERS_BOOST.get(rnd);
        int rnd2 = new Random().nextInt(NUMBERS_BOOST.size());
        BOOST_Y = NUMBERS_BOOST.get(rnd2);

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

    public static String getGameDifficultyInSystemLanguage(){
        switch(GameConstants.DIFFICULTY){
            case "Easy":
                return PlayActivity.getContext().getResources().getString(R.string.easyDifficulty);
            case "Medium":
                return PlayActivity.getContext().getResources().getString(R.string.mediumDifficulty);
            case "Hard":
                return PlayActivity.getContext().getResources().getString(R.string.hardDifficulty);
            case "Expert":
                return PlayActivity.getContext().getResources().getString(R.string.expertDifficulty);
        }
        return null;
    }

    public static void adjustGameAndPreparationTimers(){
        if(GameConstants.PREPARATION_TIME > 0){
            GameConstants.PREPARATION_TIME -= 2000;
        }
        else{
            switch(GameConstants.DIFFICULTY){
                case "Easy":
                    if(GameConstants.LEVEL_TIME > 30000)
                        GameConstants.LEVEL_TIME -= 1000;
                    break;
                case "Medium":
                    if(GameConstants.LEVEL_TIME > 40000)
                        GameConstants.LEVEL_TIME -= 1000;
                    break;
                case "Hard":
                    if(GameConstants.LEVEL_TIME > 60000)
                        GameConstants.LEVEL_TIME -= 1000;
                    break;
                case "Expert":
                    if(GameConstants.LEVEL_TIME > 90000)
                        GameConstants.LEVEL_TIME -= 1000;
                    break;
            }
        }
    }
}
