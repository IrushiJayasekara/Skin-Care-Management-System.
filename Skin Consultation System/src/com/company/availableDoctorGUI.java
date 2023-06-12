package com.company;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class availableDoctorGUI extends JFrame {

    ArrayList<Consultation> consultations;
    static ArrayList<Doctor> doctors;

    private static String[] parts;
    private JLabel labelName, label5;
    private JLabel labelId;
    private JTextField txt_date;
    private JTextField txt_time;
    private JDateChooser dateChooser;
    private JComboBox<String> start, hours;
    private Doctor doctor;

    public availableDoctorGUI() {
        initUI();
        setLocationRelativeTo(null);
        doctors = new ArrayList<>();
        readDoctorsFromFile("test.txt");
        consultations = new ArrayList<>();
        readConsultationFromFile("booking.txt");
    }

    public availableDoctorGUI(Doctor doctor) {
        this();
        this.doctor = doctor;
        labelId.setText(String.valueOf(doctor.getLicenceNum()));
        labelName.setText(doctor.getName());

    }

    private void initUI() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setTitle("Westminster Consultation Manager");
        BorderLayout borderLayout = new BorderLayout();

        JPanel panel1 = new JPanel();

        JLabel label1 = new JLabel("Doctor Licence Number :");
        label1.setBounds(5, 15, 200, 20);
        add(label1);

        JLabel label0 = new JLabel("Doctor Full Name :");
        label0.setBounds(5, 40, 200, 20);
        add(label0);

        labelId = new JLabel();
        labelId.setBounds(210, 15, 200, 20);
        add(labelId);

        labelName = new JLabel();
        labelName.setBounds(210, 40, 200, 20);
        add(labelName);

        JLabel label2 = new JLabel("Enter consultation Date and Time to Check Availability of the Doctor,");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        label2.setBounds(5, 80, 600, 20);
        add(label2);

        JLabel label4 = new JLabel("Consultation Date :");
        label4.setBounds(5, 120, 200, 20);
        add(label4);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(150, 120, 200, 20);
        add(dateChooser);

        JLabel label3 = new JLabel("Consultation Time :");
        label3.setBounds(5, 150, 200, 20);
        add(label3);
        start = new JComboBox<>();
        start.setModel(new DefaultComboBoxModel<>(new String[]{
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
        }));
        start.setBounds(150, 150, 100, 20);
        add(start);

        JLabel label6 = new JLabel("Consultation Duration:");
        label6.setBounds(5, 180, 200, 20);

        Integer[] numbers = {1, 2, 3};
        JComboBox<Integer> hours = new JComboBox<Integer>(numbers);
        hours.setBounds(150, 180, 100, 20);
        add(hours);
        add(label6);

        JButton checkDoctor = new JButton("Check Availability");
        checkDoctor.setBounds(10, 230, 150, 30);
        add(checkDoctor);
        checkDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                checkAvailability();
            }
        });

        label5 = new JLabel("");
        label5.setBounds(10, 270, 200, 20);
        add(label5);

        JButton bookDoctor = new JButton("Book Consultation");
        bookDoctor.setBackground(Color.cyan);
        bookDoctor.setBounds(170, 340, 170, 50);
        add(bookDoctor);
        bookDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookDoctor(e);
            }
        });

    }

    private void bookDoctor(ActionEvent e) {
        boolean availability = checkAvailability();
        if (availability) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            patientDetailsGUI patientDetailsGUI = new patientDetailsGUI(doctor,
                    sdf.format(dateChooser.getDate()), start.getSelectedItem().toString());
            patientDetailsGUI.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Soory,Selected Doctor is not available");
        }
    }

    public static void main(String[] args) {
        availableDoctorGUI availableDoctorGUI = new availableDoctorGUI();
        availableDoctorGUI.setVisible(true);
    }

    public ArrayList<Doctor> readDoctorsFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String licenceNum = parts[4];
                Doctor doctor = new Doctor(licenceNum.trim());
                doctors.add(doctor);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    private ArrayList<Consultation> readConsultationFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line != null && !line.trim().equals("")) {
                    String[] parts = line.split(",");
                    String doctorLicenceNum = parts[0];
                    String consultationDate = parts[1];
                    String consultationTime = parts[2];

                    Doctor doctor = findDoctorById(doctors, doctorLicenceNum.trim());
                    Consultation consultation = new Consultation(doctor, consultationDate, consultationTime);

                    consultations.add(consultation);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    public static Doctor findDoctorById(ArrayList<Doctor> doctors, String licenceNum) {
        for (Doctor doctor : doctors) {
            if (doctor.getLicenceNum().equals(licenceNum.trim())) {
                return doctor;
            }
        }
        return null;
    }

    public boolean checkAvailability() {
        String docLic = doctor.getLicenceNum().trim();

        Date consultationDate = dateChooser.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String consultStringDate = null;
        if (consultationDate != null) {
            consultStringDate = sdf.format(consultationDate).trim();
        }

        String consultationTime = start.getSelectedItem().toString().trim();

        boolean doctorAvailable = true;
        for (Consultation consultation : consultations) {
            if (consultation.getDoctor().getLicenceNum().trim().equals(docLic) &&
                    consultation.getConsultationDate().trim().equals(consultStringDate) &&
                    consultation.getConsultationTime().trim().equals(consultationTime)) {
                doctorAvailable = false;
                break;
            }
        }
        if (!doctorAvailable) {
            System.out.println("Doctor is not available");
            label5.setText("Your Doctor is Not Available");
        } else {
            label5.setText("Your Doctor is Available");

        }

        return doctorAvailable;
    }

}



