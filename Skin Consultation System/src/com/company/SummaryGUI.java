package com.company;

import javax.swing.*;
import java.awt.*;

public class SummaryGUI extends JFrame {

    private SummaryGUI(){
            initUI();
            setLocationRelativeTo(null);
    }

    private void initUI(){
        setSize(600,400);
        BorderLayout borderLayout = new BorderLayout();

        JLabel jl0 = new JLabel("Your Booking Confirmed !!");
        jl0.setBounds(20,10,200,20);
        add(jl0);

        JLabel jlThank = new JLabel("Thank You For Joining Us..");
        jlThank.setBounds(20,10,200,650);
        add(jlThank);

        JPanel jp1 = new JPanel();
        add(jp1,borderLayout.CENTER);
        jp1.setBorder(BorderFactory.createEmptyBorder(50,30,50,20));

        GridLayout gridLayout = new GridLayout(10,2);
        gridLayout.setVgap(5);
        jp1.setLayout(gridLayout);

        JLabel sjl1 = new JLabel("Doctor Name :");
        JLabel sjl2 = new JLabel("txt");
        jp1.add(sjl1);
        jp1.add(sjl2);

        JLabel sjl3 = new JLabel("Doctor licence Number :");
        JLabel sjl4 = new JLabel("txt");
        jp1.add(sjl3);
        jp1.add(sjl4);

        JLabel sjl5 = new JLabel("Patient Name :");
        JLabel sjl6 = new JLabel("txt");
        jp1.add(sjl5);
        jp1.add(sjl6);

        JLabel jl7 = new JLabel("Patient Surname :");
        JLabel sjl8 = new JLabel("txt");
        jp1.add(jl7);
        jp1.add(sjl8);

        JLabel sjl9 = new JLabel("Patient Date Of Birth :");
        JLabel sjl10 = new JLabel("date");
        jp1.add(sjl9);
        jp1.add(sjl10);

        JLabel sjl11 = new JLabel("Patient Mobile Number :");
        JLabel sjl12 = new JLabel("num");
        jp1.add(sjl11);
        jp1.add(sjl12);

        JLabel sjl13 = new JLabel("Patient ID :");
        JLabel sjl14 = new JLabel("num");
        jp1.add(sjl13);
        jp1.add(sjl14);

        JLabel sjl15 = new JLabel("Consultation Date :");
        JLabel sjl16 = new JLabel("date");
        jp1.add(sjl15);
        jp1.add(sjl16);

        JLabel sjl17 = new JLabel("Consultation Time :");
        JLabel sjl18 = new JLabel("time");
        jp1.add(sjl17);
        jp1.add(sjl18);

        JLabel sjl19 = new JLabel("Consultation Cost :");
        JLabel sjl20 = new JLabel("cost");
        jp1.add(sjl19);
        jp1.add(sjl20);

        setVisible(true);

    }

    public static void main(String[] args) {
        SummaryGUI SummaryGUI = new SummaryGUI();
        SummaryGUI.setVisible(true);
    }
}
