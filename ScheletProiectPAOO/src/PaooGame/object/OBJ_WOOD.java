package PaooGame.object;

import PaooGame.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_WOOD extends SuperObject {
    public OBJ_WOOD(Game game) {
        name = "wood";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/wood.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
