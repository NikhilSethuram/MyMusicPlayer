
import java.io.IOException;
public class Song {
    private String title; //The title of this song
    private String artist; //The artist of this song
    private int duration; //The duration of this song in number of seconds
    private AudioUtility audioClip; //This song's AudioUtility interface to javax.sound.sampled

    public Song(String title, String artist, String filepath) throws IllegalArgumentException{
        try{
            audioClip=new AudioUtility(filepath);
        }catch (IOException e){
            System.out.println("here");
            System.out.println(filepath);
            throw new IllegalArgumentException("THE SONG FILE CANNOT BE READ");
        }
        if (title==null){
            this.title=""; //set to empty string if title is null
        }else{
            this.title=title;
        }
        if (artist==null){
            this.artist=""; //set to empty string if artist is null
        }else{
            this.artist=artist;
        }
        this.duration=audioClip.getClipLength();
    }

    public boolean isPlaying(){
        return audioClip.isRunning();
    }


    public String getTitle(){
        return this.title;
    }

    public String getArtist(){
        return this.artist;
    }

    public void play(){
        audioClip.startClip();
        System.out.println("Playing... "+toString());
    }

    public void stop(){
        audioClip.stopClip();
    }

    @Override
    public String toString(){
        String s= "\"" + this.title+ "\" " + "("+duration/60+":"+duration%60+") "+"by "+getArtist();
        return s.trim();
    }

}
