package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenListItems {

    private String yearofjoining;
    private String yearofcourses;
    private String yearofspecialization;
    private String programname;

    public ProgramScreenListItems(String programname) {
        this.programname = programname;
    }

    public ProgramScreenListItems(String yearofjoining, String yearofcourses, String yearofspecialization, String programname) {
        this.yearofjoining = yearofjoining;
        this.yearofcourses = yearofcourses;
        this.yearofspecialization = yearofspecialization;
        this.programname = programname;
    }

    public String getYearofjoining() {
        return yearofjoining;
    }

    public void setYearofjoining(String yearofjoining) {
        this.yearofjoining = yearofjoining;
    }

    public String getYearofcourses() {
        return yearofcourses;
    }

    public void setYearofcourses(String yearofcourses) {
        this.yearofcourses = yearofcourses;
    }

    public String getYearofspecialization() {
        return yearofspecialization;
    }

    public void setYearofspecialization(String yearofspecialization) {
        this.yearofspecialization = yearofspecialization;
    }

    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }
}
