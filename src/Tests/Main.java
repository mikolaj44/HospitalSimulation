package Tests;

import GUI.DisplayMethods.Method1;
import GUI.GUI;

public class Main {

    public static void main(String[] args) {

        GUI gui = new GUI(new Method1());
        gui.start();

//        ArrayList<Department> departments = new ArrayList<>();
//        departments.add(new Department("Onkologia", 0, 10, null));
//        departments.add(new Department("Ortopedia", 0, 10, null));
//        Setup setup = new Setup(1, true, true, departments);
//        GenerationMethod method = new AutoGeneration(setup);
//        System.out.println(method.generatePatient());
//        System.out.println(method.generateDoctor());
    }
}