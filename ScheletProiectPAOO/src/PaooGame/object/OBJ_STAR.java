package PaooGame.object;

import PaooGame.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_STAR extends SuperObject {
    public OBJ_STAR(Game g) {
        name = "star";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/star.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
