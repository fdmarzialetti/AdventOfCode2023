package Day6;

public class Race {
    private Long id;
    private Long raceTime;

    private Long lastRecord;

    public Race(Long id, Long raceTime, Long lastRecord) {
        this.id = id;
        this.raceTime = raceTime;
        this.lastRecord = lastRecord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(Long raceTime) {
        this.raceTime = raceTime;
    }

    public Long getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(Long lastRecord) {
        this.lastRecord = lastRecord;
    }
}
