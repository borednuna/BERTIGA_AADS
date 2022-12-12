package Entity.Playable;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Rama extends Playable {
    public Rama() {
        health = 3;
        x = 300;
        y = 300;
        x_speed = 0;
        y_speed = 0;
        direction = KeyEvent.VK_RIGHT;
        dead = false;

        try {
            icon_left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entity/Playable/ShintaKiri.png")));
            icon_right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entity/Playable/ShintaKiri.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (direction == KeyEvent.VK_RIGHT) {
            // x_speed = 5;
            g.drawImage(icon_left, x, y, null);
        } else if (direction == KeyEvent.VK_LEFT) {
            // x_speed = -5;
            g.drawImage(icon_left, x, y, null);
        } else if (direction == KeyEvent.VK_UP) {
            // y_speed = -5;
            g.drawImage(icon_left, x, y, null);
        } else if (direction == KeyEvent.VK_DOWN) {
            // y_speed = 5;
            g.drawImage(icon_right, x, y, null);
        }
    }

    @Override
    public void set_x_speed(int x) {
        this.x_speed = x;
    }

    @Override
    public void set_y_speed(int y) {
        this.y_speed = y;
    }
    public void set_direction(int dir) {
        this.direction = dir;
    }

    @Override
    public void reduceHealth() {
        this.health -= 1;
        if (health <= 0) dead = true;
    }

    @Override
    public void collide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update() {
        x += x_speed;
        y += y_speed;
    }
}
