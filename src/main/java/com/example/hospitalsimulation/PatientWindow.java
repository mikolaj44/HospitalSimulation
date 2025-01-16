package com.example.hospitalsimulation;

import Person.Patient;
import Simulation.SimulationManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientWindow {

    public static void display(Patient patient) {
        Stage window = new Stage();

        window.setTitle("Dane pacjenta");



        Label nameAndSurname = new Label(patient.getName() + " " + patient.getSurname());
        Label lifeStats = new Label(patient.getLife());
        Label illnessessLabel = new Label("Choroby: \n" + patient.getIllnesses().toString());
        Label departmentLabel = new Label("Aktualny oddział: " + SimulationManager.getDepartments().get(patient.getDepartmentIndex()));

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(nameAndSurname, lifeStats, illnessessLabel, departmentLabel);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

    }
}
