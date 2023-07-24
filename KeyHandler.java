import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// whenever you use the KeyListener class, u need those 3 built-in method keyTeyped, keyPressed, keyReleased below
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    // debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int code = e.getKeyCode(); // returns the integer which keyCode associated with

        // Play State
       if(gp.gameState == gp.playState){

           if(code == KeyEvent.VK_W){
               upPressed = true;
           }
           if(code == KeyEvent.VK_S){
               downPressed = true;
           }
           if(code == KeyEvent.VK_A){
               leftPressed = true;
           }
           if(code == KeyEvent.VK_D){
               rightPressed = true;
           }
           if(code == KeyEvent.VK_P){ // P for pause
               gp.gameState = gp.pauseState;
           }
           if(code == KeyEvent.VK_ENTER){ // NPC dialogue
               enterPressed = true;

           }

           // Debug
           if(code == KeyEvent.VK_T){
               if(checkDrawTime == false){ // the drawTime is not appearing yet
                   checkDrawTime = true; // this means if you pushed the T,the time will appear meaning it's true
               }
               else {
                   checkDrawTime = false; // otherwise it disappears
               }
           }
       }
       // Pause State
       else if(gp.gameState == gp.pauseState){
           if(code == KeyEvent.VK_P){ // P for pause
               gp.gameState = gp.playState;
           }
       }
       // Dialogue State
       else if(gp.gameState == gp.dialogueState) {
           if(code == KeyEvent.VK_ENTER){
               gp.gameState = gp.playState;
           }
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       int code = e.getKeyCode();

       if(code == KeyEvent.VK_W){
          upPressed = false;
       }
       if(code == KeyEvent.VK_S){
        downPressed = false;
       }
       if(code == KeyEvent.VK_A){
          leftPressed = false;
       }
       if(code == KeyEvent.VK_D){
          rightPressed = false;
       }
    }
    
}
