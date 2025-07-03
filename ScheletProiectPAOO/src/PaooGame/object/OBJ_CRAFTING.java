package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CRAFTING extends SuperObject {
    public OBJ_CRAFTING() {
        name = "crafting";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/crafting.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
