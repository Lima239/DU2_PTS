package test;

import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import transit_connection.*;
import java.time.LocalTime;
import java.util.*;

public class InMemoryIntegrationTest {
    private final ArrayList<Time> startingTimes = (ArrayList<Time>) List.of(new Time(LocalTime.of(10,10)),
            new Time(LocalTime.of(10,20)), new Time(LocalTime.of(10,30)));
    private Map<StopName, List<LineName>> stopsInMemory  = Map.ofEntries(
            Map.entry(new StopName("stop A"), List.of(new LineName("linka1"), new LineName("linka2"))),
            Map.entry(new StopName("stop B"), List.of(new LineName("linka1"))),
            Map.entry(new StopName("stop C"), List.of(new LineName("linka1"), new LineName("linka2"))),
            Map.entry(new StopName("stop D"), List.of(new LineName("linka2"))),
            Map.entry(new StopName("stop X"), List.of()),
            Map.entry(new StopName("stop Y"), List.of())
            );
    private Map<ArrayList<Time>, StopName> tmp = new HashMap<>();


    private Map<LineName, Dictionary<ArrayList<Time>, StopName>> linesInMemory =  Map.of(
            new LineName("linka1"),new HashMap<ArrayList<Time>, StopName>(startingTimes, new StopName("Stop A")),
            new LineName("linka2"), new HashMap<ArrayList<Time>, StopName>(startingTimes, new StopName("Stop C")));


    private Map<LineName, ArrayList<LineSegmentInterface>> lineSegmentsInMemory;

    @Before
    public void setUp() {

        tmp.put(startingTimes, new StopName("Stop A"));
        tmp.put(startingTimes, new StopName("Stop C"));
        linesInMemory = Map.of(
                new LineName("linka1"), tmp.get(0),
                new LineName("linka2"), new HashMap<ArrayList<Time>, StopName>(startingTimes, new StopName("Stop C")));
        }
    }
