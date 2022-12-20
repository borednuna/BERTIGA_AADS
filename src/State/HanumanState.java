package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;
import Map.Map;
import Entity.*;
import Entity.Playable.Playable;
import Entity.Playable.Hanuman;
import Entity.HUD;
import Utility.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

import java.awt.Graphics;


import java.awt.Graphics2D;

public class HanumanState extends State {
    private Background bg;
    private Map map = new Map(50);
    private Playable main_character;
    private Time t;
    private HUD hud;

    public HanumanState(StateManager stateManager){
        this.stateManager = stateManager;

        try{
            bg = new Background("/Backgrounds/bg_LABIRINHANUMAN.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        init();
    }

    @Override
    public void init() {
        this.map.loadMap("/Map/labirinhanuman.map");
        this.map.loadTiles("/Tiles/tile_labirinhanuman.png");
        this.map.setPosition(98, 30);
        main_character =  new Hanuman (map);
        t = new Time();
        hud = new HUD(main_character, t);
        t.start();
    }

    @Override
    public void update() {
        main_character.update();

        if (main_character.isDead()) stateManager.setState(StateManager.DEATHSTATE);
        if (main_character.getX() >= 1300) stateManager.setState(StateManager.RAHWANASTATE);
        
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        map.draw(g);
        main_character.draw(g);
        hud.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_RIGHT){
            main_character.set_x_speed(5);
            main_character.set_y_speed(0);
            main_character.set_direction(k);
        }else if(k == KeyEvent.VK_LEFT){
            main_character.set_x_speed(-5);
            main_character.set_y_speed(0);
            main_character.set_direction(k);
        }else if(k == KeyEvent.VK_UP){
            main_character.set_x_speed(0);
            main_character.set_y_speed(-5);
            main_character.set_direction(k);
        }else if(k == KeyEvent.VK_DOWN){
            main_character.set_x_speed(0);
            main_character.set_y_speed(5);
            main_character.set_direction(k);
        }
    }

    @Override
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_UP ||
        k == KeyEvent.VK_DOWN ||
        k == KeyEvent.VK_LEFT ||
        k == KeyEvent.VK_RIGHT){
            main_character.set_x_speed(0);
            main_character.set_y_speed(0);
        }
    }

    @Override //gapake deh
    public void initMaze() {
        // TODO Auto-generated method stub
        
    }
    
}
