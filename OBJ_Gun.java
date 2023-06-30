import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Gun extends SuperObject{
    public OBJ_Gun(){
        name = "Gun";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gun.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
