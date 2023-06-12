package com.company;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<Doctor> doctors = new ArrayList<>();


    public static void main(String[] args) throws FileNotFoundException {
        Scanner D = new Scanner(System.in);
        WestminsterSkinConsultationManager doc = new WestminsterSkinConsultationManager();
        //initialise(doctor);
        try {
            doc.loadFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println();
            System.out.println("Welcome to the Skin Consultation Centre");
            System.out.println("------------Menu------------");
            System.out.println("A - Add a new doctor");
            System.out.println("D - Delete a doctor");
            System.out.println("P - Print the list of the doctors");
            System.out.println("S - Save in a file");
            System.out.println("G - View Doctor List");
            System.out.println("E - Exit ");
            String manu = D.nextLine();

            if (manu.equalsIgnoreCase("A")) {
                doc.addDoctor();
            } else if (manu.equalsIgnoreCase("D")) {
                doc.deleteDoctor();
            } else if (manu.equalsIgnoreCase("P")) {
                doc.printDoctorList();
            } else if (manu.equalsIgnoreCase("S")) {
                doc.saveFile();

            } else if (manu.equalsIgnoreCase("G")) {
                homeFrameGUI.main(args);

            } else if (manu.equalsIgnoreCase("E")){
                System.out.println("Thank You!");
                break;
            }else {
                System.out.println("Please Enter Valid Letter");
            }

        }
    }

    @Override
    public void addDoctor() {
        Scanner A = new Scanner(System.in);
        if (doctors.size()!= 10) {

            try {
                System.out.println("Enter Licence Number :");
                String licenceNum = A.next();
                System.out.println("Enter doctor's specialisation");
                String specialisation = A.next();
                System.out.println("Enter doctor name :");
                String name = A.next();
                System.out.println("Enter doctor SurName :");
                String surName = A.next();
                System.out.println("Enter doctor's Date of birth yyy-MM-dd :");
                LocalDate dateOfBirth = LocalDate.parse(A.next());
                System.out.println("Enter doctor's mobile number : ");
                int mobileNum = A.nextInt();
                doctors.add(new Doctor(name, surName, dateOfBirth, mobileNum, licenceNum, specialisation));

            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }else {
            System.out.println("Doctor Table Full");
        }
    }

    public void deleteDoctor() {
        Scanner R = new Scanner(System.in);
        System.out.println("Enter Doctor Licence Number :");
        String licenceNum = R.next();
        Doctor r1 = null;
        for (Doctor x : doctors) {
            if (x.getLicenceNum() == (licenceNum)) {
                r1 = x;
            }
        }
        doctors.remove(r1);
        System.out.println("Doctor with Licence Number" + " " + licenceNum + " " + "successfully Deleted...");
    }

    public void printDoctorList() {
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getSurName().compareTo(o2.getSurName());
            }
        });
        for (Doctor x : doctors) {
            System.out.println("Doctor name : " + x.getName() + " ");
            System.out.println("Doctor Surname : " + x.getSurName());
            System.out.println("Doctor Licence Number :" + x.getLicenceNum());
            System.out.println("Doctor Date of birth :" + x.getDate_Of_Birth());
            System.out.println("Doctor Mobile Number :" + x.getMobileNum());
            System.out.println("Doctor specialisation :" + x.getSpecialisation());
            System.out.println(" ");
        }
    }

    public void saveFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
            for (Doctor x : doctors) {
                writer.write(x.getName() + " , " + x.getSurName() + " , " + x.getDate_Of_Birth() +
                        " , " + x.getMobileNum() + " , " + x.getLicenceNum() + " , " + x.getSpecialisation());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n Data is successfully stored");
    }


    public void loadFile() throws FileNotFoundException, ParseException {
        File file = new File("test.txt");
        //using a temporary array to hold the data from the Doctor file
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

            doctors.add(new Doctor(filedata[0].trim(), filedata[1].trim(), dateD, Integer.parseInt(filedata[3].trim()),
                    filedata[4].trim(), filedata[5].trim()));
            System.out.println(Arrays.toString(filedata));
        }
        System.out.println("Data added Succesfully");
    }
}


   /* throws IOException {  //load all the infor to this method
            FileInputStream file = new FileInputStream("test.txt");
            ObjectInputStream obj = new ObjectInputStream(file);

            for (int i = 0; i < 10; i++) {
                try {
                    doctors = (ArrayList<Doctor>) obj.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

    }

}

//name, surName, dateOfBirth, mobileNum, licenceNum, specialisation

    //addDoctor(Integer.parseInt(filedata[0])-1,filedata[1],filedata[2],filedata[3],filedata[4]);

       /* */