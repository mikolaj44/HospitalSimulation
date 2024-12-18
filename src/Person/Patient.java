package Person;

import java.util.ArrayList;

public class Patient extends Person {

    private ArrayList<Observer> observers;
    private int departmentIndex;
    private LifeStats<Integer> stats;
    private ArrayList<Illness> Illnesses;

    public Patient(String name, String surname, String pesel, int departmentIndex, LifeStats<Integer> stats, ArrayList<Illness> illnesses) {
        super(name, surname, pesel);
        this.departmentIndex = departmentIndex;
        this.stats = stats;
        Illnesses = illnesses;
    }

    public Patient(){

    };

    public void registerObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void notifyObservers(){

        for(Observer o : observers)
            o.update((Subject)this);
    }

    public int getDepartmentIndex() {
        return departmentIndex;
    }

    public void setDepartmentIndex(int departmentIndex) {
        this.departmentIndex = departmentIndex;
    }

    public ArrayList<Illness> getIllnesses() {
        return Illnesses;
    }

    public void setIllnesses(ArrayList<Illness> illnesses) {
        Illnesses = illnesses;
    }

    public LifeStats<Integer> getStats() {
        return stats;
    }

    public void setStats(LifeStats<Integer> stats) {
        this.stats = stats;
    }
}
