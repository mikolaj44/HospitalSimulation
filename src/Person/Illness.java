package Person;

public class Illness {

    String name;
    LifeStats<Double> statsMultiplier;

    public Illness(String name, LifeStats<Double> stats) {
        this.name = name;
        this.statsMultiplier = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LifeStats<Double> getStats() {
        return statsMultiplier;
    }

    public void setStats(LifeStats<Double> stats) {
        this.statsMultiplier = stats;
    }
}