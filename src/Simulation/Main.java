package Simulation;

import GUI.DisplayMethods.Method1;
import GUI.GUI;
import Person.LifeStats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args){

        GUI gui = new GUI(new Method1());
        gui.start();
    }
}