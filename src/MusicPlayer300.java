//////////////// FILE HEADER //////////////////////////
//
// Title:    MusicPlayer300
// Course:   CS 300 Fall 2022
//
// Author:   Nikhil Sethuram Thenmozhi
// Email:    nst@wisc.edu
// Lecturer: Hobbes LeGault
//
/////////////////////////  OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A linked-queue based music player which plays Actual Music Files based on keyboard input in
 * an interactive console method.
 * This music player can load playlists of music or add individual song files to the queue.
 */
public class MusicPlayer300 {
    static Playlist playlist; //The current playlist of Songs
    private boolean filterPlay; //Whether the current playback mode should be filtered by artist; false by default
    private String filterArtist; //The artist to play if filterPlay is true; should be null otherwise

    /**
     * Creates a new MusicPlayer300 with an empty playlist
     */
    public MusicPlayer300() {
        playlist = new Playlist();
        filterPlay = false;
        filterArtist = null;
    }

    /**
     * Stops any song that is playing and clears out the playlist
     */
    public void clear() {
        while (!playlist.isEmpty()) {
            if (playlist.peek().isPlaying()) {
                playlist.peek().stop(); //stop if playing
            }
            playlist.dequeue();
        }
    }

    /**
     * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded.
     * Note that filenames in the provided files do NOT include the audio directory,
     * and will need that added before they are loaded.
     * Print "Loading" and the song's title in quotes before you begin loading a song, and an "X"
     * if the load was unsuccessful for any reason.
     * @param file the File object to load
     * @throws FileNotFoundException if the playlist file cannot be loaded
     */
    public void loadPlaylist(File file) throws FileNotFoundException {
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

    /**
     * Loads a single song to the end of the playlist given the title, artist, and filepath
     * @param title the title of the song
     * @param artist the artist of this song
     * @param filepath the full relative path to the song file, begins with the "audio" directory for P08
     */
    public void loadOneSong(String title, String artist, String filepath) throws IllegalArgumentException {
        Song songtoadd = new Song(title, artist, filepath);
        playlist.enqueue(songtoadd);
    }

    /**
     * Provides a string representation of all songs in the current playlist
     * @return a string representation of all songs in the current playlist
     */
    public String printPlaylist() {
        String s=playlist.toString();
        return s;
    }

    /**
     * Creates and returns the menu of options for the interactive console program.
     * @return the formatted menu String
     */
    public String getMenu() {
        String s = "Enter one of the following options:\n" +
                "[A <filename>] to enqueue a new song file to the end of this playlist\n" +
                "[F <filename>] to load a new playlist from the given file\n" +
                "[L] to list all songs in the current playlist\n" +
                "[P] to start playing ALL songs in the playlist from the beginning\n" +
                "[P -t <Title>] to play all songs in the playlist starting from <Title>\n" +
                "[P -a <Artist>] to start playing only the songs in the playlist by Artist\n" +
                "[N] to play the next song\n" +
                "[Q] to stop playing music and quit the program";
        return s;
    }

    /**
     * Stops playback of the current song (if one is playing) and advances to the next song in the playlist.
     * @throws IllegalStateException if the playlist is null or empty, or becomes empty at any time during this method
     */
    public void playNextSong() throws IllegalStateException {
        if (playlist == null || playlist.isEmpty()) {
            throw new IllegalStateException("playlist null or empty");
        }
        if (filterPlay) { //check for filter
            if (playlist.peek().isPlaying()) { //if present song is filter song and playing, remove it
                playlist.dequeue();
            }
            while (!playlist.peek().getArtist().equals(filterArtist) ) {
                playlist.dequeue();
                if (playlist.isEmpty()){
                    throw  new IllegalStateException("empty");
                }
            }
            playlist.peek().play();
        } else {
            if (playlist.peek().isPlaying()) {
                playlist.peek().stop();
            }
            playlist.dequeue();
            if (playlist.isEmpty()){
                throw  new IllegalStateException("empty");
            }
            playlist.peek().play();
        }
    }

//    public static void userinput1(){
//        Scanner in = new Scanner(System.in);
//        String userinput = in.nextLine();
//        userinput = userinput.trim();
//        System.out.println("please enter title");
//        String title = in.nextLine();
//        System.out.println("please enter artist");
//        String artist = in.nextLine();
//        userinput = userinput.substring(1, userinput.length()); //to get audio filepath
//        userinput = userinput.trim();
//        try {
//            Song songtoAdd = new Song(title, artist, userinput);
//            playlist.enqueue(songtoAdd);
//        } catch (IllegalArgumentException e) {
//            System.out.println("unable to load song");
//        }
//    }

    /**
     * Interactive method to display the MusicPlayer300 menu and get keyboard input from the user.
     * @param in Scanner variable to get userinput
     */
    public void runMusicPlayer300(Scanner in) {
        boolean runagain=true;
//        in = new Scanner(System.in);
        while(runagain) {
            System.out.println(getMenu());
            System.out.print(">");
            String userinput = in.nextLine();
            userinput = userinput.trim();

            if (userinput.charAt(0) == 'A') {
                System.out.println("please enter title");
                String title = in.nextLine();
                System.out.println("please enter artist");
                String artist = in.nextLine();
                userinput = userinput.substring(1, userinput.length()); //to get audio filepath
                userinput = userinput.trim();
                try {
                    Song songtoAdd = new Song(title, artist, userinput);
                    playlist.enqueue(songtoAdd);
                } catch (IllegalArgumentException e) {
                    System.out.println("unable to load song");
                }
            } else if (userinput.charAt(0) == 'F') {
                userinput = userinput.substring(1, userinput.length()); //to get filename
                userinput = userinput.trim();
                File file = new File(userinput);
                try {
                    loadPlaylist(file);

                } catch (FileNotFoundException e) {
                    System.out.println("unable to load file");
                }
            } else if (userinput.equals("L")) {
                if (playlist.isEmpty()){
                    System.out.println("empty");
                }else {
                    System.out.println(printPlaylist());
                }
            } else if (userinput.equals("P")) {
                filterPlay = false;
                if (playlist.isEmpty()) {
                    System.out.println("no songs left");
                }else {
                    playlist.peek().stop();
                    MusicPlayer300 duplicate = new MusicPlayer300();

                    while (!playlist.isEmpty()) {
                        Song toadd = playlist.dequeue();
                        duplicate.playlist.enqueue(toadd);
                    }
                    while (!duplicate.playlist.isEmpty()) {
                        Song toadd = duplicate.playlist.dequeue();
                        playlist.enqueue(toadd);
                    }
                    playlist.peek().play();
                }
            } else if (userinput.contains("P -t")) {
                userinput=userinput.substring(4, userinput.length());
                while (!playlist.peek().getTitle().equals(userinput) && !(playlist.isEmpty())) {
                    playlist.dequeue();
                }
                if (playlist.isEmpty()) {
                    System.out.println("no such song found");
                } else {
                    playlist.peek().play();
                }
            } else if (userinput.contains("P -a")) {
                userinput = userinput.substring(4, userinput.length());
                userinput = userinput.trim();
                filterPlay = true;
                filterArtist = userinput;
                try {
                    playNextSong();
                }catch (IllegalStateException e){
                    System.out.println("no such song found");
                }
            } else if (userinput.equals("N")) {
                if (playlist.peek()==null){
                    System.out.println("no songs left");
                }else {
                    try {
                        playlist.peek().stop();
                        playlist.dequeue();
                    } catch (IllegalStateException e) {
                        System.out.println("no songs left");
                    }
                    try {
                        playNextSong();
                    } catch (IllegalStateException e) {
                        System.out.println("no songs left");
                    }
                }
            } else if (userinput.equals("Q")) {
                runagain = false;
                clear();
                System.out.println("Goodbye!");
            } else {
                System.out.println("I don't know how to do that.");
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        MusicPlayer300 one = new MusicPlayer300();
        Scanner scnr = new Scanner(System.in);
        one.runMusicPlayer300(scnr);
    }
}
