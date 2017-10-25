import java.util.LinkedList;

public class Bucket implements BucketInterface{

    private LinkedList<MusicItem> content;
    
    public Bucket(){
        content = new LinkedList<MusicItem>();
    }
    
    public void addItem(MusicItem itemToAdd){
        if(size() == 0){
            content.add(itemToAdd);
        }else{
            for(int i = 0 ; i < size(); ++i){
                MusicItem comparedItem = content.get(i);
                if(comparedItem.title.compareTo(itemToAdd.title) > 0){
                    content.add(i, itemToAdd);
                    break;
                }
            }
            content.add(itemToAdd);                
        }
    }
    
    public LinkedList<MusicItem> getItems(){
        return content;
    }
    
    public int size(){
        return content.size();
    }
}
