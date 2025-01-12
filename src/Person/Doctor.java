package Person;

import Simulation.SimulationManager;
import Utils.ColorCodes;

import java.util.ArrayList;

public class Doctor extends Person implements Observer {

    private int departmentIndex;
    private LifeStats<Double> lifeStatsModifiers;
    private int shift;
    private int amountOfPatients = 0;

    public Doctor(String name, String surname, String pesel, int departmentIndex, LifeStats<Double> lifeStatsModifiers, int shift) {
        super(name, surname, pesel);
        this.departmentIndex = departmentIndex;
        this.lifeStatsModifiers = lifeStatsModifiers; // Zakres od 0-20
        this.shift = shift;
    }

    public Doctor() {

    }

    public void performHealing(Patient p) {

        // jeśli nie ma chorób, to sprawdza to wcześniej Pacjent

        ArrayList<Illness> illnesses = p.getIllnesses();

        for (int i = illnesses.size() - 1; i >= 0; i--) {

            LifeStats<Double> currentStats = illnesses.get(i).getStats();

            LifeStats<Double> newStats = new LifeStats<>(0.0, 0.0, 0.0);

            for(int j = 0; j < newStats.getStatAmount(); j++){
                newStats.setStatByIndex(j, Math.max(0.0, currentStats.getStatByIndex(j) - this.lifeStatsModifiers.getStatByIndex(j)));
            }

            illnesses.get(i).setStats(newStats);

            if (illnesses.get(i).isCured()) {
                illnesses.remove(illnesses.get(i));
                p.setIllnesses(illnesses);
            }
        }
    }

    public void onUpdate(Subject s) {

        // przypadek śmierci jest obsłużony w Pacjencie zanim Doktor zostanie powiadomiony
        performHealing((Patient) s);
    }

    public String toString() {

        String info = "######### Doktor #########" + "\n";
        info += "Imię: " + super.getName() + "\n";
        info += "Nazwisko: " + super.getSurname() + "\n";
        info += "Umiejętności: " + "\n" + getLifeStatsModifiers() + "\n";
        info += "####################" + "\n";

        return info;
    }

    public int getAmountOfPatients() {
        return amountOfPatients;
    }

    public void setAmountOfPatients(int amountOfPatients) {
        this.amountOfPatients = amountOfPatients;
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