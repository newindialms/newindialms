package edu.thapar.newindialms;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class SubmitFeedBackScreenAdapter extends ArrayAdapter<SubmitFeedbackScreenListItems> {

    //the list values in the List of type hero
    List<SubmitFeedbackScreenListItems> submitFeedbackScreenListItems;
    AlertDialog.Builder builder;
    //activity context
    Context context;

    String textVal, rateVal, smileyVal, likeVal;
    EditText feedbackanswer;

    //the layout resource file for the list items
    int resource;
    List<String> feedbacklist = new ArrayList<>();


    //constructor initializing the values
    public SubmitFeedBackScreenAdapter(Context context, int resource, List<SubmitFeedbackScreenListItems> submitFeedbackScreenListItems) {
        super(context, resource, submitFeedbackScreenListItems);
        this.context = context;
        this.resource = resource;
        this.submitFeedbackScreenListItems = submitFeedbackScreenListItems;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        final TextView feedbackquestion = (TextView) view.findViewById(R.id.submitfeedbackscreenlist_question);
        feedbackanswer = (EditText) view.findViewById(R.id.submitfeedbackscreenlist_editext);
        final RatingBar feedbackrate = (RatingBar) view.findViewById(R.id.submitfeedbackscreenlist_ratebar);
        final SmileRating smileRating = (SmileRating) view.findViewById(R.id.smile_rating);
        final LikeButton likerating = (LikeButton) view.findViewById(R.id.submitfeedbackscreenlist_like);

        //getting the hero of the specified position
        final SubmitFeedbackScreenListItems hero = submitFeedbackScreenListItems.get(position);

        if (hero.getFeedbacktype().equals("Rate")) {
            feedbackrate.setVisibility(View.VISIBLE);
            feedbackrate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                    rateVal = String.valueOf(rating);
                    feedbackrate.setRating(rating);
                    hero.setRateval(rateVal);
                    hero.setRateStatus(true);
                    feedbacklist.add(rateVal);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            submitFeedbackScreenListItems.remove(position);
                            notifyDataSetChanged();
                        }
                    }, 1000);
                }
            });
        }

        if (hero.getFeedbacktype().equals("Text")) {
            feedbackanswer.setTextColor(Color.parseColor("#d63d0a"));
            feedbackquestion.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View view) {
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
                    alertbox.setMessage("Submit your Feedback");
                    alertbox.setTitle("Feedback");

                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.textview_dialog_layout, null,false);
                    alertbox.setView(promptsView);

                    final EditText userInput = (EditText) promptsView
                            .findViewById(R.id.editTextDialogUserInput);
                    userInput.setTextColor(Color.parseColor("#d63d0a"));
                    alertbox.setNeutralButton("OK",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    feedbacklist.add(userInput.getText().toString());
                                    submitFeedbackScreenListItems.remove(position);
                                }
                            });
                    alertbox.show();
                }
            });
        }
        if (hero.getFeedbacktype().equals("Smiley")) {
            smileRating.setVisibility(View.VISIBLE);
            smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(int smiley, boolean reselected) {
                    switch (smiley) {

                        case SmileRating.BAD:
                            smileyVal = "BAD";
                            break;
                        case SmileRating.GOOD:
                            smileyVal = "GOOD";
                            break;
                        case SmileRating.GREAT:
                            smileyVal = "GREAT";
                            break;
                        case SmileRating.OKAY:
                            smileyVal = "OKAY";
                            break;
                        case SmileRating.TERRIBLE:
                            smileyVal = "TERRIBLE";
                            break;

                    }
                    hero.setSmileyval(smileyVal);
                    hero.setSmileyStatus(true);
                    feedbacklist.add(smileyVal);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            submitFeedbackScreenListItems.remove(position);
                            notifyDataSetChanged();
                        }
                    }, 1000);
                }
            });
        }
        if (hero.getFeedbacktype().equals("Like")) {
            likerating.setVisibility(View.VISIBLE);
            likerating.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    likeVal = "Liked";
                    hero.setLikeval(likeVal);
                    hero.setLikeStatus(true);
                    feedbacklist.add(likeVal);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            submitFeedbackScreenListItems.remove(position);
                            notifyDataSetChanged();
                        }
                    }, 1000);
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    likeVal = "none";
                    hero.setLikeval(likeVal);
                    hero.setLikeStatus(true);
                    feedbacklist.add(likeVal);
                }
            });
        }

        //adding values to the list item
        feedbackquestion.setText(hero.getFeedbackquestion());
        //finally returning the view
        return view;
    }

    public void displayAlert(View v) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder( context,R.style.MyStudentAlertDialogStyle);
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.textview_dialog_layout, null,false);
        alertDialog.setView(promptsView);
        alertDialog.setTitle("Enter your feedback");
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        alertDialog.setPositiveButton(
                "Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        feedbacklist.add(userInput.getText().toString());
                    }
                }
        );
        alertDialog.create();
        alertDialog.show();
    }


    public int getItemCount() {
        return submitFeedbackScreenListItems.size();
    }

    List<String> getSubmittedFeedbackDetails() {
        //  ArrayList<String> feedback = new ArrayList<>();
        return feedbacklist;
    }


}