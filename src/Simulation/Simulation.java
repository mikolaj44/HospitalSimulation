package Simulation;

import Person.*;
import java.util.ArrayList;
import java.util.Scanner;

import static Utils.RandomRange.randomRange;

public class Simulation {

    private ArrayList<Department> departments;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<GenerationMethod> generationMethods;
    private Setup setup;
    private int recovered;
    private int deceased;

    public Simulation(ArrayList<Department> departments, ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<GenerationMethod> generationMethods, Setup setup) {
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
        this.generationMethods = generationMethods;
        this.setup = setup;
        recovered = 0;
        deceased = 0;
    }

    public void simulationLoop(){
//        System.out.println("Simulation started");
        Scanner scanner = new Scanner(System.in);
//        System.out.println(patients.get(0).getInfo());
//        System.out.println(doctors.get(0).getInfo());
//        doctors.get(0).performHealing(patients.get(0));
//        System.out.println(patients.get(0).getInfo());

        while (true){
            System.out.println(patients.get(0).getInfo());
            System.out.println("Kontynuować?(T/N)");
            String input = scanner.next();
            if(input.equals("N")){
                break;
            }
            else {
                doctors.get(0).performHealing(patients.get(0));
                patients.get(0).updateLifeStats();
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

}