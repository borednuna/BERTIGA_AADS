package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;
import Map.Map;
import Entity.*;
import Entity.Collectibles.Collectibles;
import Entity.Collectibles.Flower;
import Entity.Playable.Playable;
import Entity.Playable.Hanuman;
import Entity.Enemy.*;
import Entity.Enemy.Ghost_Vertical;
import Entity.Enemy.Ghost_Horizontal;
import Entity.HUD;
import Utility.Time;
import Audio.AudioPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


import java.awt.Graphics2D;

public class HanumanState extends State {
    private Background bg;
    private Map map = new Map(50);
    private Playable main_character;
    private List <Enemy> enemy;
    private List <Collectibles> flowers;
    private AudioPlayer music;

    private Time t;
    private HUD hud;

    public HanumanState(StateManager stateManager){
        this.stateManager = stateManager;
        this.flowers = new ArrayList<Collectibles>();
        this.enemy = new ArrayList<Enemy>();
        music = new AudioPlayer("/SFX/music_labirin.wav");

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
        music.play();

        enemy.add(new Ghost_Horizontal(150, 675, 350, main_character, 2));
        enemy.add(new Ghost_Horizontal(150, 200, 200, main_character, 3));
        enemy.add(new Ghost_Vertical(500, 475, 100, main_character, 2));
        enemy.add(new Ghost_Vertical(850, 275, 350, main_character, 2));
        enemy.add(new Ghost_Horizontal(950, 675, 200, main_character, 3));
        enemy.add(new Ghost_Horizontal(950, 475, 100, main_character, 5));
        enemy.add(new Ghost_Vertical(650, 75, 100, main_character, 2));
        enemy.add(new Ghost_Horizontal(180, 600, 150, main_character, 3));
        enemy.add(new Ghost_Horizontal(1050, 75, 100, main_character, 2));
        enemy.add(new Ghost_Horizontal(1000, 75, 200, main_character, 2));

        flowers.add(new Flower(main_character, 400, 690));
        flowers.add(new Flower(main_character, 1000, 85));
        flowers.add(new Flower(main_character, 650, 280));
        flowers.add(new Flower(main_character, 300, 80));
        flowers.add(new Flower(main_character, 800, 380));
        flowers.add(new Flower(main_character, 1150, 575));
        flowers.add(new Flower(main_character, 150, 175));
        flowers.add(new Flower(main_character, 500, 475));

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

        for(Enemy ghost: enemy){
            ghost.update();
        }
        for (Collectibles flower : flowers) {
            flower.update();
        }

        if (main_character.isDead()) {
            music.stop();
            SaveData.writeLatestLevel(3);
            stateManager.setState(StateManager.DEATHSTATE);
        }
        if (main_character.getX() >= 1300) {
            music.stop();
            SaveData.writeHighScore(3, String.valueOf(t.getSecond() - main_character.getScore()) + "." + String.valueOf(t.getMilisecond()));
            stateManager.setState(StateManager.RAHWANASTATE);
        }
        
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
        } else if (k == KeyEvent.VK_ESCAPE) {
            music.stop();
            SaveData.writeLatestLevel(3);
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
}
