package State;

import Main.GamePanel;
import Map.Background;
import Utility.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

public class HighscoreState extends State {
    private Background bg;
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public HighscoreState(StateManager stateManager) {
        this.stateManager = stateManager;

        try {
            bg = new Background("/Backgrounds/bg_highscore_state.png");

            Font ManilaCity = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/ManilaCity.ttf")).getPath()));
            Font AccidentalPrecidency = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/AccidentalPrecidency.ttf").getPath())));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(ManilaCity);
            ge.registerFont(AccidentalPrecidency);

            titleColor = new Color(255,235, 72);
            titleFont = ManilaCity.deriveFont(60f);
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

        Utility.horizontalCenteredText(g, "High Score", GamePanel.WIDTH, 280, titleFont, Color.BLACK);
        Utility.horizontalCenteredText(g, "High Score", GamePanel.WIDTH, 275, titleFont, titleColor);

        g.setFont(font);

        String level1 = "Level 1: " + SaveData.readHighScore(SaveData.LEVEL1);
        String level2 = "Level 2: " + SaveData.readHighScore(SaveData.LEVEL2);
        String level3 = "Level 3: " + SaveData.readHighScore(SaveData.LEVEL3);
        String level4 = "Level 4: " + SaveData.readHighScore(SaveData.LEVEL4);
        String level5 = "Level 5: " + SaveData.readHighScore(SaveData.LEVEL5);

        g.setColor(Color.BLACK);

        Utility.horizontalCenteredText(g, level1, GamePanel.WIDTH, 350, g.getFont(), Color.WHITE);
        Utility.horizontalCenteredText(g, level2, GamePanel.WIDTH, 400, g.getFont(), Color.WHITE);
        Utility.horizontalCenteredText(g, level3, GamePanel.WIDTH, 450, g.getFont(), Color.WHITE);
        Utility.horizontalCenteredText(g, level4, GamePanel.WIDTH, 500, g.getFont(), Color.WHITE);
        Utility.horizontalCenteredText(g, level5, GamePanel.WIDTH, 550, g.getFont(), Color.WHITE);
    }
    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) stateManager.setState(StateManager.MENUSTATE);
    }
    @Override
    public void keyReleased(int k) {}

}
