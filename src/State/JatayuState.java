package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;
import Map.Map;
import Entity.*;
import Entity.Playable.Playable;
import Entity.Playable.Jatayu;
import Entity.HUD;
import Entity.Enemy.Enemy;
import Entity.Enemy.Ghost_Horizontal;
import Entity.Enemy.Ghost_Vertical;
import Utility.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class JatayuState extends State {
    private Background bg;
    private  Map map = new Map(50);
    private Playable main_character;
    private List <Enemy> enemy;
    private Time t;
    private HUD hud;
    

    public JatayuState(StateManager stateManager){
        this.stateManager  = stateManager;
        enemy = new ArrayList<Enemy>();

        try{
            bg = new Background("/Backgrounds/bg_LABIRINJATAYU.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        init();
    }

    @Override
    public void init() {
        this.map.loadMap("/Map/labirinjatayu.map");
        this.map.loadTiles("/Tiles/tile_labirinjatayu.png");
        this.map.setPosition(98, 30);
        main_character = new Jatayu(map);
        t = new Time();
        hud = new HUD(main_character, t);

        enemy.add(new Ghost_Horizontal(450, 75, 200, main_character, 2));
        enemy.add(new Ghost_Vertical(450, 525, 150, main_character, 5));
        enemy.add(new Ghost_Vertical(900, 75, 150, main_character, 5));
        enemy.add(new Ghost_Vertical(800, 315, 100, main_character, 2));
        enemy.add(new Ghost_Vertical(1100, 450, 100, main_character, 5));
        enemy.add(new Ghost_Horizontal(150, 75, 150, main_character, 3));


        t.start();
    }

    @Override
    public void update() {
        main_character.update();
        
        for(Enemy rahwana : enemy){
            rahwana.update();
        }

        if (main_character.isDead()) stateManager.setState(StateManager.DEATHSTATE);
        if (main_character.getX() >= 1300) stateManager.setState(StateManager.STORYLINE3);
        
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        map.draw(g);
        main_character.draw(g); 
        
        for(Enemy rahwana: enemy){
            rahwana.draw(g);
        }

        hud.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_RIGHT) {
            main_character.set_x_speed(5);
            main_character.set_y_speed(0);
            main_character.set_direction(k);
        } else if (k == KeyEvent.VK_LEFT) {
            main_character.set_x_speed(-5);
            main_character.set_y_speed(0);
            main_character.set_direction(k);
        } else if (k == KeyEvent.VK_UP) {
            main_character.set_y_speed(-5);
            main_character.set_x_speed(0);
            main_character.set_direction(k);
        } else if (k == KeyEvent.VK_DOWN) {
            main_character.set_y_speed(5);
            main_character.set_x_speed(0);
            main_character.set_direction(k);
        }else if (k == KeyEvent.VK_ESCAPE){
            stateManager.setState(StateManager.MENUSTATE);
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

    @Override // gapake deh
    public void initMaze() {
        // TODO Auto-generated method stub
        
    }
    
}
