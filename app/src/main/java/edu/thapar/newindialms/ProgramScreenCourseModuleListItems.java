package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class ProgramScreenCourseModuleListItems {

    private String coursemodulelist, specializationname, coursecode;

    public ProgramScreenCourseModuleListItems(String specializationname) {
        this.specializationname = specializationname;
    }

    public ProgramScreenCourseModuleListItems(String coursemodulelist, String coursecode, String specializationname) {
        this.coursemodulelist = coursemodulelist;
        this.coursecode = coursecode;
        this.specializationname = specializationname;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getCoursemodulelist() {
        return coursemodulelist;
    }


    public String getSpecializationname() {
        return specializationname;
    }

    public void setSpecializationname(String specializationname) {
        this.specializationname = specializationname;
    }
}
