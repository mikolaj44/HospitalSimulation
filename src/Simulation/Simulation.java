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

        this();
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
        this.generationMethods = generationMethods;
        this.assignmentMethods = assignmentMethods;
    }

    public Simulation(ArrayList<Department> departments, ArrayList<GenerationMethod> generationMethods, ArrayList<DepartmentAssignmentMethod> assignmentMethods) {

        this();
        this.departments = departments;
        this.generationMethods = generationMethods;
        this.assignmentMethods = assignmentMethods;
    }

    public Simulation(ArrayList<Department> departments, ArrayList<GenerationMethod> generationMethods) {

        this();
        this.departments = departments;
        this.generationMethods = generationMethods;
    }

    public Simulation(ArrayList<Department> departments, Setup setup) {

        this();
        this.departments = departments;
        this.setup = setup;
        generationMethods.set(0, new AutoGeneration(setup));
        generationMethods.set(1, new UserGeneration(setup));
    }

    public Simulation(ArrayList<Department> departments) {

        this();
        this.departments = departments;
    }

    public Simulation(ArrayList<Department> departments, ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<GenerationMethod> generationMethods, Setup setup) {

        this();
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
        this.generationMethods = generationMethods;
        this.setup = setup;
        generationMethods.set(0, new AutoGeneration(setup));
        generationMethods.set(1, new UserGeneration(setup));
    }

    private Simulation(){

        departments = new ArrayList<>();
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        this.setup = new Setup(departments);
        addDefaultGenerationMethods();
        addDefaultAssignmentMethods();
        recovered = 0;
        deceased = 0;
    }

    private void addDefaultAssignmentMethods(){

        assignmentMethods = new ArrayList<>();
        assignmentMethods.add(new ClosestAssignment());
        assignmentMethods.add(new BestAssignment());
        assignmentMethods.add(new MostFreeSpotsAssignment());
        assignmentMethods.add(new RandomAssignment());
    }

    private void addDefaultGenerationMethods(){

        generationMethods = new ArrayList<>();
        generationMethods.add(new AutoGeneration(setup));
        generationMethods.add(new UserGeneration(setup));
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

            int departmentIndex = assignmentMethods.get(3).getDepartmentIndex(patients.getLast(), departments); // na razie (closest assignment)

            if(departmentIndex == -1) // wszystkie oddziały pełne
                return;

            patients.getLast().setDepartmentIndex(departmentIndex);

            departments.get(departmentIndex).addPatient();
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
        // nieoptymalne

        assignDoctorsToPatients();

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

            //System.out.println(patients.get(0));

            // reczne wywolanie symulacji
//            System.out.println("Kontynuować?(T/N)");
//            String input = scanner.next();
//
//            if(input.equals("N")){
//                return;
//            }

            //symulacja automatyczna
            try {
                // Opóźnienie na 2 sekundy
                Thread.sleep(setup.getDelayMs());  // 2000 milisekund = 2 sekundy
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //doctors.get(0).performHealing(patients.get(0));

            // jesli pacjent jest wyleczony to usuwa w pacjecie(pewnie tezeba poprawic to i dodac usuwanie z list tu)
            //if(!patients.isEmpty())
            //    patients.get(0).update();

            for(int i = patients.size() - 1; i >= 0; i--) {

                System.out.println(patients.get(i));
                patients.get(i).update();
            }
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

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }
}