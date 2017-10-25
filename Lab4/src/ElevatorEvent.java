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

public class ElevatorEvent{
   
    private int destination;
    private int expectedArrival;
    
    ElevatorEvent(int destination){
      this.destination = destination;
    }
  
    public int getDestination(){
        return destination;
    }
    public void setDestination(int destination){
        this.destination = destination;
    }
    public int isExpecteArrival(){
        return expectedArrival;
    }
    public void setExpectedArrival(int expectedArrival){
        this.expectedArrival = expectedArrival;
    }
  
    public void calcAndSetExpectedArrival(int prevFloor, int timeToAppend){
        expectedArrival = Math.abs(((destination-prevFloor)*5)) + 10 + timeToAppend;
        setExpectedArrival(expectedArrival);
    }
  
  public int getExpectedArrival(){
    return expectedArrival;
  }

}

  