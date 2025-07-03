package entity;

import PaooGame.Game;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;


public class NPC_train extends Entity {


    public NPC_train(Game g) {
        super(g);
        direction = "stop";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 150;
        solidArea.height = 80;

        getImage();
    }
    //metoda de selectare a imaginii
    public void getImage() {

        up1 = setup("/textures/train.png", g.tileSize*2, g.tileSize);
        up2 = setup("/textures/train2.png", g.tileSize*2, g.tileSize);
        down1 = setup("/textures/train3.png", g.tileSize*2, g.tileSize);
        down2 = setup("/textures/train4.png", g.tileSize*2, g.tileSize);
    }
    // functie care schimba directia trenului
    public void setAction() {

        if(g.player.nivel == 1) {

            if(playTime < 180 && playTime > 179.9) {
                direction="right";
            }
            //rail2
            if(playTime < 178 && playTime > 177.9 && g.player.hasRail == 2) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;

            }
            //rail3
            if(playTime < 171.3 && playTime > 171.2 && g.player.hasRail == 3) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail4
            if(playTime < 164.6 && playTime > 164.5 && g.player.hasRail == 4) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }

            if(playTime < 157.9 && playTime > 157.8) {
                direction="down";
            }

            //rail5
            if(playTime < 155.9 && playTime > 155.8 && g.player.hasRail == 5) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }

            if(playTime < 151 && playTime > 150.9) {
                direction="right";
            }

            //rail6
            if(playTime < 150.9 && playTime > 150.8 && g.player.hasRail == 6) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail7
            if(playTime < 144.2 && playTime > 144.1 && g.player.hasRail == 7) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail8
            if(playTime < 137.5 && playTime > 137.4 && g.player.hasRail == 8) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail9
            if(playTime < 130.8 && playTime > 130.7 && g.player.hasRail == 9) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail10
            if(playTime < 124.1 && playTime > 124 && g.player.hasRail == 10) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail11
            if(playTime < 117.4 && playTime > 117.3 && g.player.hasRail == 11) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail12
            if(playTime < 110.7 && playTime > 110.6 && g.player.hasRail == 12) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail13
            if(playTime < 104.2 && playTime > 104.1 && g.player.hasRail == 13) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail14
            if(playTime < 97.9 && playTime > 97.8 && g.player.hasRail == 14) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }

            if(playTime < 92 && playTime > 91.9) {
                direction="up";
            }
            //rail15
            if(playTime < 86.9 && playTime > 86.8 && g.player.hasRail == 15) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail16
            if(playTime < 80.3 && playTime > 80.2 && g.player.hasRail == 16) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail17
            if(playTime < 73.7 && playTime > 73.6 && g.player.hasRail == 17) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }

            if(playTime < 72.1 && playTime > 71) {
                direction="right";
            }

            //rail18
            if(playTime < 70.8 && playTime > 70.7 && g.player.hasRail == 18) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }

            if(playTime < 60 && playTime > 59.9) {
                direction="stop";
            }
        } else if(g.player.nivel == 2) {
            if(playTime > 199.9) {
                direction = "stop";
            }
            else if(playTime < 180 && playTime > 179.9) {
                direction="right";
            }
            //rail2
            else if(playTime < 178 && playTime > 177.9 && g.player.hasRail == 2) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail3
            else if(playTime < 171.3 && playTime > 171.2 && g.player.hasRail == 3) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            else if(playTime < 164.7 && playTime > 164.6) {
                direction="down";
            }
            //rail4
            else if(playTime < 162.7 && playTime > 162.6 && g.player.hasRail == 4) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail5
            else if(playTime < 156.1 && playTime > 156 && g.player.hasRail == 5) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail6
            else if(playTime < 149.5 && playTime > 149.4 && g.player.hasRail == 6) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail7
            else if(playTime < 142.9 && playTime > 142.8 && g.player.hasRail == 7) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail8
            else if(playTime < 136.3 && playTime > 136.2 && g.player.hasRail == 8) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail9
            else if(playTime < 129.7 && playTime > 129.6 && g.player.hasRail == 9) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            else if(playTime < 124.7 && playTime > 124.6) {
                direction="right";
            }
            //rail10
            else if(playTime < 124.5 && playTime > 124.4 && g.player.hasRail == 10) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail11
            else if(playTime < 117.9 && playTime > 117.8 && g.player.hasRail == 11) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail12
            else if(playTime < 111.3 && playTime > 111.2 && g.player.hasRail == 12) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail13
            else if(playTime < 104.7 && playTime > 104.6 && g.player.hasRail == 13) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail14
            else if(playTime < 98.1 && playTime > 98 && g.player.hasRail == 14) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            else if(playTime < 91.5 && playTime > 91.4) {
                direction="down";
            }
            //rail15
            else  if(playTime < 89.5 && playTime > 89.4 && g.player.hasRail == 15) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }//rail16
            else if(playTime < 82.9 && playTime > 82.8 && g.player.hasRail == 16) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            else if(playTime < 78.2 && playTime > 78.1) {
                direction="right";
            }
            //rail17
            else if(playTime < 78 && playTime > 77.9 && g.player.hasRail == 17) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail18
            else if(playTime < 71.4 && playTime > 71.3 && g.player.hasRail == 18) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail19
            else if(playTime < 64.8 && playTime > 64.7 && g.player.hasRail == 19) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail20
            else if(playTime < 58.2 && playTime > 58.1 && g.player.hasRail == 20) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail21
            else if(playTime < 51.6 && playTime > 51.5 && g.player.hasRail == 21) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail22
            else if(playTime < 45 && playTime > 44.9 && g.player.hasRail == 22) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
            //rail23
            else if(playTime < 38.4 && playTime > 38.3 && g.player.hasRail == 23) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 200;
            }
        } else if(g.player.nivel == 3) {
            if(playTime > 279.9) {
                direction = "stop";
            }
            if(playTime < 260 && playTime > 259.9) {
                direction="right";
            }
            //rail2
            if(playTime < 258 && playTime > 257.9 && g.player.hasRail == 2) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail3
            if(playTime < 251.4 && playTime > 251.3 && g.player.hasRail == 3) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail4
            if(playTime < 244.8 && playTime > 244.7 && g.player.hasRail == 4) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail5
            if(playTime < 238.2 && playTime > 238.1 && g.player.hasRail == 5) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail6
            if(playTime < 231.6 && playTime > 231.5 && g.player.hasRail == 6) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail7
            if(playTime < 225 && playTime > 224.9 && g.player.hasRail == 7) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail8
            if(playTime < 218.4 && playTime > 218.3 && g.player.hasRail == 8) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail9
            if(playTime < 211.8 && playTime > 211.7 && g.player.hasRail == 9) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail10
            if(playTime < 205.2 && playTime > 205.1 && g.player.hasRail == 10) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail11
            if(playTime < 198.6 && playTime > 198.5 && g.player.hasRail == 11) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail12
            if(playTime < 192 && playTime > 191.9 && g.player.hasRail == 12) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail13
            if(playTime < 185.4 && playTime > 185.3 && g.player.hasRail == 13) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail14
            if(playTime < 178.8 && playTime > 178.7 && g.player.hasRail == 14) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail15
            if(playTime < 172.2 && playTime > 172.1 && g.player.hasRail == 15) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            if(playTime < 165.6 && playTime > 165.5) {
                direction = "down2";
            }
            //rail16
            if(playTime < 163.6 && playTime > 163.5 && g.player.hasRail == 16) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail17
            if(playTime < 157 && playTime > 156.9 && g.player.hasRail == 17) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail18
            if(playTime < 150.4 && playTime > 150.3 && g.player.hasRail == 18) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail19
            if(playTime < 143.8 && playTime > 143.7 && g.player.hasRail == 19) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail20
            if(playTime < 137.2 && playTime > 137.1 && g.player.hasRail == 20) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail21
            if(playTime < 130.6 && playTime > 130.5 && g.player.hasRail == 21) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail22
            if(playTime < 124 && playTime > 123.9 && g.player.hasRail == 22) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail23
            if(playTime < 117.4 && playTime > 117.3 && g.player.hasRail == 23) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            if(playTime < 112.2 && playTime > 112.1) {
                direction="left";
            }
            //rail24
            if(playTime < 105.6 && playTime > 105.5 && g.player.hasRail == 24) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail25
            if(playTime < 99 && playTime > 98.9 && g.player.hasRail == 25) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail26
            if(playTime < 92.4 && playTime > 92.3 && g.player.hasRail == 26) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail27
            if(playTime < 85.8 && playTime > 85.7 && g.player.hasRail == 27) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail28
            if(playTime < 79.2 && playTime > 79.1 && g.player.hasRail == 28) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail29
            if(playTime < 72.6 && playTime > 72.5 && g.player.hasRail == 29) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail30
            if(playTime < 66 && playTime > 65.9 && g.player.hasRail == 30) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail31
            if(playTime < 59.4 && playTime > 59.3 && g.player.hasRail == 31) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail32
            if(playTime < 52.8 && playTime > 52.7 && g.player.hasRail == 32) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail33
            if(playTime < 46.2 && playTime > 46.1 && g.player.hasRail == 33) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail34
            if(playTime < 39.6 && playTime > 39.5 && g.player.hasRail == 34) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail35
            if(playTime < 33 && playTime > 32.9 && g.player.hasRail == 35) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail36
            if(playTime < 26.4 && playTime > 26.3 && g.player.hasRail == 36) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail37
            if(playTime < 19.8 && playTime > 19.7 && g.player.hasRail == 37) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail38
            if(playTime < 13.2 && playTime > 13.1 && g.player.hasRail == 38) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }
            //rail39
            if(playTime < 6.6 && playTime > 6.5 && g.player.hasRail == 39) {
                direction = "stop";
                g.gameState = g.gameLose;
                playTime = 280;
            }


        }


    }




}
