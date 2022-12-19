package Entity.Playable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Playable {
    protected int score;
    protected int health;
    protected int x;
    protected int y;
    protected int x_speed;
    protected int y_speed;
    protected int direction;
    protected BufferedImage icon_right;
    protected BufferedImage icon_left;
    protected boolean dead;

    public abstract void draw(Graphics g);
    public abstract void set_x_speed(int x);
    public abstract void set_y_speed(int y);
    public abstract void set_x(int x);
    public abstract void set_y(int y);
    public abstract void set_direction(int dir);
    public abstract void reduceHealth();
    public abstract void update();

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
