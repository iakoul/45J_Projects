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


public class SimClock{
    private static int simulatedTime;
    
    public SimClock(){
        simulatedTime = 0;
    }
    
    public static void tick(){
        simulatedTime++;
    }
    
    public static int getTime(){
        return simulatedTime;
    }
    
}
    

/*
- 1000 simulated seconds long
- Each simulated second will be 1 simulatedSecondRate (100ms/10seconds)



1000 (10 seconds long)
To_floor     Num_people   Simulated_rate
(dest)           
  2             4              100;                 5      2     300
  3             0              500;                 1      4     200
  5             0              200;                 2      1     500;   3     3     600
  4             0              200
  2             3              300;                 6      2     100;   4     0     40


Each elevator will run in its own thread.
- elevator constantly checks building and passenger state (NO SLEEPING)

- elevators start on 0 floor

- only go to load if FINISHED

- GO UP PREFERENCE
- CLOSEST FLOOR FIRST

- IF IDLE, CONSTANTLY CHECKING 

- "No two elevators will approach a floor for passenger pickup at the same
time."

- Pick up passengers only if passengers are waiting and no other elevator is going to pick up

- 10 simulated seconds to load / unload passengers.
- 5 simulated seconds to travel each floor

- NO MAX CAPACITY FOR # OF PASSENGERS


elevator 2 needs to drop people of at level 4 and 5
its not done even after unloading at level 4
*/

