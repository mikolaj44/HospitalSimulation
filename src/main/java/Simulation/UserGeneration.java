package Simulation;

import Person.*;

import java.util.Scanner;

public class UserGeneration implements GenerationMethod {

    private Setup setup;

    public UserGeneration(Setup setup) {
        this.setup = setup;
    }

    private Person setPersonFields(Class<?> type){

        Person person = null;
        Scanner scanner = new Scanner(System.in);

        if(type == Patient.class)
            person = new Patient();
        else
            person = new Doctor();

        System.out.print("Podaj imię: ");
        person.setName(scanner.next());

        System.out.print("Podaj nazwisko: ");
        person.setSurname(scanner.next());

        System.out.print("Podaj PESEL: ");
        person.setPesel(scanner.next());

        return person;
    }

    public Patient generatePatient() {

        Patient patient = (Patient)setPersonFields(Patient.class);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj numer oddziału: ");
        patient.setDepartmentIndex(scanner.nextInt());

        return patient;
    }

    public Doctor generateDoctor() {

        Doctor doctor = (Doctor) setPersonFields(Doctor.class);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj numer oddziału: ");
        doctor.setDepartmentIndex(scanner.nextInt());

//        System.out.print("Podaj poziom : ");
//        doctor.setLifeStatsModifiers(scanner);

        System.out.print("Podaj numer zmiany: ");
        doctor.setShift(scanner.nextInt());

        return doctor;
    }
}