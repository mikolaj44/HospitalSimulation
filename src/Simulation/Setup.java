package Simulation;

import java.util.ArrayList;

public class Setup {

    private int simulationSpeed;
    private boolean generatePatientsAutomatically;
    private boolean diagnosePatientsAutomatically;
    private ArrayList<Department> departments;

    public Setup(int simulationSpeed, boolean generatePatientsAutomatically, boolean diagnosePatientsAutomatically, ArrayList<Department> departments) {
        this.simulationSpeed = simulationSpeed;
        this.generatePatientsAutomatically = generatePatientsAutomatically;
        this.diagnosePatientsAutomatically = diagnosePatientsAutomatically;
        this.departments = (departments != null) ? departments : new ArrayList<>();
    }

    public void toggleAutoDiagnose(){
        diagnosePatientsAutomatically = !diagnosePatientsAutomatically;
    }
    public void toggleAutoGenerate(){
        generatePatientsAutomatically = !generatePatientsAutomatically;
    }
    public void setSimulationSpeed(int speed){
        simulationSpeed = speed;
    }
    public void addDepartment(Department department){
        this.departments.add(department);
    }

    public ArrayList<Department> getDepartments() {
        return new ArrayList<>(departments);
    }
}
