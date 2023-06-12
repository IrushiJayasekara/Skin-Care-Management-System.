package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class doctorTableGUI extends JFrame {
    private JTable doctorTable;
    private List<Doctor> doctors = new ArrayList<>();
    public doctorTableGUI()  {
        initUI();
        setLocationRelativeTo(null);
        loadDoctors();
    }

    private void initUI() {
        setSize(700,500);
        setTitle("Westminster Consultation Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JLabel header = new JLabel("List Of All Doctors");
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));
        header.setBounds(250,5,600,25);
        add(header);

        JScrollPane jScrollPane = new JScrollPane();
        doctorTable = new javax.swing.JTable();
        doctorTable.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Licence Number",
                        "Name",
                        "SurName",
                        "Specialisation"
                }
        ));

        jScrollPane.setViewportView(doctorTable);
        jScrollPane.setBounds(25,40,640,300);
        add(jScrollPane);

        JButton buttonAssignDoctor = new JButton("Assign Doctor");
        buttonAssignDoctor.setBackground(Color.CYAN);
        buttonAssignDoctor.setBounds(35,360,200,30);
        add(buttonAssignDoctor);

        buttonAssignDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAssignDoctorActionPerformed(e);
            }
        });

        JButton button_sort = new JButton("Sort Doctors to Ascending Order");
        button_sort.setBackground(Color.CYAN);
        button_sort.setBounds(350,360,300,30);
        add(button_sort);
        button_sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSortActionPerfoemed(e);
            }
        });

    }

    private void buttonSortActionPerfoemed(ActionEvent e) {
        List<Doctor> sortedList = doctors.stream()
                .sorted(Comparator.comparing(Doctor::getSurName))
                .collect(Collectors.toList());

        DefaultTableModel dtm = (DefaultTableModel) doctorTable.getModel();
        dtm.setRowCount(0);
        for (Doctor doctor : sortedList) {
            Vector v = new Vector();
            v.add(doctor.getLicenceNum());
            v.add(doctor.getName());
            v.add(doctor.getSurName());
            v.add(doctor.getSpecialisation());
            dtm.addRow(v);
        }
    }

    private void buttonAssignDoctorActionPerformed(ActionEvent e) {
        int selectedRow = doctorTable.getSelectedRow();
        String dL = doctorTable.getValueAt(selectedRow, 0).toString();
        Doctor doctor = new Doctor();
        doctor.setLicenceNum(dL);
        String name = doctorTable.getValueAt(selectedRow,1).toString() + " " + doctorTable.getValueAt(selectedRow,2).toString();
        doctor.setName(name);
        availableDoctorGUI availableDoctorGUI = new availableDoctorGUI(doctor);
        availableDoctorGUI.setVisible(true);
        this.dispose();
    }

    private void loadDoctors() {
        try {
            loadFile();
            DefaultTableModel dtm = (DefaultTableModel) doctorTable.getModel();
            dtm.setRowCount(0);
            for (Doctor doctor : doctors) {
                Vector v = new Vector();
                v.add(doctor.getLicenceNum());
                v.add(doctor.getName());
                v.add(doctor.getSurName());
                v.add(doctor.getSpecialisation());
                dtm.addRow(v);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void loadFile() throws FileNotFoundException, ParseException {
        File file = new File("test.txt");
        String[] filedata;
        //using Scanner class to read data
        Scanner myscanner = new Scanner(file);
        while (myscanner.hasNextLine()) {
            filedata = myscanner.nextLine().split(",");
            LocalDate dateD = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (filedata[2]!=null){
                dateD = LocalDate.parse(filedata[2].trim(),formatter);
            }
            doctors.add(new Doctor(filedata[0].trim(), filedata[1].trim(), dateD, Integer.parseInt(filedata[3].trim()), filedata[4].trim(), filedata[5].trim()));
        }
    }

    public static void main(String[] args) {
        doctorTableGUI doctorTableGUI = new doctorTableGUI();
        doctorTableGUI.setVisible(true);
    }

}
