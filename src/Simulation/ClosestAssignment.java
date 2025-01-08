package Simulation;

import Person.LifeStats;
import Person.Patient;

import java.util.ArrayList;

public class ClosestAssignment implements DepartmentAssignmentMethod{

    // oddział, który może pomóc najbardziej statystyce, która jest najmniejsza

    public int getDepartmentIndex(Patient patient, ArrayList<Department> departments){

        int matchingDepartmentIndex = -1;
        double maxScore = -1;
        double score = 0;

        LifeStats<Integer> stats = patient.getStats();

        // szukanie najmniejszej statystyki pacjenta

        int minStatIndex = 0;
        int minV = Integer.MAX_VALUE;

        for(int i = 0; i < stats.getStatAmount(); i++){

            if(stats.getStatByIndex(i) < minV){
                minV = stats.getStatByIndex(i);
                minStatIndex = i;
            }
        }

        for (int i = 0; i < departments.size(); i++){

            LifeStats<Double> departmentMultiplier = departments.get(i).getStatsMultiplier();

            score = departmentMultiplier.getStatByIndex(minStatIndex);

            if(score > maxScore && departments.get(i).getMaxAmountOfPatients() > departments.get(i).getAmountOfPatients()){

                maxScore = score;
                matchingDepartmentIndex = i;
            }
        }

        return matchingDepartmentIndex;
    }
}
