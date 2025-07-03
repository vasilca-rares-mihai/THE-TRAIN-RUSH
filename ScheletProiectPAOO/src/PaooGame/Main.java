package PaooGame;

import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rares Game");
        Game gamePanel = new Game();
        window.add(gamePanel);
        window.pack();

        window.setVisible(true);
        gamePanel.StartGame();
    }
}