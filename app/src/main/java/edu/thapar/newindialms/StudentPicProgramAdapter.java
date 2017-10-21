package edu.thapar.newindialms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/20/2017.
 */

public class StudentPicProgramAdapter extends RecyclerView.Adapter<StudentPicProgramAdapter.ViewHolder> {

    private List<StudentPicListItems> listItemsProgramLists;
    private Context context;

    public StudentPicProgramAdapter(List<StudentPicListItems> listItemsProgramLists, Context context) {
        this.listItemsProgramLists = listItemsProgramLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.studentpic_program_listitem,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudentPicListItems listItemProgramList=listItemsProgramLists.get(position);
        holder.programlist.setText(listItemProgramList.getProgramName());

    }

    @Override
    public int getItemCount() {
        return listItemsProgramLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView programlist;

        public ViewHolder(View itemView) {
            super(itemView);
            programlist=(TextView) itemView.findViewById(R.id.studentpic_programlist);
        }
    }
}
