package Simulation;

import java.util.ArrayList;
import java.util.List;

import Person.Illness;
import Person.LifeStats;
import Person.Patient;
import Person.Observer;
import Person.Doctor;

public class BestModifiersForPatientAssignment implements DepartmentAssignmentMethod {

    @Override
    public int getDepartmentIndex(Patient patient, ArrayList<Department> departments) {

        List<Illness> patientIllnesses = patient.getIllnesses();
        int bestDepartmentIndex = -1;
        double bestScore = 0;

        // LifeStats<Double> doctorsStatsModifier = new LifeStats<>(0.0, 0.0, 0.0);

        // for (Observer observer : patient.getObservers()) {

        //     LifeStats<Double> doctorLifeStats = ((Doctor)(observer)).getLifeStatsModifiers();

        //     for (int i = 0; i < doctorLifeStats.getStatAmount(); i++) {
        //         doctorsStatsModifier.setStatByIndex(i,
        //                 doctorLifeStats.getStatByIndex(i) + doctorsStatsModifier.getStatByIndex(i));
        //     }
        // }

        for (int departmentIndex = 0; departmentIndex < departments.size(); departmentIndex++) {

            // zapchany oddział
            if(departments.get(departmentIndex).getMaxAmountOfPatients() <= departments.get(departmentIndex).getAmountOfPatients()){
                continue;
            }

            Department department = departments.get(departmentIndex);
            LifeStats<Double> departmentModifiers = department.getStatsMultiplier();

            double score = 0;

            for (Illness illness : patientIllnesses) {

                LifeStats<Double> illnessLifeStats = illness.getStats();

                for (int lifeStatIndex = 0; lifeStatIndex < departmentModifiers.getStatAmount(); lifeStatIndex++) {

                    score += Math.max(
                            Math.min(illnessLifeStats.getStatByIndex(lifeStatIndex)
                                   // - doctorsStatsModifier.getStatByIndex(lifeStatIndex)
                                    - departmentModifiers.getStatByIndex(lifeStatIndex)
                                            * SimulationManager.getSetup().getDepartmentInfluenceFactor(),
                                    0)
                                    + departmentModifiers.getStatByIndex(lifeStatIndex)
                                            * SimulationManager.getSetup().getDepartmentInfluenceFactor(),
                            0);
                }
            }

            if (score < bestScore) {
                bestScore = score;
                bestDepartmentIndex = departmentIndex;
            }
        }

        return bestDepartmentIndex;
    }

}
