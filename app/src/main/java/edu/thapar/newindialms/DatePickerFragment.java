package edu.thapar.newindialms;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by kamalshree on 10/19/2017.
 */

public class DatePickerFragment extends DialogFragment {
    OnDateSetListener ondateSet;
    private int year, month, day;

    public DatePickerFragment() {}

    public void setCallBack(OnDateSetListener ondate) {
        ondateSet = ondate;
    }

    @SuppressLint("NewApi")
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
    }
}