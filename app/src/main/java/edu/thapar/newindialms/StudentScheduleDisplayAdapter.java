package edu.thapar.newindialms;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class StudentScheduleDisplayAdapter extends RecyclerView.Adapter<StudentScheduleDisplayAdapter.HeroViewHolder> {

    private Context context;
    private List<StudentScheduleDisplayListItems> heroList;

    private static int currentPosition = 0;

    public StudentScheduleDisplayAdapter(List<StudentScheduleDisplayListItems> heroList, Context context) {
        this.heroList = heroList;
        this.context = context;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_schedule_display_listitems, parent, false);
        return new HeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HeroViewHolder holder, final int position) {
        StudentScheduleDisplayListItems hero = heroList.get(position);
        holder.student_scheduledisplay_program.setText(hero.getProgram());
        holder.student_scheduledisplay_course.setText(hero.getCourse());
        holder.student_scheduledisplay_starttime.setText(hero.getStarttime());
        holder.student_scheduledisplay_starttimes.setText(hero.getStarttime());
        holder.student_scheduledisplay_endtime.setText(hero.getEndtime());
        holder.student_scheduledisplay_faculty.setText(hero.getFaculty());
        holder.imageView.setImageResource(R.drawable.schedule_arrow_down);
        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim);
            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.linearLayout.startAnimation(slideDown);

        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView student_scheduledisplay_program, student_scheduledisplay_course, student_scheduledisplay_starttime, student_scheduledisplay_endtime, student_scheduledisplay_starttimes, student_scheduledisplay_faculty;
        LinearLayout linearLayout;
        ImageView imageView;

        HeroViewHolder(View itemView) {
            super(itemView);

            student_scheduledisplay_program = (TextView) itemView.findViewById(R.id.student_scheduledisplay_program);
            student_scheduledisplay_course = (TextView) itemView.findViewById(R.id.student_scheduledisplay_course);
            student_scheduledisplay_starttime = (TextView) itemView.findViewById(R.id.student_scheduledisplay_starttime);
            student_scheduledisplay_starttimes = (TextView) itemView.findViewById(R.id.student_scheduledisplay_starttimes);
            student_scheduledisplay_endtime = (TextView) itemView.findViewById(R.id.student_scheduledisplay_endtime);
            student_scheduledisplay_faculty = (TextView) itemView.findViewById(R.id.student_scheduledisplay_faculty);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
