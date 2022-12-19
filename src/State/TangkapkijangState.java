package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;
import Map.Map;
import Entity.*;
import Entity.Collectibles.Collectibles;
import Entity.Collectibles.Flower;
import Entity.Playable.Playable;
import Entity.Playable.Rama;
import Entity.Enemy.*;
import Entity.Enemy.Ghost_Vertical;
import Entity.Enemy.Ghost_Horizontal;
import Entity.HUD;
import Utility.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class TangkapkijangState extends State {
    private Background bg;
    private Map map = new Map(50);
    private Playable main_character;
    private List <Enemy> enemy;
    private List <Collectibles> flowers;
    private Time t;
    private HUD hud;

    public TangkapkijangState(StateManager stateManager) {
        this.stateManager = stateManager;
        this.flowers = new ArrayList<Collectibles>();
        this.enemy = new ArrayList<Enemy>();
        // ghost_h.add(new Ghost_Horizontal(50, 380, 100, main_character));
        
        try {
            bg = new Background("/Backgrounds/bg_LABIRINTANGKAPKIJANG.png");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        init();
    }

    @Override
    public void init() {
        this.map.loadMap("/Map/tangkapkijang.map");
        this.map.loadTiles("/Tiles/tile_tangkapkijang.png");
        this.map.setPosition(98, 30);
        main_character = new Rama(map);
        t = new Time();
        hud = new HUD(main_character, t);
        // 
        enemy.add(new Ghost_Horizontal(150, 675, 300, main_character, 2));
        enemy.add(new Ghost_Horizontal(250, 175, 150, main_character, 3));
        enemy.add(new Ghost_Vertical(550, 475, 100, main_character, 2));
        enemy.add(new Ghost_Vertical(800, 275, 100, main_character, 4));
        enemy.add(new Ghost_Horizontal(1000, 675, 200, main_character, 3));
        enemy.add(new Ghost_Horizontal(1000, 75, 200, main_character, 7));

        flowers.add(new Flower(main_character, 400, 690));
        flowers.add(new Flower(main_character, 1000, 130));
        flowers.add(new Flower(main_character, 650, 330));
        flowers.add(new Flower(main_character, 350, 80));
        flowers.add(new Flower(main_character, 800, 380));

        t.start();
    }
  
    @Override
    public void update() {
        main_character.update();

        for(Enemy ghost: enemy){
            ghost.update();
        }
        for (Collectibles flower : flowers) {
            flower.update();
        }

        if (main_character.isDead()) stateManager.setState(StateManager.DEATHSTATE);
        if (main_character.getX() >= 1300) stateManager.setState(StateManager.STORYLINE2);
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        map.draw(g);
        main_character.draw(g);

        for(Enemy ghost: enemy){
            ghost.draw(g);
        }
        for (Collectibles flower : flowers) {
            flower.draw(g);
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
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_UP ||
        k == KeyEvent.VK_DOWN ||
        k == KeyEvent.VK_LEFT ||
        k == KeyEvent.VK_RIGHT) {
            main_character.set_x_speed(0);
            main_character.set_y_speed(0);
        }
    }

    @Override // kayanya g kepake
    public void initMaze() {
            
    }

}
