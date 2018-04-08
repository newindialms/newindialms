package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {

       SpinnerListItem spinnerStudentList=spinnerListItems.get(position);
        holder.firstname.setText(spinnerStudentList.getLastname()+" "+spinnerStudentList.getFirstname());
        holder.rollno.setText(spinnerStudentList.getRollno());
        holder.email.setText(spinnerStudentList.getEmail());
        holder.mailimage.setImageResource(R.drawable.envelope);

        holder.mailimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),EmailActivity.class);
                int newPosition = holder.getAdapterPosition();


                String email_address=spinnerListItems.get(newPosition).getEmail();
                intent.putExtra("email_address", email_address);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return spinnerListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView firstname,rollno,email;
        public ImageView mailimage;
        public ViewHolder(View itemView) {
            super(itemView);

            firstname=(TextView)itemView.findViewById(R.id.spinner_firstname);
            rollno =(TextView)itemView.findViewById(R.id.spinner_rollno);
            email=(TextView)itemView.findViewById(R.id.spinner_email);
            mailimage=(ImageView) itemView.findViewById(R.id.mail_image);
        }

    }
}
