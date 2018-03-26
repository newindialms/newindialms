package edu.thapar.newindialms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 2/20/2018.
 */

public class FacultySelectFeedbackScreenAdapter extends RecyclerView.Adapter<FacultySelectFeedbackScreenAdapter.FacultySelectFeedbackScreenViewHolder> {

    private Context ctx;
    private List<FacultySelectFeedbackScreenDetails> facultySelectFeedbackScreenDetails;
    public FacultySelectFeedbackScreenAdapter(Context ctx, List<FacultySelectFeedbackScreenDetails> facultySelectFeedbackScreenDetails) {
        this.ctx = ctx;
        this.facultySelectFeedbackScreenDetails = facultySelectFeedbackScreenDetails;
    }

    @Override
    public FacultySelectFeedbackScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.cardlayout_faculty_select_feedbackscreen, null);
        return new FacultySelectFeedbackScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FacultySelectFeedbackScreenViewHolder holder, int position) {

        final FacultySelectFeedbackScreenDetails notification = facultySelectFeedbackScreenDetails.get(position);

        holder.feedbackTitle.setText(notification.getFeedbackQuestions());
        holder.feedbackMsg.setText(notification.getFeedbackDescription());
        holder.feedbackType.setText(notification.getFeedbackType());
        holder.selectedFeedback.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    notification.setStatus(true);
                }
                else{
                    notification.setStatus(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return facultySelectFeedbackScreenDetails.size();
    }

    class FacultySelectFeedbackScreenViewHolder extends RecyclerView.ViewHolder {

        TextView feedbackTitle, feedbackMsg, feedbackType;
        CheckBox selectedFeedback;

        public FacultySelectFeedbackScreenViewHolder(View itemView) {
            super(itemView);

            feedbackTitle = (TextView) itemView.findViewById(R.id.faculty_select_feedbackscreen_questions_title);
            feedbackMsg = (TextView) itemView.findViewById(R.id.faculty_select_feedbackscreen_questions_description);
            feedbackType = (TextView) itemView.findViewById(R.id.faculty_select_feedbackscreen_selectQ);
            selectedFeedback = (CheckBox) itemView.findViewById(R.id.faculty_select_feedbackscreen_checkbox);
        }

    }

    ArrayList<FacultySelectFeedbackScreenDetails> getFeedbackDetails() {
        ArrayList<FacultySelectFeedbackScreenDetails> feedback = new ArrayList<>();
        for (FacultySelectFeedbackScreenDetails feedbackvalue : facultySelectFeedbackScreenDetails) {
            if (feedbackvalue.isStatus())
                feedback.add(feedbackvalue);
        }
        return feedback;
    }
}
