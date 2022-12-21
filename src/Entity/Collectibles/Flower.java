package Entity.Collectibles;

import Entity.Playable.Playable;
import Audio.AudioPlayer;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Flower extends Collectibles {
    Playable mc;
    AudioPlayer fx;

    public Flower(Playable mc, int x, int y){
        this.collected = false;
        this.mc = mc;
        this.x = x;
        this.y = y;
        this.x_range = 50;
        fx = new AudioPlayer("/SFX/fx_pickitem.wav");

        try {
            icon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entity/Collectibles/flower.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(icon, x, y, null);
    }

    @Override
    public void update() {
        if (!collected) {
            if (isCatch(mc)) {
                fx.play();
                mc.addScore(5);
                dispose();
                collected = true;
            }
        }
    }

    @Override
    public void dispose() {
        icon = null;
    }
    
}
