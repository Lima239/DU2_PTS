package transit_connection;

import java.time.LocalTime;
import java.util.*;

public class Stops implements StopsInterface{
    private DataLoaderInterface dataLoader;
    private Map<StopName, StopInterface> stops;

    public Stops(DataLoaderInterface dataLoader) {
        this.dataLoader = dataLoader;
        this.stops = new HashMap<>();
    }

    public Optional<Map.Entry<StopName, Time>> earliestReachableStopAfter(Time time) {
        Time minTime = new Time(LocalTime.of(23,59,59,999999999));
        for (StopName stop : stops.keySet()) {
            Map.Entry<Time, LineName> info = stops.get(stop).getReachableAt();
            Time reachableTime = info.getKey();
            if (time.getTime().compareTo(reachableTime.getTime()) < 0) {
                if (reachableTime.getTime().compareTo(minTime.getTime()) < 0) minTime = reachableTime;
            }
        }

        StopName earliestReachable = null;
        for (StopName stop : stops.keySet()) {
            Map.Entry<Time, LineName> info = stops.get(stop).getReachableAt();
            if (info.getKey().getTime().equals(minTime.getTime())) earliestReachable = stop;
        }
        Map.Entry<StopName, Time> result = new AbstractMap.SimpleEntry<>(earliestReachable, minTime);

        return Optional.of(result);
    }

    public void setStartingStop(StopName stop, Time time) throws IllegalArgumentException {
        if (!stops.containsKey(stop)) {
            Stop s = dataLoader.loadStop(stop);
            if (s == null) throw new IllegalArgumentException("Stop does not exist.");
            stops.put(stop, s);
        }
        stops.get(stop).updateReachableAt(time, null);
    }

    public List<LineName> getLines(StopName stop) throws IllegalArgumentException {
        if (!stops.containsKey(stop)){
            Stop s = dataLoader.loadStop(stop);
            if (s == null)  throw new IllegalArgumentException("Stop does not exist.");
            stops.put(stop, s);
        }
        return stops.get(stop).getLines();
    }

    public Map.Entry<Time, LineName> getReachableAt(StopName stop) throws NoSuchElementException{
        if(!stops.containsKey(stop)) throw new NoSuchElementException("Stop hasn't been loaded.");
        return stops.get(stop).getReachableAt();
    }

    public void clean(){
        stops = new HashMap<>();
    }

}
