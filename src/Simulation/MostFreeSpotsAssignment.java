package Simulation;

import Person.LifeStats;
import Person.Patient;

import java.util.ArrayList;

public class MostFreeSpotsAssignment implements DepartmentAssignmentMethod{

    // oddział, który ma najwięcej wolnych miejsc

    public int getDepartmentIndex(Patient patient, ArrayList<Department> departments){

        int matchingDepartmentIndex = -1;
        int maxAmount = 0;

        for(int i = 0; i < departments.size(); i++){

            int amount = departments.get(i).getMaxAmountOfPatients() - departments.get(i).getAmountOfPatients();

            if(amount > maxAmount){
                maxAmount = amount;
                matchingDepartmentIndex = i;
            }
        }

        return matchingDepartmentIndex;
    }
}
