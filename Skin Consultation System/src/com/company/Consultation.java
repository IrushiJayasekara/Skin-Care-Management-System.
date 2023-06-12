package com.company;

import java.util.Date;

public class Consultation {
    private Doctor doctor;
    private String consultationDate;
    private String consultationTime;
    private double consultationCost;
    private String consultationNotes;
    private int consultationHour;

    public Patient getPatient(){return patient;}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private Patient patient;


    public Consultation(Doctor doctor, String consultationDate, String consultationTime) {
        this.doctor = doctor;
        this.consultationTime = consultationTime;
        this.consultationDate =consultationDate;
    }




    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getConsultationDate() {

        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(String consultationTime) {
        this.consultationTime = consultationTime;
    }

    public double getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(double consultationCost) {
        this.consultationCost = consultationCost;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public int getConsultationHour() {
        return consultationHour;
    }

    public void setConsultationHour(int consultationHour) {
        this.consultationHour = consultationHour;
    }
}
