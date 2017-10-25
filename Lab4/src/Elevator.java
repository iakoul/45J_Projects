// Vincent Tong #52928473
// Aaron Zhong  #67737879

import java.util.Arrays;
import java.util.ArrayList;

public class Elevator implements Runnable{

    private int                 elevatorID;
    private int                 currentFloor;
    private int                 numPassengers;
    private int                 totalLoadedPassengers;
    private int                 totalUnloadedPassengers;
    private int                 passengersToUnload;
    private boolean             scanCompleteFlag;
    private boolean             scannedBuildingFloorFlag;
    private boolean             scanMassUpdateFlag;
    int[]                       passengerDestinations = {0,0,0,0,0};
    

    ArrayList<ElevatorEvent>    moveQueue;
    BuildingManager             aBuildingManager;   
  
    // Elevator Constructor
    Elevator(int elevatorID, BuildingManager aBuildingManager){
        moveQueue               = new ArrayList<ElevatorEvent> ();
        this.elevatorID         = elevatorID;
        this.aBuildingManager   = aBuildingManager;
        this.currentFloor = 0;
        this.numPassengers = 0;
        this.totalLoadedPassengers = 0;
        this.totalUnloadedPassengers = 0;
        this.passengersToUnload = 0;
    }
    
    
    public boolean isBusy(){
        int sum = 0;
        for(int i = 0; i < passengerDestinations.length; i++)
            sum += passengerDestinations[i];
        
        if(sum >= 1)
            return true;
        
        return false;
    }
    
    public boolean moveElevatorAndPickUpAndMassUpdate() throws InterruptedException{
        
        
            //System.out.println("inside moveElevatorAndPickUpAndMassUpdate for elevator (" + elevatorID + ")");
            ElevatorEvent aElevatorEvent = moveQueue.get(0); 
            aBuildingManager.getFloor(aElevatorEvent.getDestination()).setApproachingElevator(elevatorID);
            
            //System.out.println("elevator event arrival is: " + aElevatorEvent.getExpectedArrival());
              
            int currentTime = SimClock.getTime();
            //System.out.println("current time is: " + currentTime);
            while(currentTime != aElevatorEvent.getExpectedArrival()){
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){throw new InterruptedException();}
                
                currentTime = SimClock.getTime();
            }
            
            BuildingFloor currentBuildingFloor = aBuildingManager.getFloor(this.currentFloor);
            this.currentFloor = aElevatorEvent.getDestination();
            
            passengersToUnload   = passengerDestinations[this.currentFloor];
            
            //System.out.println("passengerstoUnload = " + passengersToUnload);
          
            //Update elevator and floor status
            numPassengers -= passengersToUnload;
            totalUnloadedPassengers += passengersToUnload;      
            passengerDestinations[this.currentFloor] = 0;        
          
            currentBuildingFloor.getArrivedPassengers()[currentFloor] += passengersToUnload;
            currentBuildingFloor.setApproachingElevator(-1);
            
