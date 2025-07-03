package entity;

import PaooGame.Game;

import java.util.Random;

public class NPC_man extends Entity{
    public NPC_man(Game g) {
        super(g);

        direction = "down";
        speed = 1;
        getNPCImage();
    }
// metoda care stabileste miscarile NPC urilor
    public void setAction() {
        actionLockCounter ++;
        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
