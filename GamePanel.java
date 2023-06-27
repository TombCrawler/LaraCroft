import javax.swing.JPanel;
import java.awt.*;

// inherit the JPanel calss
public class GamePanel extends JPanel implements Runnable{
    
    // Screen settings 
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // scale the resolution of the original tile size by this by this int

    final int tileSize = originalTileSize * scale; // gives me the 48x48 tile size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    
    // Get pixels
    final int screenWidth = tileSize * maxScreenCol; // 48 * 16 = 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 48 * 12 = 576 pixels 
    
    // Set FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // Thread is something you can start and stop the program, such as drawing screen

    // set player's default position. the ints are pixels 
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // meaans 4 pixels

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Set true for improving game's rendering performance
        this.addKeyListener(keyH); // the GamePanel can recognize key input 
        this.setFocusable((true)); // the GamePanel can be focused to receive key inputs 
    }
    
    // throw a method
    public void startGameThread(){
        // the "this" argument means the GamePanel class. Basically we are passing the GamePanel class to this thread's constructor to initiate a thread
          gameThread = new Thread(this);
          gameThread.start(); 
    }

    // this method corresponds to the Runnable class
    @Override
    public void run() {
        // one billion nano-sec means 1 sec, use it for more precise calculation
        double drawInterval = 1000000000/ FPS; // 0.01666 
        double nextDrawTime = System.nanoTime() + drawInterval;

        // create a game loop which will be the core of our game. 
        //This line means that as long as gameThread exists, it repeats the process that is written inside of these curly braces
        while(gameThread != null){
            //  To test by adding this method to the Main file so that you can print out this message 
            //   System.out.println(" The game loop is running");    
            
            // 1 Update: update information such as character positions
            update();
            // 2 Draw: draw the screen with the updated information
            repaint(); // this is how you call the paintComponent method, although a bit     
           

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // get the remaining time until the nextDraw time
                remainingTime = remainingTime/ 1000000; // divided by milliseconds. it is how it is when use the sleep method
                
                if(remainingTime < 0){
                    remainingTime = 0; // this situation might not happen but just in case
                }

                nextDrawTime += drawInterval; // when the sleep time is over and the thread is awakened, we're gonna set a new nextDrawTime, which means the nextDrawTime will be in  0.01666 sec 
                
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // this method is for changing the player's position
    public void update(){
          if(keyH.upPressed == true){
            playerY -= playerSpeed; // If Y value decreases, the character goes up (opposite to math as this is the convention how most of the graphic frameworks behave)
          }
          else if(keyH.downPressed == true){
            playerY += playerSpeed;
          }
          else if(keyH.leftPressed == true){
            playerX -= playerSpeed; 
          }
          else if(keyH.rightPressed == true){
            playerX += playerSpeed;
          }
    }
    // paintComponent is a builtin method in Java which is a standard method to draw things on JPanel
    // the Graphics argument is a class which has many functions to draw objects on the screen, named g
    public void paintComponent(Graphics g){  // Imagine the Graphics class is your pencil or brush
        // the GamePanel is a subclass of JPanel
        super.paintComponent(g);  // whenever use paitComponent, you need this line. the super means the parent of the paintcomponent class
        // before using this Graphics, convert Grphics to Graphics2D class which extends the Graphics class to provide more sophisticated control over geometry, coordinates, color,text layout
        Graphics2D g2 = (Graphics2D)g; 

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize ); // Draw a rectangle and fill it with the specified color (x, y, width, height) x, y are the postion of the rect
        g2.dispose(); // the program works w/o this line but it is a good practice to save  by disposing grpahic context nd release any system resources 
 
    }
} 