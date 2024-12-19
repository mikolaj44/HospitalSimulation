package Simulation;

import Person.LifeStats;

import java.util.ArrayList;

public class Setup {

    private int simulationSpeed;
    private boolean generatePatientsAutomatically;
    private boolean diagnosePatientsAutomatically;
    private ArrayList<Department> departments;

    // parametry do generacji:

    private int maxIllnessAmount;
    private int numberOfShifts;
    private int minDoctorSkill;
    private int maxDoctorSkill;
    private LifeStats<Integer> minLifeStats;
    private LifeStats<Integer> maxLifeStats;

    public Setup(int maxIllnessAmount, int simulationSpeed, boolean generatePatientsAutomatically, boolean diagnosePatientsAutomatically, ArrayList<Department> departments, int numberOfShifts, int minDoctorSkill, int maxDoctorSkill, LifeStats<Integer> minLifeStats, LifeStats<Integer> maxLifeStats) {
        this.maxIllnessAmount = maxIllnessAmount;
        this.simulationSpeed = simulationSpeed;
        this.generatePatientsAutomatically = generatePatientsAutomatically;
        this.diagnosePatientsAutomatically = diagnosePatientsAutomatically;
        this.departments = departments;
        this.numberOfShifts = numberOfShifts;
        this.minDoctorSkill = minDoctorSkill;
        this.maxDoctorSkill = maxDoctorSkill;
        this.minLifeStats = minLifeStats;
        this.maxLifeStats = maxLifeStats;
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

    public int getMaxIllnessAmount() {
        return maxIllnessAmount;
    }

    public void setMaxIllnessAmount(int maxIllnessAmount) {
        this.maxIllnessAmount = maxIllnessAmount;
    }

    public int getSimulationSpeed() {
        return simulationSpeed;
    }

    public boolean isGeneratePatientsAutomatically() {
        return generatePatientsAutomatically;
    }

    public boolean isDiagnosePatientsAutomatically() {
        return diagnosePatientsAutomatically;
    }

    public int getNumberOfShifts() {
        return numberOfShifts;
    }

    public int getMinDoctorSkill() {
        return minDoctorSkill;
    }

    public int getMaxDoctorSkill() {
        return maxDoctorSkill;
    }

    public LifeStats<Integer> getMinLifeStats() {
        return minLifeStats;
    }

    public LifeStats<Integer> getMaxLifeStats() {
        return maxLifeStats;
    }

    public void setNumberOfShifts(int numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }

    public void setMinDoctorSkill(int minDoctorSkill) {
        this.minDoctorSkill = minDoctorSkill;
    }

    public void setMaxDoctorSkill(int maxDoctorSkill) {
        this.maxDoctorSkill = maxDoctorSkill;
    }

    public void setMinLifeStats(LifeStats<Integer> minLifeStats) {
        this.minLifeStats = minLifeStats;
    }

    public void setMaxLifeStats(LifeStats<Integer> maxLifeStats) {
        this.maxLifeStats = maxLifeStats;
    }

    public ArrayList<Department> getDepartments() {
        return new ArrayList<>(departments);
    }
}
