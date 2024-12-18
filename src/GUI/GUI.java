package GUI;

import GUI.DisplayMethods.DisplayMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {
    private DisplayMethod displayMethod;

    public GUI(DisplayMethod displayMethod) {
        this.displayMethod = displayMethod;
    }

    private void setup(){
        displayMethod.setup();
    }

    public void start(){
        setup();
    }
}
