package PaooGame;

import entity.Entity;
import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI extends Entity {
    Game g;
    Graphics2D g2;
    Font arial_25;
    private double SCOR = 680;
    double playTime=200;
    public int commandNum = 0;
    public boolean gameIsOver = false;
    public int slotCol = 0;
    public int slotRow = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(Game g) {
        super(g);
        this.g = g;
        arial_25 = new Font("Arial Black", Font.PLAIN, 25);

    }
//metoda in care sunt apelate toate Staturile jocului
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_25);
        g2.setColor(Color.white);

        if(g.gameState == g.titleState) {
            drawTitleScreen();
        } else if(g.gameState == g.loadingState) {
            drawLoadingScreen();
        }


        if(g.gameState == g.playState) {
            g2.setColor(Color.black);

            g2.drawString("NIVEL " + g.player.nivel, g.tileSize*7, 30);
            playTime -=(double)1/60;
            if(playTime > 0) {
                g2.drawString("Time: "+dFormat.format(playTime), 10, 30);
            } else {
                playTime =0;
                g2.drawString("Time: "+dFormat.format(playTime), 10, 30);
            }
            SCOR -=(double)1/60;



        }
        if(g.gameState == g.pauseState) {
            drawPauseScreen(g2);
        }
        if(g.gameState == g.gameOver) {

            drawGameOver();
        }
        if(g.gameState == g.gameOver && gameIsOver == false) {

            g.endGame();
            gameIsOver = true;
        }
        if(g.gameState == g.gameLose) {
            drawGameLose();
        }
        if(g.gameState == g.buttonsState) {
            drawControls();
        }
        if(g.gameState == g.Inventory) {
            drawInvertory();
        }
        if(g.gameState == g.loadGame) {
            drawLoadGame();
        }
    }
// metoda pentru a desena meniul de infrangere
    public void drawGameLose() {
        if(g.player.nivel == 1) {
            playTime = 200;
        }else if(g.player.nivel == 2) {
            playTime = 200;
        } else if(g.player.nivel == 3) {
            playTime = 280;
        }

        g2.setColor(new Color(0,222,255));
        g2.fillRect(0,0,g.screenWidth, g.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "YOU LOSE!";
        int x = g.tileSize*3;
        int y = g.tileSize*3;
        g2.setColor(Color.gray);
        g2.drawString(text, x+7, y+7);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(38F));

        text = "RESTART LEVEL";
        x = g.tileSize*3;
        y += g.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-g.tileSize+50, y);
        }

        text = "QUIT GAME";
        x = g.tileSize*3;
        y += g.tileSize/1.5;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-g.tileSize+50, y);
        }
    }
// metoda care deseneaza meniul de final
    public void drawGameOver() {

        g2.setColor(new Color(0,222,255));
        g2.fillRect(0,0,g.screenWidth, g.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "GAME OVER!";
        int x = g.tileSize*3;
        int y = g.tileSize*3;
        g2.setColor(Color.gray);
        g2.drawString(text, x+7, y+7);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);



        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
        text = "YOUR SCOR: " + (int)SCOR;
        x = g.tileSize*6;
        y = g.tileSize*6;
        g2.setColor(Color.black);
        g2.drawString(text, x, y);
    }
// mretda care desenaza loadscreenul
    private void drawLoadingScreen() {

        g2.setColor(new Color(0,222,255));
        g2.fillRect(0,0,g.screenWidth, g.screenHeight);

        if(g.player.nivel == 2) {
            playTime = 200;
        } else if(g.player.nivel == 3) {
            playTime = 280;
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        String text = "PRESS ''E'' TO CONTINUE";
        int x = g.tileSize*9;
        int y = g.tileSize*8;
        g2.setColor(Color.black);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        text = "NIVEL ";
        text = text + g.player.nivel;
        x = g.tileSize*5;
        y = g.tileSize*3;
        g2.setColor(Color.gray);
        g2.drawString(text, x+7, y+7);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }
//metoda care deseneaza meniul principal al jocului
    private void drawTitleScreen() {
        g2.setColor(new Color(0,222,255));
        g2.fillRect(0,0,g.screenWidth, g.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "THE TRAIN RUSH";
        int x = g.tileSize*(7/4);
        int y = g.tileSize*3;

        g2.setColor(Color.white);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.black);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(38F));

        text = "START GAME";
        x = g.tileSize*3;
        y += g.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-g.tileSize+50, y);
        }

        text = "LOAD GAME";
        x = g.tileSize*3;
        y += g.tileSize/1.5;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-g.tileSize+50, y);
        }

        text = "CONTROLS";
        x = g.tileSize*3;
        y += g.tileSize/1.5;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-g.tileSize+50, y);
        }
        text = "QUIT GAME";
        x = g.tileSize*3;
        y += g.tileSize/1.5;
        g2.drawString(text, x, y);
        if(commandNum == 3) {
            g2.drawString(">", x-g.tileSize+50, y);
        }

    }
