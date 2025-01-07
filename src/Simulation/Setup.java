package Simulation;

import Person.LifeStats;

import java.util.ArrayList;

public class Setup {

    // na razie są zmienne "globalne", do których dostęp jest przed gettery i settery
    // można też zrobić publiczne ale tak bezpieczniej może

    private static int delayMs;
    private static boolean generatePatientsAutomatically;
    private static boolean diagnosePatientsAutomatically;
    private static ArrayList<Department> departments;

    // parametry do generacji:

    private static int maxIllnessAmount;
    private static int numberOfShifts;
    private static int minDoctorSkill;
    private static int maxDoctorSkill;
    private static LifeStats<Integer> minLifeStats;
    private static LifeStats<Integer> maxLifeStats;

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