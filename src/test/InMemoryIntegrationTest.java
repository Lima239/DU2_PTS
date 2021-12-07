package test;

import org.junit.Test;
import org.junit.*;
import transit_connection.*;
import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.assertEquals;

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
    private Dictionary<ArrayList<Time>, StopName> tmp1 = new Hashtable();
    private Dictionary<ArrayList<Time>, StopName> tmp2 = new Hashtable();
    private Map<LineName, Dictionary<ArrayList<Time>, StopName>> linesInMemory;
    private ConnectionSearch connectionSearch;
    private Map<LineName, ArrayList<LineSegmentInterface>> lineSegmentsInMemory;
    private DataLoaderInterface dataLoader;


    @Before
    public void setUp() {
        tmp1.put(startingTimes, new StopName("Stop A"));
        tmp2.put(startingTimes, new StopName("Stop C"));
        linesInMemory = Map.of(
                new LineName("linka1"), tmp1,
                new LineName("linka2"), tmp2);

        dataLoader = new InMemoryDataLoader(stopsInMemory, linesInMemory, lineSegmentsInMemory);
        StopsInterface stops = new Stops(dataLoader);
        LinesInterface lines = new Lines(dataLoader);
    }

    @Test
    public void dataLoaderTest(){
        assertEquals(new Stop(new StopName("stop A"), List.of(new LineName("linka1"), new LineName("linka2"))).getName()
                , dataLoader.loadStop(new StopName("stop A")).getName());
        assertEquals(new Stop(new StopName("stop B"), List.of(new LineName("linka1"))).getName()
                , dataLoader.loadStop(new StopName("stop B")).getName());
        assertEquals(new Stop(new StopName("stop C"), List.of(new LineName("linka1"), new LineName("linka2"))).getName()
                , dataLoader.loadStop(new StopName("stop C")).getName());
    }
}
