import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile; // a tile array
    int mapTileNum[][];

    // Create a constructor
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10]; // set the tile size
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        // call the methods within this constructor
        getTileImage();
        loadMap();
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
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String numbers[] = line.split("");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        // this is a manual way to draw tiles
//        g2.drawImage(tile[0].image, 0,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96,0, gp.tileSize, gp.tileSize, null);

        // we need an automated method
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }
}
