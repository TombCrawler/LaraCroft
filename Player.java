
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH){
         this.gp = gp;
         this.keyH = keyH;

         screenX = gp.screenWidth/2 - (gp.tileSize/2);
         screenY = gp.screenHeight/2 - (gp.tileSize/2);

         solidArea = new Rectangle(8, 16, 32, 32);

         setDefaultValues(); // call this method from this constructor
         getPlayerImage();
    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 23; // this X and Y the default player position in the world map
        worldY = gp.tileSize * 21; // the tile size is 48x48
        speed = 4; 
        direction = "down";
    }
    public void getPlayerImage(){

        try{
        up1 = ImageIO.read(getClass().getResourceAsStream("/player/LaraCroft_up1.png"));
        up2 = ImageIO.read(getClass().getResourceAsStream("/player/LaraCroft_up2.png"));
        down1 = ImageIO.read(getClass().getResourceAsStream("/player/LaraCroft_down1.png"));
        down2 = ImageIO.read(getClass().getResourceAsStream("/player/LaraCroft_down2.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/player/LaraCroft_left1.png"));
        left2 = ImageIO.read(getClass().getResourceAsStream("/player/LaraCroft_left2.png"));
        right1 = ImageIO.read(getClass().getResourceAsStream("/player/LaraCroft_right1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("/player/LaraCroft_right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // If collision is false, player can move
            if(collisionOn == false){

                switch(direction){
                    case "up":
                        worldY -= speed; // If Y value decreases, the character goes up (opposite to math as this is the convention how most of the graphic frameworks behave)
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            // update() method get called 60 frames per sec in GamePanel class. if it hits 10 changes,it changes the sprite 1 to 2, 2 to 1
            // meaning the player image changes in every 10 frames
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize ); // Draw a rectangle and fill it with the specified color (x, y, width, height) x, y are the postion of the rect
        
        BufferedImage image = null;

        switch(direction){
        case "up":
            if(spriteNum == 1){
              image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            break;
        case "down":  
            if(spriteNum == 1){
              image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
            break;
        case "left":
            if(spriteNum == 1){
              image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
            break;
        case "right":
            if(spriteNum == 1){
            image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
            break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // the null is for image observer argument , just set as null
    } 

} 
