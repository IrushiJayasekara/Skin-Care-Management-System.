package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class homeFrameGUI extends JFrame {

        private Container bc;

        public homeFrameGUI () {
            Homeframe();
        }

        private void Homeframe(){
            setSize(700, 500);
            setTitle("Westminster Consultation Manager");

            setLayout(null);
            JLabel header = new JLabel("Westminster Skin Consultation Manager ");
            header.setFont(new Font("Times New Roman", Font.BOLD, 30));
            header.setBounds(50, 10, 550, 150);

            add(header);

            JLabel icon = new JLabel();
            icon.setIcon(new ImageIcon("picture.png.jpg"));
            Dimension size = icon.getPreferredSize();
            icon.setBounds(-35, 5, 750, 590);
            add(icon);

            JButton button_home = new JButton("View ALL Doctors ");
            button_home.setFont(new Font("Calluna", Font.BOLD, 20));
            button_home.setBounds(150, 320, 300, 50);
            button_home.setBackground(Color.GREEN);
            button_home.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {goDoctorTable(e);

                }
            });
            add(button_home);
        }

        public static void main (String[]args){
            homeFrameGUI frame = new homeFrameGUI();
            frame.setVisible(true);
        }
        private void goDoctorTable(ActionEvent e){
            doctorTableGUI doctorTableGUI = new doctorTableGUI();
            doctorTableGUI.setVisible(true);
            this.dispose();
        }
    }


