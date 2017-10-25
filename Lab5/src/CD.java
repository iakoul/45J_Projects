// Aaron Zhong #67737879
// Manal Rasheed 34763062

import java.util.ArrayList;

public class CD extends MusicItem{
    
    /*
     * Static final variables for indexing the strings
     * when the constructor is called
     */
    
    private static final int TRACKS_POSITION = 3;
    private int tracks;
    
    private static final int RELEASE_YEAR_POSITION = 4;
    private String releaseYear;
    
    /*
     * Constructor
     */
    public CD(ArrayList<String> item){
        accessionNumber         = item.get(ACCESSION_NUMBER_POSITION);
        title                   = item.get(TITLE_POSITION);
        mediaCode               = item.get(MEDIA_CODE_POSITION);
        tracks                  = Integer.valueOf(item.get(TRACKS_POSITION));
        releaseYear             = item.get(RELEASE_YEAR_POSITION);
    }
    
    /*
     * Returns string of additional information as per specified in the index.txt and Lab5
     */
    public String displaySupplementalInfo(){
        return String.format("# of tracks: %d | Year released: %s", tracks, releaseYear);
    }
}
