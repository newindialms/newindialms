package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenListItems {

    private String yearofjoining;
    private String yearofcourses;
    private String yearofspecialization;
    private String yearofcorecourses;

    private String programname;

    public ProgramScreenListItems(String programname) {
        this.programname = programname;
    }

    public ProgramScreenListItems(String yearofjoining,  String yearofspecialization, String yearofcourses,String yearofcorecourses,String programname) {
        this.yearofjoining = yearofjoining;
        this.yearofcourses = yearofcourses;
        this.yearofspecialization = yearofspecialization;
        this.yearofcorecourses=yearofcorecourses;
        this.programname = programname;
    }


    public String getYearofjoining() {
        return yearofjoining;
    }


    public String getYearofcourses() {
        return yearofcourses;
    }


    public String getYearofspecialization() {
        return yearofspecialization;
    }


    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }
}
