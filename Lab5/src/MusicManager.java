// Aaron Zhong   67737879
// Manal Rasheed 34763062

import java.io.IOException;
import java.util.ArrayList;

public class MusicManager implements MusicManagerInterface
{
    private MusicList aMusicList;
   
    public MusicManager(){
        aMusicList = new MusicList();
        
        try{
            MusicFIle aMusicFIle = new MusicFIle();
            
            boolean hasNextLine = aMusicFIle.hasMoreItems();
            
            while(hasNextLine == true){
                /*
                 * Reads the line if there is a next one
                 * and depending on the switch adds the
                 * corresponding object to aMusicList
                 */
                ArrayList<String> stringArray = aMusicFIle.readItem();
                String mediaCode = stringArray.get(2);
                switch(mediaCode){
                    case "P":
                        aMusicList.addItem(new Paper(stringArray));
                        break;
                    case "C":
                        aMusicList.addItem(new CD(stringArray));
                        break;
                    case "V":
                        aMusicList.addItem(new Vinyl(stringArray));
                        break;
                    case "W":
                        aMusicList.addItem(new Wax(stringArray));
                        break;
                    default:
                }
                hasNextLine = aMusicFIle.hasMoreItems();
            }
            aMusicFIle.close();
        }catch(IOException e){
            System.out.println("MusicManager NOT successfully created. Have a Nice Day :)");
        }
        
    }
    
    public void makeIndexAndDisplayCounts(){ 
        
        /*
         * Creates aIndexFile and prints out the labels onto the first line of the console
         */
        
        IndexFile aIndexFile = new IndexFile();
        try{
            aIndexFile.open("index.txt");
            
            /*
             * Prints all labels using the ^above format"
             * TODO: FIX INDENTATIONS
             */
            ArrayList<Bucket> allBuckets = aMusicList.getBuckets();
            for(int i = 0; i < allBuckets.size(); ++i){
                Bucket aBucket = allBuckets.get(i);
                if (aBucket.size() != 0){
                    for(int j = 0; j < aBucket.getItems().size(); ++j){
                        MusicItem aMusicItem = aBucket.getItems().get(j);
                        
                        /*
                         * Writes musicItem to file
                         */
                        aIndexFile.writeItem(aMusicItem);
                        
                        /*
                         * Prints musicItem to console
                         *
                         */
                        //System.out.println(String.format("%-50s %-12s %-6s %s", aMusicItem.title,     aMusicItem.accessionNumber, 
                        //                                                        aMusicItem.mediaCode, aMusicItem.displaySupplementalInfo()));                            
                    }
                }
            }
            aIndexFile.close();
            
            /*
             * Print out media counts
             */
            System.out.println("\nIndex File SUCCESSFULLY CREATED! (index.txt)\n");
            System.out.println("Paper Count: " + aMusicList.getPaperItemCount() + 
                "  | Compact Media Count: "    + aMusicList.getCompactMediaItemCount() +
                "  | Vinyl Count: "            + aMusicList.getVinylItemCount() +
                "  | Wax Cylinder Count: "     + aMusicList.getPaperItemCount() +
                "\nTotal Item Count: "         + aMusicList.getTotalItemCount());
            
        }catch(IOException e){
            System.out.println("Specificed file could not be written to. Have a nice day :)");
        }
    }
}
