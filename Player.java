
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

//    GamePanel gp; // mute this line cz you passed gp as a superclass of the Entity class below
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
//    public int hasKey = 0;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH){
         super(gp); // calling the Entity class's constructor of the super class
         this.gp = gp;
         this.keyH = keyH;

         screenX = gp.screenWidth/2 - (gp.tileSize/2);
         screenY = gp.screenHeight/2 - (gp.tileSize/2);

//         solidArea = new Rectangle(8, 16, 32, 32); // can be written in 4 separated lines as below
         solidArea = new Rectangle();
         solidArea.x = 8;
         solidArea.y = 16;
         solidAreaDefaultX = solidArea.x;
         solidAreaDefaultY = solidArea.y;
         solidArea.width = 32;
         solidArea.height = 32;

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

        up1 = setup("LaraCroft_up1");
        up2 = setup("LaraCroft_up2");
        down1 = setup("LaraCroft_down1");
        down2 = setup("LaraCroft_down2");
        left1 = setup("LaraCroft_left1");
        left2 = setup("LaraCroft_left2");
        right1 = setup("LaraCroft_right1");
        right2 = setup("LaraCroft_right2");

    }

    public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
          image = ImageIO.read(getClass().getResourceAsStream("/player/"+imageName+".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
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

            // check Tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check Object collision and return an int
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Check the NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex); // create a method

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
            if(spriteCounter > 12){
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

    // add another method
    public void pickUpObject(int i){

        if(i !=999){

        }
    }
    public void interactNPC(int i){
        if(i !=999){
          System.out.println("Hitting an NPC");
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
        g2.drawImage(image, screenX, screenY, null); // the null is for image observer argument , just set as null
    } 

} 
