package Simulation;

import Person.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static Utils.RandomRange.randomRange;

public class Simulation {

    private ArrayList<Department> departments;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private Setup setup;
    private ArrayList<GenerationMethod> generationMethods;
    private ArrayList<DepartmentAssignmentMethod> assignmentMethods;

    private int recovered;
    private int deceased;

    public Simulation(ArrayList<Department> departments, ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<GenerationMethod> generationMethods, ArrayList<DepartmentAssignmentMethod> assignmentMethods) {
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
        this.setup = new Setup(departments);
        this.generationMethods = generationMethods;
        this.assignmentMethods = assignmentMethods;
        recovered = 0;
        deceased = 0;
    }

    public Simulation(ArrayList<Department> departments, ArrayList<GenerationMethod> generationMethods, ArrayList<DepartmentAssignmentMethod> assignmentMethods) {
        this.departments = departments;
        this.generationMethods = generationMethods;
        this.assignmentMethods = assignmentMethods;
        this.setup = new Setup(departments);
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        recovered = 0;
        deceased = 0;
    }

    public Simulation(ArrayList<Department> departments, ArrayList<GenerationMethod> generationMethods) {
        this.departments = departments;
        this.generationMethods = generationMethods;
        this.setup = new Setup(departments);

        this.assignmentMethods = new ArrayList<>();
        assignmentMethods.add(new ClosestAssignment());
        assignmentMethods.add(new BestAssignment());
        assignmentMethods.add(new MostFreeSpotsAssignment());
        assignmentMethods.add(new RandomAssignment());

        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        recovered = 0;
        deceased = 0;
    }

    public Simulation(ArrayList<Department> departments) {
        this.departments = departments;

        generationMethods = new ArrayList<>();
        this.setup = new Setup(departments);
        generationMethods.add(new AutoGeneration(setup));
        generationMethods.add(new UserGeneration(setup));

        assignmentMethods = new ArrayList<>();
        assignmentMethods.add(new ClosestAssignment());
        assignmentMethods.add(new BestAssignment());
        assignmentMethods.add(new MostFreeSpotsAssignment());
        assignmentMethods.add(new RandomAssignment());

        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        recovered = 0;
        deceased = 0;
    }

    public Simulation(ArrayList<Department> departments, ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<GenerationMethod> generationMethods, Setup setup) {
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
        this.generationMethods = generationMethods;
        this.setup = setup;
        recovered = 0;
        deceased = 0;
    }

    private void generateDoctorsInDepartments(){

        int numberOfDoctors = randomRange(setup.getMinNumberOfDoctors(), setup.getMaxNumberOfDoctors());
        int doctorsPerDepartment = numberOfDoctors / departments.size();

        for (int i = 0; i < departments.size(); i++) {

            for(int j = 0; j < doctorsPerDepartment; j++){

                // na razie
                if(setup.isGeneratingPatientsAutomatically()) {
                    addDoctor(generationMethods.get(0)); // autogeneracja
                }
                else {
                    addDoctor(generationMethods.get(1)); // wpisywanie przez użytkownika
                }

                doctors.getLast().setDepartmentIndex(i);
            }
        }
    }

    private void generatePatientsInDepartments(){

        int numberOfPatients = randomRange(setup.getMinNumberOfPatients(), setup.getMaxNumberOfPatients());

        for(int i = 0; i < numberOfPatients; i++){

            // na razie
            if(setup.isGeneratingPatientsAutomatically()) {
                addPatient(generationMethods.get(0)); // autogeneracja
            }
            else {
                addPatient(generationMethods.get(1)); // wpisywanie przez użytkownika
            }

            int departmentIndex = assignmentMethods.get(0).getDepartmentIndex(patients.getLast(), departments); // na razie (closest assignment)

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

            int doctorAmount = randomRange(1, Math.min(matchingDoctors.size(), setup.getMaxNumberOfDoctorsPerPatient()));

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

//        for(int i = 0; i < patients.size(); i++){
//
//            System.out.println("statystyki pacjenta " + (i+1) + ":\n\n" + patients.get(i).getStats() + "\n");
//            System.out.println("mnożniki oddziału: \n\n" + departments.get(patients.get(i).getDepartmentIndex()).getStatsMultiplier() + "\n");
//            System.out.println("pojemność oddziału: " + departments.get(patients.get(i).getDepartmentIndex()).getMaxAmountOfPatients());
//            System.out.println("ilość osób na oddziale: " + departments.get(patients.get(i).getDepartmentIndex()).getAmountOfPatients() + "\n");
//            System.out.println("Lekarze pacjenta:\n");
//
//            for(int j = 0; j < patients.get(i).getObservers().size(); j++) {
//
//                Doctor doctor = (Doctor) patients.get(i).getObservers().get(j);
//
//                System.out.println("statystyki lekarza " + (j+1) + ":\n\n" + doctor.getSkill() + "\n");
//            }
//            System.out.println("\n\n");
//        }

        simulationLoop();
    }

    public void simulationLoop(){
//        System.out.println("Simulation started");
        Scanner scanner = new Scanner(System.in);
//        System.out.println(patients.get(0).getInfo());
//        System.out.println(doctors.get(0).getInfo());
//        doctors.get(0).performHealing(patients.get(0));
//        System.out.println(patients.get(0).getInfo());

        while (true){
            if(patients.isEmpty() || doctors.isEmpty())
                return;
            System.out.println(patients.get(0).getInfo());
            System.out.println("Kontynuować?(T/N)");
            String input = scanner.next();
            if(input.equals("N")){
                return;
            }
            doctors.get(0).performHealing(patients.get(0));
            if(!patients.isEmpty()) // if cured patient is removed
                patients.get(0).update();

        }
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

    public void onPatientDied() {
        deceased++;
    }

    public void onPatientRecovered() {
        recovered++;
    }

    public int getDeceased() {
        return deceased;
    }

    public int getRecovered() {
        return recovered;
    }
}