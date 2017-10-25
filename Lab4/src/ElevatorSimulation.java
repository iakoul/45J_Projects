// Vincent Tong #52928473
// Aaron Zhong

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class ElevatorSimulation{
    
    BuildingManager aBuildingManager = new BuildingManager();
    ArrayList<ArrayList<PassengerArrival>> passengerArrivals = new ArrayList<ArrayList<PassengerArrival>>(5);
    
    private int totalSimulatedTime;
    private int simulatedSecondRate;
    private Elevator[] elevatorArray = new Elevator[5];
    private Thread[] threadArray = new Thread[5];
   // private int[] prevFloorArray = {-1, -1, -1, -1, -1};
    public static Lock writeToConsoleLock;
    public int a = 1;
    private int nextArrivalTime = -1;
    
    
    
    //constructs 5 elevators and a thread for each one
    public ElevatorSimulation(){    
        for (int onFloor = 0; onFloor< 5; onFloor++){
            passengerArrivals.add(new ArrayList<PassengerArrival>());
        }
        
        parseFile();

        for (int i = 0; i < a; i++){
            elevatorArray[i] = new Elevator(i+1, aBuildingManager);
            threadArray[i] = new Thread(elevatorArray[i]);

        }
        writeToConsoleLock = new ReentrantLock();
        
    }
    
    // updates BuildingManager based on text file spawn rates
    public void updateBuildingManager(){
      for(int onFloor = 0; onFloor < passengerArrivals.size(); onFloor++){
          for(int i = 0; i < passengerArrivals.get(onFloor).size(); i++){
              PassengerArrival aPassengerArrival = passengerArrivals.get(onFloor).get(i);
              if(SimClock.getTime() % aPassengerArrival.getTimePeriod() == 0){
                  
                      aBuildingManager.updatePassengerRequestsOnFloor(onFloor, aPassengerArrival.getNumPassengers(),
                                                                               aPassengerArrival.getDestinationFloor());
              }     
          }
      }
    }
  
    // method that calls run() in Elevator
    public void start(){
        
        boolean continueSimulation = true;
        
        for (int i = 0; i < a; i++){
            threadArray[i].start();
        }
   
        while(continueSimulation == true){         
            updateBuildingManager();
            
            try{
                Thread.sleep(simulatedSecondRate);
            }catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
            // printing Simulation output every 10 seconds
            if(SimClock.getTime() % 5 == 0){
               printSimulationState();
            }
          
            SimClock.tick();

            /*The simulation ends when the current simulation time is 
            greater than the total simulation time defined in 
            ElevatorConfig.txt*/      
            
            if(SimClock.getTime() >= totalSimulatedTime){

                continueSimulation = false;   
                
                // interrupting threads here
                for (int i = 0; i < a; i++){
                    threadArray[i].interrupt();
                }
                
                System.out.println();
                System.out.println("=========================SIMULATION STOPPED @" + SimClock.getTime() +" Simulated Seconds =========================");
            }
        }
    }
  
    //method that parses the file and storing appropriate information into appropriate variables
    public void parseFile(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("ElevatorConfig.txt"));
            
            String fileRead;
            
            totalSimulatedTime = 1000;
            Integer.parseInt(br.readLine());
            simulatedSecondRate = 30;
            Integer.parseInt(br.readLine());
            
            for (int onFloor = 0; onFloor< 5; onFloor++){
                
                fileRead = br.readLine(); 
                String[] tokenize = fileRead.split(";");
                for (int i = 0; i < tokenize.length; i++){
                    String[] floorArrayContent = tokenize[i].split(" ");
                    
                    int destinationFloor = Integer.parseInt(floorArrayContent[1]);
                    int numPassengers = Integer.parseInt(floorArrayContent[0]);
                    int timePeriod = Integer.parseInt(floorArrayContent[2]);
                    passengerArrivals.get(onFloor).add(new PassengerArrival(numPassengers, destinationFloor, timePeriod));
                }
            }
            
            br.close();
      
            } catch(FileNotFoundException e){ 
                System.out.println(e);
            } catch(IOException ioe){
                ioe.printStackTrace();
            }

            }
    
