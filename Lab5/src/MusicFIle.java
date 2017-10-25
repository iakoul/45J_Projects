// Aaron Zhong   67737879
// Manal Rasheed 34763062

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;
//import java.io.FileReader;

import java.net.URL;

public class MusicFIle implements MusicFileInterface{
    
    //private BufferedReader reader = null;
    
    private File file = null;
    private Scanner scanner = null;
    private String nextLine = null;
    
    public MusicFIle() throws IOException{
        
        /*
         * Ask the user for a valid response (d/w)
         */
        String s;
        Scanner scan = new Scanner(System.in);
        while(true){
            
            System.out.print("File on Drive or Web? (d/w): ");
            s = scan.nextLine();
            if (s.equals("d") || s.equals("w")){
                break;
            }
        }
        scan.close();
        
        /*
         * Depending on the response, either use disk or web file.
         */
        switch(s){
            case "d":
                try{
                    openDiskFile("music.txt"); 
                }catch(IOException e){
                    System.out.println("Error reading music.txt from DISK");
                    throw e;
                }
                break;
            case "w":
                try{
                    openWebFile("http://www.ics.uci.edu/~rkwang/music.txt"); 
                }catch(IOException e){
                    System.out.println("Error reading music.txt from WEB");
                    throw e;
                }
                break;
        }
        
    }
    
    public void openDiskFile(String musicFileName) throws IOException{
        file = new File(musicFileName);
        scanner = new Scanner(file);
        //try{
        //    reader = new BufferedReader(new FileReader(musicFileName));
        //}catch(IOException e){
        //    System.err.format("Exception occurred trying to read '%s'.", musicFileName);
        //    e.printStackTrace();
        //    throw e;
        //}*/
        
    }
    public void openWebFile(String musicFileName) throws IOException{
        URL musicFileURL = new URL(musicFileName);
        scanner = new Scanner(musicFileURL.openStream());
        
        //try{
        //    reader = new BufferedReader(new InputStreamReader(musicFileURL.openStream()));
        //}catch(IOException e){
        //    System.err.format("Exception occurred trying to read '%s'.", musicFileName);
        //    e.printStackTrace();
        //    throw e;
        //}
    }
    
    public boolean hasMoreItems(){
        if(scanner.hasNextLine()){
            nextLine = scanner.nextLine();
            return true;
        }
        return false;
    }
    
    public ArrayList<String> readItem(){
        return new ArrayList<String>(Arrays.asList(nextLine.split("; ")));
    }
    
    public void close(){
            scanner.close();
    }
}
