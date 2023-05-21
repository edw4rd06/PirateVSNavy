import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Dot extends Entity {
	
	Panel p;
	KeyHandler keyH;

	private double x;
	private double y;
	private double diameter;
	private double radius;
	private Color color;
	private double speed;
	
	private int deflectionY;
	private int speedY;
	static boolean navyWin;
	
	public static int score = 0;
	public static boolean gameOver = false;
	
	//"hitboxes" for player, enemy, sword
	int hitBoxPlayer;
	int hitBoxEnemy;
	int hitBoxSword;

    public Dot(double newX, double newY, double newSpeed, double newDiameter, Color newColor, Panel p) {
		this.x = newX;
		this.y = newY;
		this.speed = newSpeed;
		this.diameter = newDiameter;
		this.radius = newDiameter/2;
		this.color = newColor;
		
		this.p = p;
		
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		
		projectileVis = true;
		
		hitBoxPlayer = 72 * 4;
		hitBoxSword = 95 * 4;
		
		speedY = 0;
		
	}

    public int getProjX() {
		return (int)x;
	}
	
	public int getProjY() {
		return (int)y;
	}
	
	public int getProjSpeed() {
		return (int)speed;
	}
	
	public boolean getVis() {
		return projectileVis;
	}
	
	public void move() {
		
		x -= speed;
		y -= speedY;
		
		if(Player.swordBox == true) {
			if(isCollisionSword()) {
				speed = -speed;
				score++;
				deflectionY = (int)(Math.random()*2);
				if(deflectionY == 0) {
					speedY = 2;
				}
				else if(deflectionY == 1) {
					speedY = -3;
				}
			}
		}

		if(score == 10){
			projectileVis = false;
			navyWin = true;
			p.gameState = 4;
		}
		
		if(isCollisionPlayer()) {
			projectileVis = false;
			navyWin = false;
			p.gameState = 4;
		}
		
		if(y <= 0 || y >= 120*4) {
			projectileVis = false;
		}
		
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius),(int)(y - radius),(int)diameter,(int)diameter);
	}
	
	public boolean isCollisionPlayer() {
		
		if(x - radius <= hitBoxPlayer) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean isCollisionSword() {
		
		if(x - radius == hitBoxSword) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean isCollisionEnemy() {
		
		if(x + radius >= hitBoxEnemy) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}