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

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Patient selectedPatient;
    private Department selectedDepartment;

    public GUI(Simulation simulation) {
        this.simulation = simulation;
        initializeGUI();
    }

    private void initializeGUI() {

        JFrame frame = new JFrame("Symulacja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

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
                patientButton.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 20));  // Wypełnia szerokość
                patientButton.setMaximumSize(new Dimension(WIDTH / 3, HEIGHT / 20));

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

                if(selectedPatient != null) // żeby odświeżało na bieżąco (tymczasowe rozwiązanie)
                    outputArea.setText(selectedPatient.toString());

                else if(selectedDepartment != null) // żeby odświeżało na bieżąco (tymczasowe rozwiązanie)
                    outputArea.setText("Szczegóły oddziału: " + selectedDepartment.toString() + "\n");

                // Ustawianie akcji po kliknięciu w przycisk pacjenta
                patientButton.addActionListener(ev -> {
                    selectedPatient = patient;
                    selectedDepartment = null;
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
            departmentButton.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 16));  // Wypełnia szerokość
            departmentButton.setMaximumSize(new Dimension(WIDTH / 3, HEIGHT / 16));
            departmentButton.setBackground(new Color(200, 200, 255));  // Kolor tła kafelka

            // Akcja po kliknięciu na kafelek oddziału
            departmentButton.addActionListener(e -> {
                selectedDepartment = department;
                selectedPatient = null;
            });

            // Dodanie kafelka do panelu oddziałów
            departPanel.add(departmentButton);
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        LifeStats<Double> stats_department = new LifeStats<Double>(0.1, 0.2, 0.3);
        ArrayList<Department> departments = new ArrayList<>();
        departments.add(new Department("Onkologia", 0, 10, stats_department));
        departments.add(new Department("Ortopedia", 0, 10, stats_department));

        // Statystyki dla lekarzy
        LifeStats<Double> minDoctorModifiers = new LifeStats<>(1.0, 1.0, 1.0);
        LifeStats<Double> maxDoctorModifiers = new LifeStats<>(20.0, 20.0, 20.0);

        // Statystyki życiowe dla pacjentów
        LifeStats<Integer> minLifeStats = new LifeStats<>(0, 0, 0);
        LifeStats<Integer> maxLifeStats = new LifeStats<>(1000, 1000, 1000);

        int delayMs = 4000; // Opóźnienie w ms
        boolean generatePatientsAutomatically = true;
        boolean diagnosePatientsAutomatically = true;
        int maxNumberOfDoctorsPerPatient = 3;
        int minNumberOfDoctors = 10;
        int maxNumberOfDoctors = 20;
        int minNumberOfPatients = 5;
        int maxNumberOfPatients = 40;
        int maxIllnessAmount = 2;
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

        SimulationManager.setSimulation(new Simulation(departments, setup));

        new GUI(SimulationManager.getSimulation());
        SimulationManager.getSimulation().start();
    }

}