package com.example.hospitalsimulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Simulation.*;
import Person.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GUIApplication extends Application {
    SimulationManager simulationManager;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private ObservableList<Patient> patientsList;
    private IntegerProperty recovered;
    private IntegerProperty deceased;
    private StringProperty time;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Symulacja");

        startSimulation();

        ListView<Patient> listView = generatePatientList();
        recovered = new SimpleIntegerProperty(simulationManager.getRecovered());
        deceased = new SimpleIntegerProperty(simulationManager.getDeceased());
        time = new SimpleStringProperty(simulationManager.getTime());

        Label recoveredLabel = new Label();
        recoveredLabel.textProperty().bind(recovered.asString("Wyleczeni: %d"));
        Label deceasedLabel = new Label();
        deceasedLabel.textProperty().bind(deceased.asString("Zmarli: %d"));
        Label clockLabel = new Label();
        clockLabel.textProperty().bind(time);
        clockLabel.setTextFill(Color.rgb(21, 100, 4));

        HBox topInfo = new HBox();
        topInfo.setSpacing(20);
        topInfo.setStyle("-fx-background-color: lightblue;");
        topInfo.getChildren().addAll(recoveredLabel, deceasedLabel, clockLabel);
        Button setupButton = new Button("Setup");
        setupButton.setOnAction(event -> {SetupWindow.display();});

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(listView);
        borderPane.setTop(topInfo);
        borderPane.setCenter(setupButton);
        listView.prefHeightProperty().bind(primaryStage.heightProperty());

        // Tworzymy scenę i przypisujemy ją do okna
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            syncData();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private ListView<Patient> generatePatientList(){
        patientsList = FXCollections.observableArrayList(simulationManager.getPatients());

        ListView<Patient> listView = new ListView<>(patientsList);

        listView.setCellFactory(param -> new ListCell<Patient>() {
            @Override
            protected void updateItem(Patient patient, boolean empty) {
                super.updateItem(patient, empty);
                if (empty || patient == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(patient.getShortInfo());
                    double lifeLevel = patient.getLowestHealth();

                    if (lifeLevel < 150) {
                        setStyle("-fx-background-color: #ff3636;");
                    } else if (lifeLevel < 300) {
                        setStyle("-fx-background-color: #fff07a;");
                    } else {
                        setStyle("-fx-background-color: #72ff63;");
                    }
                }
            }
        });

        return listView;
    }

    private void syncData() {
        // Pobieramy najnowszą listę pacjentów z symulacji
        List<Patient> updatedPatients = simulationManager.getPatients();

        // Usuwamy wszystkie elementy z ObservableList
        patientsList.clear();

        // Dodajemy nowych pacjentów z symulacji
        patientsList.addAll(updatedPatients);

        recovered.set(simulationManager.getRecovered());
        deceased.set(simulationManager.getDeceased());
        time.set(simulationManager.getTime());
    }

    private void startSimulation() {
        simulationManager = new SimulationManager();
        ArrayList<Department> departments = new ArrayList<>();
        LifeStats<Double> stats_department = new LifeStats<Double>(0.1, 0.2, 0.3);
        departments.add(new Department("Onkologia", 0, 10, stats_department));
        departments.add(new Department("Ortopedia", 0, 10, stats_department));
        simulationManager.buildSimulation(departments);
        simulationManager.startSimulation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
