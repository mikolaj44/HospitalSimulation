public class Illness {

    String name;
    LifeStats<Double> stats;

    public Illness(String name, LifeStats<Double> stats) {
        this.name = name;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LifeStats<Double> getStats() {
        return stats;
    }

    public void setStats(LifeStats<Double> stats) {
        this.stats = stats;
    }
}