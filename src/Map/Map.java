package Map;

import Main.GamePanel;
import Entity.Playable.*;
import Entity.Enemy.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class Map {
	
	// position
	private double x;
	private double y;
	
	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	// tileset
	private BufferedImage tileset;
	private ObjectTile tiles;
	
	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

    // checkCollision
    private int currentColumn;
    private int currentRow;
	
	public Map(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = 15;
		numColsToDraw = 25;
	}
	
	public void loadTiles(String s) {
		try {

			tileset = ImageIO.read(
                Objects.requireNonNull(getClass().getResourceAsStream(s))
            );
			
			BufferedImage subimage = tileset;
			tiles = new ObjectTile(subimage);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String s) {
		
		try {
			
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
            numRowsToDraw = numRows;
            numColsToDraw = numCols;

			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;
			
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getTileSize() { return tileSize; }
	public double getx() { return x; }
	public double gety() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public void setPosition(double x, double y) {
		
		this.x += (x - this.x);
		this.y += (y - this.y);
	}

    public void collideRight(int positionX, int positionY, Playable player) {
        currentColumn = (int)((positionX - 98) / tileSize);
        currentRow = (int)((positionY - 30) / tileSize);
        
        if (map[currentRow][currentColumn + 1] == 0) {
            player.set_x_speed(0);
            player.set_x(currentColumn * tileSize + 100);
        }
    }

    public void collideLeft(int positionX, int positionY, Playable player) {
        currentColumn = (int)((positionX - 98) / tileSize);
        currentRow = (int)((positionY - 30) / tileSize);
        
        if (map[currentRow][currentColumn] == 0) {
            player.set_x_speed(0);
            player.set_x((currentColumn + 1) * tileSize + 100);
        }
    }

    public void collideUp(int positionX, int positionY, Playable player) {
        currentColumn = (int)((positionX - 98) / tileSize);
        currentRow = (int)((positionY - 30) / tileSize);
        
        if (map[currentRow][currentColumn] == 0) {
            player.set_y_speed(0);
            player.set_y((currentRow + 1) * tileSize + 30);
        }
    }

    public void collideDown(int positionX, int positionY, Playable player) {
        currentColumn = (int)((positionX - 98) / tileSize);
        currentRow = (int)((positionY - 30) / tileSize);
        
        if (map[currentRow + 1][currentColumn] == 0) {
            player.set_y_speed(0);
            player.set_y(currentRow * tileSize + 30);
        }
    }

	public void draw(Graphics2D g) {
		for(
			int row = rowOffset;
			row < rowOffset + numRowsToDraw;
			row++) {
			
			for(
				int col = colOffset;
				col < colOffset + numColsToDraw;
				col++) {
				
				if(map[row][col] != 0) continue;
				
				g.drawImage(
					tiles.getImage(),
					(int)x + col * tileSize,
					(int)y + row * tileSize,
					null
				);
				
			}
			
		}
		
	}
	
}
