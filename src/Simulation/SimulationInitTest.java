package Simulation;

import GUI.DisplayMethods.Method1;
import GUI.GUI;
import Person.LifeStats;
import Person.Patient;

import java.util.ArrayList;

public class SimulationInitTest {

    public static void main(String[] args){

        ArrayList<Department> departments = new ArrayList<>();
        departments.add(new Department("Onkologia",0,3,new LifeStats<Double>(0.1,0.2,0.3)));
        departments.add(new Department("Ortopedia",0,2,new LifeStats<Double>(0.3,0.2,0.1)));
        departments.add(new Department("Zakaźny",0,4,new LifeStats<Double>(0.1,0.1,0.1)));
        departments.add(new Department("Kardiologia",0,5,new LifeStats<Double>(0.25,0.5,0.15)));

        Simulation simulation = new Simulation(departments);

        simulation.start(); // "init" method
    }
}
