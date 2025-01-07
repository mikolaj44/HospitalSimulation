package Simulation;

import Person.*;

import java.util.ArrayList;
import java.util.Collections;

import static Utils.RandomRange.*;

public class Simulation {

    private ArrayList<Department> departments;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<GenerationMethod> generationMethods;
    private int recovered;
    private int deceased;

    public Simulation(ArrayList<Department> departments, ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<GenerationMethod> generationMethods) {
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
        this.generationMethods = generationMethods;
        recovered = 0;
        deceased = 0;
    }

    public Simulation(ArrayList<Department> departments, ArrayList<GenerationMethod> generationMethods) {
        this.departments = departments;
        this.generationMethods = generationMethods;
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        recovered = 0;
        deceased = 0;
    }

    public Simulation(ArrayList<Department> departments) {
        this.departments = departments;
        generationMethods = new ArrayList<>();
        generationMethods.add(new AutoGeneration(new Setup(departments)));
        generationMethods.add(new UserGeneration(new Setup(departments)));
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        recovered = 0;
        deceased = 0;
    }

    private int getClosestDepartmentIndex(Patient patient){

        // oddział, który może pomóc najbardziej statystyce, która jest najmniejsza

        int matchingDepartmentIndex = -1;
        double maxScore = -1;
        double score = 0;

        LifeStats<Integer> stats = patient.getStats();

        // szukanie najmniejszej statystyki pacjenta

        int minStatIndex = 0;
        int minV = Integer.MAX_VALUE;

        for(int i = 0; i < stats.getStatAmount(); i++){

            if(stats.getStatByIndex(i) < minV){
                minV = stats.getStatByIndex(i);
                minStatIndex = i;
            }
        }

        for (int i = 0; i < departments.size(); i++){

            LifeStats<Double> departmentMultiplier = departments.get(i).getStatsMultiplier();

            score = departmentMultiplier.getStatByIndex(minStatIndex);

            if(score > maxScore && departments.get(i).getMaxAmountOfPatients() > departments.get(i).getAmountOfPatients()){

                maxScore = score;
                matchingDepartmentIndex = i;
            }
        }

        return matchingDepartmentIndex;
    }

    private int getBestDepartmentIndex(Patient patient){

        // oddział, który może pomóc najbardziej statystyce, która jest najmniejsza

        int matchingDepartmentIndex = -1;
        double maxScore = -1;
        double score = 0;

        LifeStats<Integer> stats = patient.getStats();

        for (int i = 0; i < departments.size(); i++){

            score = 0;
            LifeStats<Double> departmentMultiplier = departments.get(i).getStatsMultiplier();

            for(int j = 0; j < departmentMultiplier.getStatAmount(); j++)
                score += departmentMultiplier.getStatByIndex(j);

            if(score > maxScore && departments.get(i).getMaxAmountOfPatients() > departments.get(i).getAmountOfPatients()){

                maxScore = score;
                matchingDepartmentIndex = i;
            }
        }

        return matchingDepartmentIndex;
    }

    private void generateDoctorsInDepartments(){

        int numberOfDoctors = randomRange(Setup.getMinNumberOfDoctors(), Setup.getMaxNumberOfDoctors());
        int doctorsPerDepartment = numberOfDoctors / departments.size();

        for (int i = 0; i < departments.size(); i++) {

            for(int j = 0; j < doctorsPerDepartment; j++){

                // na razie
                if(Setup.isGeneratingPatientsAutomatically()) {
                    addDoctor(generationMethods.getFirst()); // autogeneracja
                }
                else {
                    addDoctor(generationMethods.get(1)); // wpisywanie przez użytkownika
                }

                doctors.getLast().setDepartmentIndex(i);
            }
        }
    }

