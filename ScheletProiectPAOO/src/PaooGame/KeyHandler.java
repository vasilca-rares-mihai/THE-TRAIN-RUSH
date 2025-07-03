package PaooGame;

import PaooGame.object.OBJ_CRAFTING;
import entity.Dry_Stone;
import entity.Dry_Tree;
import entity.NPC_train;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean enterPressed;
    Game g;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(Game g) {
        this.g = g;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    // functie care verifica starea jocului si initiaza metode pentru butoanele apasate
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(g.gameState == g.titleState) {
            if(code == KeyEvent.VK_W) {
                g.ui.commandNum--;
                if(g.ui.commandNum < 0) {
                    g.ui.commandNum = 3;
                }
            }
            if(code == KeyEvent.VK_S) {
                g.ui.commandNum++;
                if(g.ui.commandNum > 3) {
                    g.ui.commandNum = 0;
                }
            }

        }
        if(g.gameState == g.buttonsState) {
            if(code == KeyEvent.VK_E) {
                g.gameState = g.titleState;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            if(g.ui.commandNum == 0) {
                if(g.gameState == g.titleState)
                    g.gameState = g.loadingState;
            }
            if(g.ui.commandNum == 1) {
            }
            if(g.ui.commandNum == 2) {
                g.gameState = g.buttonsState;
            }

            if(g.ui.commandNum == 3) {
                System.exit(0);
            }
        }
        if(g.gameState == g.loadingState) {
            if(code == KeyEvent.VK_E) {
                g.gameState=g.playState;
            }
        }
        if(g.gameState == g.gameLose) {
            if(code == KeyEvent.VK_W) {
                g.ui.commandNum--;
                if(g.ui.commandNum < 0) {
                    g.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S) {
                g.ui.commandNum++;
                if(g.ui.commandNum > 1) {
                    g.ui.commandNum = 0;
                }

            }
        }
        if(code == KeyEvent.VK_ENTER) {
            if(g.ui.commandNum == 0) {
                if(g.gameState == g.gameLose){
                    restart();
                    g.gameState = g.loadingState;

                }
            }
            if(g.gameState == g.titleState) {

                if(g.ui.commandNum == 1) {
                    g.gameState = g.loadGame;
                }

                if(g.ui.commandNum == 3) {
                    System.exit(0);
                }
                if(g.ui.commandNum == 2) {
                    g.gameState = g.buttonsState;
                }
            }
        }

        if(g.gameState == g.Inventory) {
            if(code == KeyEvent.VK_W) {
                if(g.ui.slotRow != 0) {
                    g.ui.slotRow = g.ui.slotRow - 2;
                }
            }
            if(code == KeyEvent.VK_A) {
                if(g.ui.slotCol != 0) {
                    g.ui.slotCol = g.ui.slotCol - 2;
                }
            }
            if(code == KeyEvent.VK_S) {
                if(g.ui.slotRow != 0) {
                    g.ui.slotRow = g.ui.slotRow + 2;
                }
            }
            if(code == KeyEvent.VK_D) {
                if(g.ui.slotCol != 6) {
                    g.ui.slotCol = g.ui.slotCol + 2;
                }
            }
        }



        if(code == KeyEvent.VK_ENTER) {
            enterPressed=true;
        }
        if(code == KeyEvent.VK_W) {
            upPressed=true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed=true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed=true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed=true;
        }
        if(code == KeyEvent.VK_P) {
            if(g.gameState == g.playState) {
                g.gameState = g.pauseState;
            }
            else if(g.gameState == g.pauseState) {
                g.gameState = g.playState;
            }
        }
        if(code == KeyEvent.VK_O) {
            g.save.insertData(g.player.nivel, g.player.hasRail, g.player.hasWood, g.player.hasStone, g.player.hasStars, g.npcTren[0].worldX, g.npcTren[0].worldY, g.score, g.npcTren[0].direction);
            System.out.println("Score saved to database");
        }
        if(code == KeyEvent.VK_K) {
            g.player.HACK();
        }
        if(code == KeyEvent.VK_I) {
            if(g.gameState == g.playState) {
                g.gameState = g.Inventory;
            } else if(g.gameState == g.Inventory) {
                g.gameState = g.playState;
            }
        }
    }

// functie care pune pe "fals" butoanele apasate
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed=false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed=false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed=false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed=false;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed=false;
        }

    }
// functie care reseteaza obiecetele in functie de nivel
    public void restart() {
        g.player.restart();
        g.player.caleFerata();
        if (g.player.nivel == 1) {
            int k = 1, ip = 6, jp = 9;
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

                g.objTREE[0] = new OBJ_CRAFTING();
                g.objTREE[0].worldX = 17 * g.tileSize;
                g.objTREE[0].worldY = 5 * g.tileSize;
                g.objTREE[0].collision = true;

                g.npcTren[0] = new NPC_train(g);
                g.npcTren[0].worldX = g.tileSize * 1;
                g.npcTren[0].worldY = g.tileSize * (4.7);


                g.player.worldX = g.tileSize * 9;
                g.player.worldY = g.tileSize * 7;

        } else if(g.player.nivel == 2) {

            int k = 1, ip = 1, jp = 4;
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


            g.npcTren[0].worldX = g.tileSize * 1;
            g.npcTren[0].worldY = g.tileSize * 1.7;


            g.npcMan[0].worldX = g.tileSize * 7;
            g.npcMan[0].worldY = g.tileSize * 7;

            g.objTREE[0] = new OBJ_CRAFTING();
            g.objTREE[0].worldX = 13 * g.tileSize;
            g.objTREE[0].worldY = 9 * g.tileSize;
            g.objTREE[0].collision = true;

            g.player.worldX = g.tileSize * 6;
            g.player.worldY = g.tileSize * 6;

        } else if(g.player.nivel == 3) {
            int k = 1, ip = 1, jp = 3;
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
            for (int i = 0; i < 9; i++) {
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
            for (int i = 0; i < 24; i++) {
                g.tree[k] = new Dry_Tree(g);
                g.tree[k].worldX = g.tileSize * ip;
                g.tree[k].worldY = g.tileSize * jp;
                k++;
                ip++;
                if (ip == 17) {
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
            ip2=11;
            jp2=7;
            for (int j = 0; j < 21; j++) {
                g.stone[l] = new Dry_Stone(g);
                g.stone[l].worldX = g.tileSize * ip2;
                g.stone[l].worldY = g.tileSize * jp2;
                l++;
                ip2++;
                if (ip2 == 17) {
                    ip2 = 11;
                    jp2++;
                }
            }

            g.npcTren[0].worldX = g.tileSize * 1;
            g.npcTren[0].worldY = g.tileSize * 1.7;

            g.objTREE[0] = new OBJ_CRAFTING();
            g.objTREE[0].worldX = 11 * g.tileSize;
            g.objTREE[0].worldY = 1 * g.tileSize;
            g.objTREE[0].collision = true;


            g.player.worldX = g.tileSize * 6;
            g.player.worldY = g.tileSize * 1;
        }
    }

}
