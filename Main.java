import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this lets the window close properly when users click the close button(x)
        window.setResizable(false); // we can not resize this window
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // the pack() method is to fit the window to the preferred size and layouts of its subcomponents(=GamePanel)

        window.setLocationRelativeTo(null); // make the window center
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
 