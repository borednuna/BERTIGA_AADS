package Entity.Enemy;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

import Entity.Playable.*;
import Audio.AudioPlayer;

public class Ghost_Horizontal extends Enemy {
    Playable mc;
    AudioPlayer fx;

    public Ghost_Horizontal(int x, int y, int range, Playable mc,int speedx) {
        this.mc = mc;
        this.x = x;
        this.y = y;
        initial_x = x;
        initial_y = y;
        this.x_speed = speedx;
        y_speed = 0;
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
        x += x_speed;

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
        if(x <= initial_x || x >= initial_x + range) {
            x_speed = x_speed*-1;
        }
    }
}
