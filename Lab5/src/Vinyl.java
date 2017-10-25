// Aaron Zhong #67737879
// Manal Rasheed 34763062

import java.util.ArrayList;

public class Vinyl extends MusicItem{
    
    /*
     * Static final variables for indexing the strings
     * when the constructor is called
     */
    private static final int RECORD_LABEL_IMPRINT_POSITION = 3;
    private String recordLabelImprint;
    
    private static final int PLAY_SPEED_POSITION = 4;
    private int playSpeed;
    
    /*
     * Constructor
     */
    public Vinyl(ArrayList<String> item){
        accessionNumber         = item.get(ACCESSION_NUMBER_POSITION);
        title                   = item.get(TITLE_POSITION);
        mediaCode               = item.get(MEDIA_CODE_POSITION);
        recordLabelImprint      = item.get(RECORD_LABEL_IMPRINT_POSITION);
        playSpeed               = Integer.valueOf(item.get(PLAY_SPEED_POSITION));
    }
    
    /*
     * Returns string of additional information as per specified in the index.txt and Lab5
     */
    public String displaySupplementalInfo(){
        return String.format("Label: %s | RPM: %d", recordLabelImprint, playSpeed);
    }
}
