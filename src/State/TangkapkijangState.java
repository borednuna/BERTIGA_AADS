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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class TangkapkijangState extends State {
    private Background bg;
    private Map map = new Map(50);
    private Playable main_character;
    private Enemy ghostver;
    private Enemy ghosthor;
    private List <Collectibles> flowers;

    public TangkapkijangState(StateManager stateManager) {
        this.stateManager = stateManager;
        this.flowers = new ArrayList<Collectibles>();
        
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
        ghostver = new Ghost_Vertical(400, 325, 100, main_character);
        ghosthor = new Ghost_Horizontal(450, 385, 150, main_character);
        flowers.add(new Flower(main_character, 400, 690));
    }

    @Override
    public void update() {
        main_character.update();
        ghostver.update();
        ghosthor.update();

        for (Collectibles flower : flowers) {
            flower.update();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        map.draw(g);
        main_character.draw(g);
        ghostver.draw(g);
        ghosthor.draw(g);

        for (Collectibles flower : flowers) {
            flower.draw(g);
        }
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

    @Override
    public void initMaze() {
            
    }

}
