package Simulation;

import Person.*;

public interface GenerationMethod {

    public Patient generatePatient();
    public Doctor generateDoctor();
    public Illness generateIllness();
}