            moveQueue.remove(0);
            return true;
    }
    
    public void createElevatorEventAndAddToMoveQueue(int destinationFloor, int prevFloor, int prevExpectedTime){
      ElevatorEvent elevatorEvent = new ElevatorEvent(destinationFloor);
      elevatorEvent.calcAndSetExpectedArrival(prevFloor, prevExpectedTime);
      moveQueue.add(elevatorEvent);      
    }
    
  public void updateElevatorPickupStatusAndBuildingManager(int currentfloor, int goToFloor){
       
         int passengersGoingToFloor = aBuildingManager.checkFloorForPassengersToFloor(currentFloor, goToFloor);    
         
         
         passengerDestinations[goToFloor]  = passengersGoingToFloor;         
         totalLoadedPassengers            += passengersGoingToFloor;
         numPassengers                    += passengersGoingToFloor;
         aBuildingManager.getPassengerRequests(currentFloor)[goToFloor] -= passengersGoingToFloor;
  
     }
  
  
   public boolean scanPassengerDestinationsAndUpdate(){
       
     int prevExpectedTime = SimClock.getTime();
     int prevFloor = currentFloor;

     
     for (int goToFloor = currentFloor; goToFloor < aBuildingManager.getFloors().length; goToFloor++){
       int requestForFloor = aBuildingManager.checkFloorForPassengersToFloor(currentFloor, goToFloor);
       if (requestForFloor != 0){
         
         createElevatorEventAndAddToMoveQueue(goToFloor, prevFloor, prevExpectedTime);
         prevFloor = goToFloor;
         prevExpectedTime = moveQueue.get(moveQueue.size()-1).getExpectedArrival();
         updateElevatorPickupStatusAndBuildingManager(currentFloor, goToFloor);
         
       }
     }
    
     if (moveQueue.size() == 0){
         for (int goToFloor = currentFloor; goToFloor >= 0; goToFloor--){
             int requestForFloor = aBuildingManager.checkFloorForPassengersToFloor(currentFloor, goToFloor);
             if (requestForFloor != 0){
             createElevatorEventAndAddToMoveQueue(goToFloor, prevFloor, prevExpectedTime);
             prevFloor = goToFloor;
             prevExpectedTime = moveQueue.get(moveQueue.size()-1).getExpectedArrival();
             updateElevatorPickupStatusAndBuildingManager(currentFloor, goToFloor);
           }
         }
     }
     aBuildingManager.unlockFloor(currentFloor);
     
     return true;
     //System.out.println("scanPassengerDestinationsAndUpdate has finished for elevator (" + elevatorID + ")");
   }
  
    public boolean scanBuildingFloorsAndUpdateMoveQueue(){
        //System.out.println("elevator (" + elevatorID +  ")on: " + currentFloor);
        if(!isBusy()){
            //System.out.println("Inside scanBuildingFloorsAndUpdateMoveQueue");
            for(int checkedFloor = 0; checkedFloor < aBuildingManager.getFloors().length; checkedFloor++){
                BuildingFloor floor = aBuildingManager.getFloors()[checkedFloor];
                if(floor.getFloorLock().tryLock()){
                    ////System.out.println("Lock Succeeded for Floor: " + checkedFloor);
                    if(!aBuildingManager.isFloorEmpty(checkedFloor)){

                        createElevatorEventAndAddToMoveQueue(checkedFloor, currentFloor, SimClock.getTime());                        
                        ////System.out.println("createElevatorEventAndAddToMoveQueue succeeded for Floor to move to floor ->");
                        ////System.out.println("Will move to floor: " + moveQueue.get(0).getDestination());
                        
                        return true;
                    } else{
                      ////System.out.println("Floor: " + checkedFloor +  " was empty");
                      aBuildingManager.unlockFloor(checkedFloor);
                      ////System.out.println("Unlock Succeeded for Floor: " + checkedFloor);
                    }
                    
                }
            }
        }
        return false;
    }
    
    public void run(){
      try{
          while(!isBusy()){
              
              int a = moveQueue.size();
              while (a == 0){
                  Thread.sleep(10);
                  scanBuildingFloorsAndUpdateMoveQueue();
                  a = moveQueue.size();
              }
              
              
              ////System.out.println("Finished scanBuildingFloorsAndUpdateMoveQueue by elevator (" + elevatorID + ")\n");           
              
              scannedBuildingFloorFlag = true;
              
              moveElevatorAndPickUpAndMassUpdate();

        
              ////System.out.println("Moved elevator to floor");
              ////System.out.println("Updated all necessary values");
              ////System.out.println("Elevator is now on floor: " + currentFloor);
              
              ////System.out.println("\nScan floor and make ElevatorEvents");
              scanCompleteFlag = scanPassengerDestinationsAndUpdate();
              ////System.out.println("MoveQueue is size:" + moveQueue.size());
                
              while(moveQueue.size() != 0){
                  
                  //System.out.println("Before moving");
                  //testPrinter();
                  scanMassUpdateFlag = moveElevatorAndPickUpAndMassUpdate();
                  //System.out.println("After moving");
                  //testPrinter();
              }
          }
      }
      catch(InterruptedException e){System.out.println("Thread " + elevatorID + " STOPPED");}
               
    }
    
    public void testPrinter(){
        System.out.println("\nElevator (" + elevatorID + ") on Floor: " + currentFloor);
        System.out.println("MoveQueue: " + moveQueue.size());            
        System.out.println("PassengerDestination:  " + Arrays.toString(passengerDestinations));
        System.out.println("Time Now: " + SimClock.getTime() + "\n");
        
        for(int i = 0;  i < aBuildingManager.getFloors().length; i++){
            int[] passengerRequestArray = aBuildingManager.getFloors()[i].getPassengerRequests();
            System.out.println(Arrays.toString(passengerRequestArray));
        }
    }
    
    
    public int getElevatorID(){
        return elevatorID;
    }

    public void setElevatorID(int elevatorID){
        this.elevatorID = elevatorID;
    }

    public int getCurrentFloor(){
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor){
        this.currentFloor = currentFloor;
    }

    public int getNumPassengers(){
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers){
        this.numPassengers = numPassengers;
    }

    public int getTotalLoadedPassengers(){
        return totalLoadedPassengers;
    }

    public void setTotalLoadedPassengers(int totalLoadedPassengers){
        this.totalLoadedPassengers = totalLoadedPassengers;
    }

    public int getTotalUnloadedPassengers(){
        return totalUnloadedPassengers;
    }

    public void setTotalUnloadedPassengers(int totalUnloadedPassengers){
        this.totalUnloadedPassengers = totalUnloadedPassengers;
    }

    public ArrayList<ElevatorEvent> getMoveQueue(){
        return moveQueue;
    }

    public void setMoveQueue(ArrayList<ElevatorEvent> moveQueue){
        this.moveQueue = moveQueue;
    }

    public int[] getPassengerDestinations(){
        return passengerDestinations;
    }
  
   public boolean getScanCompleteFlag(){
        return scanCompleteFlag;
    }
   
   public boolean getScannedBuildingFloorFlag(){
        return scannedBuildingFloorFlag;
   }

   public boolean getScanMassUpdateFlag(){
        return scanMassUpdateFlag;
   }
   public void setPassengerDestinations(int[] passengerDestinations){
        this.passengerDestinations = passengerDestinations;
    }
  
  public int getPassengersToUnload(){
      return passengersToUnload;
  }
   
  public void resetPassengersToUnload(){
      passengersToUnload = 0;
  }
  
  public void resetScanCompleteFlag(){
        scanCompleteFlag = false;
    }
  
  public void resetScannedBuildingFloorFlag(){
      scannedBuildingFloorFlag = false;
  }
  
  public void resetScanMassUpdateFlag(){
         scanMassUpdateFlag = false;
  }
  
  public int getNumPassengersForSomeFloor(int goingToFloor){
      return passengerDestinations[goingToFloor];
  }
}



    