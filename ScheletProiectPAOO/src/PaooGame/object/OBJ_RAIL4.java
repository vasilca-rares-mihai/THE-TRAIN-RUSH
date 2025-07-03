package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_RAIL4 extends SuperObject {
    public OBJ_RAIL4() {
        name = "rail4";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/rail4.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
