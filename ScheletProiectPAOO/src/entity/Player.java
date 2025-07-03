package entity;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.object.*;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import static PaooGame.Graphics.Assets.tree;

public class Player extends Entity {
    Game a;
    KeyHandler keyH;
    public int k = 2;
    public int l = 0;
    public final int screenX;
    public final int screenY;

    public int hasStone = 0;
    public int hasWood = 0;
    public int hasRail = 2;
    public int hasStars = 0;
    private char character = 'a';
    public ArrayList<SuperObject> inventory = new ArrayList<>();



    public Player(Game a, KeyHandler keyH) {
        super(a);
        this.a = a;
        this.keyH = keyH;

        screenX = a.screenWidth / 2 - (a.tileSize / 2);
        screenY = a.screenHeight / 2 - (a.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 5;
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 70;
        solidArea.height = 70;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();

    }

    // Adauga obiectele initiale in inventar
    public void setItems() {
        inventory.add(new OBJ_WOOD(g));
        inventory.add(new OBJ_STONE(g));
        inventory.add(new OBJ_STAR(g));
        inventory.add(new OBJ_RAIL1(g));
    }

    // Seteaza valorile initiale ale jucatorului
    public void setDefaultValues() {
        worldX = a.tileSize * 16;
        worldY = a.tileSize * 5;
        speed = 4;
        direction = "down";
    }

    // Incarca imaginile jucatorului pentru diferite directii
    public void getPlayerImage() {
        up1 = setup("/textures/boy_up_1.png", g.tileSize, g.tileSize);
        up2 = setup("/textures/boy_up_2.png", g.tileSize, g.tileSize);
        down1 = setup("/textures/boy_down_1.png", g.tileSize, g.tileSize);
        down2 = setup("/textures/boy_down_2.png", g.tileSize, g.tileSize);
        left1 = setup("/textures/boy_left_1.png", g.tileSize, g.tileSize);
        left2 = setup("/textures/boy_left_2.png", g.tileSize, g.tileSize);
        right1 = setup("/textures/boy_right_1.png", g.tileSize, g.tileSize);
        right2 = setup("/textures/boy_right_2.png", g.tileSize, g.tileSize);
        tren = setup("/textures/train.png", g.tileSize * 2, g.tileSize);
    }

    // Incarca imaginile jucatorului pentru atacuri
    public void getPlayerAttackImage() {
        attackup = setup("/textures/boy_up_sword.png", g.tileSize, g.tileSize * 2);
        attackdown = setup("/textures/boy_down_sword.png", g.tileSize, g.tileSize * 2);
        attackright = setup("/textures/boy_right_sword.png", g.tileSize * 2, g.tileSize);
        attackleft = setup("/textures/boy_left_sword.png", g.tileSize * 2, g.tileSize);

    }

    // Actualizeaza starea jucatorului
    public void Update() {
        if (attacking == true) {
            attaking();
        }

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";

            }
            collisionOn = false;
            a.cChecker.checkTile(this);


            int objIndexTREE = g.cChecker.checkObjectTREE(this, true);
            pickUpObjectTREE(objIndexTREE);

            int objIndexSTONE = g.cChecker.checkObjectSTONE(this, true);
            pickUpObjectSTONE(objIndexSTONE);

            int npcIndexTren = g.cChecker.checkEntity(this, g.npcTren);
            interactNPCTren(npcIndexTren);

            int npcIndexMan = g.cChecker.checkEntity(this, g.npcMan);
            interactNPCMan(npcIndexMan);

            int treeIndex = g.cChecker.checkD(this, g.tree);
            interactTREE(treeIndex);

            int stoneIndex = g.cChecker.checkD(this, g.stone);
            interactSTONE(stoneIndex);

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
        caleFerata();
    }

    // Gestioneaza logica atacului jucatorului
    private void attaking() {
        // Obține indexul obiectului cu care interacționează jucătorul în timpul atacului
        int objIndex = g.cChecker.checkObjectTREE(this, true);
        // Verifică dacă jucătorul interacționează cu un obiect și dacă acel obiect nu este null
        if (objIndex != 999 && g.objTREE[objIndex] != null) {
            spriteCounter++;
            if (spriteCounter <= 5) {
                spriteNum = 1;
            }
            if (spriteCounter > 5 && spriteCounter <= 25) {
                spriteNum = 1;
            }
            if (spriteCounter > 25) {
                spriteNum = 1;
                spriteCounter = 0;
                attacking = false;
            }
        }
    }



