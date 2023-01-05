
public class SongNode {
    private Song song; //The Song object in this node
    private SongNode next ;//The next SongNode in this queue


    public SongNode(Song data) throws IllegalArgumentException{
        if (data==null){
            throw new IllegalArgumentException("data cannot be null");
        }
        this.song=data;
        this.next=null;
    }

    public SongNode(Song data, SongNode next) throws IllegalArgumentException{
        if (data==null){
            throw new IllegalArgumentException("data cannot be null");
        }
        this.song=data;
        this.next=next;
    }

    public Song getSong(){
        return this.song;
    }


    public SongNode getNext(){
        return this.next;
    }

    public void setNext(SongNode next){
        this.next=next;
    }

}
