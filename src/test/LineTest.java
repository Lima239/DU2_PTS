package test;

import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import transit_connection.*;
import java.time.LocalTime;
import java.util.*;

public class LineTest {
    private LineName name = new LineName("linka1");;
    private ArrayList<Time> startingTimes = new ArrayList<>(Arrays.asList(new Time(LocalTime.of(10,15)),
            new Time(LocalTime.of(10,20))));
    private StopName firstStop = new StopName("stop 1");;
    private ArrayList<LineSegmentInterface> lineSegments = new ArrayList<>();
    private StopName nextStop = new StopName("stop 2");
    private final TimeDiff lineSegmentsTimeDiff = new TimeDiff(LocalTime.of(10,15), LocalTime.of(10,20));
    private Line line;
    private  List<Dictionary<Time, LineName>> stopsData;
    private List<Boolean> lineSegmentCapacityUpdated;

    @Before
    public void setUp() {
        lineSegments.add(new LineSegmentInterface() {
            int number = 0;
            @Override
            public Dictionary<Time, StopName> nextStop(Time startTime) {
                Time time = new Time(startTime.getTime().plus(lineSegmentsTimeDiff.getDifference()));
                Dictionary<Time, StopName> tmp = new Hashtable();
                tmp.put(time, nextStop);
                return tmp;
            }

            @Override
            public Tuple nextStopAndUpdateReachable(Time startTime) {
                Time time = new Time(startTime.getTime().plus(lineSegmentsTimeDiff.getDifference()));
                Dictionary<Time, LineName> tmp = new Hashtable();
                tmp.put(time, name);
                stopsData.set(number + 1, tmp);
                return new Tuple(time, nextStop, true);
            }

            @Override
            public void incrementCapacity(Time startTime) {
                lineSegmentCapacityUpdated.set(number, true);
            }

        });

        line = new Line(name, startingTimes, firstStop, lineSegments);
    }

    @Test
    public void updateCapacityAndGetPreviousStopTest() {
        StopName data = line.updateCapacityAndGetPreviousStop(new Time(LocalTime.of(10,0)),
                new StopName("stop 1"));
        assertEquals(data.get(), new StopName("stop 1").get());
    }
}
