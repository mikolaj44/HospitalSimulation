package Simulation;

import Person.Patient;

import java.util.ArrayList;

public interface DepartmentAssignmentMethod {

    int getDepartmentIndex(Patient patient, ArrayList<Department> departments);
}