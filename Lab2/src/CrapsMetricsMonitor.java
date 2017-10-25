//Lab2
//CrapsSimulation
//CrapsGame
//CrapsMetricsMonitor

/* 
 *      Lab Section 1 12:00pm - 1:50pm
 *      Kishan Rajasekhar,  Student ID# 16621055
 *      Aaron Zhong,        Student ID# 67737879
 */ 

public class CrapsMetricsMonitor{
    private int totalGamesPlayed;
    private int totalGamesWon;
    private int totalGamesLost;
    private int maxTotalRollsInAGame;
    private int totalNaturals;
    private int totalCraps;
    private int tempWinStreak;
    private int tempLoseStreak;
    private double maxBalanceEver;
    private int maxBalanceGame;
  
  public CrapsMetricsMonitor(){
    totalGamesPlayed      = 0;
    totalGamesWon         = 0;
    totalGamesLost        = 0;
    maxTotalRollsInAGame  = 0;
    totalNaturals         = 0;
    totalCraps            = 0;
    tempWinStreak         = 0;
    tempLoseStreak        = 0;
    maxBalanceEver        = 0;
    maxBalanceGame        = 0;
  }

    public void printStatistics(int maxWinStreak, int maxLoseStreak){
        System.out.println(
        //(Optional) TODO: More advanced formatting
        "*****************************\n" +
        "*** SIMULATION STATISTICS ***\n" +
        "*****************************\n" +
        "Games Played: " +                      totalGamesPlayed + "\n" +
        "Games Won: " +                         totalGamesWon + "\n" +      
        "Games Lost: " +                        totalGamesLost + "\n" +      
        "Maximum Rolls in a Single Game: " +    maxTotalRollsInAGame + "\n" +      
        "Natural Count: " +                     totalNaturals + "\n" +      
        "Craps Count: " +                       totalCraps + "\n" +
        "Maximum Winning Streak: " +            maxWinStreak + "\n" +
        "Maximum Loosing Streak: " +            maxLoseStreak + "\n" +
        "Maximum Balance: $" +                  maxBalanceEver + " during game " + maxBalanceGame + "\n"
        );
    }
  
    public void reset(){
      totalGamesPlayed      = 0;
      totalGamesWon         = 0;
      totalGamesLost        = 0;
      maxTotalRollsInAGame  = 0;
      totalNaturals         = 0;
      totalCraps            = 0;
      tempWinStreak         = 0;
      tempLoseStreak        = 0;
      maxBalanceEver        = 0;
      maxBalanceGame        = 0;
    }
  
    public int updateWinStreaks(int maxWinStreak){
            tempWinStreak++;
      if(tempWinStreak > maxWinStreak){
        return tempWinStreak;
      }else{
          return maxWinStreak;
      }
    }
         
    public int updateLoseStreaks(int maxLoseStreak){
            tempLoseStreak++;
      if(tempLoseStreak > maxLoseStreak){
        return tempLoseStreak;
      }else{
          return maxLoseStreak;
      }
    }
  
    public void resetTempWinStreak(){
        tempWinStreak = 0;
    }   
  
    public void resetTempLoseStreak(){
        tempLoseStreak = 0;
    }
    
    public void increaseTotalGamesCount(){
      ++totalGamesPlayed;
    }
    
    public void increaseGamesWonCount(){
      ++totalGamesWon;
    }
    
    public void increaseGamesLostCount(){
      ++totalGamesLost;
    }
    
      public void increaseTotalCrapsCount(){
      ++totalCraps;
    }
    
    public void increaseTotalNaturalsCount(){
      ++totalNaturals;
    }
    
    
    public int getTotalGamesPlayed()
    {
        return totalGamesPlayed;
    }
    public void setTotalGamesPlayed(int totalGamesPlayed)
    {
        this.totalGamesPlayed = totalGamesPlayed;
    }
    public int getTotalGamesWon()
    {
        return totalGamesWon;
    }
    public void setTotalGamesWon(int totalGamesWon)
    {
        this.totalGamesWon = totalGamesWon;
    }
    public int getTotalGamesLost()
    {
        return totalGamesLost;
    }
    public void setTotalGamesLost(int totalGamesLost)
    {
        this.totalGamesLost = totalGamesLost;
    }
    public int getMaxTotalRollsInAGame()
    {
        return maxTotalRollsInAGame;
    }
    public void setMaxTotalRollsInAGame(int maxTotalRollsInAGame)
    {
        this.maxTotalRollsInAGame = maxTotalRollsInAGame;
    }
    public int getTotalNaturals()
    {
        return totalNaturals;
    }
    public void setTotalNaturals(int totalNaturals)
    {
        this.totalNaturals = totalNaturals;
    }
    public int getTotalCraps()
    {
        return totalCraps;
    }
    public void setTotalCraps(int totalCraps)
    {
        this.totalCraps = totalCraps;
    }
    public int getTempWinStreak()
    {
        return tempWinStreak;
    }
    public void setTempWinStreak(int tempWinStreak)
    {
        this.tempWinStreak = tempWinStreak;
    }
    public int getTempLoseStreak()
    {
        return tempLoseStreak;
    }
    public void setTempLoseStreak(int tempLoseStreak)
    {
        this.tempLoseStreak = tempLoseStreak;
    }
    public double getMaxBalanceEver()
    {
        return maxBalanceEver;
    }
    public void setMaxBalanceEver(double maxBalanceEver)
    {
        this.maxBalanceEver = maxBalanceEver;
    }
  
    public int getMaxBalanceGame()
    {
        return maxBalanceGame;
    }
    public void setMaxBalanceGame(int maxBalanceGame)
    {
        this.maxBalanceGame = maxBalanceGame;
    }
    
}