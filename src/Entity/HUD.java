package Entity;

import Entity.Playable.*;
import Main.GamePanel;
import Utility.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class HUD {
    private Playable mc;
	private Font font;
	private Time t;

    public HUD(Playable mc, Time t) {
        this.t = t;
        this.mc = mc;

        try {
            Font ManilaCity = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/ManilaCity.ttf")).getPath()));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(ManilaCity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void draw(Graphics2D g) {
        g.setFont(font);
        g.setColor(Color.BLACK);

        String score = "+Points: " + mc.getScore();
        String timer = String.valueOf(t.getSecond()) + "secs";

        g.drawString(score, 16, 16);
        g.drawString(timer, 180, 15);
        g.setColor(Color.WHITE);
        g.drawString(score, 15, 15);
        g.drawString(timer, 185, 16);
    }
}
