import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
	
	//Entity parameters
	public int x, y;
	public int projectileSpeed;
	public int projectileX, projectileY;
	public int reactionTime;
	boolean projectileVis;
	static ArrayList projectile;
	boolean parry = false;
	
	//Images for entities
	public BufferedImage playerDef;
	public BufferedImage unsheathe1, unsheathe2, unsheathe3;
	public BufferedImage parry1;
	public BufferedImage enemyDef;
	
	//Sprite/Object/Entity Animation Frames
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
}
