package Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;

import Person.*;
import static Utils.RandomRange.*;

public class AutoGeneration implements GenerationMethod {

    private ArrayList<String> maleNames;
    private ArrayList<String> femaleNames;
    private ArrayList<String> surnames;
    private ArrayList<Illness> illnesses = new ArrayList<Illness>();

    private Setup simulationSetup;

    static String[] filePaths = {"./generation_data/malenames.txt", "./generation_data/femalenames.txt", "./generation_data/surnames.txt"};
    static String illnessPath = "./generation_data/illnesses.txt";

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

    private Illness stringToIllness(String illnessString) {

        // Najpierw dopasowujemy wszystkie znaki aż do napotkania spacji
        // Potem po kolei dopasowujemy liczby
        String regex = "^(.*?)\\s(\\d+)\\s(\\d+)\\s(\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(illnessString.trim());

        matcher.matches();
        String name = matcher.group(1);
        double physical = Double.parseDouble(matcher.group(2));
        double internal = Double.parseDouble(matcher.group(3));
        double infection = Double.parseDouble(matcher.group(4));

        return new Illness(name, new LifeStats<>(physical, internal, infection));
    }

    private boolean readIllnessesFromFile(){

        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(illnessPath))){

            ArrayList<String> lines = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            for (String illnessString: lines) {
                illnesses.add(stringToIllness(illnessString));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public AutoGeneration(Setup simulationSetup){
        this.simulationSetup = simulationSetup;
        readNamesFromFile();
        readIllnessesFromFile();
    }

    private Illness generateIllness(){
        return illnesses.get(randomRange(illnesses.size()));
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
        return new LifeStats<>(randomRange(simulationSetup.getMinLifeStats().getPhysical(), simulationSetup.getMaxLifeStats().getPhysical()), randomRange(simulationSetup.getMinLifeStats().getInternal(), simulationSetup.getMaxLifeStats().getInternal()), randomRange(simulationSetup.getMinLifeStats().getInfection(), simulationSetup.getMaxLifeStats().getInfection()));
    }

    public Patient generatePatient() {

        ArrayList<Illness> illnesses = new ArrayList<>();

        int length = randomRange(simulationSetup.getMaxIllnessAmount());

        for(int i = 0; i < length; i++){
            Illness generatedIllness = generateIllness();
            if (!illnesses.contains(generatedIllness)){
                illnesses.add(generateIllness());
            }
            else {
                i--;
            }
        }

        return new Patient(generateName(), surnames.get(randomRange(surnames.size())), randomRange(10000000000L, 999999999999L) + "", generateDepartmentIndex(), generateLifeStats(), illnesses);
    }

    public Doctor generateDoctor() {
        return new Doctor(generateName(), surnames.get(randomRange(surnames.size())), randomRange(10000000000L, 999999999999L) + "", generateDepartmentIndex(), randomRange(simulationSetup.getMinDoctorSkill(), simulationSetup.getMaxDoctorSkill()), randomRange(1, simulationSetup.getNumberOfShifts()));
    }

}