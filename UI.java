import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_30, arial_80_Bold;
//    BufferedImage keyImage;
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
//        OBJ_Key key = new OBJ_Key(gp);
//        keyImage = key.image;
    }
    // create a method
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    // a method tp make the key counter or other message appear on the game screen
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_30);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            // Do playState stuff later
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x ,y);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
