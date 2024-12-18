package Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

import Person.*;
import static Utils.RandomRange.*;

public class AutoGeneration implements GenerationMethod{

    private ArrayList<String> maleNames;
    private ArrayList<String> femaleNames;
    private ArrayList<String> surnames;

    private Settings simulationSettings;

    static String[] filePaths = {"./generation_data/malenames.txt", "./generation_data/femalenames.txt", "./generation_data/surnames.txt"};

    private boolean readNamesFromFile(){

        String line;
        System.out.println(this.getClass());

        Field[] fields = this.getClass().getDeclaredFields();

        for(int i = 0; i < filePaths.length; i++) {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePaths[i]));
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

    public AutoGeneration(Settings simulationSettings){
        this.simulationSettings = simulationSettings;
        readNamesFromFile();
    }

    private Illness generateIllness(){
        return null;
    }

    private String generateName(){

        if(randomBoolean()) // na wypadek gdyby ktoś chciał dodać jeszcze nazwiska podzielone na męskie i żeńskie - nie znalazłem jakiejś fajnej listy
            return femaleNames.get(randomRange(maleNames.size()));
        return maleNames.get(randomRange(maleNames.size()));
    }

    public Patient generatePatient(){

        return null;
        //return new Patient(generateName(),surnames.get(randomRange(surnames.size())),randomRange(10000000000l,999999999999l) + "",4,5,6);
    }

    public Doctor generateDoctor(){

        return null;
        //return new Doctor(generateName(),surnames.get(randomRange(surnames.size())),randomRange(10000000000l,999999999999l) + "",4,5,1);
    }


}