package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import transit_connection.*;
import java.time.LocalTime;
import java.util.*;

public class StopTest {

    private List<LineName> lines = List.of(new LineName("31"), new LineName("39"), new LineName("212"),
            new LineName("204"));
    private Stop stop;
    private StopName stopName = new StopName("Zochova");

    @Before
    public void setUp() {
        stop = new Stop(stopName, lines);
    }

    @Test
    public void initTest() {
        assertEquals(lines, stop.getLines());
        assertEquals(stopName, stop.getName());
    }

    @Test
    public void getReachableTest() {
        stop.updateReachableAt(new Time(LocalTime.of(10,10)), Optional.of(new LineName("linka 1")));
        Map.Entry<Time, LineName> data = stop.getReachableAt();
        assertEquals(data.getKey().getTime(), new Time(LocalTime.of(10,10)).getTime());
        assertEquals(data.getValue().get(), data.getValue().get());
    }

    @Test
    public void updateReachableTest() {
        stop.updateReachableAt(new Time(LocalTime.of(18,45)), Optional.of(new LineName("4")));
        Map.Entry<Time, LineName> data = stop.getReachableAt();
        assertEquals(data.getKey().getTime(), new Time(LocalTime.of(18,45)).getTime());
        assertEquals(data.getValue().get(),new LineName("4").get());

        stop.updateReachableAt(new Time(LocalTime.of(18,50)), Optional.of(new LineName("9")));
        data = stop.getReachableAt();
        assertEquals(data.getKey().getTime(), new Time(LocalTime.of(18,50)).getTime());
        assertEquals(data.getValue().get(), new LineName("9").get());

        stop.updateReachableAt(new Time(LocalTime.of(18,56)), Optional.of(new LineName("11")));
        data = stop.getReachableAt();
        assertEquals(data.getKey().getTime(), new Time(LocalTime.of(18,56)).getTime());
        assertEquals(data.getValue().get(), new LineName("11").get());
    }
}
