package transit_connection;

import java.util.*;

public class Line {
    private LineName name;
    private ArrayList<Time> startingTimes;
    private String firstStop;

    public Line(LineName name, ArrayList<Time> startingTimes, String firstStop){
        this.name = name;
        this.startingTimes = startingTimes;
        this.firstStop = firstStop;
    }

    public void updateReachable(Time time, StopName stop){

    }

    public String updateCapacityAndGetPreviousStop(Time time, String stop){
        return "";
    }
}
