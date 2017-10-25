
//Lab2
//CrapsSimulation
//CrapsGame
//CrapsMetricsMonitor

import java.util.Random;

public class CrapsGame {
  
    private short numRolls;
    private CrapsMetricsMonitor aCrapsMetricsMonitor;
    
    public CrapsGame(CrapsMetricsMonitor aCrapsMetricsMonitor){
        numRolls = 0;
        this.aCrapsMetricsMonitor = aCrapsMetricsMonitor;
    }
  
    public short b6n1(){
        Random rand = new Random();
        return (short) (rand.nextInt(6) +1);
    }
  
    public short getDiceSum(){
        increaseNumRolls();
        return (short) (b6n1() + b6n1());   
    } 
  
  /*
    Returns
  */
    public short playGame(){
        resetNumRolls();
        short diceSum = getDiceSum();
        System.out.println("Rolled a " + diceSum);
        if((diceSum==7) || (diceSum==11)){ //natural
            return 1;
        }else if((diceSum==2) || (diceSum==3) || (diceSum==12)){ //lose on first turn
            return -2; 
        }else{
            short point = diceSum;
            while(true){
                diceSum = getDiceSum();
                System.out.println("Rolled a " + diceSum);
                if(diceSum == point){
                    return 0; 
                }else if(diceSum == 7){
                    return -1; 
                }
            }
        }
    }
  
    public short getNumRolls(){
        return this.numRolls; 
    }

    public void increaseNumRolls(){
        ++this.numRolls; 
    }
    
    public void resetNumRolls(){
        this.numRolls=0; 
    }
    
    public CrapsMetricsMonitor getaCrapsMetricsMonitor() {
        return this.aCrapsMetricsMonitor;
    }
    public void setaCrapsMetricsMonitor(CrapsMetricsMonitor aCrapsMetricsMonitor) {
        this.aCrapsMetricsMonitor = aCrapsMetricsMonitor;
    }
}



