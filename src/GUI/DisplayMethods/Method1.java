package GUI.DisplayMethods;

import Person.*;
import Simulation.*;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Method1 implements DisplayMethod{

//    static JButton departmentButtonGenerator(){
//
//    };

    public void setup(){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

//        ================
//        Docelowo DisplayMethod będzie pobierał listę departments i z niej tworzył panel
//        Na ten moment wszystko statycznie
//        ================  
        ArrayList<Department> departments = new ArrayList<Department>();
        departments.add(new Department("Onkologia",0,10,new LifeStats<Double>(1.0,1.0,1.0)));
        departments.add(new Department("Ortpedia",0,10,new LifeStats<Double>(1.0,1.0,1.0)));
        departments.add(new Department("Kardiologia",0,10,new LifeStats<Double>(1.0,1.0,1.0)));
        departments.add(new Department("Zakaźny",0,10,new LifeStats<Double>(1.0,1.0,1.0)));
        departments.add(new Department("OIOM",0,10,new LifeStats<Double>(1.0,1.0,1.0)));


//        ================
//        Departments panel
//        ================
        JPanel departmentsPanel = new JPanel();
        departmentsPanel.setSize(400,600);
        departmentsPanel.setBackground(Color.lightGray);
        departmentsPanel.setLayout(new BorderLayout());

        JPanel departmentsPanel_labelPanel = new JPanel();
        departmentsPanel_labelPanel.setSize(400,100);
        departmentsPanel_labelPanel.setBackground(Color.BLACK);
        JLabel departmentsPanel_label = new JLabel("Oddziały");
        departmentsPanel_label.setFont(new Font("Arial",Font.BOLD,20));
        departmentsPanel_label.setForeground(Color.white);
        departmentsPanel_label.setSize(400,50);
        departmentsPanel.add(departmentsPanel_label);
        departmentsPanel_labelPanel.add(departmentsPanel_label);

        JPanel departmentsPanel_buttonsPanel = new JPanel();
        departmentsPanel_buttonsPanel.setSize(400,500);
        departmentsPanel_buttonsPanel.setLayout(new GridLayout(departments.size()/2+(departments.size()%2),2));

        for (Department department : departments) {
            departmentsPanel_buttonsPanel.add(new JButton(department.getName()));
        }

        departmentsPanel.add(departmentsPanel_labelPanel, BorderLayout.NORTH);
        departmentsPanel.add(departmentsPanel_buttonsPanel, BorderLayout.CENTER);

//        ================
//        Informations panel
//        ================
        JPanel informationsPanel = new JPanel();
        informationsPanel.setSize(400,600);
        informationsPanel.setBackground(Color.white);

        frame.add(departmentsPanel);
        frame.add(informationsPanel);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setTitle("Hospital");
    }
}
