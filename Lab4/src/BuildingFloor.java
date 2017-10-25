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

import java.util.concurrent.locks.ReentrantLock;


public class BuildingFloor{
    private int[] totalDestinationRequests;
    private int[] arrivedPassengers;
    private int[] passengerRequests;  
    private int approachingElevator;
    private ReentrantLock floorLock;
  
    BuildingFloor(){
        this.totalDestinationRequests  = new int[5];
        this.arrivedPassengers         = new int[5];
        this.passengerRequests         = new int[5];
        this.floorLock                 = new ReentrantLock();
        this.approachingElevator       = -1;
    }
    
    BuildingFloor(int[] totalDestinationRequests, int[] arrivedPassengers, int[] passengerRequests){
        this.totalDestinationRequests = totalDestinationRequests;
        this.arrivedPassengers = arrivedPassengers;
        this.passengerRequests = passengerRequests;
        
    }
    
    public int[] getTotalDestinationRequests(){
        return totalDestinationRequests;
    }
    public void setTotalDestinationRequests(int[] totalDestinationRequests){
        this.totalDestinationRequests = totalDestinationRequests;
    }
    public int[] getArrivedPassengers(){
        return arrivedPassengers;
    }
    public void setArrivedPassengers(int[] arrivedPassengers){
        this.arrivedPassengers = arrivedPassengers;
    }
    public int[] getPassengerRequests(){
        return passengerRequests;
    }
    public void setPassengerRequests(int[] passengerRequests){
        this.passengerRequests = passengerRequests;
    }
    public int getApproachingElevator(){
        return approachingElevator;
    }
    public void setApproachingElevator(int approachingElevator){
        this.approachingElevator = approachingElevator;
    }
  public void addPassengerRequestsOnFloor(int numPassengers, int toFloor){
    this.passengerRequests[toFloor] += numPassengers;
    this.totalDestinationRequests[toFloor] += numPassengers;
  }
  
  public ReentrantLock getFloorLock(){
    return floorLock;
  }
}
