package transit_connection;

import java.time.LocalTime;

public class Time {
    private LocalTime time;

    public Time(LocalTime time){
        this.time = time;
    }

    public LocalTime getTime(){
        return this.time;
    }

    public void setTime(LocalTime time){
        this.time = time;
    }
}
