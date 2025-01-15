package Simulation;

import Person.LifeStats;
import Person.Patient;

import java.util.ArrayList;
import java.util.Collections;

public class RandomAssignment implements DepartmentAssignmentMethod {

    // losowy oddział

    public int getDepartmentIndex(Patient patient, ArrayList<Department> departments) {

        ArrayList<Integer> departmentIndices = new ArrayList<>();

        for (int i = 0; i < departments.size(); i++) {
            departmentIndices.add(i);
        }

        Collections.shuffle(departmentIndices);

        for (int index : departmentIndices) {

            if (departments.get(index).getMaxAmountOfPatients() > departments.get(index).getAmountOfPatients()) {
                return index;
            }
        }
        return -1;
    }
}