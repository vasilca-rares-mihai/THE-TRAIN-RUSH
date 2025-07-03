package PaooGame.object;

import PaooGame.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_RAIL1 extends SuperObject {
    public OBJ_RAIL1(Game g) {
        name = "rail1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/rail1.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
