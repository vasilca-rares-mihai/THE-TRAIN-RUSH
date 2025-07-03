package PaooGame.Tiles;
import PaooGame.Game;
import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends Entity {
    int size = 80;

    Game g;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(Game g) {
        super(g);
        this.g=g;
        tile=new Tile[80];
        mapTileNum = new int [g.maxWorldCol][g.maxWorldRow];
        getTileImage();
        loadMap("/maps/map1.txt");


    }
    // metoda update a hartilor
    public void Update() {
        if(g.player.nivel == 1) {
            loadMap("/maps/map1.txt");
        }
        if(g.player.nivel == 2) {
            loadMap("/maps/map2.txt");
        }
        if(g.player.nivel == 3) {
            loadMap("/maps/map3.txt");
        }
    }
    //metoda care extrage imagini.
    public void getTileImage() {
        try {

            tile[10] = new Tile();
            tile[10].image= ImageIO.read(getClass().getResourceAsStream("/textures/map1/grass.jpg"));

            tile[11] = new Tile();
            tile[11].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/stone.jpg"));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/tree.jpg"));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/rail1.jpg"));

            tile[14] = new Tile();
            tile[14].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/rail2.jpg"));

            tile[15] = new Tile();
            tile[15].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/rail3.jpg"));

            tile[16] = new Tile();
            tile[16].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/rail4.jpg"));

            tile[17] = new Tile();
            tile[17].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/rail5.jpg"));

            tile[18] = new Tile();
            tile[18].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/rail6.jpg"));

            tile[19] = new Tile();
            tile[19].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/1house4.jpg"));
            tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/1house3.jpg"));
            tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/1house2.jpg"));
            tile[21].collision = true;

            tile[22] = new Tile();
            tile[22].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/1house1.jpg"));
            tile[22].collision = true;


            tile[23] = new Tile();
            tile[23].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/Irail1.jpg"));

            tile[24] = new Tile();
            tile[24].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/Irail2.jpg"));

            tile[25] = new Tile();
            tile[25].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/Irail3.jpg"));

            tile[26] = new Tile();
            tile[26].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/Irail4.jpg"));

            tile[27] = new Tile();
            tile[27].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/Irail5.jpg"));

            tile[28] = new Tile();
            tile[28].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/Irail6.jpg"));

            tile[30] = new Tile();
            tile[30].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/snow.jpg"));

            tile[31] = new Tile();
            tile[31].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/Irail21.jpg"));

            tile[32] = new Tile();
            tile[32].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/Irail22.jpg"));

            tile[33] = new Tile();
            tile[33].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/Irail23.jpg"));

            tile[34] = new Tile();
            tile[34].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/Irail24.jpg"));

            tile[35] = new Tile();
            tile[35].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/Irail25.jpg"));

            tile[36] = new Tile();
            tile[36].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/Irail26.jpg"));

            tile[40] = new Tile();
            tile[40].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/sand.jpg"));

            tile[41] = new Tile();
            tile[41].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/Irail31.jpg"));

            tile[42] = new Tile();
            tile[42].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/Irail32.jpg"));

            tile[43] = new Tile();
            tile[43].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/Irail33.jpg"));

            tile[44] = new Tile();
            tile[44].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/Irail34.jpg"));

            tile[45] = new Tile();
            tile[45].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/Irail35.jpg"));

            tile[46] = new Tile();
            tile[46].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/Irail36.jpg"));

            tile[47] = new Tile();
            tile[47].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/2house1.jpg"));
            tile[47].collision = true;

            tile[48] = new Tile();
            tile[48].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/2house2.jpg"));
            tile[48].collision = true;

            tile[49] = new Tile();
            tile[49].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/2house3.jpg"));
            tile[49].collision = true;

            tile[50] = new Tile();
            tile[50].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/2house4.jpg"));
            tile[50].collision = true;

            tile[51] = new Tile();
            tile[51].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/3house1.jpg"));
            tile[51].collision = true;

            tile[52] = new Tile();
            tile[52].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/3house2.jpg"));
            tile[52].collision = true;

            tile[53] = new Tile();
            tile[53].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/3house3.jpg"));
            tile[53].collision = true;

            tile[54] = new Tile();
            tile[54].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/3house4.jpg"));
            tile[54].collision = true;


            tile[55] = new Tile();
            tile[55].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/grass1.jpg"));
            tile[55].collision = true;

            tile[56] = new Tile();
            tile[56].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/grass2.jpg"));
            tile[56].collision = true;

            tile[57] = new Tile();
            tile[57].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/grass3.jpg"));
            tile[57].collision = true;

            tile[58] = new Tile();
            tile[58].image=ImageIO.read(getClass().getResourceAsStream("/textures/map1/grass4.jpg"));
            tile[58].collision = true;

            tile[59] = new Tile();
            tile[59].image=ImageIO.read(getClass().getResourceAsStream("/textures/transparent.png"));
            tile[59].collision = true;


            tile[60] = new Tile();
            tile[60].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/snow1.jpg"));
            tile[60].collision = true;

            tile[61] = new Tile();
            tile[61].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/snow2.jpg"));
            tile[61].collision = true;

            tile[62] = new Tile();
            tile[62].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/snow3.jpg"));
            tile[62].collision = true;

            tile[63] = new Tile();
            tile[63].image=ImageIO.read(getClass().getResourceAsStream("/textures/map2/snow4.jpg"));
            tile[63].collision = true;

            tile[64] = new Tile();
            tile[64].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/sand1.jpg"));
            tile[64].collision = true;

            tile[65] = new Tile();
            tile[65].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/sand2.jpg"));
            tile[65].collision = true;

            tile[66] = new Tile();
            tile[66].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/sand3.jpg"));
            tile[66].collision = true;

            tile[67] = new Tile();
            tile[67].image=ImageIO.read(getClass().getResourceAsStream("/textures/map3/sand4.jpg"));
            tile[67].collision = true;





        }catch(IOException e) {
            e.printStackTrace();
        }

    }
    //metoda de incarcare a hartii
    public void loadMap(String filePath) {
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < g.maxWorldCol && row<g.maxWorldRow) {
                String line = br.readLine();

                while(col < g.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == g.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e) {

        }
    }
//metoda de desenare a hartii
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < g.maxWorldCol && worldRow < g.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            if (tileNum >= 0 && tileNum < tile.length) {
                Tile currentTile = tile[tileNum];
                if (currentTile != null) {
                    int worldX = worldCol * g.tileSize;
                    int worldY = worldRow * g.tileSize;
                    int screenX = (int) (worldX - g.player.worldX + g.player.screenX);
                    int screenY = (int) (worldY - g.player.worldY + g.player.screenY);

                    if (worldX + g.tileSize > g.player.worldX - g.player.screenX &&
                            worldX - g.tileSize < g.player.worldX + g.player.screenX &&
                            worldY + g.tileSize > g.player.worldY - g.player.screenY &&
                            worldY - g.tileSize < g.player.worldY + g.player.screenY) {
                        g2.drawImage(currentTile.image, screenX, screenY, g.tileSize, g.tileSize, null);
                    }
                }
            }

            worldCol++;
            if (worldCol == g.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }


}
