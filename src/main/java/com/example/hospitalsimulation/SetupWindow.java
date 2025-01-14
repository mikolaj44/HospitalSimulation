package com.example.hospitalsimulation;


import Simulation.SimulationManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class SetupWindow {

    public static void display() {
        Stage window = new Stage();
        window.setTitle("Ustawienia");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);

        Label autoPatientGeneration = new Label("Automatyczna generacja pacjentów:");
        CheckBox autoPatientGenerationCheckbox = new CheckBox();
        autoPatientGenerationCheckbox.setSelected(SimulationManager.getSetup().isGeneratingPatientsAutomatically());

        // Labels and input fields for each parameter
        Label ilnessessLabel = new Label("Maksymalna ilość chorób:");
        TextField ilnessessField = new TextField();
        ilnessessField.setText(String.valueOf(SimulationManager.getSetup().getMaxIllnessAmount()));
        ilnessessField.setPromptText("Podaj liczbę");

        Label departmentHealthImpactLabel = new Label("Wpływ oddziału na zdrowie pacjenta:");
        TextField departmentHealthImpactField = new TextField();
        departmentHealthImpactField.setText(String.valueOf(SimulationManager.getSetup().getDepartmentInfluenceFactor()));
        departmentHealthImpactField.setPromptText("Podaj wartość");

        Label maxLifeLabel = new Label("Maksymalne życie pacjenta:");
        TextField maxLifeField = new TextField();
        maxLifeField.setText(String.valueOf(SimulationManager.getSetup().getMaxLifeStats().getInternal()));
        maxLifeField.setPromptText("Podaj liczbę");

        Label minLifeLabel = new Label("Minimalne życie pacjenta:");
        TextField minLifeField = new TextField();
        minLifeField.setText(String.valueOf(SimulationManager.getSetup().getMinLifeStats().getInternal()));
        minLifeField.setPromptText("Podaj liczbę");

        Label simulationDelayLabel = new Label("Opóźnienie symulacji (ms):");
        TextField simulationDelayField = new TextField();
        simulationDelayField.setText(String.valueOf(SimulationManager.getSetup().getDelayMs()));
        simulationDelayField.setPromptText("Podaj liczbę");

        // Exit button
        Button exitButton = new Button("Zapisz i zamknij");
        exitButton.setOnAction(e -> window.close());

        // Layout setup
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(autoPatientGeneration, 0, 0);
        layout.add(autoPatientGenerationCheckbox, 1, 0);

        // Adding components to the layout
        layout.add(ilnessessLabel, 0, 1);
        layout.add(ilnessessField, 1, 1);

        layout.add(departmentHealthImpactLabel, 0, 2);
        layout.add(departmentHealthImpactField, 1, 2);

        layout.add(maxLifeLabel, 0, 3);
        layout.add(maxLifeField, 1, 3);

        layout.add(minLifeLabel, 0, 4);
        layout.add(minLifeField, 1, 4);

        layout.add(simulationDelayLabel, 0, 5);
        layout.add(simulationDelayField, 1, 5);

        layout.add(exitButton, 0, 6, 2, 1);
        Scene scene = new Scene(layout, 500, 300);
        window.setScene(scene);
        window.showAndWait();

        SimulationManager.getSetup().setGeneratePatientsAutomatically(autoPatientGenerationCheckbox.isSelected());
        try{
            SimulationManager.getSetup().setMaxIllnessAmount(Integer.parseInt(ilnessessField.getText()));
        }catch(NumberFormatException e){
            AlertWindow.display("Błędnie podana ilość chorób");
        }
        try{
            SimulationManager.getSetup().setMaxLifeStats(Integer.parseInt(maxLifeField.getText()));
        }catch(NumberFormatException e){
            AlertWindow.display("Błędnie podana wartość maksymalnego życia pacjenta");
        }
        try{
            SimulationManager.getSetup().setMaxLifeStats(Integer.parseInt(minLifeField.getText()));
        }catch(NumberFormatException e){
            AlertWindow.display("Błędnie podana wartość minimalnego życia pacjenta");
        }
        try{
            SimulationManager.getSetup().setDelayMs(Integer.parseInt(simulationDelayField.getText()));
        }catch(NumberFormatException e){
            AlertWindow.display("Błędnie podana wartość opóźnienia symulacji");
        }
        try{
            SimulationManager.getSetup().setDepartmentInfluenceFactor(Double.parseDouble(departmentHealthImpactField.getText()));
        }catch(NumberFormatException e){
            AlertWindow.display("Błędnie podana wartość wpłýwu oddziału");
        }

    }

    private void saveAndExit(){

    }

}
