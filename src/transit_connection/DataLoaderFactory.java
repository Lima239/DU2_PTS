package transit_connection;

import java.util.List;

public interface DataLoaderFactory {
    public List<Line> loadLines(StopName stop);
    public Line loadLine(LineName line);
    public Stop loadStop(StopName stop);
}