    private void generatePatientsInDepartments(){

        int numberOfPatients = randomRange(Setup.getMinNumberOfPatients(), Setup.getMaxNumberOfPatients());

        for(int i = 0; i < numberOfPatients; i++){

            // na razie
            if(Setup.isGeneratingPatientsAutomatically()) {
                addPatient(generationMethods.getFirst()); // autogeneracja
            }
            else {
                addPatient(generationMethods.get(1)); // wpisywanie przez użytkownika
            }

            int departmentIndex = getClosestDepartmentIndex(patients.getLast());

            if(departmentIndex == -1) // wszystkie oddziały pełne
                return;

            patients.getLast().setDepartmentIndex(departmentIndex);

            departments.get(departmentIndex).setAmountOfPatients(departments.get(departmentIndex).getAmountOfPatients() + 1);
        }
    }

    private void assignDoctorsToPatients(){

        for(Patient patient : patients){

            ArrayList<Doctor> matchingDoctors = new ArrayList<>();

            for(Doctor doctor : doctors){

                if(doctor.getDepartmentIndex() == patient.getDepartmentIndex())
                    matchingDoctors.add(doctor);
            }

            if(matchingDoctors.isEmpty()) // nie ma żadnych lekarzy na tym oddziale, co pacjent
                continue;

            int doctorAmount = randomRange(1, Math.min(matchingDoctors.size(), Setup.getMaxNumberOfDoctorsPerPatient()));

            Collections.shuffle(matchingDoctors);

            for(int i = 0; i < doctorAmount; i++){

                patient.registerObserver(matchingDoctors.get(i));
            }
        }
    }

    public void start() {

        // trzeba zrobić weryfikację (pewnie gdzieś wyżej, w konstruktorze albo coś)
        // czy aby departments albo generationMethods nie są null albo puste bo od nich dużo zależy

        // generacja lekarzy i wrzucanie ich do oddziałów (może ich wyjść trochę mniej niż wylosowana liczba)

        generateDoctorsInDepartments();

        // generacja pacjentów i ich chorób oraz przydzielanie do oddziałów

        generatePatientsInDepartments();

        // łączenie pacjentów z lekarzami (na razie losowo)
        // strasznie nieoptymalne

        assignDoctorsToPatients();

        for(int i = 0; i < patients.size(); i++){

            System.out.println("statystyki pacjenta " + (i+1) + ":\n\n" + patients.get(i).getStats() + "\n");
            System.out.println("mnożniki oddziału: \n\n" + departments.get(patients.get(i).getDepartmentIndex()).getStatsMultiplier() + "\n");
            System.out.println("pojemność oddziału: " + departments.get(patients.get(i).getDepartmentIndex()).getMaxAmountOfPatients());
            System.out.println("ilość osób na oddziale: " + departments.get(patients.get(i).getDepartmentIndex()).getAmountOfPatients() + "\n");
            System.out.println("Lekarze pacjenta:\n");

            for(int j = 0; j < patients.get(i).getObservers().size(); j++) {

                Doctor doctor = (Doctor) patients.get(i).getObservers().get(j);

                System.out.println("statystyki lekarza " + (j+1) + ":\n\n" + doctor.getSkill() + "\n");
            }
            System.out.println("\n\n");
        }

        // zacząć tutaj simulationLoop()?
    }

    public void simulationLoop(){

    }

    public void addPatient(GenerationMethod method){
        patients.add(method.generatePatient());
    }

    public void addDoctor(GenerationMethod method){
        doctors.add(method.generateDoctor());
    }

    public void removeDoctor(Doctor d){
        doctors.remove(d);
    }

    public void removePatient(Patient p){
        patients.remove(p);
    }

    public void removePerson(Person p){

        for(Person person : doctors){
            if(person.equals(p)){
                doctors.remove((Doctor)p);
                return;
            }
        }

        for(Person person : patients){
            if(person.equals(p)){
                patients.remove((Patient)p);
                return;
            }
        }
    }

    public int getNumberOfDepartments(){
        return departments.size();
    }

}