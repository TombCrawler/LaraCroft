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
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        // call the methods within this constructor
        getTileImage();
        loadMap("/maps/world01.txt");
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

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
    }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
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
        int worldCol = 0;
        int worldRow = 0;
//        int x = 0; // we do not need this x and y for the camera function
//        int y = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;
//            x += gp.tileSize;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
//                x = 0;
                worldRow++;
//                y += gp.tileSize;
            }
        }

    }
}