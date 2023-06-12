/*package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Example {
    public Doctor checkDoctorAvailability(String licenceId, Date appointmentdate){

        Doctor randomDoctor;

        int licenceNo = Integer.parseInt(licenceId);


        int i=0;

        /**Checks all doctors to see if the selected doctor is available, If doctor is available
         that respective doctor will be called otherwise getRandomDoctor() will be called which
         in turn will return a random doctor**/


        /*for (Doctor d : WestminsterSkinConsultationManager.doctorsList){
            if (d.getlicenseNo() == licenceNo){
                if(d.dateTimeList.isEmpty()){
                    d.dateTimeList.add(appointmentdate);
                    JOptionPane.showMessageDialog(detailsPanel,
                            "The doctor has been booked", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
                    return d;
                }
                for (Date ld : d.dateTimeList){

                    if (ld.compareTo(appointmentdate) ==0){  //Localdates compareTo method returns 0 if dates are equal
                        randomDoctor = getRandomDoctor(appointmentdate);
                        System.out.println(randomDoctor);
                        if (randomDoctor==null){
                            JOptionPane.showMessageDialog(detailsPanel,
                                    "No doctors available", "Alert",JOptionPane.ERROR_MESSAGE);
                            return null;
                        }

                        JOptionPane.showMessageDialog(detailsPanel,
                                "The doctor chosen is not available at that date, another doctor has been assigned", "Confirmation",JOptionPane.INFORMATION_MESSAGE);


                        randomDoctor.dateTimeList.add(appointmentdate);
                        return randomDoctor;

                    }

                }
                JOptionPane.showMessageDialog(detailsPanel,
                        "The doctor has been booked", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
                d.dateTimeList.add(appointmentdate);
                return d;
            }else{
                i=1;
            }

        }
        if(i==1){
            JOptionPane.showMessageDialog(detailsPanel,
                    "Invalid License No", "Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;


    }

    /**Returns a random doctor if user's choice oo doctor is not available**/

  /*  public Doctor getRandomDoctor(Date appointmentdate){

        Random random = new Random();

        Doctor doc;
        ArrayList<Doctor> availableDocList = new ArrayList<>();

        for(Doctor d : WestminsterSkinConsultationManager.doctorsList){
            doc= checkDoctorEligibility(d,appointmentdate);
            if(doc==null){
                continue;
            }else{
                availableDocList.add(doc);
            }
        }

        if (availableDocList.isEmpty()){
            return null;
        }

        int randomNum = random.nextInt(availableDocList.size());
        return availableDocList.get(randomNum);



    }

    /**Checks whether the doctor is available**/

    /*public Doctor checkDoctorEligibility(Doctor doc, Date appDate){
        if(doc.dateTimeList.isEmpty()){
            return doc;
        }
        for (Date date : doc.dateTimeList){
            if (date.compareTo(appDate)==0){
                return null;
            }
        }
        return doc;

    }


}*/