    // Interactioneaza cu NPC-ul tren
    private void interactNPCTren(int i) {
        if (i != 999) {
            System.out.println("LOVESTI TRENUL");
        }
    }
    // Interactioneaza cu NPC-ul barbat
    private void interactNPCMan(int i) {
        if (i != 999) {
            if(g.player.hasStars > 0) {
                hasStars--;
                hasRail++;
            }
        }
    }

    // Interactioneaza cu un copac
    private void interactTREE(int i) {
        if (i != 999) {
            if (keyH.enterPressed == true) {
                attacking = true;
                g.tree[i] = null;
                g.objTREE[k] = new OBJ_WOOD(g);
                g.objTREE[k].worldX = 17 * g.tileSize;
                g.objTREE[k].worldY = 8 * g.tileSize;
                k++;


            }
        }

    }

    // Interactioneaza cu o piatra
    private void interactSTONE(int i) {
        if (i != 999) {
            if (keyH.enterPressed == true) {
                attacking = true;
                g.stone[i] = null;
                g.objSTONE[l] = new OBJ_STONE(g);
                g.objSTONE[l].worldX = 17 * g.tileSize;
                g.objSTONE[l].worldY = 7 * g.tileSize;
                l++;
            }
        }

    }

    // Colecteaza un obiect de tip copac
    public void pickUpObjectTREE(int i) {
        if (i != 999) {
            String objectName = g.objTREE[i].name;
            switch (objectName) {
                case "wood":
                    if(hasWood < 4) {
                        hasWood++;
                        g.objTREE[i] = null;
                    }
                    break;
                case "star":
                    hasStars++;
                    g.objTREE[i] = null;
                    break;
                case "crafting":
                    if(g.player.nivel == 1) {
                        if (hasStone >= 3 && hasWood >= 3) {
                            hasStone = hasStone - 3;
                            hasWood = hasWood - 3;
                            hasRail++;
                        }
                    }
                    else if (g.player.nivel == 2) {
                        if (hasStone >= 2 && hasWood >= 2) {
                            hasStone = hasStone - 2;
                            hasWood = hasWood - 2;
                            hasRail++;
                        }
                    }
                    else if (g.player.nivel == 3) {
                        if (hasStone >= 1 && hasWood >= 1) {
                            hasStone = hasStone - 1;
                            hasWood = hasWood - 1;
                            hasRail++;
                        }
                    }
                    break;
                case "rail":
                    break;
            }
            System.out.println("WOOD STONE RAIL " + hasWood + " " + hasStone + " " + hasRail);

        }
    }

    // Colecteaza un obiect de tip piatra
    public void pickUpObjectSTONE(int i) {
        if (i != 999) {
            String objectName = g.objSTONE[i].name;
            switch (objectName) {
                case "stone":
                    if(hasStone < 4) {
                        hasStone++;
                        g.objSTONE[i] = null;
                    }
                    break;
                case "rail":
                    break;
            }
            System.out.println("WOOD STONE RAIL " + hasWood + " " + hasStone + " " + hasRail);

        }
    }


    // Deseneaza jucatorul pe ecran
    public void Draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackup;
                    }
                    if (spriteNum == 2) {
                        image = attackup;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackdown;
                    }
                    if (spriteNum == 2) {
                        image = attackdown;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackleft;
                    }
                    if (spriteNum == 2) {
                        image = attackleft;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackright;
                    }
                    if (spriteNum == 2) {
                        image = attackright;
                    }
                }
                break;
        }

        g2.drawImage(image, (int) screenX, (int) screenY, a.tileSize, a.tileSize, null);
    }

