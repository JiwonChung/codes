package GUI;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.lang.Object;
import java.awt.*;

/**
 * LastChrismas
 */
public class LastChrismas extends JFrame {

    private static final long serialVersionUID = 1L;



    public LastChrismas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);

        setContentPane(new I_gave_you());
        setVisible(true);
    }

    class I_gave_you extends JPanel {

        private static final long serialVersionUID = 1L;

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        ImageIcon imageIcon = new ImageIcon("background.png");
        BufferedImage backgoundImage = ImageLoader
        
        public I_gave_you() {
            
        }

        @Override
        protected void paintComponent(Graphics g) {
            
            
        }

    }



    
    public static void main(String[] args) {
        
        new LastChrismas();
    }
}