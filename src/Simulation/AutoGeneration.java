package Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

import Person.*;

import static Utils.RandomRange.*;

public class AutoGeneration implements GenerationMethod {

    private ArrayList<String> maleNames;
    private ArrayList<String> femaleNames;
    private ArrayList<String> surnames;

    private Setup simulationSetup;

    static String[] filePaths = {"./generation_data/malenames.txt", "./generation_data/femalenames.txt", "./generation_data/surnames.txt"};

    private boolean readNamesFromFile() {

        String line;

        Field[] fields = this.getClass().getDeclaredFields();

        for (int i = 0; i < filePaths.length; i++) {

            try (BufferedReader reader = new BufferedReader(new FileReader(filePaths[i]))){
                fields[i].setAccessible(true);

                ArrayList<String> list = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }

                fields[i].set(this, list);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public AutoGeneration(Setup simulationSetup) {
        this.simulationSetup = simulationSetup;
        readNamesFromFile();
    }

    private Illness generateIllness() {
        return null;
    }

    private String generateName() {

        if (randomBoolean()) // na wypadek gdyby ktoś chciał dodać jeszcze nazwiska podzielone na męskie i żeńskie - nie znalazłem jakiejś fajnej listy
            return femaleNames.get(randomRange(maleNames.size()));
        return maleNames.get(randomRange(maleNames.size()));
    }

    private int generateDepartmentIndex() {
        return randomRange(simulationSetup.getDepartments().size());
    }

    private LifeStats<Integer> generateLifeStats() {
        return new LifeStats<>(randomRange(1, 10), randomRange(1, 10), randomRange(1, 10));
    }

    public Patient generatePatient() {
        return new Patient(generateName(), surnames.get(randomRange(surnames.size())), randomRange(10000000000L, 999999999999L) + "", generateDepartmentIndex(), generateLifeStats(), null);
    }

    public Doctor generateDoctor() {
        return new Doctor(generateName(), surnames.get(randomRange(surnames.size())), randomRange(10000000000L, 999999999999L) + "", generateDepartmentIndex(), randomRange(1, 10), randomRange(1, 3));
    }
}