package Simulation;

import Person.*;

public interface GenerationMethod {

    Patient generatePatient();
    Doctor generateDoctor();
}