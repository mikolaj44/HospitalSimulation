package Simulation;

import Person.*;
import java.util.ArrayList;

public class Simulation {

    private ArrayList<Department> departments;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<GenerationMethod> generationMethods;
    private Settings settings;
    private int recovered;
    private int deceased;

    public Simulation(ArrayList<Department> departments, ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<GenerationMethod> generationMethods, Settings settings) {
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
        this.generationMethods = generationMethods;
        this.settings = settings;
        recovered = 0;
        deceased = 0;
    }

    public void simulationLoop(){

    }

    public void addPatient(GenerationMethod method){

    }

    public void addDoctor(GenerationMethod method){

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