package State;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import Map.Background;

public class Storyline3 extends State{
    private Background bg;

    public Storyline3 (StateManager stateManager){
        this.stateManager = stateManager;

        try{
            bg = new Background("/Backgrounds/Storyline_BG/3. CERITA_TIGA.png");
        }catch(Exception e){
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
        
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) stateManager.setState(StateManager.HANUMANSTATE);
        
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
