package transit_connection;

import java.util.*;

public class Stop implements StopInterface{
    private StopName name;
    private List<LineName> lines;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;

    public Stop(StopName name, List<LineName> lines) {
        this.name = name;
        this.lines = Collections.unmodifiableList(lines);
    }

    public void updateReachableAt(Time time, Optional<LineName> line) {
        this.reachableAt = Optional.of(time);
        this.reachableVia = line;
    }

    public Map.Entry<Time, LineName> getReachableAt() {
        return Map.entry(this.reachableAt.get(), this.reachableVia.get());
    }

    public List<LineName> getLines() {
        return this.lines;
    }

    public StopName getName(){
        return this.name;
    }
}
