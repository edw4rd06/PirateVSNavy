import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Panel extends JPanel implements Runnable{
    
    //Screen settings
    public final int originalX = 64; //ship size: 32x32
    public final int originalY = 32;
    final int scale = 2;

    //battle settings
    public static int gameState = 0;
	public final int bScale = 4;

    final int unitX = originalX * scale;
    final int unitY = originalY * scale;
    final int maxScreenX = 1280;
	final int maxScreenY = 720;
    BufferedImage map;
    BufferedImage battleImage;
    BufferedImage openScreen;
    int upperBound = 30;
    int lowerBound = 670;
    int rightBound = 1170;
    int leftBound = 60;

    private boolean collide;
    private int counter = 0;

    public void getMapImage() {
        
        try{

            map = ImageIO.read(getClass().getResourceAsStream("res/map.png"));
            battleImage = ImageIO.read(getClass().getResourceAsStream("res/PiratevNavybattlebackdrop.png"));
            openScreen = ImageIO.read(getClass().getResourceAsStream("res/open.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    //FPS
    int FPS = 60;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Navy navy = new Navy(this, keyH, 600, 100, 3);
    Pirate pirate = new Pirate(this, keyH, 600, 600, 3);

    Player player = new Player(this, keyH);
	Enemy enemy = new Enemy(this, keyH);
    
    public Panel() {

        this.setPreferredSize(new Dimension(maxScreenX, maxScreenY));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        getMapImage();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //0.01666 s
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update(); //updates information
                repaint(); //redraws with new info
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }
    public void update() {

        if(gameState == 1) {
            navy.update(); 
            pirate.update();
        }
        
        if(gameState == 3) {
            player.update();
            enemy.update();
        }
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        
        // g2.getImage("pirate ship.png", x, y, null);
        // g2.getImage("navy ship.png", x, y, null);

        if(keyH.escPress == true) {
            System.exit(0);
        }

        if(gameState == 0) {
			
			g2.setColor(Color.black);
			g2.drawImage(openScreen, 0, 0, 1280, 720, null);
			
			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 10 * bScale));
			g2.drawString("Press Enter to Start", 220 * bScale, 45 * bScale);
            g2.drawString("Press Escape to Exit", 220 * bScale, 60 * bScale);
			
			if(keyH.enterPress == true) {
				gameState = 1;
			}
			
		}
        if(gameState == 1) {
            g2.drawImage(map, 0, 0, 1280, 720, null);
        
            navy.draw(g2);
            pirate.draw(g2);

            if(navy.isCollision(pirate)) {
                collide = true;
            }
        
            if(collide) {
                gameState = 2;
            }

            g2.dispose(); 
        }
        if(gameState == 2) {

            player.draw(g2);
            enemy.draw(g2);

            g2.setColor(Color.white);
			g2.setFont(new Font("SansSerif", Font.PLAIN, 20));

            g2.drawString("Navy", 200, 200);
            g2.drawString("Pirate", 1100, 200);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 40));
            counter++;
            if(counter < 60)
                g2.drawString("3", 600, 320);
            else if(counter < 120)
                g2.drawString("2", 600, 320);
            else if(counter < 180)
                g2.drawString("1", 600, 320);
            else {
                gameState = 3;
                counter = 0;
            }

            g2.dispose();

        }
		if(gameState == 3) {
			
            g2.drawImage(battleImage, 0, 0, 1280, 720, null);
            player.draw(g2);
            enemy.draw(g2);
			
			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
            g2.drawString("Deflects: " + Dot.score, 60, 60);
			
			g2.dispose();
			
		}
		if(gameState == 4) {

            if(Dot.navyWin){
                g2.setColor(Color.black);
                g2.fillRect(0, 0, 1280, 720);
                
                g2.setColor(Color.white);
                g2.setFont(new Font("SansSerif", Font.BOLD, 12 * bScale));
                g2.drawString("Deflects: " + Dot.score, 20 * bScale, 20 * bScale);
                g2.drawString("Navy Wins!", 120 * bScale, 80 * bScale);
                g2.drawString("Press [Enter] to Try Again", 100 * bScale, 120 * bScale);
                g2.drawString("Press [Escape] to Exit", 100 * bScale, 135 * bScale);
            }
			else {
                g2.setColor(Color.black);
                g2.fillRect(0, 0, 1280, 720);
                
                g2.setColor(Color.white);
                g2.setFont(new Font("SansSerif", Font.BOLD, 12 * bScale));
                g2.drawString("Deflects: " + Dot.score, 20 * bScale, 20 * bScale);
                g2.drawString("Pirate Wins!", 120 * bScale, 80 * bScale);
                g2.drawString("Press [Enter] to Try Again", 100 * bScale, 120 * bScale);
                g2.drawString("Press [Escape] to Exit", 100 * bScale, 135 * bScale);
            }
			
			if(keyH.enterPress == true) {
				Dot.score = 0;
				gameState = 1;
                collide = false;
                navy.setPos(600, 100);
                pirate.setPos(600, 600);
			}
			
		}
    }
    
    
    public void flash() {
        
    }
    
    

}