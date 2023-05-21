import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Enemy extends Entity {

	Panel p;
	KeyHandler keyH;

    private int shotSchedule;

    public Enemy(Panel p, KeyHandler keyH) {
		
		this.p = p;
		this.keyH = keyH;
		
		setDefaultValues();
		getEnemyImage();
		
	}
	
	public void setDefaultValues() {
		
		//enemy
		x = 235 * 4;
		y = 75 * 4;
		//bullets
		projectileSpeed = 2 * 4;
		projectileX = 234 * 4;
		projectileY = 95 * 4;
		projectile = new ArrayList();
		
	}

    //Enemy images
	public void getEnemyImage() {
		
		try {
			
			enemyDef = ImageIO.read(getClass().getResourceAsStream("res/enemy/enemy_default.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

    //BULLETS
	public void shoot() {
		
		Dot bullet = new Dot(projectileX, projectileY, projectileSpeed, 2 * 4, Color.white, p);
		projectile.add(bullet);
		
	}
	
	public void update() {
		
		for(int i = 0; i < projectile.size(); i++) {
			
			Dot b = (Dot)projectile.get(i);
			if(b.getVis() == true) {
				b.move();
			}
			else {
				projectile.remove(i);
			}
			
		}

		if(keyH.slashPress) {
			shoot();
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		//drawing enemy
		BufferedImage image = null;
		
		image = enemyDef;
		
		g2.drawImage(image, x, y, 240, 240, null);
		
		//drawing bullets
		for(int i = 0; i < projectile.size(); i++) {
			
			Dot b = (Dot)projectile.get(i);
			b.draw(g2);
			
		}
		
	}
	
}