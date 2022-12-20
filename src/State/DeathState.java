package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

import Map.Background;
import Utility.Utility;

public class DeathState extends State{
    private Background bg;
    private Font font;

    public DeathState (StateManager stateManager){
        this.stateManager = stateManager;

        try{
            bg = new Background("/Backgrounds/bg_DEATHSTATE.png");
            Font ManilaCity = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource("/Fonts/ManilaCity.ttf")).getPath()));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(ManilaCity);

            font = ManilaCity.deriveFont(50f);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(font);
        g.setColor(Color.WHITE);

        String str = "Try Again :(";
        Utility.centeredText(g, str, GamePanel.WIDTH, GamePanel.HEIGHT, font, Color.WHITE);
        
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) stateManager.setState(StateManager.MENUSTATE);
        
    }

    @Override
    public void keyReleased(int k) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initMaze() {
        // TODO Auto-generated method stub
        
    }

}
