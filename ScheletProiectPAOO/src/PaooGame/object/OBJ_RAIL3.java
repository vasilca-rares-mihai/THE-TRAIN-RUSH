package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_RAIL3 extends SuperObject {
    public OBJ_RAIL3() {
        name = "rail3";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/rail3.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
