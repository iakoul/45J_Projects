/*
Lab4
ElevatorSimulation
BuildingManager
BuildingFloor
Elevator
ElevatorEvent
PassengerArrival
SimClock
*/



public class BuildingManager{

    private BuildingFloor[] floors;

  
    public BuildingManager(){
        floors = new BuildingFloor[5];
      
        for(int i = 0; i < 5; i++){
            floors[i]  = new BuildingFloor();
        }
    }
    
  
    public void unlockFloor(int floorToUnlock){
        floors[floorToUnlock].getFloorLock().unlock();
    }
  
  
    public int checkFloorForPassengersToFloor(int floorToCheck, int floorToGo){
        return floors[floorToCheck].getPassengerRequests()[floorToGo];
     
    }
   
    public void lockFloor(int floorToLock){  
        floors[floorToLock].getFloorLock().lock();
    }
  
    public BuildingFloor[] getFloors(){
        return floors;
    }
  
    public BuildingFloor getFloor(int floorToReturn){
        return floors[floorToReturn];
    }
    

  
  
    public boolean isFloorEmpty(int floorToCheck){
        int sum = 0;
        for (int i = 0 ; i < floors[floorToCheck].getPassengerRequests().length; i++){
          sum += floors[floorToCheck].getPassengerRequests()[i];
        }
        if (sum == 0){
          return true;
        }
        return false;
        
      }

    public void setFloor(int[] totalDestinationRequests, int[] arrivedPassengers, int[] passengerRequests, int onFloor){
        System.out.println(floors.length);
        floors[onFloor] = new BuildingFloor(totalDestinationRequests, arrivedPassengers, passengerRequests);
    }
    
    public void setFloors(BuildingFloor[] floors){
        this.floors = floors;
    }
  

    
    public void updatePassengerRequestsOnFloor(int onFloor, int numPassengers, int toFloor){
        floors[onFloor].addPassengerRequestsOnFloor(numPassengers, toFloor);
    }
  
   public int[] getPassengerRequests(int floor){
        return floors[floor].getPassengerRequests();
    }
  

}