// meniul care desenaza meniul de prezentare al controalelor
    public void drawControls() {

        g2.setColor(new Color(0,222,255));
        g2.fillRect(0,0,g.screenWidth, g.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        g2.setColor(Color.black);

        String text = "W - Walk Forward";
        int x = g.tileSize*1;
        int y = g.tileSize*1;
        g2.drawString(text, x, y);
        text = "S - Walk Backward";
        x = g.tileSize*1;
        y += g.tileSize*1/2;
        g2.drawString(text, x, y);
        text = "D - Strafe Right ";
        x = g.tileSize*1;
        y += g.tileSize*1/2;
        g2.drawString(text, x, y);
        text = "A - Strafe Left  ";
        x = g.tileSize*1;
        y += g.tileSize*1/2;
        g2.drawString(text, x, y);
        text = "ENTER - Broke Destructibles  ";
        x = g.tileSize*1;
        y += g.tileSize*1/2;
        g2.drawString(text, x, y);
        text = "E - Accept Button  ";
        x = g.tileSize*1;
        y += g.tileSize*1/2;
        g2.drawString(text, x, y);
        text = "I - Open/Close inventory  ";
        x = g.tileSize*1;
        y += g.tileSize*1/2;
        g2.drawString(text, x, y);
        text = "O - Save game to load later  ";
        x = g.tileSize*1;
        y += g.tileSize*1/2;
        g2.drawString(text, x, y);


        text = "Press E to the menu";
        x = g.tileSize*9;
        y = g.tileSize*8;
        g2.setColor(Color.black);
        g2.drawString(text, x, y);

    }
    // metoda care deseneaza meniul de pauza al jocului
    public void drawPauseScreen(Graphics2D g2) {
        String text = "PAUSED";
        int x = getXforCenteredText(text, g2);
        int y = g.screenHeight/2;
        g2.drawString(text, x, y);

    }

    //metoda care desenaza metniul de inventar
    public void drawInvertory() {
        int frameX = (int)(g.tileSize*3);
        int frameY = g.tileSize*2;
        int frameWidth = (int)(g.tileSize*8.5);
        int frameHeight = (int)(g.tileSize*6.5);
        drawSubWIndow(frameX, frameY, frameWidth, frameHeight);

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        for(int i=0; i<g.player.inventory.size(); i++) {
            g2.drawImage(g.player.inventory.get(i).image, slotX, slotY,160, 160, null);
            slotX+=2*g.tileSize;
        }

        int cursorX = slotXstart + (g.tileSize * slotCol);
        int cursorY = slotYstart + (g.tileSize * slotRow);
        int cursorWidth = g.tileSize;
        int cursorHeight = g.tileSize;

        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX, cursorY, cursorWidth*2, cursorHeight*2, 10, 10);
        if(cursorX == 260) {
            g2.setColor(new Color(0, 222, 255));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            String text = "WOOD";
            int x = (int) (g.tileSize * 3.2);
            int y = g.tileSize * 5;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString("WOOD = " + g.player.hasWood, x, y + g.tileSize);
            g2.drawString("Ai colectat busteni. Du-te la cel mai apropiat workbench", x, (int) (y + 1.5 * g.tileSize));
            g2.drawString("pentru a crafta sine de tren.", x, (int) (y + 1.8 * g.tileSize));
        } else  if(cursorX == 420) {
            g2.setColor(new Color(0,222,255));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
            String text = "STONE";
            int x = (int)(g.tileSize*3.2);
            int y = g.tileSize*5;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString("STONE = " + g.player.hasStone, x, y+g.tileSize);
            g2.drawString("Ai colectat piatra. Du-te la cel mai apropiat workbench", x, (int)(y+1.5*g.tileSize));
            g2.drawString("pentru a crafta sine de tren.", x, (int)(y+1.8*g.tileSize));
        } else  if(cursorX == 580) {
            g2.setColor(new Color(0,222,255));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
            String text = "STAR";
            int x = (int)(g.tileSize*3.2);
            int y = g.tileSize*5;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString("STAR = " + g.player.hasStars, x, y+g.tileSize);
            g2.drawString("Ai colectat o stea. Du-te la cea mai apropiata persoana", x, (int)(y+1.5*g.tileSize));
            g2.drawString("pentru a da la schimb steaua pentru o sina.", x, (int)(y+1.8*g.tileSize));
        } else  if(cursorX == 740) {
            g2.setColor(new Color(0,222,255));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
            String text = "RAILURI";
            int x = (int)(g.tileSize*3.2);
            int y = g.tileSize*5;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString("RAILURI = " + g.player.hasRail, x, y+g.tileSize);
            g2.drawString("Ai craftat sine de tren din lemn si piatra. Continua", x, (int)(y+1.5*g.tileSize));
            g2.drawString("munca si nu lasa trenul sa deraiază.", x, (int)(y+1.8*g.tileSize));
        }

    }
// metoda care deseneaza stare de load game
    public void drawLoadGame() {
        /*
        g.getDateDB();
        g.player.restart();
        g.gameState = g.playState;
        g.player.nivel = g.niv;
        g.tileM.Update();
        if(g.player.nivel == 1) {

        } else if(g.player.nivel == 2) {
            g.player.nivel--;
            g.player.UpdateLvl2();
        } else if(g.player.nivel == 3) {
            g.player.nivel--;
            g.player.UpdateLvl3();
        }
        for(int i=2; i<=g.rail; i++) {
            g.player.hasRail = i;
            g.player.caleFerata();
        }
        g.player.hasWood = g.lemn;
        g.player.hasStone = g.piatra;
        g.player.hasStars = g.stea;
        g.npcTren[0].worldX = g.iX;
        g.npcTren[0].worldY = g.iY;
*/

        g.getDateDB();
        g.player.restart();
        g.gameState = g.playState;
        g.player.nivel = g.niv;
        System.out.println(g.player.nivel);
        g.tileM.Update();
        g.score = g.sc;
        if(g.player.nivel == 1) {
            g.player.UpdateLvl1();

        } else if(g.player.nivel == 2) {
            g.player.nivel--;
            g.player.UpdateLvl2();
        } else if(g.player.nivel == 3) {
            g.player.nivel--;
            g.player.UpdateLvl3();
        }

    }

// metoda care gaseste centrul ecranului
    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = g.screenWidth/2 - length/2;
        return x;
    }
//metoda folosita pentru inventar, deseneaza patratele care incercuiesc obiectul selectat
    public void drawSubWIndow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }


}