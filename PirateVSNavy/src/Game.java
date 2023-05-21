import javax.swing.JFrame;

public class Game {
    //1600x900
    public static void main (String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Port Protect");
        
        Panel panel = new Panel();
        window.add(panel);
        window.pack();

        window.setLocationRelativeTo(null); //window will be centered on screen
        window.setVisible(true);

        panel.startGameThread();
    }

}
