import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Navy{
    //pirates and navy are a type of Player

    Panel p;
    KeyHandler keyH;
    //private data fields for coordinates of the player
    private int x = 0;
    private int y = 0;
    private int speed = 8;
    BufferedImage left, right;
    String direction;

    // g.drawLine(30,);

    public Navy(Panel p, KeyHandler keyH, int x, int y, int speed) {
        
        this.p = p;
        this.keyH = keyH;

        this.x = x;
        this.y = y;
        this.speed = speed;

        getNavyImage();
        direction = "right";
    }
    public void getNavyImage() {
        
        try{

            right = ImageIO.read(getClass().getResourceAsStream("res/NavyRight.png"));
            left = ImageIO.read(getClass().getResourceAsStream("res/NavyLeft.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if(keyH.upPress) {
            if(x < 260 && y > 133)
                y-= speed;
            if(x > 260 && y > 30)
                y -= speed;
        }
        else if(keyH.downPress) {
            if(x < 166 && y < 505)
                y += speed;

            if(x > 166 && y < 620)
                y += speed;
        }
        if(keyH.leftPress) {
            direction = "left";
            if(y > 505 && x > 180)
                x -= speed;
            else if(y < 505 && x > 65)
                x -= speed;
            else if(y < 133 && x > 275)
                x -= speed;
            // else if(y > 133 && x > 65)
            //     x -= speed;
            
        }
        else if(keyH.rightPress) {
            direction = "right";
            if(x < p.rightBound - 64)
                x += speed;
        }
    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, p.originalUnit, p.originalUnit);

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
        // System.out.println(x + " , " + y);
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




    public boolean isCollision(Pirate pirate) {
        if(this.x + 64 >= pirate.getX() && this.x <= pirate.getX() + 64 && this.y + 32 >= pirate.getY() && this.y <= pirate.getY() + 32)
            return true;
        return false;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }



}