package transit_connection;

import java.util.*;

public interface StopInterface {
    void updateReachableAt(Time time, Optional<LineName> line);
    Map.Entry<Time, LineName> getReachableAt();
    List<LineName> getLines();
    StopName getName();
}
