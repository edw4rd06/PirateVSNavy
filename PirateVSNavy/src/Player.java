import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {

	Panel p;
	KeyHandler keyH;
	
	public static boolean swordBox = false;

    public Player(Panel p, KeyHandler keyH) {
		
		this.p = p;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 300;
		projectileSpeed = 20;
		
	}

	public void getPlayerImage() {
		
		try {
			
			playerDef = ImageIO.read(getClass().getResourceAsStream("res/player/player_default.png"));
			
			unsheathe1 = ImageIO.read(getClass().getResourceAsStream("res/player/player_unsheathe_1.png"));
			unsheathe2 = ImageIO.read(getClass().getResourceAsStream("res/player/player_unsheathe_2.png"));
			unsheathe3 = ImageIO.read(getClass().getResourceAsStream("res/player/player_unsheathe_3.png"));
			parry1 = ImageIO.read(getClass().getResourceAsStream("res/player/player_parry_1.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public void parrying() {
		
		spriteCounter++;
		
		if(spriteCounter <= 2) {
			spriteNum = 1;
		}
		if(spriteCounter > 2 && spriteCounter <= 4) {
			spriteNum = 2;
		}
		if(spriteCounter > 4 && spriteCounter <= 8) {
			spriteNum = 3;
		}
		if(spriteCounter > 8 && spriteCounter <= 16) {
			spriteNum = 4;
			swordBox = true;
		}
		if(spriteCounter > 16 && spriteCounter <= 18) {
			spriteNum = 3;
			swordBox = false;
		}
		if(spriteCounter > 18 && spriteCounter <= 20) {
			spriteNum = 2;
		}
		if(spriteCounter > 20 && spriteCounter <= 24) {
			spriteNum = 1;
			spriteCounter = 0;
			parry = false;
		}
		
	}

	public void update() {
		
		if(keyH.contPress) {
			parry = true;
		}
		if(parry == true) {
			parrying();
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		if(parry == false) {
			image = playerDef;
			g2.drawImage(image, x, y, 480, 240, null);
		}
		
		if(parry == true) {
			if(spriteNum == 1) {
				image = unsheathe1;
			}
			if(spriteNum == 2) {
				image = unsheathe2;
			}
			if(spriteNum == 3) {
				image = unsheathe3;
			}
			if(spriteNum == 4) {
				image = parry1;
			}
			g2.drawImage(image, x, y, 480, 240, null);
		}
		
	}
	
}