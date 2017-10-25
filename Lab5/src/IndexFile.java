// Aaron Zhong #67737879
// Manal Rasheed 34763062

import java.io.IOException;
import java.io.PrintWriter;

public class IndexFile implements IndexFileInterface{
    
   PrintWriter writer;
    
   public void open(String indexFileName) throws IOException{
       /*
        * Tries to open a file for the writer to write to
        * and writes the labels on the first line
        */
       try{
           writer = new PrintWriter(indexFileName, "UTF-8");
           writer.println( "TITLE                                             ACCESSION # MEDIA  ADDITIONAL INFORMATION\n" +
                   "--------------------------------");
       }catch(IOException e){
           System.err.format("Exception occurred trying to open index file");
           e.printStackTrace();
           throw e;
       }
   }
   
   public void writeItem(MusicItem itemToWrite){
       
       /*
        * Prints itemToWrite formatted correctly to the file
        */

       writer.println(String.format("%-50s %-12s %-6s %s", itemToWrite.title,     itemToWrite.accessionNumber, 
                                                           itemToWrite.mediaCode, itemToWrite.displaySupplementalInfo()));    
   }
   
   public void close(){
           writer.close();
   }
}
