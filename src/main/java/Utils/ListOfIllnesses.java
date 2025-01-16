package Utils;

import Person.Illness;
import Person.LifeStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListOfIllnesses {
    static String illnessPath = "./generation_data/illnesses.txt";
    static ArrayList<Illness> illnesses = new ArrayList<>();
    public static boolean readIllnessesFromFile() {

        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(illnessPath))) {

            ArrayList<String> lines = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            for (String illnessString : lines) {
                illnesses.add(stringToIllness(illnessString));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    private static Illness stringToIllness(String illnessString) {

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

    public static ArrayList<Illness> getIllnesses() {
        return illnesses;
    }

    public static ObservableList<Illness> getIllnessesO() {
        return FXCollections.observableArrayList(getIllnesses());
    }
}
