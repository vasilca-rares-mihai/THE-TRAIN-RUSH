package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_RAIL2 extends SuperObject {
    public OBJ_RAIL2() {
        name = "rail2";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/rail2.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
