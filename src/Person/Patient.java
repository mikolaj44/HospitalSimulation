package Person;

import Simulation.SimulationManager;
import Utils.ColorCodes;

import java.util.ArrayList;

public class Patient extends Person implements Subject, Updateable {

    private ArrayList<Observer> observers;
    private int departmentIndex;
    private LifeStats<Integer> stats;
    private ArrayList<Illness> illnesses;

    public Patient(String name, String surname, String pesel, int departmentIndex, LifeStats<Integer> stats, ArrayList<Illness> illnesses) {
        super(name, surname, pesel);
        this.departmentIndex = departmentIndex;
        this.stats = stats;
        this.illnesses = illnesses;
        observers = new ArrayList<>();
    }

    public Patient() {

    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {

        for(int i = observers.size() - 1; i >= 0; i--){
            observers.get(i).onUpdate(this);
        }
    }

    public int getDepartmentIndex() {
        return departmentIndex;
    }

    public void setDepartmentIndex(int departmentIndex) {
        this.departmentIndex = departmentIndex;
    }

    public ArrayList<Illness> getIllnesses() {
        return illnesses;
    }

    public String getStringOfIllnesses() {

        ArrayList<Illness> illnesses = getIllnesses();
        String output = "";

        for (Illness illness : illnesses) {
            output += illness + "\n";
        }

        if (!output.equals("")) {
            output = output.substring(0, output.length() - 2);
        } else {
            output = "Brak chorób";
        }

        return output;
    }

    public void setIllnesses(ArrayList<Illness> illnesses) {
        this.illnesses = illnesses;
    }

    public LifeStats<Integer> getStats() {
        return stats;
    }

    public void setStats(LifeStats<Integer> stats) {
        this.stats = stats;
    }

    public String toString() {

        String output = "Imię: "  +  super.getName() + "\n";
        output += "Nazwisko: " + super.getSurname() + "\n";
        output += "Choroby: \n" + getStringOfIllnesses() + "\n";
        output += "Statystki pacjenta: \n" + getStats() + "\n";

        for(Observer observer : observers){

            output += observer;
        }

        return output;
    }

    public void update() {
        executeIllnesses();
    }

    public void executeIllnesses() {

        LifeStats<Integer> currentStats = getStats();

        if(illnesses.isEmpty()) {
            SimulationManager.getSimulation().removePatient(this);
            return;
        }

        for (Illness illness : this.illnesses) {
            currentStats.setPhysical(Math.max((int) (currentStats.getPhysical() - illness.getStats().getPhysical()), 0));
            currentStats.setInfection(Math.max((int) (currentStats.getInfection() - illness.getStats().getInfection()), 0));
            currentStats.setInternal(Math.max((int) (currentStats.getInternal() - illness.getStats().getInternal()), 0));
        }

        if (currentStats.getPhysical() <= 0 || currentStats.getInfection() <= 0 || currentStats.getInternal() <= 0) {
            die();
            return;
        }

        this.stats = currentStats;

        notifyObservers();
    }

    public void die() {
        this.stats = new LifeStats<Integer>(0, 0, 0);

        System.out.println(ColorCodes.RED + "Pacjent: " + getName() + " " + getSurname() + " zmarł" + ColorCodes.RESET + "\n");

        notifyObservers();
        SimulationManager.getSimulation().removePatient(this);
    }

    public Integer getLowestHealth() {
        Integer infection = stats.getInfection();
        Integer physical = stats.getPhysical();
        Integer internal = stats.getInternal();

        if(infection < physical && internal < physical){
            if (infection < internal){
                return infection;
            } else {
                return internal;
            }
        }
        return physical;
    }

    public String getShortInfo() {
        String output = super.getName() + " " + super.getSurname() + "\n";
        return output;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}