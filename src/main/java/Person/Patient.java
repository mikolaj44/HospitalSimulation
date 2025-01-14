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
        ((Doctor)(o)).setAmountOfPatients(((Doctor)(o)).getAmountOfPatients() + 1);
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        ((Doctor)(o)).setAmountOfPatients( Math.max(0, ((Doctor)(o)).getAmountOfPatients() - 1) );
        observers.remove(o);
    }

    public void notifyObservers() {

        for(int i = observers.size() - 1; i >= 0; i--){
            observers.get(i).onUpdate(this);
        }
    }

    private void removeAllDoctors(){

        for(int i = observers.size() - 1; i >= 0; i--){

            if(observers.get(i) instanceof Doctor){
                removeObserver(observers.get(i));
            }
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

        // pacjent wyleczony
        if(illnesses.isEmpty()) {

            System.out.println(ColorCodes.BLUE + "Pacjent: " + getName() + " " + getSurname() + " wyleczony" + ColorCodes.RESET + "\n");

            removeAllDoctors(); // odłączenie wszystkich lekarzy pacjenta
            SimulationManager.getSimulation().getDepartments().get(getDepartmentIndex()).removePatient(); // zmniejszenie liczby pacjentów w danym oddziale
            SimulationManager.getSimulation().onPatientRecovered(); // zwiększenie ogólnej liczby wyleczonych
            SimulationManager.getSimulation().removePatient(this); // usunięcie pacjenta z listy pacjentów symulacji
            return;
        }

        for (Illness illness : this.illnesses) {

            for(int i = 0; i < currentStats.getStatAmount(); i++){
                currentStats.setStatByIndex(i, Math.max(0, (int)(currentStats.getStatByIndex(i) - illness.getStats().getStatByIndex(i)) ));
            }
        }

        for(int i = 0; i < currentStats.getStatAmount(); i++){
            if (currentStats.getStatByIndex(i) <= 0) {
                die();
                return;
            }
        }

        this.stats = currentStats;

        notifyObservers();
    }

    // pacjent umarł
    public void die() {

        this.stats = new LifeStats<>(0, 0, 0);

        System.out.println(ColorCodes.RED + "Pacjent: " + getName() + " " + getSurname() + " zmarł" + ColorCodes.RESET + "\n");

        removeAllDoctors(); // odłączenie wszystkich lekarzy pacjenta (już ich nie powiadamiamy, trupa nie wyleczymy)

        SimulationManager.getSimulation().getDepartments().get(getDepartmentIndex()).removePatient(); // zmniejszenie liczby pacjentów w danym oddziale
        SimulationManager.getSimulation().onPatientDied();// zwiększenie ogólnej liczby zmarłych
        SimulationManager.getSimulation().removePatient(this); // usunięcie pacjenta z listy pacjentów symulacji
    }

    public int getLowestHealth() {

        int lowestVal = stats.getStatByIndex(0);

        for(int i = 1; i < stats.getStatAmount(); i++){
            lowestVal = Math.min(stats.getStatByIndex(i), lowestVal);
        }

        return lowestVal;
    }

    public String getShortInfo() {
        String output = super.getName() + " " + super.getSurname() + "\n";
        return output;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}