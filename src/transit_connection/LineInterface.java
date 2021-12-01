package transit_connection;

public interface LineInterface {
    void updateReachable(Time time, StopName stop);
    StopName updateCapacityAndGetPreviousStop(Time time, StopName stop);
}
