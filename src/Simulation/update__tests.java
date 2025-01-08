package Simulation;

import Person.*;
import Utils.*;
import java.util.*;


public class update__tests {
    public static void main(String[] args) {

        ArrayList<Department> departments = new ArrayList<>();
        departments.add(new Department("Onkologia", 0, 10, null));
        departments.add(new Department("Ortopedia", 0, 10, null));

        LifeStats<Double> min = new LifeStats<>(10.0,10.0,10.0);
        LifeStats<Double> max = new LifeStats<>(500.0,500.0,500.0);

        Setup setup = new Setup(5,3, true, true, departments,2,5,10,min,max);

        GenerationMethod method = new AutoGeneration(setup);

        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(method.generatePatient());

        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(method.generateDoctor());

        patients.get(0).registerObserver(doctors.get(0));

        ArrayList<GenerationMethod> generationMethods = new ArrayList<>();
        generationMethods.add(method);


        SimulationManager.simulation = new Simulation(departments,doctors,patients,generationMethods,setup);
        SimulationManager.simulation.simulationLoop();
    }

}
