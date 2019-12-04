package integration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaSwingGUI{

    public static void main(String args[]){
        JFrame menu = new JFrame("Menu");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(800,500);
        FlowLayout flowLayout = new FlowLayout();
        menu.setLayout(flowLayout);
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        menu.getContentPane().add(button1);
        menu.getContentPane().add(button2);
        menu.setVisible(true);

        JFrame gradeInputMenu = new JFrame("grades");
        gradeInputMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeInputMenu.setSize(800,500);
        gradeInputMenu.setVisible(false);

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //do something
            }
        });

        while(true) {
            if (button2.isSelected()) {
                menu.setVisible(false);
                gradeInputMenu.setVisible(true);
            }
        }
    }
}