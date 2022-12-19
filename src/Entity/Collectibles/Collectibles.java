package Entity.Collectibles;

import java.awt.*;
import java.awt.image.BufferedImage;

import Entity.Playable.*;

public abstract class Collectibles {
    protected int x;
    protected int y;
    protected int x_range;
    protected boolean collected;
    protected BufferedImage icon;

    public abstract void draw(Graphics g);
    public abstract void update();

    public boolean isCatch(Playable mc) {
        if ((int)((mc.getX() - 98) / 50) == (int)((x - 98) / 50) && (int)((mc.getY() - 30) / 50) == (int)((y - 30) / 50)) return true;
        return false;
    }

    public abstract void dispose();
}
