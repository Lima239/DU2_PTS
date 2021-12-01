package transit_connection;

import java.util.Dictionary;

public interface LineSegmentInterface {
    Dictionary<Time, StopName> nextStop(Time startTime);
    Tuple nextStopAndUpdateReachable(Time startTime);
    void incrementCapacity(Time startingTime);
}
