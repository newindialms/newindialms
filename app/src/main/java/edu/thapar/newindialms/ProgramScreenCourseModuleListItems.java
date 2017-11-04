package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class ProgramScreenCourseModuleListItems {

    private String coursemodulelist,specializationname;

    public ProgramScreenCourseModuleListItems(String specializationname) {
        this.specializationname = specializationname;
    }

    public ProgramScreenCourseModuleListItems(String coursemodulelist, String specializationname) {
        this.coursemodulelist = coursemodulelist;
        this.specializationname=specializationname;
    }

    public String getCoursemodulelist() {
        return coursemodulelist;
    }

    public void setCoursemodulelist(String coursemodulelist) {
        this.coursemodulelist = coursemodulelist;
    }

    public String getSpecializationname() {
        return specializationname;
    }

    public void setSpecializationname(String specializationname) {
        this.specializationname = specializationname;
    }
}
