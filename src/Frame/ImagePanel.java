package Frame;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JComponent {

    private Image image;
    public ImagePanel(Image image){
        this.image = image;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }

}
