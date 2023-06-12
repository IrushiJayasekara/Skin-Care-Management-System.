package com.company;
import java.time.LocalDate;
import java.util.Date;

public class Person {

    private String name;
    private String surName;
    private LocalDate dateOfBirth;
    private int mobileNum;

    public Person(){

    }
    public Person(String name, String surName, LocalDate dateOfBirth, int mobileNum) {
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNum = mobileNum;
    }

    public void setName(String name){

        this.name = name;
    }
    public String getName(){

        return name;
    }
    public void setSurName(String surName){

        this.surName = surName;
    }
    public String getSurName(){

        return surName;
    }
    public void setDate_Of_Birth(LocalDate dateOfBirth){

        this.dateOfBirth= dateOfBirth;
    }
    public LocalDate getDate_Of_Birth(){

        return dateOfBirth;
    }
    public void setMobileNum(int mobileNum){

        this.mobileNum = mobileNum;
    }
    public int getMobileNum(){

        return mobileNum;
    }

}
