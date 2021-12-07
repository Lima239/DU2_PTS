package transit_connection;

public interface DBInterface {
    void loadLineAndFirstStop(LineName line);
    void loadStop(StopName stopName);
}
