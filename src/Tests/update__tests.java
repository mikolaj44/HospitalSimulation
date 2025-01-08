package Tests;

import Person.*;
import Simulation.*;

import java.util.*;


public class update__tests {
    public static void main(String[] args) {

        ArrayList<Department> departments = new ArrayList<>();
        departments.add(new Department("Onkologia", 0, 10, new LifeStats<Double>(0.1, 0.2, 0.3)));
        departments.add(new Department("Ortopedia", 0, 10, new LifeStats<Double>(0.25, 0.35, 0.1)));

        LifeStats<Integer> min = new LifeStats<>(10, 10, 10);
        LifeStats<Integer> max = new LifeStats<>(500, 500, 500);

        Setup setup = new Setup(departments); //new Setup(5, 3, true, true, departments, 2, 5, 10, min, max);

        GenerationMethod method = new AutoGeneration(setup);

        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(method.generatePatient());

        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(method.generateDoctor());

        patients.get(0).registerObserver(doctors.get(0));

        ArrayList<GenerationMethod> generationMethods = new ArrayList<>();
        generationMethods.add(method);

        SimulationManager.setSimulation(new Simulation(departments, doctors, patients, generationMethods, setup));
        SimulationManager.getSimulation().start();
    }
}