package Entity.Enemy;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Map.Map;
import Entity.Playable.*;

import java.io.IOException;
import java.util.Objects;

public class Ghost_Horizontal extends Enemy {
    Playable mc;

    public Ghost_Horizontal(int x, int y, int range, Playable mc,int speedx) {
        this.mc = mc;
        this.x = x;
        this.y = y;
        initial_x = x;
        initial_y = y;
        this.x_speed = speedx;
        y_speed = 0;
        this.range = range;

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
        // if (x_speed > 0) {
        //     g.drawImage(icon_right, x, y, null);
        // } else if (x_speed < 0) {
        //     g.drawImage(icon_left, x, y, null);
        // } else if (y_speed < 0) {
        //     g.drawImage(icon_left, x, y, null);
        // } else if (y_speed > 0) {
        //     g.drawImage(icon_right, x, y, null);
        // }
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
    public void set_direction(int dir) {
        // this.direction = dir;
    }

    @Override
    public void update() {
        x += x_speed;

        move();
        checkMC();
    }

    private void checkMC() {
        if ((int)((mc.getX() - 98) / 50) == (int)((x - 98) / 50) && (int)((mc.getY() - 98) / 50) == (int)((y - 98) / 50)) {
            mc.reduceHealth();
        }
    }

    private void move() {
        if(x <= initial_x || x >= initial_x + range) {
            x_speed = x_speed*-1;
        }
    }

	@Override
	public void collide_maze_walls(int positionX, int positionY) {
		if (y > 375 && y < 400) {
            if (x > 115 && x_speed > 0) {
                x_speed = -1*x_speed;
                x = 115;
            } else if (x < 250 && x_speed < 0) {
                x_speed = -1*x_speed;
                x = 250;
            }
        }
	}
}
