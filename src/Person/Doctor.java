package Person;

import Simulation.SimulationManager;

import java.util.ArrayList;

import static Utils.RandomRange.*;

public class Doctor extends Person implements Observer{

    private int departmentIndex;
    private LifeStats<Double> lifeStatsModifiers;
    private int shift;

    public Doctor(String name, String surname, String pesel, int departmentIndex, LifeStats<Double> lifeStatsModifiers, int shift) {
        super(name, surname, pesel);
        this.departmentIndex = departmentIndex;
        this.lifeStatsModifiers = lifeStatsModifiers; // Zakres od 0-20
        this.shift = shift;
    }

    public Doctor(){

    }

    public void performHealing(Patient p) {
        if(p.getIllnesses().isEmpty())
        {
            p.removeObserver(this);
            SimulationManager.simulation.removePatient(p);
            SimulationManager.simulation.onPatientRecovered();
            System.out.println("Pacjent: " + p.getName() + " " + p.getSurname() + " wyleczony");

            return;
        }

        try {
            for (Illness illness : p.getIllnesses()) {
                if (!illness.isCured()) {
                    LifeStats<Double> currentStats = illness.getStats();
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

                    illness.setStats(newStats);

                    if (illness.isCured()) {
                        ArrayList<Illness> illnesses = p.getIllnesses();
                        illnesses.remove(illness);
                        p.setIllnesses(illnesses);
                    }
                } else {
                    ArrayList<Illness> illnesses = p.getIllnesses();
                    illnesses.remove(illness);
                    p.setIllnesses(illnesses);
                }
            }
        } catch (Exception e) {
//                System.out.println(e.getMessage());
        }
    }

    public void onUpdate(Subject s){
        if(((Patient)s).getStats().getPhysical() <= 0) {
            s.removeObserver(this);
        }
        else {
            performHealing((Patient)s);
        }

    }

    public String getInfo(){
        String info = "========= Doktor =========\n";
        info += "Imię: " + super.getName() + "\n";
        info += "Nazwisko: " + super.getSurname() + "\n";
        info += "Umiejętności: " + getLifeStatsModifiers() + "\n";
        info += "====================\n";
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