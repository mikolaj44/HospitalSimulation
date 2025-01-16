package com.example.hospitalsimulation;


import Person.Doctor;
import Person.Illness;
import Person.LifeStats;
import Person.Patient;
import Simulation.AutoGeneration;
import Simulation.BestAssignment;
import Simulation.SimulationManager;
import Utils.ListOfIllnesses;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.util.ArrayList;

public class ManualAddingWindow {

    public static void display() {
        Stage window = new Stage();
        window.setTitle("Dodawanie pacjenta");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);

        // Labels and input fields for each parameter
        Label peselLabel = new Label("PESEL: ");
        TextField peselField = new TextField();
        peselField.setPromptText("Podaj PESEL");

        Label nameLabel = new Label("Imię:");
        TextField nameField = new TextField();
        nameField.setPromptText("Podaj wartość");

        Label surnameLabel = new Label("Nazwisko:");
        TextField surnameField = new TextField();
        surnameField.setPromptText("Podaj nazwisko");

        Label doctorLabel = new Label("Przypisz lekarza:");
        ChoiceBox<Doctor> doctorBox = new ChoiceBox<Doctor>();
        doctorBox.setItems(SimulationManager.getDoctorsO());

        Label illnessLabel = new Label("Przypisz choroby:");
        ListOfIllnesses.readIllnessesFromFile();

        CheckComboBox<Illness> illnessBox = new CheckComboBox<>();
        illnessBox.getItems().addAll(ListOfIllnesses.getIllnessesO());

        Label statsLabel = new Label("Podaj statystyki:");

        Label physLabel = new Label("Fizyczne:");
        TextField physField = new TextField();

        Label intLabel = new Label("Wewnętrzne:");
        TextField intField = new TextField();

        Label infLabel = new Label("Infekcja:");
        TextField infField = new TextField();

        // Exit button
        Button exitButton = new Button("Zapisz i zamknij");
        exitButton.setOnAction(e -> window.close());

        // Layout setup
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        layout.setHgap(10);


        // Adding components to the layout
        layout.add(peselLabel, 0, 0);
        layout.add(peselField, 1, 0);

        layout.add(nameLabel, 0, 1);
        layout.add(nameField, 1, 1);

        layout.add(surnameLabel, 0, 2);
        layout.add(surnameField, 1, 2);

        layout.add(doctorLabel, 0, 3);
        layout.add(doctorBox, 1, 3);

        layout.add(illnessLabel, 0, 4);
        layout.add(illnessBox, 1, 4);
//
        layout.add(statsLabel, 0, 5);

        layout.add(physLabel, 0, 6);
        layout.add(physField, 1, 6);

        layout.add(intLabel, 0, 7);
        layout.add(intField, 1, 7);

        layout.add(infLabel, 0, 8);
        layout.add(infField, 1, 8);

        layout.add(exitButton, 0, 9, 2, 1);
        Scene scene = new Scene(layout, 500, 400);
        window.setScene(scene);
        window.showAndWait();

        try{
            String name = nameField.getText();
            String surname = surnameField.getText();
            String pesel = peselField.getText();
            ArrayList<Illness> illnesses = new ArrayList<Illness>(illnessBox.getCheckModel().getCheckedItems());
            System.out.println(illnesses);
            LifeStats<Integer> lifeStats = new LifeStats<>(Integer.parseInt(physField.getText()), Integer.parseInt(intField.getText()), Integer.parseInt(infField.getText()));
            BestAssignment assign = new BestAssignment();
            Patient patient = new Patient(name, surname, pesel, 0, lifeStats, illnesses);
            int departmentIndex = assign.getDepartmentIndex(patient, SimulationManager.getDepartments());
            SimulationManager.getSimulation().manuallyAddPatient(patient);
            SimulationManager.getPatients().getLast().setDepartmentIndex(departmentIndex);
        }catch (Exception e){
            AlertWindow.display("Błędnie podane dane");
        }

    }




}
