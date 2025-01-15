package com.example.hospitalsimulation;


import Simulation.SimulationManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class AlertWindow {

    public static void display(String info) {
        Stage window = new Stage();
        window.setTitle("Błąd");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);

        Label text = new Label(info);
        text.setWrapText(true);

        Button ok = new Button("OK");
        ok.setOnAction(e -> {window.close();});

        VBox vbox = new VBox();
        vbox.getChildren().addAll(text, ok);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        window.setScene(new Scene(vbox, 300, 100));
        window.showAndWait();

        System.out.println(SimulationManager.getSetup().getMaxIllnessAmount());
    }

}
