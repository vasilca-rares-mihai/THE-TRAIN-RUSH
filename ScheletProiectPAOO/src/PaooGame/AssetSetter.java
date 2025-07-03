package PaooGame;

import PaooGame.object.*;
import entity.*;

public class AssetSetter extends Entity {
    Game g;
    public AssetSetter(Game g) {
        super(g);
        this.g = g;
    }

// metoda care seteaza 2 obiecte importante pe nivel 1
    public void setObject() {

        g.objTREE[0] = new OBJ_CRAFTING();
        g.objTREE[0].worldX = 17 * g.tileSize;
        g.objTREE[0].worldY = 5 * g.tileSize;
        g.objTREE[0].collision = true;

        g.objTREE[1] = new OBJ_STAR(g);
        g.objTREE[1].worldX = 17 * g.tileSize;
        g.objTREE[1].worldY = 1 * g.tileSize;


    }


// metoda care initializeaza npc urile
    public void setNPC() {
        g.npcTren[0] = new NPC_train(g);
        g.npcTren[0].worldX=g.tileSize*1;
        g.npcTren[0].worldY=g.tileSize*(4.7);

        g.npcMan[0] = new NPC_man(g);
        g.npcMan[0].worldX=g.tileSize*6;
        g.npcMan[0].worldY=g.tileSize*6;
    }
    //metoda metoda care initializeaza copacii in functie de nivel
    public void setTREE() {
        if(nivel == 1) {
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
    }

    //metoda metoda care initializeaza pietrele in functie de nivel
    public void setSTONE() {
        if (nivel == 1) {
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
        }
    }





}

