package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;
import Audio.AudioPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

public class OpeningState extends State {
    private Background bg;
    private AudioPlayer music;

    public OpeningState(StateManager stateManager) {
        this.stateManager = stateManager;
        music = new AudioPlayer("/SFX/music_menustate.wav");
        music.play();

        try {
            bg = new Background("/Backgrounds/Storyline_BG/1. CERITA_SATU.png");

            Font ManilaCity = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/ManilaCity.ttf")).getPath()));
            Font AccidentalPrecidency = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/AccidentalPrecidency.ttf").getPath())));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(ManilaCity);
            ge.registerFont(AccidentalPrecidency);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {}

    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            music.stop();
            stateManager.setState(StateManager.TANGKAP_KIJANG_STATE);
        }
    }

    @Override
    public void keyReleased(int k) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initMaze() {
        // TODO Auto-generated method stub
        
    }
}
