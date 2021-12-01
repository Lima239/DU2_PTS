package transit_connection;

import java.util.*;

public interface StopsInterface {
    Optional<Map.Entry<StopName, Time>> earliestReachableStopAfter(Time time);
    void setStartingStop(StopName stop, Time time) throws IllegalArgumentException;
    List<LineName> getLines(StopName stop) throws IllegalArgumentException;
    Map.Entry<Time, LineName> getReachableAt(StopName stop) throws NoSuchElementException;
    void clean();
}
