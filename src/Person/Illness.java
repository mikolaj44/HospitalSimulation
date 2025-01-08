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

    public String toString() {

        String output = "+--------------+\n";
        output += "Nazwa choroby: " + name + "\n";
        output += "Statystyki: " + statsMultiplier;

        return output;
    }

    public boolean isCured() {
        return (this.statsMultiplier.getPhysical() <= 0 && this.statsMultiplier.getInfection() <= 0 && this.statsMultiplier.getInternal() <= 0);
    }
}