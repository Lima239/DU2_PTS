package transit_connection;

import java.util.*;

public class ConnectionSearch {
    private Stops stops;
    private Lines lines;

    public ConnectionSearch(DataLoaderInterface dataLoader) {
        this.stops = new Stops(dataLoader);
        this.lines = new Lines(dataLoader);
    }

    public ConnectionData search(StopName from, StopName to, Time time) {
        StopName currStop = from;
        Time currTime = time;
        stops.setStartingStop(currStop, currTime);

        while (currStop.get() != to.get()) {
            List<LineName> out_lines = stops.getLines(currStop);
            lines.updateReachable(out_lines, currStop, currTime);
            Optional<Map.Entry<StopName, Time>> res = stops.earliestReachableStopAfter(currTime);
            if (res.isEmpty())
                return null;
            currStop = res.get().getKey();
            currTime = res.get().getValue();
        }
        currStop = to;
        ConnectionData data = new ConnectionData();
        while (currStop.get() != from.get()) {
            Map.Entry<Time, LineName> res = stops.getReachableAt(currStop);
            currStop = lines.updateCapacityAndGetPreviousStop(res.getValue(), currStop, res.getKey());
            data.getRoute().add(0, Map.entry(currStop, res.getKey()));
        }

        this.stops.clean();
        this.lines.clean();

        return data;
    }
}
