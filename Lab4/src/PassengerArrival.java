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
public class PassengerArrival
{

    private int numPassengers;
    private int destinationFloor;
    private int timePeriod;
    private int expectedTimeOfArrival;
    

    public PassengerArrival(int numPassengers, int destinationFloor, int timePeriod){
        this.numPassengers = numPassengers;
        this.destinationFloor = destinationFloor;
        this.timePeriod = timePeriod;
    }
    
    
    public int getNumPassengers(){
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers){
        this.numPassengers = numPassengers; 
    }

    public int getDestinationFloor(){
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor){
        this.destinationFloor = destinationFloor;
    }

    public int getTimePeriod(){
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod){
        this.timePeriod = timePeriod;
    }

    public int getExpectedTimeOfArrival(){
        return expectedTimeOfArrival;
    }

    public void setExpectedTimeOfArrival(int expectedTimeOfArrival){
        this.expectedTimeOfArrival = expectedTimeOfArrival;
    }
}