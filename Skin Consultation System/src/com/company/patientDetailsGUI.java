package com.company;

import com.toedter.calendar.JDateChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class patientDetailsGUI extends JFrame {

    private JTextField txt_Name;
    private JTextField txt_Surname;
    //private JTextField txt_dateOfbirth;
    private JTextField txt_mobileNum;
    private JTextField txt_patientId;
    private JTextArea txt_notes;
    private JTextField txt_Lnum;
    private JTextField txt_Dname;
    private JButton button;
    private JDateChooser dateChooser;
    private JComboBox<String> hours;
    private String consultDate;
    private String time;

    JButton imageUploadButton = new JButton();
    JLabel imgLabel = new JLabel();
    JButton CbookingButton = new JButton();

    ArrayList<Patient> patientList = new ArrayList<>();
    //ArrayList<Consultation> consultations = new ArrayList<>();
   // ArrayList<Integer> cost = new ArrayList<>();


    public patientDetailsGUI(){
        initUI();
        setLocationRelativeTo(null);
    }

    public patientDetailsGUI(Doctor doctor,String consulDate,String time) {
        this();
        txt_Lnum.setText(" " + doctor.getLicenceNum());
        txt_Dname.setText(doctor.getName());
        this.consultDate = consulDate;
        this.time = time;
    }

    private void initUI(){
        setSize(700,500);
        setTitle("Westminster Consultation Manager");
        BorderLayout borderLayout = new BorderLayout();

        JLabel jl0 = new JLabel("Enter Patient Details");
        jl0.setBounds(250,10,400,20);
        jl0.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(jl0);

        JPanel jp1 = new JPanel();
        add(jp1,borderLayout.CENTER);
        jp1.setBorder(BorderFactory.createEmptyBorder(50,20,20,20));

        GridLayout gridLayout = new GridLayout(11,2);
        gridLayout.setVgap(10);
        jp1.setLayout(gridLayout);

        JLabel jl01 = new JLabel("Doctor Licence Number");
        txt_Lnum = new JTextField(20);
        jp1.add(jl01);
        jp1.add(txt_Lnum);

        JLabel jl02 = new JLabel("Doctor Full Name");
        txt_Dname = new JTextField(20);
        jp1.add(jl02);
        jp1.add(txt_Dname);

        JLabel jl1 = new JLabel("Patient Name :");
        txt_Name = new JTextField(20);
        jp1.add(jl1);
        jp1.add(txt_Name);

        JLabel jl2 = new JLabel("Patient Surame :");
        txt_Surname = new JTextField(20);
        jp1.add(jl2);
        jp1.add(txt_Surname);

        JLabel jl3 = new JLabel("Patient Date of Birth :");
        dateChooser = new JDateChooser();
        dateChooser.setBounds(150,90,200,20);
        jp1.add(jl3);
        jp1.add(dateChooser);

        JLabel jl4 = new JLabel("Patient Mobile Number :");
        txt_mobileNum = new JTextField(20);
        jp1.add(jl4);
        jp1.add(txt_mobileNum);

        JLabel jl5 = new JLabel("Patient ID :");
        txt_patientId = new JTextField(20);
        jp1.add(jl5);
        jp1.add(txt_patientId);

        JLabel jl19 = new JLabel("Enter Consultation Duration(hours)");

        Integer[] numbers = {1, 2, 3};
        JComboBox<Integer> hours = new JComboBox<Integer>(numbers);
        hours.setBounds(260,120,100,20);
        jp1.add(jl19);
        jp1.add(hours);

        JLabel jl6 = new JLabel("Additional Notes :");
        txt_notes = new JTextArea(10,20);
        jp1.add(jl6);
        jp1.add(txt_notes);

        JLabel jl8 = new JLabel();
        imageUploadButton.setText("Upload images of the skin");
        imageUploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File file = chooser.getSelectedFile();
                try {
                    ImageIcon icon=new ImageIcon(ImageIO.read(new File(file.getAbsolutePath())));
                    imgLabel.setIcon(icon);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        jp1.add(jl8);
        jp1.add(imageUploadButton);

        JLabel jl7 = new JLabel();
        CbookingButton.setText("Confirm Your Booking");
        CbookingButton.setFont(new Font("Calluna", Font.BOLD, 15));
        CbookingButton.setBackground(Color.green);
        CbookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String licenceNum = txt_Lnum.getText();
                String dname = txt_Dname.getText();
                String name = txt_Name.getText();
                String surName = txt_Surname.getText();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = dateChooser.getDate();
                LocalDate dateD = null;

                String mobileNotxt = txt_mobileNum.getText();
                int mobileNum = parseInt(mobileNotxt);

                String patientIdtxt = txt_patientId.getText();
                int patientId = parseInt(patientIdtxt);

                int consultationHours = (int) hours.getSelectedItem();


                patientList.add(new Patient(name, surName, dateD, mobileNum, consultationHours,patientId));

                Set<String> patientIds = new HashSet<>();
                boolean isfirstTime = true;
                try(BufferedReader reader = new BufferedReader(new FileReader("patient.txt"))){
                    String line;
                    while((line = reader.readLine()) != null){

                        String[] fields = line.split(",");
                        String setPatientId = fields[4];


                        patientIds.add(setPatientId.trim());
                        if ((txt_patientId.getText().trim().equals(setPatientId.trim()))){
                            isfirstTime = false;
                            break;
                        }

                    }
                }  catch (IOException ex) {
                    ex.printStackTrace();
                }

                    int consultationcost = 0;
                if(isfirstTime){
                    consultationcost = consultationHours * 15;
                }else {
                    consultationcost = consultationHours * 25;
                }
                int totalcost = consultationcost;

                JOptionPane.showMessageDialog(rootPane, "Doctor Licence Number : " + licenceNum +
                        "\nDoctor Full Name: "+ dname +
                        "\nPatient Name : " + name +
                        "\nPatient Surname : " + surName +
                        "\nPatient Date Of Birth: " +sdf.format(dateOfBirth) +
                        "\nPatient Mobile Number: " + mobileNum +
                        "\nPatient ID: " + patientId +
                        "\nConsultation Cost: " + totalcost + " £"+
                        "\n\n Booking is completed Thank You!");

                saveConsultation();
                patientSavefile();
                clearForm();

            }
        });
        jp1.add(jl7);
        jp1.add(CbookingButton);
        setVisible(true);


    }
    public static void main(String[] args) {
        patientDetailsGUI patientDetailsGUI = new patientDetailsGUI();
        patientDetailsGUI.setVisible(true);
    }
    public void clearForm(){
        txt_Name.setText("");
        txt_Surname.setText("");
        txt_patientId.setText("");
        txt_mobileNum.setText("");
        dateChooser.setDate(null);
        txt_notes.setText("");

    }
    public void patientSavefile() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("patient.txt",true));
            for (Patient x : patientList) {
                writer.write(x.getName() + " , " + x.getSurName() + " , " + x.getDate_Of_Birth() +
                        " , " + x.getMobileNum() + " , " + x.getPatientId() + " , " + x.getConsultationHours());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       System.out.println("data saved");
    }

    public void saveConsultation() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("booking.txt", true));

            String line = "";
            line += txt_Lnum.getText()+ ",";
            line += consultDate+ ",";
            line += time + ",";
//                line += hours.getSelectedItem() + ",";
            line += txt_patientId.getText();
            writer.write(line);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n Data is successfully stored");

    }


}
