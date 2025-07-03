package PaooGame.object;

import PaooGame.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_STONE extends SuperObject {
    public OBJ_STONE(Game g) {
        name = "stone";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/stone2.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
