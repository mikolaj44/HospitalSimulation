package Simulation;

public class SimulationManager {

    private static Simulation simulation;

    public static Simulation getSimulation() {
        return simulation;
    }

    public static void setSimulation(Simulation simulation) {
        SimulationManager.simulation = simulation;
    }
}