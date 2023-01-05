import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class newwindow implements ActionListener {
     static String userinput1;
    static String userinput2;
   static String userinput3;
    JFrame frame= new JFrame();
    JLabel label= new JLabel("hello");

    JTextField textField;
    JTextField textField2;
    JButton button1;
    JTextField textField3;
    JButton button2;
    JButton button3;
    JButton button4;
    newwindow(){
        label.setBounds(0,0,100,50);
        label.setFont(new Font(null,Font.PLAIN,25));
        button1=new JButton("filepath");
        button2=new JButton("Title");
        button3=new JButton("Artist");
        button4=new JButton("Submit");
        textField= new JTextField();
        textField2= new JTextField();
        textField3= new JTextField();
//        textField.setPreferredSize(new Dimension(250,40));
//        textField2.setPreferredSize(new Dimension(250,40));
//        textField3.setPreferredSize(new Dimension(250,40));
        textField.setBounds(100,200,200,50);
        textField2.setBounds(400,200,200,50);
        textField3.setBounds(700,200,200,50);

        button1.setBounds(100,300,200,50);
        button2.setBounds(400,300,200,50);
        button3.setBounds(700,300,200,50);
        button4.setBounds(450,500,200,50);

        frame.add(textField);
        frame.add(textField2);
        frame.add(textField3);
        frame.add(label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        button3.addActionListener(this);
        button2.addActionListener(this);
        button1.addActionListener(this);
        button4.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,1200);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1){
            userinput1=textField.getText();
            userinput1=userinput1.trim();
            button1.setEnabled(false);
            textField.setEditable(false);
            System.out.println(userinput1);
        }
        if(e.getSource()==button2){
            userinput2=textField2.getText();
            userinput2=userinput2.trim();
            button2.setEnabled(false);
            textField2.setEditable(false);
            System.out.println(userinput2);
        }
        if(e.getSource()==button3){
            userinput3=textField3.getText();
            userinput3=userinput3.trim();
            button3.setEnabled(false);
            textField3.setEditable(false);
            System.out.println(userinput3);
        }
        if (e.getSource()==button4){
            try {
                Song songtoAdd = new Song(newwindow.userinput3,newwindow.userinput2,newwindow.userinput1); //done
                GUIswing.playlist.enqueue(songtoAdd);
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                System.out.println("unable to load song");
            }finally {
                button4.setEnabled(false);
            }
        }
    }
}
class newwindow2 implements ActionListener{
    JFrame frame= new JFrame();
    JLabel label= new JLabel("hello");

    JTextField textField;
    JButton button;
    JButton button2;

public newwindow2(){
    label.setBounds(0,0,100,50);
    label.setFont(new Font(null,Font.PLAIN,25));
    button= new JButton("Select File");
    button2=new JButton("Submit");
    button.addActionListener(this);
    button.setBounds(100,300,200,50);
    button2.setBounds(400,300,200,50);
    button2.addActionListener(this);

    frame.add(label);
    frame.add(button);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200,1200);
    frame.setLayout(null);
    frame.setVisible(true);


}

    public void actionPerformed(ActionEvent e) {
    File file=null;
        if (e.getSource()==button){
            JFileChooser fileChooser= new JFileChooser();
            int response=fileChooser.showOpenDialog(null); //select file to open //0 if open //1 if any else
            if (response==JFileChooser.APPROVE_OPTION){
                 file= new File(fileChooser.getSelectedFile().getName());
            }
            try {
                GUIswing.loadPlaylist(file);

            } catch (FileNotFoundException e2) {
                System.out.println("unable to load file");
            }finally {
                button.setEnabled(false);
            }
        }

    }
}
class newwindow3 {

    JFrame frame= new JFrame();
    JLabel label= new JLabel("hello");
    JLabel label2;
    String output;


    public newwindow3(){
        label.setBounds(0,0,100,50);
        label.setFont(new Font(null,Font.PLAIN,25));
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,1200);
        frame.setLayout(null);
        frame.setVisible(true);

        if (GUIswing.playlist.isEmpty()){
            output="empty";
        }else {
            output=GUIswing.printPlaylist();
        }
        label2=new JLabel(output);
        label2.setFont(new Font(null,Font.PLAIN,15));
        label2.setBounds(300,300,900,90);
        frame.add(label2);
    }

}
class newwindow8{
    JLabel label= new JLabel("THANKYOU");
    JFrame frame= new JFrame();

    public newwindow8(){

        label.setBounds(300,300,1000,50);
        label.setFont(new Font(null,Font.PLAIN,50));
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,1200);
        frame.setLayout(null);
        frame.setVisible(true);

        GUIswing.playlist.peek().stop();
        GUIswing.clear();
    }
}
class newwindow7 {
    JLabel label ;
    JFrame frame = new JFrame();

    public newwindow7() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLayout(null);
        frame.setVisible(true);
        if (GUIswing.playlist.peek() == null) {
            label=new JLabel("no songs left to play");
            label.setBounds(300, 300, 1000, 50);
            label.setFont(new Font(null, Font.PLAIN, 25));
            frame.add(label);
        } else {
            try {
                GUIswing.playNextSong();
                label=new JLabel("WEAR EARPHONES FOR BETTER EXPERIENCE");
                label.setBounds(300, 300, 1000, 50);
                label.setFont(new Font(null, Font.PLAIN, 25));
                frame.add(label);
            } catch (IllegalStateException e) {
                label=new JLabel("no songs left to play");
                label.setBounds(300, 300, 1000, 50);
                label.setFont(new Font(null, Font.PLAIN, 25));
                frame.add(label);
            }
        }
    }
}

    class newwindow4 {
        JLabel label=new JLabel("PLAYLIST HAS BEEN CLEARED");
        JFrame frame = new JFrame();

        public newwindow4() {

            label.setBounds(300,300,900,50);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 1200);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.add(label);
            GUIswing.clear();


            }
        }






