import java.util.ArrayList;

public class Doctor extends Person{

    private int departmentIndex;
    private int skill;
    private int shift;

    public Doctor(String name, String surname, String pesel, int departmentIndex, int skill, int shift) {
        super(name, surname, pesel);
        this.departmentIndex = departmentIndex;
        this.skill = skill;
        this.shift = shift;
    }

    public void diagnosePatient(Patient p){

    }

    public void performHealing(Patient p){

        //p.getIllnesses().get(0).setStats(new LifeStats<Double>(.1,.2,.3));
    }

    public void update(Subject s){
        performHealing((Patient)s);
    }

    public void registerWith(Subject s){
        s.registerObserver((Observer)this);
    }

    public void unRegisterWith(Subject s){
        s.removeObserver((Observer)this);
    }

    public int getDepartmentIndex() {
        return departmentIndex;
    }

    public void setDepartmentIndex(int departmentIndex) {
        this.departmentIndex = departmentIndex;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
}