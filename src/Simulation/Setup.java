package Simulation;

import Person.Doctor;
import Person.LifeStats;

import java.util.ArrayList;

public class Setup {

    private int delayMs = 2000;
    private boolean generatePatientsAutomatically = true;
    private boolean diagnosePatientsAutomatically = true; // nieużywane
    private ArrayList<Department> departments;

    // parametry do generacji:

    private int maxNumberOfDoctorsPerPatient = 3; // ile lekarzy może leczyć jednego pacjenta

    private int minNumberOfDoctors = 10; // ile ogólnie lekarzy w szpitalu
    private int maxNumberOfDoctors = 20;

    private int minNumberOfPatients = 5; // ile ogólnie pacjentów w szpitalu
    private int maxNumberOfPatients = 10;

    // można potem dodać ewentualnie min/max tutaj:
    private int maxIllnessAmount = 3;
    private int numberOfShifts = 3; // na razie nieużywane

    private int minDoctorSkill = 0;
    private int maxDoctorSkill = 100; // for now

    private LifeStats<Integer> minLifeStats = new LifeStats<>(1,1,1);
    private LifeStats<Integer> maxLifeStats = new LifeStats<>(100,100,100);

    public Setup(int maxIllnessAmount, int delayMs, boolean generatePatientsAutomatically, boolean diagnosePatientsAutomatically, ArrayList<Department> departments, int numberOfShifts, int minDoctorSkill, int maxDoctorSkill, LifeStats<Integer> minLifeStats, LifeStats<Integer> maxLifeStats) {
        this.maxIllnessAmount = maxIllnessAmount;
        this.delayMs = delayMs;
        this.generatePatientsAutomatically = generatePatientsAutomatically;
        this.diagnosePatientsAutomatically = diagnosePatientsAutomatically;
        this.departments = departments;
        this.numberOfShifts = numberOfShifts;
        this.minDoctorSkill = minDoctorSkill;
        this.maxDoctorSkill = maxDoctorSkill;
        this.minLifeStats = minLifeStats;
        this.maxLifeStats = maxLifeStats;
    }

    public Setup(ArrayList<Department> departments){

        this.departments = departments;
    }

    public int getMaxNumberOfDoctorsPerPatient() {
        return maxNumberOfDoctorsPerPatient;
    }

    public void setMaxNumberOfDoctorsPerPatient(int maxNumberOfDoctorsPerPatient) {
        this.maxNumberOfDoctorsPerPatient = maxNumberOfDoctorsPerPatient;
    }

    public int getMinNumberOfPatients() {
        return minNumberOfPatients;
    }

    public void setMinNumberOfPatients(int minNumberOfPatients) {
        this.minNumberOfPatients = minNumberOfPatients;
    }

    public int getMaxNumberOfPatients() {
        return maxNumberOfPatients;
    }

    public void setMaxNumberOfPatients(int maxNumberOfPatients) {
        this.maxNumberOfPatients = maxNumberOfPatients;
    }

    public int getMinNumberOfDoctors() {
        return minNumberOfDoctors;
    }

    public void setMinNumberOfDoctors(int minNumberOfDoctors) {
        this.minNumberOfDoctors = minNumberOfDoctors;
    }

    public int getMaxNumberOfDoctors() {
        return maxNumberOfDoctors;
    }

    public void setMaxNumberOfDoctors(int maxNumberOfDoctors) {
        this.maxNumberOfDoctors = maxNumberOfDoctors;
    }

    public ArrayList<Department> getDepartments() {
        return new ArrayList<>(departments);
    }

    public void addDepartment(Department department){
        this.departments.add(department);
    }

    public int getMaxIllnessAmount() {
        return maxIllnessAmount;
    }

    public void setMaxIllnessAmount(int maxIllnessAmount) {this.maxIllnessAmount = maxIllnessAmount;}

    public int getDelayMs() {
        return delayMs;
    }

    public void setDelayMs(int delayMs){
        this.delayMs = delayMs;
    }

    public boolean isGeneratingPatientsAutomatically() {
        return generatePatientsAutomatically;
    }

    public void toggleAutoDiagnose(){
        diagnosePatientsAutomatically = !diagnosePatientsAutomatically;
    }

    public boolean isDiagnosingPatientsAutomatically() {
        return diagnosePatientsAutomatically;
    }

    public void toggleAutoGenerate(){
        generatePatientsAutomatically = !generatePatientsAutomatically;
    }

    public LifeStats<Integer> getMinLifeStats() {
        return minLifeStats;
    }

    public void setMinLifeStats(LifeStats<Integer> minLifeStats) {
        this.minLifeStats = minLifeStats;
    }

    public LifeStats<Integer> getMaxLifeStats() {
        return maxLifeStats;
    }

    public void setMaxLifeStats(LifeStats<Integer> maxLifeStats) {
        this.maxLifeStats = maxLifeStats;
    }

    public int getNumberOfShifts() {
        return numberOfShifts;
    }

    public void setNumberOfShifts(int numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }

    public int getMinDoctorSkill() {
        return minDoctorSkill;
    }

    public void setMinDoctorSkill(int minDoctorSkill) {
        this.minDoctorSkill = minDoctorSkill;
    }

    public int getMaxDoctorSkill() {
        return maxDoctorSkill;
    }

    public void setMaxDoctorSkill(int maxDoctorSkill) {
        this.maxDoctorSkill = maxDoctorSkill;
    }
}