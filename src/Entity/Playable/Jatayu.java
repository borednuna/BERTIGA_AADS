package Entity.Playable;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Entity;

import Main.GamePanel;
import Map.Map;

import java.io.IOException;
import java.util.Objects;

public class Jatayu extends Playable{
    Map tm;

    public Jatayu (Map tm){
        this.tm = tm;
        health = 3;
        x = 50;
        y = 380;
        x_speed = 0;
        y_speed = 0;
        direction = KeyEvent.VK_RIGHT;
        dead = false;

        try {
            icon_left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entity/Playable/Jatayu.png")));
            icon_right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entity/Playable/Jatayu.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void collidingPanel(){
        if (x <= 5 && x_speed < 0){
            x_speed = 0;
            x = 5;
        }
        if (x >= GamePanel.WIDTH - 50 && x_speed > 0){
            x_speed = 0;
            x = GamePanel.WIDTH - 50;
        }
    }

    private void collidingOutsidePath(){
        if (x <= 200 || x >= 1240){
            y_speed = 0;
            y = 385;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (direction == KeyEvent.VK_RIGHT){
            g.drawImage(icon_right, x, y, null);
        }else if (direction == KeyEvent.VK_LEFT){
            g.drawImage(icon_left, x, y, null);
        }     else if (direction == KeyEvent.VK_KP_UP){
            g.drawImage(icon_left, x, y, null);
        }else if (direction == KeyEvent.VK_DOWN){
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
        this.direction = dir;
        
    }

    @Override
    public void reduceHealth() {
        this.health -= 1;
        if(health <= 0) dead = true;
        
    }

    @Override
    public void update() {
        x += x_speed;
        y += y_speed;

        collidingPanel();
        collidingOutsidePath();

        if(x_speed > 0) tm.collideRight(x, y, this);
        if(x_speed < 0) tm.collideLeft(x, y, this);
        if(y_speed > 0) tm.collideDown(x, y, this);
        if(y_speed < 0) tm.collideUp(x, y, this);
        
    }
    
}
