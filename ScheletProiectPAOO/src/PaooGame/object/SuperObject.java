package PaooGame.object;

import PaooGame.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,70,70);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;


    public void draw(Graphics2D g2, Game g) {
        int screenX = (int) (worldX - g.player.worldX + g.player.screenX);
        int screenY = (int) (worldY - g.player.worldY + g.player.screenY);

        if(worldX + g.tileSize > g.player.worldX - g.player.screenX && worldX -g.tileSize < g.player.worldX + g.player.screenX && worldY + g.tileSize > g.player.worldY - g.player.screenY && worldY  - g.tileSize < g.player.worldY + g.player.screenY) {
            g2.drawImage(image, screenX, screenY, g.tileSize, g.tileSize, null);

        }

    }

}
