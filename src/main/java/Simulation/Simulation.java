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
    private TimeOfDay timeOfDay;

    private int departmentAssignmentMethodIndex = 3;

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
        timeOfDay = new TimeOfDay(7,0);
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

    private void generatePatientsInDepartments(boolean atStart){

        int numberOfPatients;

        // zależnie od tego czy generacja na starcie, czy w pętli
        // (na razie generacja użytkownika będzie też miała z tego powodu ustaloną liczbę pacjentów, których może dodać)
        if(atStart) {
            numberOfPatients = randomRange(setup.getMinNumberOfPatients(), setup.getMaxNumberOfPatients());
        }
        else{
            numberOfPatients = randomRange(setup.getMinNewPatientsPerIteration(), setup.getMaxNewPatientsPerIteration());
        }

        for(int i = 0; i < numberOfPatients; i++){

            // na razie
            if(setup.isGeneratingPatientsAutomatically()) {
                addPatient(generationMethods.get(0)); // autogeneracja
            }
            else {
                addPatient(generationMethods.get(1)); // wpisywanie przez użytkownika
            }

            int departmentIndex = assignmentMethods.get(departmentAssignmentMethodIndex).getDepartmentIndex(patients.getLast(), departments); // (closest assignment)

            if(departmentIndex == -1) // wszystkie oddziały pełne
                return;

            patients.getLast().setDepartmentIndex(departmentIndex);

            departments.get(departmentIndex).addPatient();
        }
    }

    private void assignDoctorsToPatients(){

        for(Patient patient : patients){

            // jeśli już ma lekarza, to nie  przypisujemy (na razie)
            // zakładamy, że wszyscy obserwatorzy są lekarzami (na razie)
            if(!patient.getObservers().isEmpty())
                continue;

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

    private void generateAndAssign(boolean atStart){

        // trzeba zrobić weryfikację (pewnie gdzieś wyżej, w konstruktorze albo coś)
        // czy aby departments albo generationMethods nie są null albo puste bo od nich dużo zależy

        // generacja lekarzy i wrzucanie ich do oddziałów (może ich wyjść trochę mniej niż wylosowana liczba)
        // tylko na początku
        if(atStart) {
            generateDoctorsInDepartments();
        }

        // generacja pacjentów i ich chorób oraz przydzielanie do oddziałów
        // na początku i w pętli
        generatePatientsInDepartments(atStart);

        // łączenie pacjentów z lekarzami (na razie losowo)
        // nieoptymalne
        assignDoctorsToPatients();
    }

    public void start() {

        generateAndAssign(true);
        Thread simulationThread = new Thread(() -> {
            simulationLoop();
        });
        simulationThread.setDaemon(true);
        simulationThread.start();
    }

    public void simulationLoop(){

        Scanner scanner = new Scanner(System.in);

        while (true){

            if(patients.isEmpty() || doctors.isEmpty())
                return;

//            //reczne wywolanie symulacji
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

            // odświeżanie pacjentów - wywoływanie chorób, powiadamianie lekarzy itd.
            for(int i = patients.size() - 1; i >= 0; i--) {

                //System.out.println(patients.get(i));
                patients.get(i).update();
            }

            generateAndAssign(false);

            System.out.println(timeOfDay);
            timeOfDay.addMinutes(SimulationManager.getSetup().getAddedMinutesPerTick());
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

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public Setup getSetup() {
        return setup;
    }

    public ArrayList<GenerationMethod> getGenerationMethods() {
        return generationMethods;
    }

    public ArrayList<DepartmentAssignmentMethod> getAssignmentMethods() {
        return assignmentMethods;
    }

    public int getDepartmentAssignmentMethodIndex() {
        return departmentAssignmentMethodIndex;
    }

    public void setDepartmentAssignmentMethodIndex(int departmentAssignmentMethodIndex) {
        this.departmentAssignmentMethodIndex = departmentAssignmentMethodIndex;
    }

    public String getTime() {
        return timeOfDay.toString();
    }
}