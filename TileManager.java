import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile; // a tile array

    // Create a constructor
    public TileManager(GamePanel gp){
        this.gp = gp;

        // set the tile size
        tile = new Tile[10];

        // call the method within this constructor
        getTileImage();
    }

    // create a method
    public void getTileImage(){
        // load the tile images
        try{
            // initiate the tile array
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_water.png"));
    }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        g2.drawImage(tile[0].image, 0,0, gp.tileSize, gp.tileSize, null);
    }
}
