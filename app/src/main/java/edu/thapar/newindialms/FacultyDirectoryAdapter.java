package edu.thapar.newindialms;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 12/31/2018.
 */

public class FacultyDirectoryAdapter extends RecyclerView.Adapter<FacultyDirectoryAdapter.FacultyDirectoryViewHolder> {

    private Context ctx;
    private List<FacultyDirectoryDetails> facultyDirectoryDetails;

    public FacultyDirectoryAdapter(Context ctx, List<FacultyDirectoryDetails> facultyDirectoryDetails) {
        this.ctx = ctx;
        this.facultyDirectoryDetails = facultyDirectoryDetails;
    }

    @Override
    public FacultyDirectoryAdapter.FacultyDirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.cardlayout_faculty_directoryscreen, null);
        return new FacultyDirectoryAdapter.FacultyDirectoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FacultyDirectoryAdapter.FacultyDirectoryViewHolder holder, int position) {

        FacultyDirectoryDetails directory = facultyDirectoryDetails.get(position);

        holder.facultyname.setText(directory.getFacultyfirstname()+" "+directory.getFacultylastname());
        holder.facultyphone.setText(directory.getFacultyphone());
        holder.facultyemail.setText(directory.getFacultyemail());
        holder.facultyspecialization.setText(directory.getFacultyspecialization());


    }

    @Override
    public int getItemCount() {
        return facultyDirectoryDetails.size();
    }

    class FacultyDirectoryViewHolder extends RecyclerView.ViewHolder {

        TextView facultyname, facultyemail,facultyphone, facultyspecialization;

        public FacultyDirectoryViewHolder(View itemView) {
            super(itemView);

            facultyname = (TextView) itemView.findViewById(R.id.faculty_directoryscreen_facultyname);
            facultyphone = (TextView) itemView.findViewById(R.id.faculty_directoryscreen_facultyphone);
            facultyemail = (TextView) itemView.findViewById(R.id.faculty_directoryscreen_facultyemail);
            facultyspecialization = (TextView) itemView.findViewById(R.id.faculty_directoryscreen_facultyspecialization);
        }


    }
}
