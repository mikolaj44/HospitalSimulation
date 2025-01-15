package Simulation;
public class TimeOfDay {
    private int hours;
    private int minutes;
    public TimeOfDay(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }
    public void addMinutes(int minutes){
        if(this.minutes + minutes >= 60){
            setHours(this.hours + (this.minutes + minutes) / 60);
        }
        setMinutes(this.minutes + minutes);
    }
    public void addHours(int hours){
        setHours(this.hours + hours);
    }
    public String toString(){
        return hours + ":" + String.format("%02d", minutes);
    }
    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        if(hours < 0 || hours >= 24){
            this.hours = 0;
        }
        else {
            this.hours = hours;
        }
    }
    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int minutes) {
        if(minutes < 0 || minutes >= 60){
            this.minutes = 0;
        }
        else {
            this.minutes = minutes;
        }
    }
}