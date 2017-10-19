package edu.thapar.newindialms;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by kamalshree on 10/19/2017.
 */

public class SpinnerAdapter extends RecyclerView.Adapter<SpinnerAdapter.ViewHolder> {
    private List<SpinnerListItem> spinnerListItems;
    private Context context;

    public SpinnerAdapter(List<SpinnerListItem> spinnerListItems, Context context) {
        this.spinnerListItems = spinnerListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(parent.getContext())
              .inflate(R.layout.spinnerstudent_listitem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       SpinnerListItem spinnerStudentList=spinnerListItems.get(position);
        holder.firstname.setText(spinnerStudentList.getFirstname());
        holder.rollno.setText(spinnerStudentList.getRollno());
        holder.email.setText(spinnerStudentList.getEmail());
    }


    @Override
    public int getItemCount() {
        return spinnerListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView firstname,rollno,email;
        public ViewHolder(View itemView) {
            super(itemView);

            firstname=(TextView)itemView.findViewById(R.id.spinner_firstname);
            rollno =(TextView)itemView.findViewById(R.id.spinner_rollno);
            email=(TextView)itemView.findViewById(R.id.spinner_email);
        }

    }
}
