package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;
import Audio.AudioPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

public class AboutState extends State {
    private Background bg;
    private Font font;
    private AudioPlayer music;

    public AboutState(StateManager stateManager) {
        this.stateManager = stateManager;
        music = new AudioPlayer("/SFX/music_menustate.wav");

        try {
            bg = new Background("/Backgrounds/bg_about_state.png");

            Font ManilaCity = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/ManilaCity.ttf")).getPath()));
            Font AccidentalPrecidency = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/AccidentalPrecidency.ttf").getPath())));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(ManilaCity);
            ge.registerFont(AccidentalPrecidency);

            font = AccidentalPrecidency.deriveFont(50f);
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
        g.setFont(font);
        g.setColor(Color.BLACK);

        String str = "Ada Apa Dengan Sinta";
        Utility.horizontalCenteredText(g, str, GamePanel.WIDTH, 220, font, Color.BLACK);

        g.setColor(Color.WHITE);
        Utility.horizontalCenteredText(g, str, GamePanel.WIDTH, 215, font, Color.YELLOW);

        String hanun = "Hanun Shaka P (5025211051)";
        String bila = "Salsabila Fatma A (5025211057)";

        Utility.horizontalCenteredText(g, hanun, GamePanel.WIDTH, 270, g.getFont(), Color.DARK_GRAY);
        Utility.horizontalCenteredText(g, bila, GamePanel.WIDTH, 320, g.getFont(), g.getColor());
    }

    @Override
    public void keyPressed(int k) {
        
        if (k == KeyEvent.VK_ENTER) {
            music.stop();
            stateManager.setState(StateManager.MENUSTATE);
        }
        
    }

    @Override
    public void keyReleased(int k) {}

    @Override
    public void initMaze() {
        // TODO Auto-generated method stub
        
    }
}
