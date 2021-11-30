package transit_connection;

import java.util.*;

public class Line {
    private LineName name;
    private ArrayList<Time> startingTimes;
    private StopName firstStop;
    private ArrayList<LineSegment> lineSegments;

    public Line(LineName name, ArrayList<Time> startingTimes, StopName firstStop, ArrayList<LineSegment> lineSegments){
        this.name = name;
        this.startingTimes = startingTimes;
        this.firstStop = firstStop;
        this.lineSegments = new ArrayList<>(lineSegments);
    }

    public void updateReachable(Time time, StopName stop){
        for (int i = 0; i < lineSegments.size(); i++){
            if (lineSegments.get(i).getName() == stop){

            }
        }
    }

    public Stop updateCapacityAndGetPreviousStop(Time time, StopName stop){

        return ;
    }
}
