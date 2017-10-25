//Lab2
//CrapsSimulation
//CrapsGame
//CrapsMetricsMonitor

/* 
 *      Lab Section 1 12:00pm - 1:50pm
 *      Kishan Rajasekhar,  Student ID# 16621055
 *      Aaron Zhong,        Student ID# 67737879
 */ 

import java.util.Scanner;


public class CrapsSimulation{
    
    private                     CrapsGame aCrapsGame;
    private String              userName;  
    private double              balance;
    private double              bet;
    private int                 maxWinStreak;
    private int                 maxLoseStreak;     
    private CrapsMetricsMonitor aCrapsMetricsMonitor;  

    /*Constructor to initialize an instance
    of the CrapsSimultion class*/
    public CrapsSimulation(){
        Scanner scan;
        scan = new Scanner(System.in);
        System.out.print("Welcome to SimCraps! Enter your user name: ");
        this.userName = scan.next();
        System.out.println("Hello " + this.userName);
        placeBet(scan);
      
        maxWinStreak = 0;
        maxLoseStreak = 0;
      
        aCrapsMetricsMonitor = new CrapsMetricsMonitor();
        aCrapsGame = new CrapsGame(aCrapsMetricsMonitor);
        
        aCrapsMetricsMonitor.setMaxBalanceEver(this.balance);
    }
  
    /*Constructor to initialize
    attributes to 0*/  
    public void placeBet(Scanner scan){
        System.out.print("Enter the amount of money you will bring to the table: ");
        this.balance = scan.nextInt();
    }
  
    /*Resets all values pertaining to statistics inside
    both CrapsSimulation and CrapsMetricsMonitor*/     
    public void reset(){
        this.aCrapsMetricsMonitor.reset();
        this.maxWinStreak = 0;
        this.maxLoseStreak = 0;
    }
    
  /*Starts a craps game by first asking for the bet and 
  running the .newGame method. Until the user decides to stop
  simulating results, simulations will occur*/
    public void start(){
        boolean play = true;
        while(play){
          double bet = putBetAmount();
          while(this.balance > 0){
              newGame(bet);
              printBalance();
          }
          this.aCrapsMetricsMonitor.printStatistics(maxWinStreak, maxLoseStreak);
          reset();
          play = playAgain();
        }
        System.out.print("Thanks for Playing! :D"); 
    }
    
  /*Asks the user for a 'y' or 'n' character
  and returns true or false correspondingly*/
    public boolean playAgain(){
        System.out.print("Replay? Enter 'y' or 'n': ");
        Scanner scan = new Scanner(System.in);
        boolean play;
       
        if (scan.next().equals("n"))
            play = false;
        else{
            placeBet(scan);  
            play = true;
        }
        return play;
      
    }
  
   /*Asks the user for a proper bet amount and returns
   the parsed bet as a double*/
    public double putBetAmount(){
        System.out.print("Enter the bet amount between $1 and $" + this.balance +": ");
        Scanner scan = new Scanner(System.in);
        double result = scan.nextDouble();
        while( (result<1) || (result>this.balance) ){
            System.out.print("Invalid bet! Please enter a bet between $1 and $" + this.balance + ": "); 
            result = scan.nextDouble();
        }
        System.out.println(this.userName + " bets $" + result);
        return result;
    }

    /*Runs the CrapsGame.playGame() algorithm to calculate
    the correct craps/natural result and corresponding balance.
    Finishes by updating the statistics values (w/o printing)*/
    public void newGame(double bet){
        short result = aCrapsGame.playGame(); //win or lose
        short numRolls = aCrapsGame.getNumRolls();
        if( this.aCrapsMetricsMonitor.getMaxTotalRollsInAGame() < numRolls )
            this.aCrapsMetricsMonitor.setMaxTotalRollsInAGame(numRolls);
        if(result == 1){ //natural
            System.out.println("*****Natural! You win!*****");
            this.balance += bet; 
        }else if(result == 0){ //rolled point
            System.out.println("*****Rolled the point! You win!*****");
            this.balance += bet; 
        }else if(result == -1){
            System.out.println("*****Crap out! You loose.*****");
            this.balance -= bet;
        }else if(result == -2){
            System.out.println("*****Craps! You loose.*****"); //lose on first turn
            this.balance -= bet;
        }
      
        updateStatistics(result);
    }
 
    /*Prints the current balance to the console*/
    public void updateStatistics(int result){
    
        //Total Games Count
        this.aCrapsMetricsMonitor.increaseTotalGamesCount();
      
        //Games Won/Lost, Total Naturals/Craps
        if(result >= 0){
            this.aCrapsMetricsMonitor.increaseGamesWonCount(); 
        if(result==1)
            this.aCrapsMetricsMonitor.increaseTotalNaturalsCount(); 
        }else{
          this.aCrapsMetricsMonitor.increaseGamesLostCount();
          if(result==-2)
              this.aCrapsMetricsMonitor.increaseTotalCrapsCount(); 
        }
      
      //Winning/Losing Streak
      if(result >= 0){
        aCrapsMetricsMonitor.resetTempLoseStreak();
        maxWinStreak = aCrapsMetricsMonitor.updateWinStreaks(maxWinStreak);
      }else{
        aCrapsMetricsMonitor.resetTempWinStreak();
        maxLoseStreak = aCrapsMetricsMonitor.updateLoseStreaks(maxLoseStreak);
      }
        
      //Max Balance
      if(this.balance > this.aCrapsMetricsMonitor.getMaxBalanceEver()){
          this.aCrapsMetricsMonitor.setMaxBalanceEver(balance);
          this.aCrapsMetricsMonitor.setMaxBalanceGame(this.aCrapsMetricsMonitor.getTotalGamesPlayed());
        }
    }

    /*Prints the current balance 
    to the console*/
    public void printBalance(){
        if(this.balance < 0) 
        this.balance = 0;
        System.out.println(this.userName + "'s balance: $" + this.balance);
    }
    
    //Getters and Setters
    public CrapsMetricsMonitor getaCrapsMetricsMonitor() {
        return this.aCrapsMetricsMonitor;
    }
    public void setaCrapsMetricsMonitor(CrapsMetricsMonitor aCrapsMetricsMonitor) {
        this.aCrapsMetricsMonitor = aCrapsMetricsMonitor;
    }
    public CrapsGame getaCrapsGame() {
        return aCrapsGame;
    }
    public void setaCrapsGame(CrapsGame aCrapsGame) {
        this.aCrapsGame = aCrapsGame;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
     public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBet() {
        return bet;
    }
    public void setBet(double bet) {
        this.bet = bet;
    }
    public int getWinningStreak() {
        return maxWinStreak;
    }
    public void setWinningStreak(short winningStreak) {
        this.maxWinStreak = winningStreak;
    }
    public int getLosingStreak() {
        return maxLoseStreak;
    }
    public void setLosingStreak(short losingStreak) {
        this.maxLoseStreak = losingStreak;
    } 
}