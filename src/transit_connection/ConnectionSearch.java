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
        stops.setStartingStop(from, time);
        List<LineName> from_lines = stops.getLines(from);
        lines.updateReachable(from_lines, from, time);
        return new ConnectionData();
    }
}
