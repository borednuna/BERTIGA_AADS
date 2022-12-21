package Entity.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemy {
    protected int x;
    protected int y;
    protected int initial_x;
    protected int initial_y;
    protected int limit_y;
    protected int limit_x;
    protected int x_speed;
    protected int y_speed;
    protected int range;
    protected BufferedImage icon_right;
    protected BufferedImage icon_left;

    public abstract void draw(Graphics g);
    public abstract void set_x_speed(int x);
    public abstract void set_y_speed(int y);
    public abstract void set_x(int x);
    public abstract void set_y(int y);
    public abstract void update();
    
    public int getSpeedX() {
        return x_speed;
    }

    public int getSpeedY() {
        return y_speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
