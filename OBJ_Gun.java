import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Gun extends SuperObject{
    GamePanel gp;
    public OBJ_Gun(GamePanel gp){
        this.gp = gp;
        name = "Gun";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gun.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
