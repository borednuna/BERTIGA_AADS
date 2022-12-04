package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

import javax.swing.plaf.FontUIResource;

public class AboutState extends State {
    private Background bg;

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public AboutState(StateManager stateManager) {
        this.stateManager = stateManager;

        try {
            bg = new Background("/Backgrounds/background.png");
            bg.setVector(0, 0);

            Font ManilaCity = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("Fonts/ManilaCity.ttf")).getPath()));
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
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);

        Utility.horizontalCenteredText(g, "About", GamePanel.WIDTH, 60, titleFont, Color.BLACK);
        Utility.horizontalCenteredText(g, "About", GamePanel.WIDTH, 58, titleFont, titleColor);

        g.setFont(font);
        g.setColor(Color.BLACK);

        String str = "Ada Apa Dengan Sinta";
        Utility.horizontalCenteredText(g, str, GamePanel.WIDTH, 150, g.getFont(), g.getColor());

        g.setColor(Color.WHITE);
        Utility.horizontalCenteredText(g, str, GamePanel.WIDTH, 145, g.getFont(), g.getColor());
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) stateManager.setState(StateManager.MENUSTATE);
        
    }

    @Override
    public void keyReleased(int k) {}
}
