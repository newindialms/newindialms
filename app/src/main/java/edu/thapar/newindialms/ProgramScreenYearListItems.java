package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import static edu.thapar.newindialms.R.id.Studentpic_program_title;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenYearListItems{
    private String Yeardetails,Programname;

    public ProgramScreenYearListItems(String yeardetails,String programname) {
        this.Yeardetails = yeardetails;
        this.Programname=programname;
    }

    public String getYeardetails() {
        return Yeardetails;
    }

    public void setYeardetails(String yeardetails) {
        Yeardetails = yeardetails;
    }

    public String getProgramname() {
        return Programname;
    }

    public void setProgramname(String programname) {
        Programname = programname;
    }
}
