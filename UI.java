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
    public String currentDialogue = ""; // make a string

//    double playTime;
//    DecimalFormat dFormat = new DecimalFormat("#0.00"); // if you want 3 decimals, 0.000

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

        // play state
        if(gp.gameState == gp.playState){
            // Do playState stuff later
        }
        // pause state
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        // dialogue state
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x ,y);
    }
    public void drawDialogueScreen(){
        // window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F ));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(currentDialogue, x, y); // Graphics.drawString() is a built-in method in Java
    }
    public void drawSubWindow(int x, int y, int width, int height){
           Color c = new Color(0,0,0, 220); // the fourth "a" alpha parameter is for opacity
           g2.setColor(c); // do not forget this line to make the graphic appear
           g2.fillRoundRect(x, y, width, height, 35, 35); // arcWidth, arcHeight are for

           c = new Color(255, 255, 255);
           g2.setColor(c); // do not forget this line to make the graphic appear
           g2.setStroke(new BasicStroke(5)); // setStroke(new BasicStroke(int)) for outlines
           g2.drawRoundRect(x, y, width, height, 25,25);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
