package dam_45133.virusmaze.Game;


import dam_45133.virusmaze.Utils.GameConstants;
import dam_45133.virusmaze.View.DrawingView;

public class GameLoopLogic implements Runnable{
    private boolean playing = true;
    private int currentPacmanFrame = 0;
    private DrawingView view;
    Thread thread;

    public GameLoopLogic(DrawingView view) {
        this.view = view;
    }

    @Override
    public void run() {
        long lastUpdatedTime = 0;
        long delay = (GameConstants.TOTAL_FRAMES * GameConstants.GAME_SPEED);
        while(playing){
            long currentTime = System.currentTimeMillis();
            if (currentTime > lastUpdatedTime + delay){
                if (currentPacmanFrame >= GameConstants.TOTAL_FRAMES) {
                    currentPacmanFrame = 0;
                }
                view.draw(currentPacmanFrame);
                view.checkBoost();
                view.checkVictory();
                lastUpdatedTime = currentTime;
                currentPacmanFrame++;
            }

        }
    }

    public void pause() {
        playing = false;
        thread = null;
    }

    public void resume() {
        if (thread != null) {
            thread.start();
        }
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        playing = true;
    }

}
