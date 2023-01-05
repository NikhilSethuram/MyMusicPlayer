
public class Playlist implements QueueADT<Song>{
    private SongNode first;
    private SongNode last;
    private int numSongs;

    public Playlist(){
        numSongs=0;
        first=null;
        last=null;
    }

    public void enqueue(Song element){
        SongNode newnode= new SongNode(element);
        //case 1 //empty list
        if (isEmpty()){
            first= newnode;
            last=newnode;
        }else { //case 2 //non empty list
            last.setNext(newnode);
            last = newnode;
            last.setNext(null);
        }
        numSongs++;
    }

    public Song dequeue(){
        Song toRemove=null;
        if (isEmpty()){
            return null;
        }
        if (size()==1){
            toRemove=first.getSong();
            first=null;
            last=null;
            numSongs--;
            return toRemove;
        }
        else{
            toRemove= first.getSong();
            first=first.getNext();
            numSongs--;
        }
        return toRemove;
    }

    public Song peek(){
        if (isEmpty()){
            return null;
        }
        return first.getSong();
    }

    public boolean isEmpty(){
        return (size()==0 && last==null && first==null);
    }

    public int size(){
        return this.numSongs;
    }

    @Override
    public String toString(){
        String s="";
        SongNode duplicate = first; //to store its value
        while (first!=null){
            s+=first.getSong().toString()+"\n";
            first=first.getNext();
        }
        first=duplicate;//bring it back to its original position
        return s;
    }
}
