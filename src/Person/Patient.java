package Person;

import java.util.ArrayList;

public class Patient extends Person implements Subject,Updatable{

    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private int departmentIndex;
    private LifeStats<Double> stats;
    private ArrayList<Illness> Illnesses;

    public Patient(String name, String surname, String pesel, int departmentIndex, LifeStats<Double> stats, ArrayList<Illness> illnesses) {
        super(name, surname, pesel);
        this.departmentIndex = departmentIndex;
        this.stats = stats;
        Illnesses = illnesses;
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
        Illnesses = illnesses;
    }

    public LifeStats<Double> getStats() {
        return stats;
    }

    public void setStats(LifeStats<Double> stats) {
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

    public void updateLifeStats(){
        LifeStats<Double> currentStats = getStats();

        for (Illness illness : this.Illnesses) {
            currentStats.setPhysical(currentStats.getPhysical()-illness.getStats().getPhysical());
            currentStats.setInfection(currentStats.getInfection()-illness.getStats().getInfection());
            currentStats.setInternal(currentStats.getInternal()-illness.getStats().getInternal());
        }

        this.stats = currentStats;
    }
}
