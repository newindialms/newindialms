package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * Created by kamal shree on 10/10/2017.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {


    private List<Feedback> feedbackList;
    private Context context;


    public FeedbackAdapter(List<Feedback> feedbackList, Context context) {
        this.feedbackList = feedbackList;
        this.context = context;
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.showfeedback_list_layout, parent, false);
        return new FeedbackViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FeedbackViewHolder holder, final int position) {
        Feedback feedback = feedbackList.get(position);
        holder.textViewtitle.setText(feedback.getFeedback_title());
        holder.textViewquestion.setText(feedback.getFeedback_question());
        holder.textViewtype.setText("Type: " + feedback.getFeedback_type());


        holder.showfeedback_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateFeedback.class);
                int newPosition = holder.getAdapterPosition();

                String id = feedbackList.get(newPosition).getId();
                String feedback_title = feedbackList.get(newPosition).getFeedback_title();
                String feedback_question = feedbackList.get(newPosition).getFeedback_question();
                String feedback_type = feedbackList.get(newPosition).getFeedback_type();

                intent.putExtra("id", id);
                intent.putExtra("feedback_title", feedback_title);
                intent.putExtra("feedback_question", feedback_question);
                intent.putExtra("feedback_type", feedback_type);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView textViewtitle, textViewquestion, textViewtype;
        LinearLayout showfeedback_linearlayout;

        FeedbackViewHolder(View itemView) {
            super(itemView);

            textViewtitle = itemView.findViewById(R.id.showfeedback_title);
            textViewquestion = itemView.findViewById(R.id.showfeedback_question);
            textViewtype = itemView.findViewById(R.id.showfeedback_type);
            showfeedback_linearlayout = itemView.findViewById(R.id.showfeedback_linearlayout);
        }
    }
}