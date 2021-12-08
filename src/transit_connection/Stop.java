package transit_connection;

import java.util.*;

public class Stop implements StopInterface{
    //mohla byt tato premenna final
    private StopName name;
    //mohla byt tato premenna final
    private List<LineName> lines;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;

    public Stop(StopName name, List<LineName> lines) {
        this.name = name;
        this.lines = Collections.unmodifiableList(lines);
    }

    public void updateReachableAt(Time time, Optional<LineName> line) {
        //mohla si skontrolovat, ci time nie je null
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
