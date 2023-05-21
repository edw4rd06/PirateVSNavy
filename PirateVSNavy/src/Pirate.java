import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pirate{
    Panel p;
    KeyHandler keyH;
    //private data fields for coordinates of the player
    private int x = 0;
    private int y = 0;
    private int speed = 8;
    BufferedImage left, right;
    String direction;

    //"import" the images for pirate ship and navy ships

    public Pirate(Panel p, KeyHandler keyH, int x, int y, int speed) {
        
        this.p = p;
        this.keyH = keyH;

        this.x = x;
        this.y = y;
        this.speed = speed;

        getPirateImage();
        direction = "right";
    }

    public void getPirateImage() {
        
        try{

            right = ImageIO.read(getClass().getResourceAsStream("res/PirateRight.png"));
            left = ImageIO.read(getClass().getResourceAsStream("res/PirateLeft.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if(keyH.upPress2) {
             if(y > p.upperBound)
                y -= speed;
            
        }
        else if(keyH.downPress2) {
            
            if(x > 166 && y < 620)
                y += speed;
        }
        if(keyH.leftPress2) {
            direction = "left";
            //if(x > p.leftBound)
            //    x -= speed;

            if(y > 505 && x > 180)
                x -= speed;
            if(y < 505 && x > 65)
                x -= speed;
        }
        else if(keyH.rightPress2) {
            direction = "right";
            if(x < p.rightBound - 64)
                x += speed;
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch(direction) {
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }

        g2.drawImage(image, x, y, p.unitX, p.unitY, null);
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }



    





}



