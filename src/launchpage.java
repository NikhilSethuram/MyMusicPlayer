import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class launchpage implements ActionListener {
    JFrame frame = new JFrame();
    JButton button;
//    JButton mybutton= new JButton("new button");
    launchpage(JButton button){

        this.button=button;
        button.addActionListener(this);
        frame.add(button);

        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
            frame.dispose();
            newwindow mywindow= new  newwindow();
        }
    }
}
class launchpage2 implements ActionListener{
    JFrame frame = new JFrame();
    JButton button;
    //    JButton mybutton= new JButton("new button");
    launchpage2(JButton button){

        this.button=button;
        button.addActionListener(this);
        frame.add(button);

        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
//            frame.dispose();
            newwindow2 mywindow= new  newwindow2();
        }
    }
}
class launchpage3 implements ActionListener{
    JFrame frame = new JFrame();
    JButton button;
    //    JButton mybutton= new JButton("new button");
     launchpage3(JButton button){

        this.button=button;
        button.addActionListener(this);
        frame.add(button);

        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
//            frame.dispose();
            newwindow3 mywindow= new  newwindow3();
        }
    }
}
class launchpage8 implements ActionListener{
    JFrame frame = new JFrame();
    JButton button;
    launchpage8(JButton button) {

        this.button = button;
        button.addActionListener(this);
        frame.add(button);

        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==button){
//            frame.dispose();
                newwindow8 mywindow= new  newwindow8();
            }
        }
}
class launchpage7 implements ActionListener{
    JFrame frame = new JFrame();
    JButton button;
    launchpage7(JButton button) {

        this.button = button;
        button.addActionListener(this);
        frame.add(button);

        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
//            frame.dispose();
            newwindow7 mywindow= new  newwindow7();
        }
    }
}
class launchpage4 implements ActionListener{
    JFrame frame = new JFrame();
    JButton button;
    launchpage4(JButton button) {

        this.button = button;
        button.addActionListener(this);
        frame.add(button);

        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
//            frame.dispose();
            newwindow4 mywindow= new  newwindow4();
        }
    }
}

