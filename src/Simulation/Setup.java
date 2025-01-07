package Simulation;

import Person.LifeStats;

import java.util.ArrayList;

public class Setup {

    // na razie są zmienne "globalne", do których dostęp jest przed gettery i settery
    // można też zrobić publiczne ale tak bezpieczniej może

    private static int delayMs = 2000;
    private static boolean generatePatientsAutomatically = true;
    private static boolean diagnosePatientsAutomatically = true; // nieużywane
    private static ArrayList<Department> departments;

    // parametry do generacji:

    private static int maxNumberOfDoctorsPerPatient = 3; // ile lekarzy może leczyć jednego pacjenta

    private static int minNumberOfDoctors = 10; // ile ogólnie lekarzy w szpitalu
    private static int maxNumberOfDoctors = 20;

    private static int minNumberOfPatients = 5; // ile ogólnie pacjentów w szpitalu
    private static int maxNumberOfPatients = 10;

    // można potem dodać ewentualnie min/max tutaj:
    private static int maxIllnessAmount = 3;
    private static int numberOfShifts = 3; // na razie nieużywane

    private static int minDoctorSkill = 0;
    private static int maxDoctorSkill = 100; // for now

    private static LifeStats<Integer> minLifeStats = new LifeStats<>(1,1,1);
    private static LifeStats<Integer> maxLifeStats = new LifeStats<>(100,100,100);

    public Setup(int maxIllnessAmount, int delayMs, boolean generatePatientsAutomatically, boolean diagnosePatientsAutomatically, ArrayList<Department> departments, int numberOfShifts, int minDoctorSkill, int maxDoctorSkill, LifeStats<Integer> minLifeStats, LifeStats<Integer> maxLifeStats) {
        Setup.maxIllnessAmount = maxIllnessAmount;
        Setup.delayMs = delayMs;
        Setup.generatePatientsAutomatically = generatePatientsAutomatically;
        Setup.diagnosePatientsAutomatically = diagnosePatientsAutomatically;
        Setup.departments = departments;
        Setup.numberOfShifts = numberOfShifts;
        Setup.minDoctorSkill = minDoctorSkill;
        Setup.maxDoctorSkill = maxDoctorSkill;
        Setup.minLifeStats = minLifeStats;
        Setup.maxLifeStats = maxLifeStats;
    }

    public Setup(ArrayList<Department> departments){

        Setup.departments = departments;
    }

    public static int getMaxNumberOfDoctorsPerPatient() {
        return maxNumberOfDoctorsPerPatient;
    }

    public static void setMaxNumberOfDoctorsPerPatient(int maxNumberOfDoctorsPerPatient) {
        Setup.maxNumberOfDoctorsPerPatient = maxNumberOfDoctorsPerPatient;
    }

    public static int getMinNumberOfPatients() {
        return minNumberOfPatients;
    }

    public static void setMinNumberOfPatients(int minNumberOfPatients) {
        Setup.minNumberOfPatients = minNumberOfPatients;
    }

    public static int getMaxNumberOfPatients() {
        return maxNumberOfPatients;
    }

    public static void setMaxNumberOfPatients(int maxNumberOfPatients) {
        Setup.maxNumberOfPatients = maxNumberOfPatients;
    }

    public static int getMinNumberOfDoctors() {
        return minNumberOfDoctors;
    }

    public static void setMinNumberOfDoctors(int minNumberOfDoctors) {
        Setup.minNumberOfDoctors = minNumberOfDoctors;
    }

    public static int getMaxNumberOfDoctors() {
        return maxNumberOfDoctors;
    }

    public static void setMaxNumberOfDoctors(int maxNumberOfDoctors) {
        Setup.maxNumberOfDoctors = maxNumberOfDoctors;
    }

    public static ArrayList<Department> getDepartments() {
        return new ArrayList<>(departments);
    }

    public static void addDepartment(Department department){
        Setup.departments.add(department);
    }

    public static int getMaxIllnessAmount() {
        return maxIllnessAmount;
    }

    public static void setMaxIllnessAmount(int maxIllnessAmount) {Setup.maxIllnessAmount = maxIllnessAmount;}

    public static int getDelayMs() {
        return delayMs;
    }

    public static void setDelayMs(int delayMs){
        Setup.delayMs = delayMs;
    }

    public static boolean isGeneratingPatientsAutomatically() {
        return generatePatientsAutomatically;
    }

    public static void toggleAutoDiagnose(){
        diagnosePatientsAutomatically = !diagnosePatientsAutomatically;
    }

    public static boolean isDiagnosingPatientsAutomatically() {
        return diagnosePatientsAutomatically;
    }

    public static void toggleAutoGenerate(){
        generatePatientsAutomatically = !generatePatientsAutomatically;
    }

    public static LifeStats<Integer> getMinLifeStats() {
        return minLifeStats;
    }

    public static void setMinLifeStats(LifeStats<Integer> minLifeStats) {
        Setup.minLifeStats = minLifeStats;
    }

    public static LifeStats<Integer> getMaxLifeStats() {
        return maxLifeStats;
    }

    public static void setMaxLifeStats(LifeStats<Integer> maxLifeStats) {
        Setup.maxLifeStats = maxLifeStats;
    }

    public static int getNumberOfShifts() {
        return numberOfShifts;
    }

    public static void setNumberOfShifts(int numberOfShifts) {
        Setup.numberOfShifts = numberOfShifts;
    }

    public static int getMinDoctorSkill() {
        return minDoctorSkill;
    }

    public static void setMinDoctorSkill(int minDoctorSkill) {
        Setup.minDoctorSkill = minDoctorSkill;
    }

    public static int getMaxDoctorSkill() {
        return maxDoctorSkill;
    }

    public static void setMaxDoctorSkill(int maxDoctorSkill) {
        Setup.maxDoctorSkill = maxDoctorSkill;
    }
}