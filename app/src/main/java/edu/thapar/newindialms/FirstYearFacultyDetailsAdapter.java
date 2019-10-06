package edu.thapar.newindialms;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 2/20/2018.
 */

public class FirstYearFacultyDetailsAdapter extends RecyclerView.Adapter<FirstYearFacultyDetailsAdapter.FacultyDetailsScreenViewHolder> {

    private Context ctx;
    private List<FirstYearFacultyDetails> firstYearFacultyDetails;

    public FirstYearFacultyDetailsAdapter(Context ctx, List<FirstYearFacultyDetails> firstYearFacultyDetails) {
        this.ctx = ctx;
        this.firstYearFacultyDetails = firstYearFacultyDetails;

    }

    @Override
    public FacultyDetailsScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.listview_item_firstyearfacultydetails, null);
        return new FacultyDetailsScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FacultyDetailsScreenViewHolder facultyDetailsScreenViewHolder, int position) {
        FirstYearFacultyDetails facultyDetails = firstYearFacultyDetails.get(position);

        facultyDetailsScreenViewHolder.facultyname.setText(facultyDetails.getFacultyfirstname()+" " +facultyDetails.getFacultylastname());
        facultyDetailsScreenViewHolder.facultyphone.setText(facultyDetails.getFacultyfirstphone());
        facultyDetailsScreenViewHolder.facultyemail.setText(facultyDetails.getFacultyfirstemail());
        facultyDetailsScreenViewHolder.facultycoursecredits.setText(facultyDetails.getFacultyfirstcredits());
    }

    @Override
    public int getItemCount() {
        return firstYearFacultyDetails.size();
    }

    class FacultyDetailsScreenViewHolder extends RecyclerView.ViewHolder {

        TextView facultyname,facultylastname,facultyphone,facultyemail,facultycoursecredits;

        public FacultyDetailsScreenViewHolder(View itemView) {
            super(itemView);

            facultyname = (TextView) itemView.findViewById(R.id.faculty_firstyear_firstname);
            facultyphone = (TextView) itemView.findViewById(R.id.faculty_firstyear_phone);
            facultyemail = (TextView) itemView.findViewById(R.id.faculty_firstyear_email);
            facultycoursecredits = (TextView) itemView.findViewById(R.id.faculty_firstyear_credits);
        }


    }
}
