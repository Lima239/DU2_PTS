package transit_connection;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lines implements LinesInterface{
    private DataLoaderInterface dataLoader;
    private Map<LineName, LineInterface> lines;

    public Lines(DataLoaderInterface dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void updateReachable(List<LineName> lines, StopName stop, Time time) {
        for (LineName line : lines) {
            if (!lines.contains(line)) {
                this.lines.put(line, dataLoader.loadLine(line));
            }
            this.lines.get(line).updateReachable(time, stop);
        }
    }

    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time) {
        return this.lines.get(line).updateCapacityAndGetPreviousStop(time,stop);
    }

    public void clean(){
        lines = new HashMap<>();
    }
}