//method to print the output of the simulation
//depending on state of elevators, will print according to the type of flags triggered
public void printSimulationState(){

    boolean flagActive   = false;

    //boolean unloadPassengersNow = false;
    
    boolean timeHasArrived = false;
    
    //sleeps for 20 to allow threads to run
    try{
        Thread.sleep(20);
    }catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
    
    //configures flags
    for(int i = 0; i< a; i++){   
        if(flagActive != true)
           flagActive = (elevatorArray[i].getScanCompleteFlag() || elevatorArray[i].getScannedBuildingFloorFlag());

      
      //System.out.println("====" + SimClock.getTime());
      //System.out.println("====" + elevatorArray[i].getMoveQueue().get(0).getExpectedArrival());
      
      if(timeHasArrived != true){
          if ((SimClock.getTime() >= nextArrivalTime) && (nextArrivalTime != -2)){
              timeHasArrived = true;
          }
          
          if (elevatorArray[i].getMoveQueue().size() > 0){
              nextArrivalTime = elevatorArray[i].getMoveQueue().get(0).getExpectedArrival();
          }else{
              nextArrivalTime = -2;
          }
      }
      
      if (flagActive && timeHasArrived){
          break;
      }
    }

                
    if(flagActive || timeHasArrived){
        System.out.println("********************** @" + (SimClock.getTime()) +  " Simulated Seconds **********************");   
        
        System.out.println("Building Floors:");
        for(int i = 0;  i < aBuildingManager.getFloors().length; i++){
             int[] passengerRequestArray = aBuildingManager.getFloors()[i].getPassengerRequests();
            System.out.println("    " + "Floor " + (i+1) + ": " + Arrays.toString(passengerRequestArray));
        }
        System.out.println();
        
        for (int i = 0; i< a; i++){
            System.out.println();
            System.out.println("Elevator " + elevatorArray[i].getElevatorID() + "\n" + "# of Passengers: " + Arrays.toString(elevatorArray[i].getPassengerDestinations()) + "\n" + "Current Floor: " + (elevatorArray[i].getCurrentFloor()+1) + 
                               "\nTotal Unloaded Passengers: " + elevatorArray[i].getTotalUnloadedPassengers() + "\n" + "Total Loaded Passengers: " + elevatorArray[i].getTotalLoadedPassengers() );
            System.out.println("=============================================================");
        } 
        

        
        for (int i = 0; i < a; i++){
            //checking to see if there exists passengersToUnload and prints corresponding output

                if(elevatorArray[i].getPassengersToUnload() != 0){   
                    System.out.println("Elevator " + elevatorArray[i].getElevatorID() + " has arrived at Floor " + (elevatorArray[i].getCurrentFloor()+1) + " to unload passengers.\n  - " + 
                    elevatorArray[i].getPassengersToUnload() + " passengers are exiting the elevator.");
                    System.out.println("=============================================================");
                    elevatorArray[i].resetPassengersToUnload();
                }
                
            if(elevatorArray[i].getScanCompleteFlag()){
                
                //prevFloorArray[i] = -1;
                //floorChanged = false;
                
                System.out.println();
                System.out.println("=============================================================");
                System.out.println("Elevator " + elevatorArray[i].getElevatorID() + " has arrived at Floor " + (elevatorArray[i].getCurrentFloor()+1) + " and picked up " + elevatorArray[i].getNumPassengers() + " passenger(s).");
                
                // iterating through each PassengerDestination in the Elevator to specify each floor's destination and amount
                // of passengers wanting to go to said floor
                for (int k = 0; k < elevatorArray[i].getPassengerDestinations().length; k++){
                    if (elevatorArray[i].getPassengerDestinations()[k] != 0)
                        System.out.println(" - " + elevatorArray[i].getPassengerDestinations()[k] + " passenger(s) will go to Floor " + (k+1));
                        
                }
                    System.out.println("=============================================================");
                    elevatorArray[i].resetScanCompleteFlag();
                    continue;
                    
            // checking if ScanBuildingFloorFlag() triggered
            }else if(elevatorArray[i].getScannedBuildingFloorFlag()){
                System.out.println("Elevator " + elevatorArray[i].getElevatorID() + " will go to Floor " + (elevatorArray[i].getMoveQueue().get(0).getDestination()+1) + " to load passengers.");
                
                //prevFloorArray[i] = -2;
                elevatorArray[i].resetScannedBuildingFloorFlag();
                continue;
            }
        }
        
        flagActive = false;
        timeHasArrived = false;        
        System.out.println();
        }
    

}

    public int getTotalSimulatedTime(){
      return totalSimulatedTime;
    }
           
    public void setTotalSimulatedTime(int totalSimulatedTime){
      this.totalSimulatedTime = totalSimulatedTime;
    }    
}