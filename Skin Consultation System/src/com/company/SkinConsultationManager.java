package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface SkinConsultationManager {
    void addDoctor();
    void printDoctorList();
    void deleteDoctor();
    void saveFile();
    void loadFile() throws IOException, ParseException;
}
