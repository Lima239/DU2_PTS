import transit_connection.*;
import org.junit.*;

import java.time.LocalTime;
import java.util.*;
import static org.junit.Assert.*;

public class LineSegmentTest {
    private TimeDiff timeToNextStop = new TimeDiff(LocalTime.of(10,20),LocalTime.of(10,30));
    private Map<Time, Integer> numberOfPassengers = new HashMap<>();
    private int capacity = 1;
    private LineName lineName = new LineName("linka 1");
    private Stop nextStop = new Stop(new StopName("zochova"), List.of(new LineName("linka1"), new LineName("linka2")));
    private LineSegment lineSegment;
    private Time startTime = new Time(LocalTime.of(10,0));

    @Before
    public void setUp() {
        lineSegment = new LineSegment(timeToNextStop, capacity ,lineName,nextStop);
    }

    @Test
    public void initTest() {
        assertEquals("zochova", nextStop.getName());
    }

    @Test
    public void nextStopTest() {
        Dictionary<Time, StopName> tmpStop = new Hashtable();
        tmpStop.put(new Time(startTime.getTime().plus(timeToNextStop.getDifference())), new StopName("zochova"));
        assertEquals(tmpStop, lineSegment.nextStop(startTime));
    }

    @Test
    public void nextStopAndUpdateReachableTest() {
        Tuple tuple = lineSegment.nextStopAndUpdateReachable(startTime);
        assertEquals(new Time(startTime.getTime().plus(timeToNextStop.getDifference())), tuple.getTime());
        assertTrue(tuple.getBool());
        assertEquals(new StopName("zochova"),tuple.getStopName());
    }

}
