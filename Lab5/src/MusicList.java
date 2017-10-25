// Aaron Zhong #67737879
// Manal Rasheed 34763062

import java.util.ArrayList;

public class MusicList implements MusicListInterface{
    
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String PCVW = "PCVW";
    
                                    //paperItemCount, compactMediaItemCount, vinylItemCount, waxCylinderItemCount, totalItemCount
    private int[] arrayOfCounts = {         0       ,            0         ,        0      ,          0          ,       0       };
    
    
    private ArrayList<Bucket> collection = new ArrayList<Bucket>();
    
    public MusicList(){
        /*
         * When constructing object, create 26 empty buckets inside the collection
         */
        int numOfAlaphabets = 26;
        
        for(int i = 0; i < numOfAlaphabets; ++i){
            collection.add(new Bucket());
        }
    }
    
    public void addItem(MusicItem item){
        /*
         * Map to first char of title to ALPHABET index and insert the item
         */
        int index = ALPHABET.indexOf(item.title.charAt(0));
        collection.get(index).addItem(item);
        
        /*
         * After inserting, add the corresponding counts to arrayOfCounts
         */
        index = PCVW.indexOf(item.mediaCode);
        arrayOfCounts[index] += 1;
        arrayOfCounts[4] += 1;
    }
    
    public ArrayList<Bucket> getBuckets(){
        return collection;
    }
    
    public int getTotalItemCount(){
        return arrayOfCounts[4];
    }
    
    public int getPaperItemCount(){
        return arrayOfCounts[0];   
    }
    
    public int getCompactMediaItemCount(){
        return arrayOfCounts[1]; 
    }
    
    public int getVinylItemCount(){
        return arrayOfCounts[2];     
    }
    
    public int getWaxCylinderItemCount(){
        return arrayOfCounts[3]; 
    }
    
}
