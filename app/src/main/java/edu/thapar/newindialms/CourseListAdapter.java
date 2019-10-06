package edu.thapar.newindialms;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 9/27/2017.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {

    private List<ListItemCourseList> listItemsCourseLists;

    public CourseListAdapter(List<ListItemCourseList> listItemsCourseLists, Context context) {
        this.listItemsCourseLists = listItemsCourseLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_program_manager_courselist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItemCourseList listItemCourseList = listItemsCourseLists.get(position);
        holder.textViewTitle.setText(listItemCourseList.getCourseListTitle());
        holder.textViewFaculty.setText(listItemCourseList.getCourseListFaculty());
        holder.textViewCode.setText(listItemCourseList.getCourseListCode());
    }

    @Override
    public int getItemCount() {
        return listItemsCourseLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewFaculty;
        public TextView textViewCode;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.cardviewCourselistTitle);
            textViewFaculty = (TextView) itemView.findViewById(R.id.cardviewCourselistFaculty);
            textViewCode = (TextView) itemView.findViewById(R.id.cardviewCourselistCode);
        }
    }
}
