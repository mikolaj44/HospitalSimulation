package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GUI{

    private int width = 800;
    private int height = 600;
    private boolean quit = false;

    private JFrame frame;
    private JPanel panel;

    public GUI(int width, int height){
        this.width = width;
        this.height = height;
    }

    public GUI(){
        this.width = 800;
        this.height = 600;
    }

    private void setup(){

        frame = new JFrame();
        panel = new DrawingPanel();

        JButton button = new JButton("exit");
        button.addActionListener(e -> quit = true);

        panel.add(button);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.getContentPane().add(panel);
        frame.setSize(width,height);
        frame.setVisible(true);
    }

    private void draw(){

        panel.repaint();
    }

    public void start(){

        setup();

        while(!quit){
            draw();
        }

        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    class DrawingPanel extends JPanel {

        public void paintComponent(Graphics g){

            g.setColor(new Color((int)(255 * Math.random()), (int)(255 * Math.random()), (int)(255 * Math.random())));
            g.drawLine(0,0,(int)(width * Math.random()),(int)(height * Math.random()));
            //g.clearRect(0,0,this.getWidth(),this.getHeight());
        }
    }
}
