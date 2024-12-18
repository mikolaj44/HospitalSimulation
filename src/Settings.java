public class Settings {

    private int simulationSpeed;
    private boolean generatePatientsAutomatically;
    private boolean diagnosePatientsAutomatically;

    public Settings(int simulationSpeed, boolean generatePatientsAutomatically, boolean diagnosePatientsAutomatically) {
        this.simulationSpeed = simulationSpeed;
        this.generatePatientsAutomatically = generatePatientsAutomatically;
        this.diagnosePatientsAutomatically = diagnosePatientsAutomatically;
    }

    public void toggleAutoDiagnose(){
        diagnosePatientsAutomatically = !diagnosePatientsAutomatically;
    }
    public void toggleAutoGenerate(){
        generatePatientsAutomatically = !generatePatientsAutomatically;
    }
    public void setSimulationSpeed(int speed){
        simulationSpeed = speed;
    }
}
