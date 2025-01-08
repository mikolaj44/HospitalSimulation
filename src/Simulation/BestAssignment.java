package Simulation;

import Person.LifeStats;
import Person.Patient;

import java.util.ArrayList;

public class BestAssignment implements DepartmentAssignmentMethod{

    public int getDepartmentIndex(Patient patient, ArrayList<Department> departments){

        // oddział, który może pomóc najbardziej statystyce, która jest najmniejsza

        int matchingDepartmentIndex = -1;
        double maxScore = -1;
        double score = 0;

        LifeStats<Integer> stats = patient.getStats();

        for (int i = 0; i < departments.size(); i++){

            score = 0;
            LifeStats<Double> departmentMultiplier = departments.get(i).getStatsMultiplier();

            for(int j = 0; j < departmentMultiplier.getStatAmount(); j++)
                score += departmentMultiplier.getStatByIndex(j);

            if(score > maxScore && departments.get(i).getMaxAmountOfPatients() > departments.get(i).getAmountOfPatients()){

                maxScore = score;
                matchingDepartmentIndex = i;
            }
        }

        return matchingDepartmentIndex;
    }

}
