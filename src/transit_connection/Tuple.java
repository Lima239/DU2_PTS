package transit_connection;

public class Tuple {
    private Time time;
    private StopName stopName;
    private boolean bool;

    public Tuple(Time time, StopName stopName, boolean bool){
        this.time = time;
        this.stopName = stopName;
        this.bool = bool;
    }

    public Time getTime(){
        return time;
    }
    public boolean getBool(){
        return bool;
    }

    public StopName getStopName(){
        return stopName;
    }
}
