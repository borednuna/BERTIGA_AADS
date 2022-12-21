package Entity.Enemy;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

import Main.GamePanel;
import Map.Map;
import Entity.Playable.*;
import Audio.AudioPlayer;

public class Ghost_Vertical extends Enemy {
    Playable mc;
    AudioPlayer fx;

    public Ghost_Vertical(int x, int y, int range, Playable mc, int speedy) {
        this.mc = mc;
        this.x = x;
        this.y = y;
        initial_x = x;
        initial_y = y;
        x_speed = 0;
        this.y_speed = speedy;
        this.range = range;
        fx = new AudioPlayer("/SFX/fx_lost.wav");

        try {
            icon_left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entity/Enemy/ghost.png")));
            icon_right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entity/Enemy/ghost.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(icon_left, x, y, null);
    }

    @Override
    public void set_x_speed(int x) {
        this.x_speed = x;
    }

    @Override
    public void set_y_speed(int y) {
        this.y_speed = y;
    }

    @Override
    public void set_x(int x) {
        this.x = x;
    }

    @Override
    public void set_y(int y) {
        this.y = y;
    }

    @Override
    public void update() {
        y += y_speed;

        move();
        checkMC();
    }

    private void checkMC() {
        if ((int)((mc.getX() - 98) / 50) == (int)((x - 98) / 50) && (int)((mc.getY() - 98) / 50) == (int)((y - 98) / 50)) {
            fx.play();
            mc.kill();
        }
    }

    private void move() {
        if(y <= initial_y || y >= initial_y + range) {
            y_speed = y_speed*-1;
        }
    }
}
