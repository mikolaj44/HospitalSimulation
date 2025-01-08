package Person;

import java.util.ArrayList;

public class Patient extends Person implements Subject, Updateable{

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

    public Patient(){

    }

    public void registerObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void notifyObservers(){

        for(Observer o : observers)
            o.onUpdate(this);
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
            output += illness.getInfo() + "\n";
        }

        if (!output.equals("")){
            output = output.substring(0, output.length() - 2);
        }
        else {
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

    public String getInfo(){

        String output = "========= Pacjent =========\n";
        output += "Imię: " + super.getName() + "\n";
        output += "Nazwisko: " + super.getSurname() + "\n";
        output += "Choroby: \n" + getStringOfIllnesses() + "\n";
        output += "+--------------+\n";
        output += "Statystki pacjenta: \n" + getStats() + "\n";
        output += "====================\n";
        return output;
    }

    public void update(){
        executeIllnesses();
    }

    public void executeIllnesses(){

        LifeStats<Integer> currentStats = getStats();

        for (Illness illness : this.illnesses) {
            currentStats.setPhysical(Math.max((int)(currentStats.getPhysical() - illness.getStats().getPhysical()), 0));
            currentStats.setInfection(Math.max((int)(currentStats.getInfection() - illness.getStats().getInfection()), 0));
            currentStats.setInternal(Math.max((int)(currentStats.getInternal() - illness.getStats().getInternal()), 0));
        }

        this.stats = currentStats;

        notifyObservers();
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}