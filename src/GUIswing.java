import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GUIswing {
     static Playlist playlist=new Playlist(); //The current playlist of Songs
    static Playlist duplicate=new Playlist();
    private static boolean filterPlay; //Whether the current playback mode should be filtered by artist; false by default
    private static String filterArtist; //The artist to play if filterPlay is true; should be null otherwise
    JButton button;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;

    public GUIswing(){
        filterPlay = false;
        filterArtist = null;
    }

    public static void clear() {
        while (!playlist.isEmpty()) {
            if (playlist.peek().isPlaying()) {
                playlist.peek().stop(); //stop if playing
            }
            playlist.dequeue();
        }
    }

    public static void loadPlaylist(File file) throws FileNotFoundException {
        Scanner scnr = null; //creating scanner instance
        if (!file.canRead()) { //checking for readable file
            throw new FileNotFoundException(" file not found ");
        }
        try {
            scnr = new Scanner(file); //reading file
            while (scnr.hasNextLine()) { //checking file if it has more content
                try {
                    String s = scnr.nextLine();
                    String[] contents = s.split(",", 3); //splitting artist, title ,filepath
                    String title = contents[0];
                    String artist = contents[1];
                    String filepath = "audio" + File.separator + contents[2];
                    System.out.print("Loading " + "\""+title+"\"" + " ");
                    loadOneSong(title, artist, filepath);
                    System.out.println();
                } catch (IllegalArgumentException e) {
                    System.out.println("X");
                }
            }
        } finally {
            if (scnr != null) {
                scnr.close(); //closing scnr
            }
        }
    }

    public static void loadOneSong(String title, String artist, String filepath) throws IllegalArgumentException {
        Song songtoadd = new Song(title, artist, filepath);
        playlist.enqueue(songtoadd);
    }

    public static String printPlaylist() {
        String s=playlist.toString();
        return s;
    }

    public static void playNextSong() throws IllegalStateException {
        if (playlist == null || playlist.isEmpty()) {
            throw new IllegalStateException("playlist null or empty");
        }
            if (playlist.peek().isPlaying()) {
                playlist.peek().stop();
                playlist.dequeue();
            }
            if (playlist.isEmpty()){
                throw  new IllegalStateException("empty");
            }
            playlist.peek().play();

    }



    public static void main(String[] args) {
        ImageIcon image = new ImageIcon("image12.jpg");
        Border border =BorderFactory.createLineBorder(Color.black,3);


        JButton button = new JButton();
        button.setBounds(100,300,300,100);
        button.setText("enqueue a new song to end of playlist");
        launchpage l1= new launchpage(button);


        JButton button2 = new JButton();
        button2.setBounds(750,300,300,100);
        button2.setText("load a new playlist from given file");
        launchpage2 l2 = new launchpage2(button2);


        JButton button3 = new JButton();
        button3.setBounds(100,450,300,100);
        button3.setText("list all songs in the current playlist");
        launchpage3 l3 = new launchpage3(button3);


        JButton button4 = new JButton();
        button4.setBounds(750,450,300,100);
        button4.setText("clear the playlist");
        launchpage4 l4 = new launchpage4(button4);

        JButton button7 = new JButton();
        button7.setBounds(100,600,300,100);
        button7.setText("play next song");
        launchpage7 l7 = new launchpage7(button7);

        JButton button8 = new JButton();
        button8.setBounds(750,600,300,100);
        button8.setText("stop playing");
        launchpage8 l8 = new launchpage8(button8);




        JLabel label =new JLabel();
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0X00FF00));
        label.setOpaque(true);
        label.setBackground(Color.GRAY);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(450,0,250,250);





        Myframe myframe= new Myframe();
        myframe.setVisible(true);
        myframe.setBackground(Color.GRAY);
        myframe.add(label);
        myframe.setLayout(null);
        myframe.setSize(1200,1200);
        myframe.add(button);
        myframe.add(button3);
        myframe.add(button7);
        myframe.add(button2);
        myframe.add(button4);
        myframe.add(button8);

    }

}
