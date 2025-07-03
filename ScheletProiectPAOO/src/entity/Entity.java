package entity;

import PaooGame.Game;
import PaooGame.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    public double playTime=200;
    Game g;

    Graphics2D g2;
    public double worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, tren;
    public BufferedImage attackup, attackdown, attackleft, attackright;
    public String direction;
    public int nivel = 1;
    public boolean nivelTerminat = false;
    private boolean resetTimeOnce = false;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0,50,50);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean attacking = false;
    public int actionLockCounter = 0;


    public Entity(Game g) {
        this.g = g;
    }

    public void setAction() {
    }
    //metoda care da update si stabileste miscarile trenului
    public void updateTren() {
        setAction();
        if (g.player.nivel == 2 && !resetTimeOnce) {
            playTime = 200.0;
            resetTimeOnce = true;
        }
        if (g.player.nivel == 3 && resetTimeOnce) {
            playTime = 280.0;
            resetTimeOnce = false;
        }

        playTime -= (double)1/60;
        collisionOn = false;
        g.cChecker.checkTile(this);

        if (!collisionOn) {
            switch(direction) {
                case "up":
                    worldY -= (0.2 * speed);
                    break;
                case "down":
                    worldY += (0.2 * speed);
                    break;
                case "left":
                    worldX -= (0.2 * speed);
                    break;
                case "right":
                    worldX += (0.2 * speed);
                    break;
                case "stop":
                    break;
                case "down2":
                    worldY += (0.2 * speed);
                    break;
            }
        }


        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
// metoda de actualizare a trenului
    public void updateMan() {

        setAction();
        collisionOn = false;
        g.cChecker.checkTile(this);
        g.cChecker.checkPlayer(this);
        g.cChecker.checkEntity(this, g.tree);
        g.cChecker.checkEntity(this, g.stone);
        g.cChecker.checkObjectTREE(this, false);
        g.cChecker.checkObjectSTONE(this, false);

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }


        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    //metoda care deseneaza trenul
    public void drawTren(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = (int) (worldX - g.player.worldX + g.player.screenX);
        int screenY = (int) (worldY - g.player.worldY + g.player.screenY);

        if(worldX + g.tileSize > g.player.worldX - g.player.screenX &&
            worldX - g.tileSize < g.player.worldX + g.player.screenX &&
            worldY + g.tileSize > g.player.worldY - g.player.screenY &&
            worldY - g.tileSize < g.player.worldY + g.player.screenY) {

            switch(direction) {
                case "up":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down2":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "stop":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, 200, 90, null);
        }
    }
// metoda care deseneaza npc ul
    public void drawNPC(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = (int) (worldX - g.player.worldX + g.player.screenX);
        int screenY = (int) (worldY - g.player.worldY + g.player.screenY);

        if(worldX + g.tileSize > g.player.worldX - g.player.screenX &&
                worldX - g.tileSize < g.player.worldX + g.player.screenX &&
                worldY + g.tileSize > g.player.worldY - g.player.screenY &&
                worldY - g.tileSize < g.player.worldY + g.player.screenY) {

            switch(direction) {
                case "up":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down2":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "stop":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, 90, 90, null);
        }
    }

//metoda care verifica obiectele destructibile
    public void updateD() {
        setAction();
        collisionOn = false;
        g.cChecker.checkTile(this);


    }

//metoda care deseneaza si verifica imaginile obiectelor destructibile
    public void drawD(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = (int) (worldX - g.player.worldX + g.player.screenX);
        int screenY = (int) (worldY - g.player.worldY + g.player.screenY);

        if (worldX + g.tileSize > g.player.worldX - g.player.screenX &&
                worldX - g.tileSize < g.player.worldX + g.player.screenX &&
                worldY + g.tileSize > g.player.worldY - g.player.screenY &&
                worldY - g.tileSize < g.player.worldY + g.player.screenY) {

            switch (direction) {
                case "stop":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
            }


            g2.drawImage(image, screenX, screenY, 80, 80, null);
        }
    }

//metoda care incarca imagini de tip PNG
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath.endsWith(".png") ? imagePath : imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
//metoda care schimba imaginile NPC ului in functie de nivel
    public void getNPCImage() {
        if(g.player.nivel == 1) {
            up1 = down1 = left1 = right1 = setup("/textures/forestman", g.tileSize, g.tileSize);
            up2 = down2 = left2 = right2 = setup("/textures/forestman", g.tileSize, g.tileSize);
        } else if(g.player.nivel == 2) {
            up1 = down1 = left1 = right1 = setup("/textures/snowman", g.tileSize, g.tileSize);
            up2 = down2 = left2 = right2 = setup("/textures/snowman", g.tileSize, g.tileSize);
        }else if(g.player.nivel == 3) {
            up1 = down1 = left1 = right1 = setup("/textures/desertman", g.tileSize, g.tileSize);
            up2 = down2 = left2 = right2 = setup("/textures/desertman", g.tileSize, g.tileSize);
        }
    }


}
