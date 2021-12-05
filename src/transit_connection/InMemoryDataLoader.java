package transit_connection;

import java.util.*;

public class InMemoryDataLoader implements DataLoaderInterface{
    private Map<StopName, List<LineName>> stopsInMemory;
    private Map<LineName, Dictionary<ArrayList<Time>, StopName>> linesInMemory;
    private Map<LineName, ArrayList<LineSegmentInterface>> lineSegmentsInMemory;

    public InMemoryDataLoader(Map<StopName, List<LineName>> stopsInMemory, Map<LineName, Dictionary<ArrayList<Time>, StopName>> linesInMemory,
        Map<LineName, ArrayList<LineSegmentInterface>> lineSegmentsInMemory){
        this.stopsInMemory = stopsInMemory;
        this.lineSegmentsInMemory = lineSegmentsInMemory;
        this.linesInMemory = linesInMemory;
    }

    @Override
    public Stop loadStop(StopName stop) {
        if (!stopsInMemory.containsKey(stop)) return null;
        return new Stop(stop, stopsInMemory.get(stop));
    }

    @Override
    public Line loadLine(LineName line) {
        if (!linesInMemory.containsKey(line)) return null;
        Dictionary<ArrayList<Time>,StopName> lineData = linesInMemory.get(line);
        return new Line(line, lineData.keys().nextElement(), lineData.elements().nextElement(), lineSegmentsInMemory.get(line));
    }
}
