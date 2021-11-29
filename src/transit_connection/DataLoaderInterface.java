package transit_connection;

public interface DataLoaderInterface {
    Stop loadStop(StopName stop);
    Line loadLine(LineName line);
}
