package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends Person{
    private String licenceNum;
    private String specialisation;


    public Doctor(String name, String surName, LocalDate dateOfBirth, int mobileNum, String licenceNum, String specialisation) {
        super(name, surName, dateOfBirth, mobileNum);
        this.licenceNum = licenceNum;
        this.specialisation = specialisation;
    }


    public Doctor(String licenceNum) {
        this.licenceNum = licenceNum;
    }

    public Doctor() {

    }


    public void setLicenceNum(String licenceNum){
        this.licenceNum = licenceNum;
    }
    public String getLicenceNum(){
        return licenceNum;
    }
    public void setSpecialisation(String specialisation){
        this.specialisation = specialisation;
    }
    public String getSpecialisation(){
        return specialisation;
    }

    public String setLicenceNum() {

        return null;
    }
}
