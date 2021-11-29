package transit_connection;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Stops {
    private DataLoaderInterface dataLoader;
    private Map<StopName, Stop> stops;

    public Stops(DataLoaderInterface dataLoader) {
        this.dataLoader = dataLoader;
    }

    public Optional<Map.Entry<String, Integer>> earliestReachableStopAfter(int time) {
        return Optional.of(Map.entry("", 0));
    }

    public boolean setStartingStop(StopName stop, Time time) {
        if (!stops.containsKey(stop)) {
            Stop s = dataLoader.loadStop(stop);
            if (s == null)
                return false;
            stops.put(stop, s);
        }
        stops.get(stop).updateReachableAt(time, null);

        return true;
    }

    public List<LineName> getLines(StopName stop) {
        return stops.get(stop).getLines();
    }

    public Map.Entry<Integer, String> getReachableAt(String stop) {
        return Map.entry(0, "");
    }

}
