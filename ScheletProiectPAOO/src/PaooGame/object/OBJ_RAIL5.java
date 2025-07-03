package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_RAIL5 extends SuperObject {
    public OBJ_RAIL5() {
        name = "rail5";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/rail5.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
