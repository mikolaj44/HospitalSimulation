package com.example.hospitalsimulation;


import Person.Doctor;
import Person.Illness;
import Simulation.AutoGeneration;
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
        ChoiceBox<Illness> illnessBox = new ChoiceBox<Illness>();
        ListOfIllnesses.readIllnessesFromFile();
        illnessBox.setItems(ListOfIllnesses.getIllnessesO());

        TextField illnessSearchField = new TextField();
        TableView<Illness> illnessTable = new TableView<>();
        illnessTable.setEditable(true);
        ObservableList<Illness> illnessList = ListOfIllnesses.getIllnessesO();

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

        layout.add(illnessBox, 0, 4);
//        layout.add(cb, 1, 4);
//
//        layout.add(simulationDelayLabel, 0, 5);
//        layout.add(simulationDelayField, 1, 5);

        layout.add(exitButton, 0, 6, 2, 1);
        Scene scene = new Scene(layout, 500, 300);
        window.setScene(scene);
        window.showAndWait();

//        SimulationManager.getSetup().setGeneratePatientsAutomatically(autoPatientGenerationCheckbox.isSelected());
//        try{
//            SimulationManager.getSetup().setMaxIllnessAmount(Integer.parseInt(ilnessessField.getText()));
//        }catch(NumberFormatException e){
//            AlertWindow.display("Błędnie podana ilość chorób");
//        }
//        try{
//            SimulationManager.getSetup().setMaxLifeStats(Integer.parseInt(maxLifeField.getText()));
//        }catch(NumberFormatException e){
//            AlertWindow.display("Błędnie podana wartość maksymalnego życia pacjenta");
//        }
//        try{
//            SimulationManager.getSetup().setMaxLifeStats(Integer.parseInt(minLifeField.getText()));
//        }catch(NumberFormatException e){
//            AlertWindow.display("Błędnie podana wartość minimalnego życia pacjenta");
//        }
//        try{
//            SimulationManager.getSetup().setDelayMs(Integer.parseInt(simulationDelayField.getText()));
//        }catch(NumberFormatException e){
//            AlertWindow.display("Błędnie podana wartość opóźnienia symulacji");
//        }
//        try{
//            SimulationManager.getSetup().setDepartmentInfluenceFactor(Double.parseDouble(departmentHealthImpactField.getText()));
//        }catch(NumberFormatException e){
//            AlertWindow.display("Błędnie podana wartość wpłýwu oddziału");
//        }

    }

    private void saveAndExit(){

    }

}
