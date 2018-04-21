package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/25/2017.
 */

public class ProgramScreenSpecializationModuleListItems {

    private String coursename, specializationname;

    public ProgramScreenSpecializationModuleListItems(String specializationname) {
        this.specializationname = specializationname;
    }

    public ProgramScreenSpecializationModuleListItems(String coursename, String specializationname) {
        this.coursename = coursename;
        this.specializationname = specializationname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getSpecializationname() {
        return specializationname;
    }

    public void setSpecializationname(String specializationname) {
        this.specializationname = specializationname;
    }
}

