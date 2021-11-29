package transit_connection;

import java.time.Duration;
import java.time.LocalTime;

public class TimeDiff {
    private Duration duration;

    public TimeDiff(LocalTime timeBegin, LocalTime timeEnd){
        this.duration = Duration.between(timeBegin, timeEnd);
    }

    public Duration getDifference(){
        return duration;
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }
}
