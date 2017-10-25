// Aaron Zhong #67737879
// Manal Rasheed 34763062

import java.util.ArrayList;

public class Paper extends MusicItem{
    
    /*
     * Static final variables for indexing the strings
     * when the constructor is called
     */
    private static final int PAGES_POSITION = 3;
    private String pages;
    
    /*
     * Constructor
     */
    public Paper(ArrayList<String> item){
        accessionNumber         = item.get(ACCESSION_NUMBER_POSITION);
        title                   = item.get(TITLE_POSITION);
        mediaCode               = item.get(MEDIA_CODE_POSITION);
        pages                   = item.get(PAGES_POSITION);
    }
    
    /*
     * Returns string of additional information as per specified in the index.txt and Lab5
     */
    public String displaySupplementalInfo(){
        return String.format("# of pages: %s", pages);
    }
}
