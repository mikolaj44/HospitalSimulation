package Tests;

import Person.Doctor;
import Person.LifeStats;
import Person.Patient;
import Simulation.*;

import java.util.ArrayList;


public class PresentationTest {
    public static void main(String[] args) {

        ArrayList<Department> departments = new ArrayList<>();
        departments.add(new Department("Onkologia", 3, new LifeStats<Double>(0.1, 0.2, 0.3)));
        departments.add(new Department("Ortopedia", 2, new LifeStats<Double>(0.3, 0.2, 0.1)));
        departments.add(new Department("Zakaźny", 4, new LifeStats<Double>(0.1, 0.1, 0.1)));
        departments.add(new Department("Kardiologia", 5, new LifeStats<Double>(0.25, 0.5, 0.15)));

        SimulationManager.setSimulation(new Simulation(departments));
        SimulationManager.getSimulation().start();
    }
}