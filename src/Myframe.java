import javax.swing.*;
import java.awt.*;

public class Myframe extends JFrame {
    Myframe(){
         this.setTitle("MUSIC PLAYER ");
        this.setResizable(false); //prevent frame from being resized
        this.setSize(500,5005); //sets x dimension y dimension
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        ImageIcon image= new ImageIcon("image12.png");
        this.setIconImage(image.getImage());//changes icon
    }
}
