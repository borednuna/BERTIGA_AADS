package State;

import Utility.SaveData;

public class StateManager {
    private State[] gameStates;
    private int currentState;
    private int previousState;
    private int time;

    public static final int GAMESTATES = 11;
    public static final int MENUSTATE = 0;
    public static final int ABOUTSTATE = 1;
    public static final int HIGHSCORESTATE = 2;
    public static final int OPENINGSTATE = 3;           // POV dr samping, panahan
    public static final int TANGKAP_KIJANG_STATE = 4;   // POV dr samping, kejar-kejaran
    public static final int JATAYUSTATE = 5;            // POV dr atas, jatayu bisa nyembur api rahwana bisa tereak
    public static final int HANUMANSTATE = 6;           // POV dr atas, kaya pacman
    public static final int RAHWANASTATE = 7;           // 
    public static final int DEATHSTATE = 8;
    public static final int STORYLINE2 = 9;
    public static final int STORYLINE3 = 10;

    public StateManager() {
        gameStates = new State[GAMESTATES];

        previousState = -1;
        currentState = MENUSTATE;
        loadState(currentState);
    }

    private void loadState(int state) {
        if (state == MENUSTATE) gameStates[state] = new MenuState(this);
        if (state == ABOUTSTATE) gameStates[state] = new AboutState(this);
        if (state == HIGHSCORESTATE) gameStates[state] = new HighscoreState(this);
        if (state == OPENINGSTATE) gameStates[state] = new OpeningState(this);
        if (state == TANGKAP_KIJANG_STATE) gameStates[state] = new TangkapkijangState(this);
        if (state == JATAYUSTATE) gameStates[state] = new JatayuState(this);
        if (state == HANUMANSTATE) gameStates[state] = new HanumanState(this);
        if (state == RAHWANASTATE) gameStates[state] = new RahwanaState(this);
        if (state == DEATHSTATE) gameStates[state] = new DeathState(this);
        if (state == STORYLINE2) gameStates[state] = new Storyline2(this);
        if (state == STORYLINE3) gameStates[state] = new Storyline3(this);
    }

    private void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        previousState = currentState;
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    public void setTime(int time) {
        this.time += time;
    }

    public void update() {
        try {
            if(gameStates[currentState] != null) gameStates[currentState].update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics2D g) {
        try {
            if (gameStates[currentState] != null) gameStates[currentState].draw(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates[currentState].keyReleased(k);
    }
}
