package Simulation;

import Person.LifeStats;

public class Department {

    private String name;
    private int amountOfPatients;
    private int maxAmountOfPatients;
    private LifeStats<Double> statsMultiplier;

    public Department(String name, int amountOfPatients, int maxAmountOfPatients, LifeStats<Double> statsMultiplier) {
        this.name = name;
        this.amountOfPatients = amountOfPatients;
        this.maxAmountOfPatients = maxAmountOfPatients;
        this.statsMultiplier = statsMultiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfPatients() {
        return amountOfPatients;
    }

    public void setAmountOfPatients(int amountOfPatients) {
        this.amountOfPatients = amountOfPatients;
    }

    public int getMaxAmountOfPatients() {
        return maxAmountOfPatients;
    }

    public void setMaxAmountOfPatients(int maxAmountOfPatients) {
        this.maxAmountOfPatients = maxAmountOfPatients;
    }

    public LifeStats<Double> getStatsMultiplier() {
        return statsMultiplier;
    }

    public void setStatsMultiplier(LifeStats<Double> statsMultiplier) {
        this.statsMultiplier = statsMultiplier;
    }
}
