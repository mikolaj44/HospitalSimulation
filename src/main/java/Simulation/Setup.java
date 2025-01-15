package Simulation;

import Person.Doctor;
import Person.LifeStats;

import java.util.ArrayList;

public class Setup {

    private int delayMs = 2000;
    private int addedMinutesPerTick = 10;
    private boolean generatePatientsAutomatically = true;
    private boolean diagnosePatientsAutomatically = true; // nieużywane
    private ArrayList<Department> departments;

    // parametry do generacji:

    private int maxNumberOfDoctorsPerPatient = 3; // ile lekarzy może leczyć jednego pacjenta

    private int minNumberOfDoctors = 10; // ilu ogólnie lekarzy w szpitalu
    private int maxNumberOfDoctors = 20;

    private int minNumberOfPatients = 5; // ilu ogólnie pacjentów w szpitalu
    private int maxNumberOfPatients = 10;

    private int minNewPatientsPerIteration = 1; // ilu pacjentów generować co iterację symulacji
    private int maxNewPatientsPerIteration = 2;

    // można potem dodać ewentualnie min/max tutaj:
    private int maxIllnessAmount = 3;
    private int numberOfShifts = 3; // na razie nieużywane

    private LifeStats<Double> minDoctorModifiers = new LifeStats<>(0.0, 0.0, 0.0);
    private LifeStats<Double> maxDoctorModifiers = new LifeStats<>(3.0, 3.0, 3.0);

    private LifeStats<Integer> minLifeStats = new LifeStats<>(10, 10, 10);
    private LifeStats<Integer> maxLifeStats = new LifeStats<>(1000, 1000, 1000);

    private double departmentInfluenceFactor = 0.1;
    private double illnessInfluenceFactor = 0.1;

    public Setup(ArrayList<Department> departments, int delayMs, int addedMinutesPerTick, boolean generatePatientsAutomatically, boolean diagnosePatientsAutomatically, int maxNumberOfDoctorsPerPatient, int minNumberOfDoctors, int maxNumberOfDoctors, int minNumberOfPatients, int maxNumberOfPatients, int minNewPatientsPerIteration, int maxNewPatientsPerIteration, int maxIllnessAmount, int numberOfShifts, LifeStats<Double> minDoctorModifiers, LifeStats<Double> maxDoctorModifiers, LifeStats<Integer> minLifeStats, LifeStats<Integer> maxLifeStats) {        this.departments = departments;
        this.delayMs = delayMs;
        this.addedMinutesPerTick = addedMinutesPerTick;
        this.generatePatientsAutomatically = generatePatientsAutomatically;
        this.diagnosePatientsAutomatically = diagnosePatientsAutomatically;
        this.maxNumberOfDoctorsPerPatient = maxNumberOfDoctorsPerPatient;
        this.minNumberOfDoctors = minNumberOfDoctors;
        this.maxNumberOfDoctors = maxNumberOfDoctors;
        this.minNumberOfPatients = minNumberOfPatients;
        this.maxNumberOfPatients = maxNumberOfPatients;
        this.minNewPatientsPerIteration = minNewPatientsPerIteration;
        this.maxNewPatientsPerIteration = maxNewPatientsPerIteration;
        this.maxIllnessAmount = maxIllnessAmount;
        this.numberOfShifts = numberOfShifts;
        this.minDoctorModifiers = minDoctorModifiers;
        this.maxDoctorModifiers = maxDoctorModifiers;
        this.minLifeStats = minLifeStats;
        this.maxLifeStats = maxLifeStats;
    }

    public Setup(ArrayList<Department> departments) {

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

    public int getMinNewPatientsPerIteration() {
        return minNewPatientsPerIteration;
    }

    public void setMinNewPatientsPerIteration(int minNewPatientsPerIteration) {
        this.minNewPatientsPerIteration = minNewPatientsPerIteration;
    }

    public int getMaxNewPatientsPerIteration() {
        return maxNewPatientsPerIteration;
    }

    public void setMaxNewPatientsPerIteration(int maxNewPatientsPerIteration) {
        this.maxNewPatientsPerIteration = maxNewPatientsPerIteration;
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

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

    public int getMaxIllnessAmount() {
        return maxIllnessAmount;
    }

    public void setMaxIllnessAmount(int maxIllnessAmount) {
        this.maxIllnessAmount = maxIllnessAmount;
    }

    public int getDelayMs() {
        return delayMs;
    }

    public void setDelayMs(int delayMs) {
        this.delayMs = delayMs;
    }

    public boolean isGeneratingPatientsAutomatically() {
        return generatePatientsAutomatically;
    }

    public void setGeneratePatientsAutomatically(boolean value) {
        this.generatePatientsAutomatically = value;
    }

    public void toggleAutoDiagnose() {
        diagnosePatientsAutomatically = !diagnosePatientsAutomatically;
    }

    public boolean isDiagnosingPatientsAutomatically() {
        return diagnosePatientsAutomatically;
    }

    public void toggleAutoGenerate() {
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

    public void setMaxLifeStats(Integer maxLife) {
        this.maxLifeStats = new LifeStats<Integer>(maxLife, maxLife, maxLife);
    }
    public void setMinLifeStats(int minLife) {
        this.minLifeStats = new LifeStats<Integer>(minLife, minLife, minLife);
    }

    public int getNumberOfShifts() {
        return numberOfShifts;
    }

    public void setNumberOfShifts(int numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }

    public LifeStats<Double> getMinDoctorModifiers() {
        return minDoctorModifiers;
    }

    public void setMinDoctorModifiers(LifeStats<Double> minDoctorModifiers) {
        this.minDoctorModifiers = minDoctorModifiers;
    }

    public LifeStats<Double> getMaxDoctorModifiers() {
        return maxDoctorModifiers;
    }

    public void setMaxDoctorModifiers(LifeStats<Double> maxDoctorModifiers) {
        this.maxDoctorModifiers = maxDoctorModifiers;
    }

    public double getDepartmentInfluenceFactor() {
        return departmentInfluenceFactor;
    }
    public void setDepartmentInfluenceFactor(double departmentInfluenceFactor) {
        this.departmentInfluenceFactor = departmentInfluenceFactor;
    }

    public double getIllnessInfluenceFactor() {
        return illnessInfluenceFactor;
    }
    public void setIllnessInfluenceFactor(double illnessInfluenceFactor) {
        this.illnessInfluenceFactor = illnessInfluenceFactor;
    }

    public int getAddedMinutesPerTick() {
        return addedMinutesPerTick;
    }
    public void setAddedMinutesPerTick(int addedMinutesPerTick) {
        this.addedMinutesPerTick = addedMinutesPerTick;
    }
}