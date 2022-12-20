package State;

import Main.GamePanel;
import Utility.*;
import Map.*;
import Audio.AudioPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

public class MenuState extends State {
    private Background bg;
    private AudioPlayer music;
    private AudioPlayer selection;

    private int currentChoice = 0;
    private String[] options = {
        "New Game",
        "Continue",
        "High Score",
        "About",
        "Quit"
    };

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(StateManager stateManager) {
        this.stateManager = stateManager;
        
        try {
            music = new AudioPlayer("/SFX/music_menustate.wav");
            bg = new Background("/Backgrounds/main_menu.jpg");

            Font ManilaCity = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/ManilaCity.ttf")).getPath()));
            Font AccidentalPrecidency = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/AccidentalPrecidency.ttf")).getPath()));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(ManilaCity);
            ge.registerFont(AccidentalPrecidency);

            titleColor = new Color(255, 235, 72);
            titleFont = ManilaCity.deriveFont(60f);

            font = AccidentalPrecidency.deriveFont(50f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        music.play();
    }

    @Override
    public void init() {}

    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);

        Utility.horizontalCenteredText(g, "Ada Apa dengan Sinta", GamePanel.WIDTH, 300, titleFont, Color.black);
        Utility.horizontalCenteredText(g, "Ada Apa dengan Sinta", GamePanel.WIDTH, 290, titleFont, titleColor);

        g.setFont(font);

        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.DARK_GRAY);
            }

            Utility.horizontalCenteredText(g, options[i], GamePanel.WIDTH, 360 + i * 45, font, g.getColor());
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            music.stop();
            select();
        }

        if (k == KeyEvent.VK_UP) {
            currentChoice = currentChoice - 1 == -1 ? options.length - 1 : currentChoice - 1;
        }

        if (k == KeyEvent.VK_DOWN) {
            currentChoice = currentChoice + 1 == options.length ? 0 : currentChoice + 1;
        }
    }

    @Override
    public void keyReleased(int k) {}

    private void select() {
        if (currentChoice == 0) stateManager.setState(StateManager.OPENINGSTATE);

        if (currentChoice == 1) {
            int currentCheckpoint = StateManager.OPENINGSTATE;
            int latestCheckpoint = SaveData.readLatestLevel();

            if (latestCheckpoint == 1) currentCheckpoint = StateManager.OPENINGSTATE;
            if (latestCheckpoint == 2) currentCheckpoint = StateManager.STORYLINE2;
            if (latestCheckpoint == 3) currentCheckpoint = StateManager.STORYLINE3;

            stateManager.setState(currentCheckpoint);
        }

        if (currentChoice == 2) stateManager.setState(StateManager.HIGHSCORESTATE);

        if (currentChoice == 3) stateManager.setState(StateManager.ABOUTSTATE);

        if (currentChoice == 4) System.exit(0);
    }

    @Override
    public void initMaze() {
        // TODO Auto-generated method stub
        
    }
}
