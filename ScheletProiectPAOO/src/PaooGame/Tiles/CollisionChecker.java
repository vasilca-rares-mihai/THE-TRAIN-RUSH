package PaooGame.Tiles;

import PaooGame.Game;
import entity.Entity;

public class CollisionChecker {
    Game g;
    public CollisionChecker(Game g) {
        this.g = g;
    }

    // metoda verificare coliziune tileuri
    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.worldX + entity.solidArea.x);
        int entityRightWorldX = (int) (entity.worldX + entity.solidArea.x + entity.solidArea.width);
        int entityTopWorldY = (int) (entity.worldY + entity.solidArea.y);
        int entityBottomWorldY = (int) (entity.worldY + entity.solidArea.y + entity.solidArea.height);

        int entityLeftCol = entityLeftWorldX/g.tileSize;
        int entityRightCol = entityRightWorldX/g.tileSize;
        int entityTopRow = entityTopWorldY/g.tileSize;
        int entityBottomRow = entityBottomWorldY/g.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/g.tileSize;
                tileNum1 = g.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = g.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(g.tileM.tile[tileNum1].collision == true || g.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/g.tileSize;
                tileNum1 = g.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = g.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(g.tileM.tile[tileNum1].collision == true || g.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/g.tileSize;
                tileNum1 = g.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = g.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(g.tileM.tile[tileNum1].collision == true || g.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/g.tileSize;
                tileNum1 = g.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = g.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(g.tileM.tile[tileNum1].collision == true || g.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }


//metoda vf coliziune cu obiectele de tip copac
    public int checkObjectTREE(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < g.objTREE.length; i++) {
            if (g.objTREE[i] != null) {

                entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
                entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);

                g.objTREE[i].solidArea.x = (int) (g.objTREE[i].worldX + g.objTREE[i].solidArea.x);
                g.objTREE[i].solidArea.y = (int) (g.objTREE[i].worldY + g.objTREE[i].solidArea.y);

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(g.objTREE[i].solidArea)) {
                            if(g.objTREE[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(g.objTREE[i].solidArea)) {
                            if(g.objTREE[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(g.objTREE[i].solidArea)) {
                            if(g.objTREE[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(g.objTREE[i].solidArea)) {
                            if(g.objTREE[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                g.objTREE[i].solidArea.x = g.objTREE[i].solidAreaDefaultX;
                g.objTREE[i].solidArea.y = g.objTREE[i].solidAreaDefaultY;

            }

        }
        return index;
    }


//metoda vf coliziune cu obiecte de tip piatra
    public int checkObjectSTONE(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < g.objSTONE.length; i++) {
            if (g.objSTONE[i] != null) {

                entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
                entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);

                g.objSTONE[i].solidArea.x = (int) (g.objSTONE[i].worldX + g.objSTONE[i].solidArea.x);
                g.objSTONE[i].solidArea.y = (int) (g.objSTONE[i].worldY + g.objSTONE[i].solidArea.y);

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(g.objSTONE[i].solidArea)) {
                            if(g.objSTONE[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(g.objSTONE[i].solidArea)) {
                            if(g.objSTONE[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(g.objSTONE[i].solidArea)) {
                            if(g.objSTONE[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(g.objSTONE[i].solidArea)) {
                            if(g.objSTONE[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                g.objSTONE[i].solidArea.x = g.objSTONE[i].solidAreaDefaultX;
                g.objSTONE[i].solidArea.y = g.objSTONE[i].solidAreaDefaultY;

            }

        }
        return index;
    }



// vf coliziune entitate
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
                entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);

                target[i].solidArea.x = (int) (target[i].worldX + target[i].solidArea.x);
                target[i].solidArea.y = (int) (target[i].worldY + target[i].solidArea.y);

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }

        }
        return index;
    }
//coliziunea playerului
    public void checkPlayer(Entity entity) {
        entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
        entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);

        g.player.solidArea.x = (int) (g.player.worldX + g.player.solidArea.x);
        g.player.solidArea.y = (int) (g.player.worldY + g.player.solidArea.y);

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(g.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(g.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(g.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(g.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        g.player.solidArea.x = g.player.solidAreaDefaultX;
        g.player.solidArea.y = g.player.solidAreaDefaultY;

    }
//coliziunea cu entitatile destructibile (copaci sau pietre de pe harta)
    public int checkD(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
                entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);

                target[i].solidArea.x = (int) (target[i].worldX + target[i].solidArea.x);
                target[i].solidArea.y = (int) (target[i].worldY + target[i].solidArea.y);

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }

        }
        return index;
    }


}