//  Initializarea sinelor de tren in functie de nivele
    public void caleFerata() {
        if (nivel == 1) {
            if (hasRail == 2) {
                g.railuri[0] = new OBJ_RAIL2();
                g.railuri[0].worldX = 1 * g.tileSize;
                g.railuri[0].worldY = 5 * g.tileSize;

                g.railuri[1] = new OBJ_RAIL2();
                g.railuri[1].worldX = 2 * g.tileSize;
                g.railuri[1].worldY = 5 * g.tileSize;



            } else if (hasRail == 3) {
                g.railuri[2] = new OBJ_RAIL2();
                g.railuri[2].worldX = 3 * g.tileSize;
                g.railuri[2].worldY = 5 * g.tileSize;
            } else if (hasRail == 4) {
                g.railuri[3] = new OBJ_RAIL2();
                g.railuri[3].worldX = 4 * g.tileSize;
                g.railuri[3].worldY = 5 * g.tileSize;
            } else if (hasRail == 5) {
                g.railuri[4] = new OBJ_RAIL4();
                g.railuri[4].worldX = 5 * g.tileSize;
                g.railuri[4].worldY = 5 * g.tileSize;
            } else if (hasRail == 6) {
                g.railuri[5] = new OBJ_RAIL6();
                g.railuri[5].worldX = 5 * g.tileSize;
                g.railuri[5].worldY = 6 * g.tileSize;
            } else if (hasRail == 7) {
                g.railuri[6] = new OBJ_RAIL2();
                g.railuri[6].worldX = 6 * g.tileSize;
                g.railuri[6].worldY = 6 * g.tileSize;
            } else if (hasRail == 8) {
                g.railuri[7] = new OBJ_RAIL2();
                g.railuri[7].worldX = 7 * g.tileSize;
                g.railuri[7].worldY = 6 * g.tileSize;
            } else if (hasRail == 9) {
                g.railuri[8] = new OBJ_RAIL2();
                g.railuri[8].worldX = 8 * g.tileSize;
                g.railuri[8].worldY = 6 * g.tileSize;
            } else if (hasRail == 10) {
                g.railuri[9] = new OBJ_RAIL2();
                g.railuri[9].worldX = 9 * g.tileSize;
                g.railuri[9].worldY = 6 * g.tileSize;
            } else if (hasRail == 11) {
                g.railuri[10] = new OBJ_RAIL2();
                g.railuri[10].worldX = 10 * g.tileSize;
                g.railuri[10].worldY = 6 * g.tileSize;
            } else if (hasRail == 12) {
                g.railuri[11] = new OBJ_RAIL2();
                g.railuri[11].worldX = 11 * g.tileSize;
                g.railuri[11].worldY = 6 * g.tileSize;
            } else if (hasRail == 13) {
                g.railuri[12] = new OBJ_RAIL2();
                g.railuri[12].worldX = 12 * g.tileSize;
                g.railuri[12].worldY = 6 * g.tileSize;
            } else if (hasRail == 14) {
                g.railuri[13] = new OBJ_RAIL2();
                g.railuri[13].worldX = 13 * g.tileSize;
                g.railuri[13].worldY = 6 * g.tileSize;
            } else if (hasRail == 15) {
                g.railuri[14] = new OBJ_RAIL3();
                g.railuri[14].worldX = 14 * g.tileSize;
                g.railuri[14].worldY = 6 * g.tileSize;
            } else if (hasRail == 16) {
                g.railuri[15] = new OBJ_RAIL1(g);
                g.railuri[15].worldX = 14 * g.tileSize;
                g.railuri[15].worldY = 5 * g.tileSize;
            } else if (hasRail == 17) {
                g.railuri[16] = new OBJ_RAIL1(g);
                g.railuri[16].worldX = 14 * g.tileSize;
                g.railuri[16].worldY = 4 * g.tileSize;
            } else if (hasRail == 18) {
                g.railuri[17] = new OBJ_RAIL5();
                g.railuri[17].worldX = 14 * g.tileSize;
                g.railuri[17].worldY = 3 * g.tileSize;
            } else if (hasRail == 19) {
                g.railuri[18] = new OBJ_RAIL2();
                g.railuri[18].worldX = 15 * g.tileSize;
                g.railuri[18].worldY = 3 * g.tileSize;
                UpdateLvl2();
                for(int i=1; i<g.objTREE.length; i++) {
                    g.objTREE[i] = null;
                }
                for(int i=0; i<g.objSTONE.length; i++) {
                    g.objSTONE[i] = null;
                }
            }

        } else if (nivel == 2) {
            if (hasRail == 2) {
                g.railuri[0] = new OBJ_RAIL2();
                g.railuri[0].worldX = 1 * g.tileSize;
                g.railuri[0].worldY = 2 * g.tileSize;

                g.railuri[1] = new OBJ_RAIL2();
                g.railuri[1].worldX = 2 * g.tileSize;
                g.railuri[1].worldY = 2 * g.tileSize;
            } else if (hasRail == 3) {
                g.railuri[2] = new OBJ_RAIL2();
                g.railuri[2].worldX = 3 * g.tileSize;
                g.railuri[2].worldY = 2 * g.tileSize;
            } else if (hasRail == 4) {
                g.railuri[3] = new OBJ_RAIL4();
                g.railuri[3].worldX = 4 * g.tileSize;
                g.railuri[3].worldY = 2 * g.tileSize;
            } else if (hasRail == 5) {
                g.railuri[4] = new OBJ_RAIL1(g);
                g.railuri[4].worldX = 4 * g.tileSize;
                g.railuri[4].worldY = 3 * g.tileSize;
            } else if (hasRail == 6) {
                g.railuri[5] = new OBJ_RAIL1(g);
                g.railuri[5].worldX = 4 * g.tileSize;
                g.railuri[5].worldY = 4 * g.tileSize;
            } else if (hasRail == 7) {
                g.railuri[6] = new OBJ_RAIL1(g);
                g.railuri[6].worldX = 4 * g.tileSize;
                g.railuri[6].worldY = 5 * g.tileSize;
            } else if (hasRail == 8) {
                g.railuri[7] = new OBJ_RAIL1(g);
                g.railuri[7].worldX = 4 * g.tileSize;
                g.railuri[7].worldY = 6 * g.tileSize;
            } else if (hasRail == 9) {
                g.railuri[8] = new OBJ_RAIL1(g);
                g.railuri[8].worldX = 4 * g.tileSize;
                g.railuri[8].worldY = 7 * g.tileSize;
            } else if (hasRail == 10) {
                g.railuri[9] = new OBJ_RAIL6();
                g.railuri[9].worldX = 4 * g.tileSize;
                g.railuri[9].worldY = 8 * g.tileSize;
            } else if (hasRail == 11) {
                g.railuri[10] = new OBJ_RAIL2();
                g.railuri[10].worldX = 5 * g.tileSize;
                g.railuri[10].worldY = 8 * g.tileSize;
            } else if (hasRail == 12) {
                g.railuri[11] = new OBJ_RAIL2();
                g.railuri[11].worldX = 6 * g.tileSize;
                g.railuri[11].worldY = 8 * g.tileSize;
            } else if (hasRail == 13) {
                g.railuri[12] = new OBJ_RAIL2();
                g.railuri[12].worldX = 7 * g.tileSize;
                g.railuri[12].worldY = 8 * g.tileSize;
            } else if (hasRail == 14) {
                g.railuri[13] = new OBJ_RAIL2();
                g.railuri[13].worldX = 8 * g.tileSize;
                g.railuri[13].worldY = 8 * g.tileSize;
            } else if (hasRail == 15) {
                g.railuri[14] = new OBJ_RAIL4();
                g.railuri[14].worldX = 9 * g.tileSize;
                g.railuri[14].worldY = 8 * g.tileSize;
            } else if (hasRail == 16) {
                g.railuri[15] = new OBJ_RAIL1(g);
                g.railuri[15].worldX = 9 * g.tileSize;
                g.railuri[15].worldY = 9 * g.tileSize;
            } else if (hasRail == 17) {
                g.railuri[16] = new OBJ_RAIL6();
                g.railuri[16].worldX = 9 * g.tileSize;
                g.railuri[16].worldY = 10 * g.tileSize;
            } else if (hasRail == 18) {
                g.railuri[17] = new OBJ_RAIL2();
                g.railuri[17].worldX = 10 * g.tileSize;
                g.railuri[17].worldY = 10 * g.tileSize;
            } else if (hasRail == 19) {
                g.railuri[18] = new OBJ_RAIL2();
                g.railuri[18].worldX = 11 * g.tileSize;
                g.railuri[18].worldY = 10 * g.tileSize;
            } else if (hasRail == 20) {
                g.railuri[19] = new OBJ_RAIL2();
                g.railuri[19].worldX = 12 * g.tileSize;
                g.railuri[19].worldY = 10 * g.tileSize;
            } else if (hasRail == 21) {
                g.railuri[20] = new OBJ_RAIL2();
                g.railuri[20].worldX = 13 * g.tileSize;
                g.railuri[20].worldY = 10 * g.tileSize;
            } else if (hasRail == 22) {
                g.railuri[21] = new OBJ_RAIL2();
                g.railuri[21].worldX = 14 * g.tileSize;
                g.railuri[21].worldY = 10 * g.tileSize;
            }else if (hasRail == 23) {
                g.railuri[22] = new OBJ_RAIL2();
                g.railuri[22].worldX = 15 * g.tileSize;
                g.railuri[22].worldY = 10 * g.tileSize;
                UpdateLvl3();
                for(int i=1; i<g.objTREE.length; i++) {
                    g.objTREE[i] = null;
                }
                for(int i=0; i<g.objSTONE.length; i++) {
                    g.objSTONE[i] = null;
                }
            }
        } else if (nivel == 3) {
            if (hasRail == 2) {
                g.railuri[0] = new OBJ_RAIL2();
                g.railuri[0].worldX = 1 * g.tileSize;
                g.railuri[0].worldY = 2 * g.tileSize;

                g.railuri[1] = new OBJ_RAIL2();
                g.railuri[1].worldX = 2 * g.tileSize;
                g.railuri[1].worldY = 2 * g.tileSize;
            } else if (hasRail == 3) {
                g.railuri[2] = new OBJ_RAIL2();
                g.railuri[2].worldX = 3 * g.tileSize;
                g.railuri[2].worldY = 2 * g.tileSize;
            } else if (hasRail == 4) {
                g.railuri[3] = new OBJ_RAIL2();
                g.railuri[3].worldX = 4 * g.tileSize;
                g.railuri[3].worldY = 2 * g.tileSize;
            } else if (hasRail == 5) {
                g.railuri[4] = new OBJ_RAIL2();
                g.railuri[4].worldX = 5 * g.tileSize;
                g.railuri[4].worldY = 2 * g.tileSize;
            } else if (hasRail == 6) {
                g.railuri[5] = new OBJ_RAIL2();
                g.railuri[5].worldX = 6 * g.tileSize;
                g.railuri[5].worldY = 2 * g.tileSize;
            } else if (hasRail == 7) {
                g.railuri[6] = new OBJ_RAIL2();
                g.railuri[6].worldX = 7 * g.tileSize;
                g.railuri[6].worldY = 2 * g.tileSize;
            } else if (hasRail == 8) {
                g.railuri[7] = new OBJ_RAIL2();
                g.railuri[7].worldX = 8 * g.tileSize;
                g.railuri[7].worldY = 2 * g.tileSize;
            } else if (hasRail == 9) {
                g.railuri[8] = new OBJ_RAIL2();
                g.railuri[8].worldX = 9 * g.tileSize;
                g.railuri[8].worldY = 2 * g.tileSize;
            } else if (hasRail == 10) {
                g.railuri[9] = new OBJ_RAIL2();
                g.railuri[9].worldX = 10 * g.tileSize;
                g.railuri[9].worldY = 2 * g.tileSize;
            } else if (hasRail == 11) {
                g.railuri[10] = new OBJ_RAIL2();
                g.railuri[10].worldX = 11 * g.tileSize;
                g.railuri[10].worldY = 2 * g.tileSize;
            } else if (hasRail == 12) {
                g.railuri[11] = new OBJ_RAIL2();
                g.railuri[11].worldX = 12 * g.tileSize;
                g.railuri[11].worldY = 2 * g.tileSize;
            } else if (hasRail == 13) {
                g.railuri[12] = new OBJ_RAIL2();
                g.railuri[12].worldX = 13 * g.tileSize;
                g.railuri[12].worldY = 2 * g.tileSize;
            } else if (hasRail == 14) {
                g.railuri[13] = new OBJ_RAIL2();
                g.railuri[13].worldX = 14 * g.tileSize;
                g.railuri[13].worldY = 2 * g.tileSize;
            } else if (hasRail == 15) {
                g.railuri[14] = new OBJ_RAIL2();
                g.railuri[14].worldX = 15 * g.tileSize;
                g.railuri[14].worldY = 2 * g.tileSize;
            } else if (hasRail == 16) {
                g.railuri[15] = new OBJ_RAIL4();
                g.railuri[15].worldX = 16 * g.tileSize;
                g.railuri[15].worldY = 2 * g.tileSize;
            } else if (hasRail == 17) {
                g.railuri[16] = new OBJ_RAIL1(g);
                g.railuri[16].worldX = 16 * g.tileSize;
                g.railuri[16].worldY = 3 * g.tileSize;
            } else if (hasRail == 18) {
                g.railuri[17] = new OBJ_RAIL1(g);
                g.railuri[17].worldX = 16 * g.tileSize;
                g.railuri[17].worldY = 4 * g.tileSize;
            } else if (hasRail == 19) {
                g.railuri[18] = new OBJ_RAIL1(g);
                g.railuri[18].worldX = 16 * g.tileSize;
                g.railuri[18].worldY = 5 * g.tileSize;
            } else if (hasRail == 20) {
                g.railuri[19] = new OBJ_RAIL1(g);
                g.railuri[19].worldX = 16 * g.tileSize;
                g.railuri[19].worldY = 6 * g.tileSize;
            } else if (hasRail == 21) {
                g.railuri[20] = new OBJ_RAIL1(g);
                g.railuri[20].worldX = 16 * g.tileSize;
                g.railuri[20].worldY = 7 * g.tileSize;
            } else if (hasRail == 22) {
                g.railuri[21] = new OBJ_RAIL1(g);
                g.railuri[21].worldX = 16 * g.tileSize;
                g.railuri[21].worldY = 8 * g.tileSize;
            }else if (hasRail == 23) {
                g.railuri[22] = new OBJ_RAIL1(g);
                g.railuri[22].worldX = 16 * g.tileSize;
                g.railuri[22].worldY = 9 * g.tileSize;
            }else if (hasRail == 24) {
                g.railuri[24] = new OBJ_RAIL3();
                g.railuri[24].worldX = 16 * g.tileSize;
                g.railuri[24].worldY = 10 * g.tileSize;
            }else if (hasRail == 25) {
                g.railuri[25] = new OBJ_RAIL2();
                g.railuri[25].worldX = 15 * g.tileSize;
                g.railuri[25].worldY = 10 * g.tileSize;
            }else if (hasRail == 26) {
                g.railuri[26] = new OBJ_RAIL2();
                g.railuri[26].worldX = 14 * g.tileSize;
                g.railuri[26].worldY = 10 * g.tileSize;
            }else if (hasRail == 27) {
                g.railuri[27] = new OBJ_RAIL2();
                g.railuri[27].worldX = 13 * g.tileSize;
                g.railuri[27].worldY = 10 * g.tileSize;
            }else if (hasRail == 28) {
                g.railuri[28] = new OBJ_RAIL2();
                g.railuri[28].worldX = 12 * g.tileSize;
                g.railuri[28].worldY = 10 * g.tileSize;
            }else if (hasRail == 29) {
                g.railuri[29] = new OBJ_RAIL2();
                g.railuri[29].worldX = 11 * g.tileSize;
                g.railuri[29].worldY = 10 * g.tileSize;
            }else if (hasRail == 30) {
                g.railuri[30] = new OBJ_RAIL2();
                g.railuri[30].worldX = 10 * g.tileSize;
                g.railuri[30].worldY = 10 * g.tileSize;
            }else if (hasRail == 31) {
                g.railuri[31] = new OBJ_RAIL2();
                g.railuri[31].worldX = 9 * g.tileSize;
                g.railuri[31].worldY = 10 * g.tileSize;
            }else if (hasRail == 32) {
                g.railuri[32] = new OBJ_RAIL2();
                g.railuri[32].worldX = 8 * g.tileSize;
                g.railuri[32].worldY = 10 * g.tileSize;
            }else if (hasRail == 33) {
                g.railuri[33] = new OBJ_RAIL2();
                g.railuri[33].worldX = 7 * g.tileSize;
                g.railuri[33].worldY = 10 * g.tileSize;
            }else if (hasRail == 34) {
                g.railuri[34] = new OBJ_RAIL2();
                g.railuri[34].worldX = 6 * g.tileSize;
                g.railuri[34].worldY = 10 * g.tileSize;
            }else if (hasRail == 35) {
                g.railuri[35] = new OBJ_RAIL2();
                g.railuri[35].worldX = 5 * g.tileSize;
                g.railuri[35].worldY = 10 * g.tileSize;
            }else if (hasRail == 36) {
                g.railuri[36] = new OBJ_RAIL2();
                g.railuri[36].worldX = 4 * g.tileSize;
                g.railuri[36].worldY = 10 * g.tileSize;
            }else if (hasRail == 37) {
                g.railuri[37] = new OBJ_RAIL2();
                g.railuri[37].worldX = 3 * g.tileSize;
                g.railuri[37].worldY = 10 * g.tileSize;
            }else if (hasRail == 38) {
                g.railuri[38] = new OBJ_RAIL2();
                g.railuri[38].worldX = 2 * g.tileSize;
                g.railuri[38].worldY = 10 * g.tileSize;
            }else if (hasRail == 39) {
                g.railuri[39] = new OBJ_RAIL2();
                g.railuri[39].worldX = 1 * g.tileSize;
                g.railuri[39].worldY = 10 * g.tileSize;
                GAMEOVER();
            }
        }
    }
    //Ecranul de final al jocului
    public void GAMEOVER() {
        g.player.nivel++;
        g.gameState = g.gameOver;
    }
    //Se actualizeaza toate obiectele pentru nivel 2
    public void UpdateLvl2() {
        g.player.nivelTerminat = true;
        g.gameState = g.loadingState;
        g.player.nivel++;
        g.tileM.Update();
        g.npcMan[0].getNPCImage();

        g.player.worldX = 12 * a.tileSize;
        g.player.worldY = 9 * a.tileSize;

        for (int i = 0; i < g.railuri.length; i++) {
            g.railuri[i] = null;
        }
        for (int i = 0; i < g.stone.length; i++) {
            g.stone[i] = null;
        }
        for (int i = 0; i < g.tree.length; i++) {
            g.tree[i] = null;
        }

        g.objTREE[30] = new OBJ_STAR(g);
        g.objTREE[30].worldX = 1 * g.tileSize;
        g.objTREE[30].worldY = 1 * g.tileSize;

        g.npcTren[0].worldX = g.tileSize * 1;
        g.npcTren[0].worldY = g.tileSize * 1.7;

        g.npcMan[0].worldX = g.tileSize * 12;
        g.npcMan[0].worldY = g.tileSize * 7;

        g.objTREE[0] = new OBJ_CRAFTING();
        g.objTREE[0].worldX = 13 * g.tileSize;
        g.objTREE[0].worldY = 9 * g.tileSize;
        g.objTREE[0].collision = true;

        hasRail = 2;
        hasStone = 0;
        hasWood = 0;


        int k = 2, ip = 1, jp = 4;
        for (int i = 0; i < 15; i++) {
            g.tree[k] = new Dry_Tree(g);
            g.tree[k].worldX = g.tileSize * ip;
            g.tree[k].worldY = g.tileSize * jp;
            k++;
            ip++;
            if (ip == 4 || ip == 7) {
                ip = 1;
                jp++;
            }
        }
        ip = 7;
        jp = 1;
        for (int i = 0; i < 28; i++) {
            g.tree[k] = new Dry_Tree(g);
            g.tree[k].worldX = g.tileSize * ip;
            g.tree[k].worldY = g.tileSize * jp;
            k++;
            ip++;
            if (ip == 18) {
                ip = 7;
                jp++;
            }
        }




        int l = 0, ip2 = 1, jp2 = 9;
        for (int j = 0; j < 21; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            ip2++;
            if (ip2 == 8 || ip2 == 15) {
                ip2 = 1;
                jp2++;
            }
        }
        ip2 = 13;
        jp2=3;
        for (int j = 0; j < 15; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            ip2++;
            if (ip2 == 18) {
                ip2 = 13;
                jp2++;
            }
        }
        ip2 = 8;
        jp2=4;
        for (int j = 0; j < 10; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            ip2++;
            if (ip2 == 13) {
                ip2 = 8;
                jp2++;
            }
        }


    }

    //Se actualizeaza toate obiectele pentru nivel 3
    public void UpdateLvl3() {
        g.player.nivelTerminat = false;
        g.gameState = g.loadingState;
        g.player.nivel++;
        g.tileM.Update();
        g.npcMan[0].getNPCImage();

        g.player.worldX = 10 * a.tileSize;
        g.player.worldY = 1 * a.tileSize;


        g.npcMan[0].worldX = g.tileSize * 15;
        g.npcMan[0].worldY = g.tileSize * 1;


        g.objTREE[1] = new OBJ_STAR(g);
        g.objTREE[1].worldX = 1 * g.tileSize;
        g.objTREE[1].worldY = 11 * g.tileSize;

        for (int i = 0; i < 23; i++) {
            g.railuri[i] = null;
        }
        for (int i = 0; i < 52; i++) {
            g.stone[i] = null;
        }
        for (int i = 0; i < 52; i++) {
            g.tree[i] = null;
        }
        g.npcTren[0].worldX = g.tileSize * 1;
        g.npcTren[0].worldY = g.tileSize * 1.7;


        g.objTREE[0] = new OBJ_CRAFTING();
        g.objTREE[0].worldX = 11 * g.tileSize;
        g.objTREE[0].worldY = 1 * g.tileSize;
        g.objTREE[0].collision = true;

        hasRail = 2;
        hasStone = 0;
        hasWood = 0;


        int k = 2, ip = 1, jp = 3;
        for (int i = 0; i < 21; i++) {
            g.tree[k] = new Dry_Tree(g);
            g.tree[k].worldX = g.tileSize * ip;
            g.tree[k].worldY = g.tileSize * jp;
            k++;
            ip++;
            if (ip == 8) {
                ip = 1;
                jp++;
            }
        }
        ip = 5;
        jp = 6;
        for (int i = 0; i < 8; i++) {
            g.tree[k] = new Dry_Tree(g);
            g.tree[k].worldX = g.tileSize * ip;
            g.tree[k].worldY = g.tileSize * jp;
            k++;
            ip++;
            if (ip == 7) {
                ip = 5;
                jp++;
            }
        }
        ip = 11;
        jp = 3;
        for (int i = 0; i < 20; i++) {
            g.tree[k] = new Dry_Tree(g);
            g.tree[k].worldX = g.tileSize * ip;
            g.tree[k].worldY = g.tileSize * jp;
            k++;
            ip++;
            if (ip == 16) {
                ip = 11;
                jp++;
            }
        }


        int l = 0, ip2 = 1, jp2 = 6;
        for (int j = 0; j < 8; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            ip2++;
            if (ip2 == 5) {
                ip2 = 1;
                jp2++;
            }
        }
        ip2=3;
        jp2=8;
        for (int j = 0; j < 4; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            ip2++;
            if (ip2 == 5) {
                ip2 = 3;
                jp2++;
            }
        }
        ip2=11;
        jp2=7;
        for (int j = 0; j < 15; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            ip2++;
            if (ip2 == 16) {
                ip2 = 11;
                jp2++;
            }
        }
        ip2=10;
        jp2=9;
        for (int j = 0; j < 25; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            jp2--;
            if (jp2 == 2) {
                jp2 = 9;
                ip2--;
            }
        }

    }

    //Se actualizeaza toate obiectele pentru nivel 1
    public void UpdateLvl1() {
        g.gameState = g.loadingState;

        g.npcMan[0].getNPCImage();

        g.player.worldX = 16 * a.tileSize;
        g.player.worldY = 5 * a.tileSize;


        g.objTREE[0] = new OBJ_CRAFTING();
        g.objTREE[0].worldX = 17 * g.tileSize;
        g.objTREE[0].worldY = 5 * g.tileSize;
        g.objTREE[0].collision = true;

        g.objTREE[1] = new OBJ_STAR(g);
        g.objTREE[1].worldX = 17 * g.tileSize;
        g.objTREE[1].worldY = 1 * g.tileSize;

        for (int i = 0; i < 23; i++) {
            g.railuri[i] = null;
        }
        for (int i = 0; i < 52; i++) {
            g.stone[i] = null;
        }
        for (int i = 0; i < 52; i++) {
            g.tree[i] = null;
        }

        g.npcTren[0] = new NPC_train(g);
        g.npcTren[0].worldX=g.tileSize*1;
        g.npcTren[0].worldY=g.tileSize*(4.7);

        g.npcMan[0] = new NPC_man(g);
        g.npcMan[0].worldX=g.tileSize*6;
        g.npcMan[0].worldY=g.tileSize*6;

        int l = 0, ip2 = 1, jp2 = 7;
        for (int j = 0; j < 25; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            ip2++;
            if (ip2 == 6 || ip2 == 11) {
                ip2 = 1;
                jp2++;
            }
        }

        ip2 = 6;
        jp2 = 1;
        for (int j = 0; j < 26; j++) {
            g.stone[l] = new Dry_Stone(g);
            g.stone[l].worldX = g.tileSize * ip2;
            g.stone[l].worldY = g.tileSize * jp2;
            l++;
            ip2++;
            if (ip2 == 14 || ip2 == 19) {
                ip2 = 6;
                jp2++;
            }
        }
        int k = 2, ip = 6, jp = 9;
        for (int i = 0; i < 36; i++) {
            g.tree[k] = new Dry_Tree(g);
            g.tree[k].worldX = g.tileSize * ip;
            g.tree[k].worldY = g.tileSize * jp;
            k++;
            ip++;
            if (ip == 18 || ip == 35) {
                ip = 6;
                jp++;
            }
        }
        ip = 1;
        jp = 1;
        for (int i = 0; i < 15; i++) {
            g.tree[k] = new Dry_Tree(g);
            g.tree[k].worldX = g.tileSize * ip;
            g.tree[k].worldY = g.tileSize * jp;
            k++;
            ip++;
            if (ip == 6 || ip == 11) {
                ip = 1;
                jp++;
            }
        }

    }

    // Functie care sterge totae obiectele de pe harta
    public void restart() {
        for (int i = 0; i < g.railuri.length; i++) {
            g.railuri[i] = null;
        }
        for (int i = 0; i < g.stone.length; i++) {
            g.stone[i] = null;
        }
        for (int i = 0; i < g.tree.length; i++) {
            g.tree[i] = null;
        }
        for (int i = 0; i < g.objSTONE.length; i++) {
            g.objSTONE[i] = null;
        }
        for (int i = 0; i < g.objTREE.length; i++) {
            g.objTREE[i] = null;
        }
        hasRail = 2;
        hasWood = 0;
        hasStone = 0;

    }
    //functie care m-a ajutat sa trec mai usor de nivele, a fost folosita doar in scopuri creative, pentru a putea lucra mai usor la joc, rog jucatorii sa nu foloseasca acest buton si sa joace corect acest joc
    public void HACK() {
        hasStone = 200;
        hasWood = 200;
    }




}
