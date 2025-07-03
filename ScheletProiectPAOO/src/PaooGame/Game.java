package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.CollisionChecker;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileManager;
import PaooGame.object.OBJ_WOOD;
import PaooGame.object.SuperObject;
import entity.Entity;
import entity.Player;
import enviroment.EnviromentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game extends JPanel implements Runnable {

    public final int tileSize = 80;
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 19;
    public final int maxWorldRow = 13;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public final int titleState = 0;

    public final int playState = 1;
    public final int pauseState = 2;
    public final int loadingState = 3;
    public final int gameOver = 4;
    public final int gameLose = 5;
    public final int buttonsState = 6;
    public final int Inventory = 7;
    public final int loadGame = 8;


    public GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    private Graphics g;
    private Tile tile;
    public EnviromentManager eManager = new EnviromentManager(this);

    public SuperObject objTREE[] = new SuperObject[200];
    public SuperObject objSTONE[] = new SuperObject[200];
    public SuperObject railuri[] = new SuperObject[200];
    public Entity tree[] = new Entity[150];
    public Entity stone[] = new Entity[150];
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui =new UI(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Entity npcTren[] = new Entity[2];
    public Entity npcMan[] = new Entity[2];
    public int gameState;
    public double score;
    private Scor scor;

    public Save save;

    int niv, rail, lemn, piatra, stea;
    double iX , iY, sc;
    String dir;


    public Player player = new Player(this, keyH);

//constructor; se formează spațiul de lucru, inițializează variabilele și setările inițiale ale jocului, adaugă ascultătorul de taste și configurează fereastra jocului.
    public Game() {
        score = 680;
        scor = new Scor();
        save = new Save();
        scor.BazaDeDate();
        save.bazaDeDate();
        this.setPreferredSize(new Dimension(1160, 800));

        this.setBackground(new Color(0, 222, 255));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        InitGame();
    }
//apelarea funcțiilor pentru a seta obiectele, NPC-urile, copacii și pietrele din joc; pornește firul de execuție al jocului și setează starea jocului la `titleState`.
    void InitGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setTREE();
        aSetter.setSTONE();
        gameThread = new Thread(this);
        gameThread.start();
        gameState = titleState;

    }
//buclă de joc care rulează un număr specific de cadre pe secundă (frames per second - FPS); actualizează starea jocului și redesează ecranul.
    public void run() {
        long oldTime = System.nanoTime();
        long curentTime;
        final int framesPerSecond = 60;
        final double timeFrame = 1000000000 / framesPerSecond;


        while (runState == true) {
            curentTime = System.nanoTime();
            if ((curentTime - oldTime) > timeFrame) {
                Update();
                repaint();




                oldTime = curentTime;
            }

            try{
                double remainingTime = curentTime - System.nanoTime();
                remainingTime=remainingTime/1000000;

                if(remainingTime<0) {
                    remainingTime=0;
                }

                Thread.sleep((long) remainingTime);

                curentTime+=timeFrame;
            }   catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//metoda este utilizată pentru a începe jocul, inițializând firul de execuție al jocului dacă acesta nu rulează deja.
    public synchronized void StartGame() {

        if (runState == false) {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();

        } else {
            return;
        }
    }
//metoda este utilizată pentru a încheia jocul, oprind firul de execuție al jocului și așteptând să se termine.
    public synchronized void StopGame() {
        if (runState == true) {
            runState = false;
            try {
                gameThread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } else {
            return;
        }
    }
//metoda este responsabilă pentru actualizarea stării jocului, inclusiv actualizarea jucătorului, mediului și NPC-urilor; de asemenea, scade scorul în timp.
    private void Update() {

        if(gameState == playState) {
            player.Update();
            eManager.setup();
            for(int i=0; i<npcTren.length; i++) {
                if(npcTren[i]!=null) {
                    npcTren[i].updateTren();
                }
            }
            for(int i=0; i<npcMan.length; i++) {
                if(npcMan[i]!=null) {
                    npcMan[i].updateMan();
                }
            }
            for(int i=0; i<tree.length; i++) {
                if(tree[i]!=null) {
                    tree[i].updateD();
                }
            }

            for(int i=0; i<stone.length; i++) {
                if(stone[i]!=null) {
                    stone[i].updateD();
                }
            }

            score -=(double)1/60;

        }
        if(gameState == pauseState) {
        }





    }
//metoda este responsabilă pentru desenarea scenei jocului folosind strategia de buffer.
    private void Draw() {
        bs = wnd.GetCanvas().getBufferStrategy();
        if (bs == null) {
            try {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());


        bs.show();
        g.dispose();
    }
//metoda pentru încheierea jocului; afișează un câmp text pentru a introduce numele jucătorului și un buton pentru a salva scorul în baza de date.
    public void endGame() {
        JTextField textField = new JTextField();
        textField.setBounds((int)(6.4 * tileSize), (int)(6.3 * tileSize), 200, 30);

        JButton button = new JButton("Introdu numele");
        button.setBounds((int)(6.4 * tileSize), (int)(6.9 * tileSize), 200, 30);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nume = textField.getText();
                System.out.println("Numele citit: " + nume);
                scor.insertData(nume, (int)score);
                System.out.println("Score saved to database");
                showDatabaseContents();
            }
        });

        this.setLayout(null);
        this.add(textField);
        this.add(button);
        this.revalidate();
        this.repaint();
    }

//metoda afișează conținutul bazei de date într-un tabel nou într-o fereastră separată.
    public void showDatabaseContents() {
        List<String[]> data = Scor.getData();

        String[] columnNames = {"Nume", "Scor"};

        String[][] dataArray = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }

        JTable table = new JTable(dataArray, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JFrame frame = new JFrame("Database Contents");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(panel);
        frame.setVisible(true);
    }
    //metoda obține datele salvate din baza de date și le afișează; de asemenea, actualizează variabilele interne ale jocului cu aceste date.
    public void getDateDB() {
        List<String[]> data = Save.getData();

        if (data.isEmpty()) {
            System.out.println("No data available in the database.");
            return;
        }

        String[] lastEntry = data.get(data.size() - 1);
        System.out.println("Last entry: " + Arrays.toString(lastEntry));

        if (lastEntry.length != 9) {
            System.out.println("Data entry does not have the expected number of columns.");
            return;
        }

        niv = Integer.parseInt(lastEntry[0]);
        rail = Integer.parseInt(lastEntry[1]);
        lemn = Integer.parseInt(lastEntry[2]);
        piatra = Integer.parseInt(lastEntry[3]);
        stea = Integer.parseInt(lastEntry[4]);
        iX = Double.parseDouble(lastEntry[5]);
        iY = Double.parseDouble(lastEntry[6]);
        sc = Double.parseDouble(lastEntry[7]);
        dir = lastEntry[8];

    }
//metoda pentru a desena componenta pe care este atașată; redesează elementele jocului pe baza stării actuale a jocului.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(gameState == titleState) {
            ui.draw(g2);
        }else if(gameState == playState){
            tileM.draw(g2);


            for (int i = 0; i < objTREE.length; i++) {
                if (objTREE[i] != null) {
                    objTREE[i].draw(g2, this);
                }
            }
            for (int i = 0; i < objSTONE.length; i++) {
                if (objSTONE[i] != null) {
                    objSTONE[i].draw(g2, this);
                }
            }
            for (int i = 0; i < railuri.length; i++) {
                if (railuri[i] != null) {
                    railuri[i].draw(g2, this);
                }
            }

            for (int i = 0; i < npcMan.length; i++) {
                if (npcMan[i] != null) {
                    npcMan[i].drawNPC(g2);
                }
            }
            for (int i = 0; i < npcTren.length; i++) {
                if (npcTren[i] != null) {
                    npcTren[i].drawTren(g2);
                }
            }
            for (int i = 0; i < tree.length; i++) {
                if (tree[i] != null) {
                    tree[i].drawD(g2);
                }
            }
            for (int i = 0; i < stone.length; i++) {
                if (stone[i] != null) {
                    stone[i].drawD(g2);
                }
            }


            player.Draw(g2);
            if(player.nivel == 2) {
                if(ui.playTime < 130 && ui.playTime > 100) {
                    eManager.draw(g2);
                }
            }
            if(player.nivel == 3) {
                if(ui.playTime < 275 && ui.playTime > 130) {
                    eManager.draw(g2);
                }
            }
            ui.draw(g2);

        } else {
            ui.draw(g2);
        }
    }

}