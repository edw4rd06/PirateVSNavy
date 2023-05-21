import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPress, downPress, leftPress, rightPress;
    public boolean upPress2, downPress2, leftPress2, rightPress2;
    public boolean contPress, slashPress, enterPress, escPress;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPress = true;
        }
        if(code == KeyEvent.VK_S) {
            downPress = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPress = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPress = true;
        }

        if(code == KeyEvent.VK_UP){
            upPress2 = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPress2 = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPress2 = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPress2 = true;
        }

        if(code == KeyEvent.VK_CONTROL){
            contPress = true;//Navy controls
        }
        if(code == KeyEvent.VK_SLASH){
            slashPress = true;//Pirate controls
        }
        if(code == KeyEvent.VK_ENTER) {
			enterPress = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			escPress = true;
		}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPress = false;
        }
        if(code == KeyEvent.VK_S) {
            downPress = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPress = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPress = false;
        }

        if(code == KeyEvent.VK_UP){
            upPress2 = false;
        }
        if(code == KeyEvent.VK_DOWN){
            downPress2 = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPress2 = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPress2 = false;
        }

        if(code == KeyEvent.VK_CONTROL){
            contPress = false;
        }
        if(code == KeyEvent.VK_SLASH){
            slashPress = false;
        }
        if(code == KeyEvent.VK_ENTER) {
			enterPress = false;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			escPress = false;
		}

    }
    
}
