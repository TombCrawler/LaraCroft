
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;



    public Player(GamePanel gp, KeyHandler keyH){
         this.gp = gp;
         this.keyH = keyH;

         setDefaultValues(); // call this method from this constructor
         getPlayerImage();
    }
    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4; 
        direction = "down";
    }
    public void getPlayerImage(){

        try{
        //    up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Volumes/MAGIC1/CS50/My2DGame/LaraCroft_up1.png"));
        //    up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Volumes/MAGIC1/CS50/My2DGame/LaraCroft_up1.png"));
        //    down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Volumes/MAGIC1/CS50/My2DGame/LaraCroft_down1.png"));
        //    down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Volumes/MAGIC1/CS50/My2DGame/LaraCroft_down2.png"));
        //    left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Volumes/MAGIC1/CS50/My2DGame/LaraCroft_left1.png"));
        //    left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Volumes/MAGIC1/CS50/My2DGame/LaraCroft_left2.png"));
        //    right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Volumes/MAGIC1/CS50/My2DGame/LaraCroft_right1.png"));
        //    right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Volumes/MAGIC1/CS50/My2DGame/LaraCroft_right2.png"));
        File f1 = new File("LaraCroft_up1.png");
        File f2 = new File("LaraCroft_up2.png");
        File f3 = new File("LaraCroft_down1.png");
        File f4 = new File("LaraCroft_down2.png");
        File f5 = new File("LaraCroft_left1.png");
        File f6 = new File("LaraCroft_left2.png");
        File f7 = new File("LaraCroft_right1.png");
        File f8 = new File("LaraCroft_right2.png");
        up1 = ImageIO.read(f1);
        up2 = ImageIO.read(f2);
        down1 = ImageIO.read(f3);
        down2 = ImageIO.read(f4);
        left1 = ImageIO.read(f5);
        left2 = ImageIO.read(f6);
        right1 = ImageIO.read(f7);
        right2 = ImageIO.read(f8);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){ 
        if(keyH.upPressed == true){
           direction = "up"; 
           y -= speed; // If Y value decreases, the character goes up (opposite to math as this is the convention how most of the graphic frameworks behave)
        }
        else if(keyH.downPressed == true){
           direction = "down";
           y += speed;
        }
        else if(keyH.leftPressed == true){
           direction = "left"; 
           x -= speed; 
        }
        else if(keyH.rightPressed == true){
           direction = "right"; 
           x += speed;
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); // the null is for image observer argument , just set as null
    } 

} 
