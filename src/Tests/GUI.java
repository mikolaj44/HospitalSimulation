package Tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Simulation.*;

import Person.*;
import Utils.*;

public class GUI {

    private Simulation simulation;

    public GUI(Simulation simulation) {
        this.simulation = simulation;
        initializeGUI();
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("Symulacja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Symulacja", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel patientPanel = new JPanel();
        patientPanel.setLayout(new BoxLayout(patientPanel, BoxLayout.Y_AXIS));
        patientPanel.setBorder(BorderFactory.createTitledBorder("Lista Pacjentów"));

        JPanel departPanel = new JPanel();
        departPanel.setLayout(new BoxLayout(departPanel, BoxLayout.Y_AXIS));
        departPanel.setBorder(BorderFactory.createTitledBorder("Lista oddziałów"));

        JScrollPane patientScrollPane = new JScrollPane(patientPanel);
        JScrollPane departScrollPane = new JScrollPane(departPanel);
        frame.add(departScrollPane, BorderLayout.WEST);
        frame.add(patientScrollPane, BorderLayout.EAST);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        Timer patientUpdater = new Timer(1000, e -> {
            patientPanel.removeAll(); // Czyszczenie panelu pacjentów
            ArrayList<Patient> patients = simulation.getPatients();

            for (int i = 0; i < patients.size(); i++) {
                Patient patient = patients.get(i);
                JButton patientButton = new JButton((i + 1) + ". " + patient.getShortInfo());
                patientButton.setPreferredSize(new Dimension(200, 40));  // Wypełnia szerokość
                patientButton.setMaximumSize(new Dimension(200, 40));

                // Ustawianie kolorów przycisków w zależności od stanu zdrowia
                if (patient.getLowestHealth() < 100) {
                    patientButton.setBackground(Color.RED);
                    patientButton.setForeground(Color.WHITE);
                } else if (patient.getLowestHealth() < 300) {
                    patientButton.setBackground(Color.YELLOW);
                    patientButton.setForeground(Color.BLACK);
                } else {
                    patientButton.setBackground(Color.GREEN);
                    patientButton.setForeground(Color.BLACK);
                }

                // Ustawianie akcji po kliknięciu w przycisk pacjenta
                patientButton.addActionListener(ev -> {
                    outputArea.setText(patient.toString());
                });

                // Dodanie przycisku do panelu pacjentów
                patientPanel.add(patientButton);
            }

            patientPanel.revalidate();
            patientPanel.repaint();
        });
        patientUpdater.start();

        ArrayList<Department> departments = simulation.getDepartments();
        for (Department department : departments) {
            // Tworzymy kafelek dla oddziału
            JButton departmentButton = new JButton(department.getName());
            departmentButton.setPreferredSize(new Dimension(200, 50));  // Wypełnia szerokość
            departmentButton.setMaximumSize(new Dimension(200, 50));
            departmentButton.setBackground(new Color(200, 200, 255));  // Kolor tła kafelka

            // Akcja po kliknięciu na kafelek oddziału
            departmentButton.addActionListener(e -> {
                outputArea.setText("Szczegóły oddziału: " + department.toString() + "\n");
            });

            // Dodanie kafelka do panelu oddziałów
            departPanel.add(departmentButton);
        }


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        LifeStats<Double> stats_department = new LifeStats<Double>(0.1,0.2,0.3);
        ArrayList<Department> departments = new ArrayList<>();
        departments.add(new Department("Onkologia", 0, 10, stats_department));
        departments.add(new Department("Ortopedia", 0, 10, stats_department));

        // Statystyki dla lekarzy
        LifeStats<Double> minDoctorModifiers = new LifeStats<>(1.0, 1.0, 1.0);
        LifeStats<Double> maxDoctorModifiers = new LifeStats<>(5.0, 5.0, 5.0);

        // Statystyki życiowe dla pacjentów
        LifeStats<Integer> minLifeStats = new LifeStats<>(0, 0, 0);
        LifeStats<Integer> maxLifeStats = new LifeStats<>(500, 500, 500);

        int delayMs = 1000; // Opóźnienie w ms
        boolean generatePatientsAutomatically = true;
        boolean diagnosePatientsAutomatically = true;
        int maxNumberOfDoctorsPerPatient = 3;
        int minNumberOfDoctors = 1;
        int maxNumberOfDoctors = 5;
        int minNumberOfPatients = 5;
        int maxNumberOfPatients = 15;
        int maxIllnessAmount = 3;
        int numberOfShifts = 2;

        Setup setup = new Setup(
                departments,
                delayMs,
                generatePatientsAutomatically,
                diagnosePatientsAutomatically,
                maxNumberOfDoctorsPerPatient,
                minNumberOfDoctors,
                maxNumberOfDoctors,
                minNumberOfPatients,
                maxNumberOfPatients,
                maxIllnessAmount,
                numberOfShifts,
                minDoctorModifiers,
                maxDoctorModifiers,
                minLifeStats,
                maxLifeStats
        );

        GenerationMethod method = new AutoGeneration(setup);

        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(method.generatePatient());
        patients.add(method.generatePatient());
        patients.add(method.generatePatient());

        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(method.generateDoctor());

        patients.get(0).registerObserver(doctors.get(0));

        ArrayList<GenerationMethod> generationMethods = new ArrayList<>();
        generationMethods.add(method);

        SimulationManager.setSimulation(new Simulation(departments, doctors, patients, generationMethods, setup));

        new GUI(SimulationManager.getSimulation());
        SimulationManager.getSimulation().start();
    }

}
