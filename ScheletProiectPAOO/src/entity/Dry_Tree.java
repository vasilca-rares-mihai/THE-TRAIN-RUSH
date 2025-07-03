package entity;

import PaooGame.Game;
import PaooGame.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Dry_Tree  extends Entity{
    public Dry_Tree(Game g) {
        super(g);
        direction = "stop";
        getTreeImage();
    }
    //metoda de selectare a imaginii in functie de harta
    public void getTreeImage() {
        if(g.player.nivel == 1) {
            up1 = setup("/textures/map1/tree4.png", g.tileSize, g.tileSize);
        }
        else if(g.player.nivel == 2) {

            up1 = setup("/textures/map2/treelvl2.png", g.tileSize, g.tileSize);
        }
        else if(g.player.nivel == 3) {

            up1 = setup("/textures/map3/treelvl3.png", g.tileSize, g.tileSize);
        }
    }
}
