package transit_connection;

import java.util.List;
import java.util.Map;

public class ConnectionData {
    private List<Map.Entry<StopName, Time>> route;

    public List<Map.Entry<StopName, Time>> getRoute() { return route; }

    public Time getOverallTime() {
        if (route.isEmpty())
            return null;
        return route.get(route.size() - 1).getValue();
    }
}
