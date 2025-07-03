package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_RAIL6 extends SuperObject {
    public OBJ_RAIL6() {
        name = "rail6";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/rail6.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
