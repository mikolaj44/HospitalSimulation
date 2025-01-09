package Person;

import Simulation.SimulationManager;

import java.util.ArrayList;

public class Doctor extends Person implements Observer {

    private int departmentIndex;
    private LifeStats<Double> lifeStatsModifiers;
    private int shift;

    public Doctor(String name, String surname, String pesel, int departmentIndex, LifeStats<Double> lifeStatsModifiers, int shift) {
        super(name, surname, pesel);
        this.departmentIndex = departmentIndex;
        this.lifeStatsModifiers = lifeStatsModifiers; // Zakres od 0-20
        this.shift = shift;
    }

    public Doctor() {

    }

    public void performHealing(Patient p) {

        if (p.getIllnesses().isEmpty()) {

            p.removeObserver(this);
            SimulationManager.getSimulation().removePatient(p);
            SimulationManager.getSimulation().onPatientRecovered();

            System.out.println("Pacjent: " + p.getName() + " " + p.getSurname() + " wyleczony" + "\n");
            return;
        }

        try {

            ArrayList<Illness> illnesses = p.getIllnesses();

            for (int i = illnesses.size() - 1; i >= 0; i--) {

                if (!illnesses.get(i).isCured()) {

                    LifeStats<Double> currentStats = illnesses.get(i).getStats();
                    LifeStats<Double> newStats = new LifeStats<Double>(0.0, 0.0, 0.0);

                    if (currentStats.getPhysical() - this.lifeStatsModifiers.getPhysical() > 0.0) {
                        newStats.setPhysical(currentStats.getPhysical() - this.lifeStatsModifiers.getPhysical());
                    } else {
                        newStats.setPhysical(0.0);
                    }

                    if (currentStats.getInternal() - this.lifeStatsModifiers.getInternal() > 0.0) {
                        newStats.setInternal(currentStats.getInternal() - this.lifeStatsModifiers.getInternal());
                    } else {
                        newStats.setInternal(0.0);
                    }

                    if (currentStats.getInfection() - this.lifeStatsModifiers.getInfection() > 0.0) {
                        newStats.setInfection(currentStats.getInfection() - this.lifeStatsModifiers.getInfection());
                    } else {
                        newStats.setInfection(0.0);
                    }

                    illnesses.get(i).setStats(newStats);

                    if (illnesses.get(i).isCured()) {
                        illnesses.remove(illnesses.get(i));
                        p.setIllnesses(illnesses);
                    }
                } else {
                    illnesses.remove(illnesses.get(i));
                    p.setIllnesses(illnesses);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void onUpdate(Subject s) {

        if (((Patient) s).getStats().getPhysical() <= 0) {
            s.removeObserver(this);
        } else {
            performHealing((Patient) s);
        }

    }

    public String toString() {

        String info = "######### Doktor #########" + "\n";
        info += "Imię: " + super.getName() + "\n";
        info += "Nazwisko: " + super.getSurname() + "\n";
        info += "Umiejętności: " + "\n" + getLifeStatsModifiers() + "\n";
        info += "####################" + "\n";

        return info;
    }

    public int getDepartmentIndex() {
        return departmentIndex;
    }

    public void setDepartmentIndex(int departmentIndex) {
        this.departmentIndex = departmentIndex;
    }

    public LifeStats<Double> getLifeStatsModifiers() {
        return lifeStatsModifiers;
    }

    public void setLifeStatsModifiers(LifeStats<Double> lifeStatsModifiers) {
        this.lifeStatsModifiers = lifeStatsModifiers;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
}