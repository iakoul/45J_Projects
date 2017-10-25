public abstract class MusicItem implements MusicItemInterface{
    
    public String accessionNumber;
    public String title;
    public String mediaCode;
    
    
    // Construct a music item from item
    // position 0: accession number
    // position 1: title
    // position 2: media code
    // public MusicItem(ArrayList<String> item)
    
    public abstract String displaySupplementalInfo();


    public String getAccessionNumber(){
        return accessionNumber;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getMedia(){
        return mediaCode;
    }
}
