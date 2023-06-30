import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_30, arial_80_Bold;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = ""; // th default is blank
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00"); // if you want 3 decimals, 0.000

    public UI(GamePanel gp){
        this.gp = gp;

        arial_30 = new Font("Arial", Font.BOLD, 30);
        arial_80_Bold = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }
    // create a method
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    // a method tp make the key counter or other message appear on the game screen
    public void draw(Graphics2D g2){
        if(gameFinished == true){
            g2.setFont(arial_30);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You are a certified Tomb Raider!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // this basically returns a length of the text
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);


            text = "Your playtime is: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // this basically returns a length of the text
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);


            g2.setFont(arial_80_Bold);
            g2.setColor(Color.white);
            text = "The relic found!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // this basically returns a length of the text
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        }
        else{
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x" + gp.player.hasKey,74,65);

            // Time
            playTime += (double)1/60;
            g2.drawString("Time:"+ dFormat.format(playTime) , gp.tileSize*11, 65);


            // Message
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize * 5);

                messageCounter++;
                // 60 Frame per sec so 120 means 2 seconds
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }

}
