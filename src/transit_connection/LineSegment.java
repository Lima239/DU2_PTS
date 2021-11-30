package transit_connection;

import java.util.*;

public class LineSegment {
    private TimeDiff timeToNextStop;
    private Map<Time, Integer> numberOfPassengers;
    private int capacity;
    private LineName lineName;
    private Stop nextStop;

    public LineSegment(TimeDiff timeToNextStop, int capacity, LineName lineName, Stop nextStop){
        this.timeToNextStop = timeToNextStop;
        this.capacity = capacity;
        this.lineName = lineName;
        this.nextStop = nextStop;
        numberOfPassengers = new HashMap<>();
    }

    public Dictionary<Time, StopName> nextStop(Time startTime){
        Dictionary<Time, StopName> tmp = new Hashtable();
        tmp.put(new Time(startTime.getTime().plus(timeToNextStop.getDifference())), nextStop.getName());
        return tmp;
    }

    public Tuple nextStopAndUpdateReachable(Time startTime){
        if (!numberOfPassengers.containsKey(startTime)){
            numberOfPassengers.put(startTime,0);
        }
        boolean enoughSpace = numberOfPassengers.get(startTime) < capacity;
        if(enoughSpace) nextStop.updateReachableAt(new Time(startTime.getTime().plus(timeToNextStop.getDifference())),
                Optional.ofNullable(lineName));
        return new Tuple(new Time(startTime.getTime().plus(timeToNextStop.getDifference())), nextStop.getName(),
                enoughSpace);
    }

    public void incrementCapacity(Time startingTime){
        numberOfPassengers.put(startingTime, numberOfPassengers.get(startingTime) + 1);
    }

    public StopName getName(){
        return nextStop.getName();
    }
}
