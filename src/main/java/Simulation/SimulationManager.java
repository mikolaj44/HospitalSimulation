package Simulation;

import Person.*;

import java.util.ArrayList;

public class SimulationManager {

    private static Simulation simulation;
    private static Setup setup;

    public static void buildSimulation(ArrayList<Department> departments, Setup setup){
        setSimulation(new Simulation(departments, setup));
    }

    public static void buildSimulation(ArrayList<Department> departments){
        setSimulation(new Simulation(departments));
    }

    public static void startSimulation(){
        simulation.start();
    }

    public static ArrayList<Patient> getPatients(){
        return simulation.getPatients();
    }

    public static ArrayList<Doctor> getDoctors(){
        return simulation.getDoctors();
    }

    public static ArrayList<Department> getDepartments(){
        return simulation.getDepartments();
    }

    public ArrayList<GenerationMethod> getGenerationMethods() {
        return simulation.getGenerationMethods();
    }

    public ArrayList<DepartmentAssignmentMethod> getAssignmentMethods() {
        return simulation.getAssignmentMethods();
    }

    public int getDepartmentAssignmentMethodIndex() {
        return simulation.getDepartmentAssignmentMethodIndex();
    }

    public void setDepartmentAssignmentMethodIndex(int index) {
        simulation.setDepartmentAssignmentMethodIndex(index);
    }

    public int getDeceased(){
        return simulation.getDeceased();
    }

    public int getRecovered(){
        return simulation.getRecovered();
    }

    public static Simulation getSimulation() {
        return simulation;
    }

    public static void setSimulation(Simulation simulation) {
        SimulationManager.simulation = simulation;
        setSetup(simulation.getSetup());
    }

    public static Setup getSetup() {
        return setup;
    }

    public static void setSetup(Setup setup) {
        SimulationManager.setup = setup;
    }
}