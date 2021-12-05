package transit_connection;

import java.time.LocalTime;
import java.util.*;

public class Line implements LineInterface{
    private LineName name;
    private ArrayList<Time> startingTimes;
    private StopName firstStop;
    private ArrayList<LineSegmentInterface> lineSegments;

    public Line(LineName name, ArrayList<Time> startingTimes, StopName firstStop, ArrayList<LineSegmentInterface> lineSegments){
        this.name = name;
        this.startingTimes = new ArrayList<>(startingTimes);
        this.firstStop = firstStop;
        this.lineSegments = new ArrayList<>(lineSegments);
    }

    public void updateReachable(Time time, StopName stop){
        Time timeTmp = startingTimes.get(0);
        StopName nextStop = firstStop;
        int counter = 0;
        int startingLineSegment = 0;
        while (!Objects.equals(nextStop, stop)) {
            Dictionary<Time, StopName> info = lineSegments.get(counter).nextStop(timeTmp);
            timeTmp = info.keys().nextElement();
            nextStop = info.elements().nextElement();
            startingLineSegment++;
            counter++;
        }
        TimeDiff timeDiff = new TimeDiff(timeTmp.getTime(), startingTimes.get(0).getTime());//total time difference
        Time earliest = new Time(startingTimes.get(0).getTime().plus(timeDiff.getDifference()));
        int earliestIndex = 0;
        int counter2 = 1;
        while(earliest.getTime().compareTo(time.getTime()) < 0) {
            if (counter2 >= startingTimes.size()) return;
            earliest = new Time(startingTimes.get(counter2).getTime().plus(timeDiff.getDifference()));
            earliestIndex++;
            counter2++;
        }
        while (startingLineSegment < lineSegments.size()) {
            Tuple info = lineSegments.get(startingLineSegment).nextStopAndUpdateReachable(earliest);
            if (!info.getBool()) {
                earliestIndex++;
                if (earliestIndex >= startingTimes.size()) return;
                TimeDiff wait = new TimeDiff(startingTimes.get(earliestIndex).getTime(),
                        startingTimes.get(earliestIndex-1).getTime());
                earliest = new Time(earliest.getTime().plus(wait.getDifference()));
                continue;
            }
            earliest = info.getTime();
            startingLineSegment++;
        }
    }

    public StopName updateCapacityAndGetPreviousStop(Time time, StopName stop){
        TimeDiff timeDiff = new TimeDiff(LocalTime.of(0,0),LocalTime.of(0,0));
        Time startingTime = startingTimes.get(0);
        StopName nextStop = firstStop;
        StopName previousStop = null;
        int counter = 0;

        while(!nextStop.equals(stop)){
            Dictionary<Time, StopName> info = lineSegments.get(counter).nextStop(startingTime);
            Time tmpTime = info.keys().nextElement();
            timeDiff = new TimeDiff(tmpTime.getTime(), startingTime.getTime());
            startingTime = tmpTime;
            previousStop = nextStop;
            nextStop = info.elements().nextElement();
            counter++;
        }
        counter--;

        Time thisTime = new Time(time.getTime().minus(timeDiff.getDifference()));
        lineSegments.get(counter).incrementCapacity(thisTime);
        return previousStop;
    }
}
