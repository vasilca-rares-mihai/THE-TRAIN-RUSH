package enviroment;

import PaooGame.Game;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.RadialGradientPaint;

public class Lighting  {
    Game g;
    BufferedImage darknessFilter;
    int r, g3, b = 0;
//functie pentru fog
    public Lighting(Game g, int circleSize) {
        darknessFilter = new BufferedImage(g.screenWidth, g.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 =(Graphics2D)darknessFilter.getGraphics();

        Area screenArea = new Area(new Rectangle2D.Double(0,0,g.screenWidth, g.screenHeight));
        int centerX = g.player.screenX + (g.tileSize)/2;
        int centerY = g.player.screenY + (g.tileSize)/2;
        double x = centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        Area lightArea = new Area(circleShape);
        screenArea.subtract(lightArea);

        Color color[] = new Color[5];
        float fraction[] = new float[5];


        fraction[0] = 0.0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1.0f;


            if(g.player.nivel == 2) {
                r = 255;
                g3 = 255;
                b = 255;
            } else if(g.player.nivel == 3) {
                r = 247;
                g3 = 255;
                b = 170;
        }

        color[0] = new Color(r, g3, b, 0);
        color[1] = new Color(r, g3, b, 63);
        color[2] = new Color(r, g3, b, 127);
        color[3] = new Color(r, g3, b, 191);
        color[4] = new Color(r, g3, b, 249);
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);


        g2.setPaint(gPaint);
        g2.fill(lightArea);

        g2.fill(screenArea);
        g2.dispose();

    }
    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
