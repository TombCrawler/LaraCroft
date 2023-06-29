import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    
    public int worldX, worldY;
    public int speed;
    
    // This is a variable which describes an image with an accessible buffer of image data, store image files
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction; // another variable
    
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
