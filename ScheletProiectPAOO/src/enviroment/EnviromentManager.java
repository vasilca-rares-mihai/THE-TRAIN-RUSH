package enviroment;

import PaooGame.Game;
import entity.Entity;

import java.awt.*;

public class EnviromentManager {
    Game g;
    Lighting lighting;

    public EnviromentManager(Game g) {
        this.g = g;
    }
    public void setup() {
        lighting = new Lighting(g, 500);
    }
    public void draw(Graphics2D g2) {
        lighting.draw(g2);

    }
}
