package Person;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

        String output = "Nazwa choroby: " + name + "\n";
        output += "Statystyki: " + "\n" + statsMultiplier;
        output += "\n" + "+--------------+" + "\n";

        return output;
    }

    public boolean isCured() {
        return (this.statsMultiplier.getPhysical() <= 0 && this.statsMultiplier.getInfection() <= 0 && this.statsMultiplier.getInternal() <= 0);
    }
}