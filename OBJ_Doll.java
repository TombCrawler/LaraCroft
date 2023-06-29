import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Doll extends SuperObject{
    public OBJ_Doll(){
        name = "Doll";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/doll.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
