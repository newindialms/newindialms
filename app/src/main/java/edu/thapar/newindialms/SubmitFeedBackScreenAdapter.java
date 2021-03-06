package edu.thapar.newindialms;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.hsalf.smilerating.SmileRating;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class SubmitFeedBackScreenAdapter extends ArrayAdapter<SubmitFeedbackScreenListItems> {

    //the list values in the List of type hero
    List<SubmitFeedbackScreenListItems> submitFeedbackScreenListItems;
    AlertDialog.Builder builder;
    //activity context
    private Context context;

    private String rateVal, smileyVal, likeVal;
    private EditText feedbackanswer;

    //the layout resource file for the list items
    private int resource;
    List<String> feedbackratelist = new ArrayList<>();
    List<String> feedbacktextlist = new ArrayList<>();
    List<String> feedbacksmileylist = new ArrayList<>();
    List<String> feedbacklikelist = new ArrayList<>();
    HashMap<String, String> texthmap = new HashMap<String, String>();
    HashMap<String, String> ratehmap = new HashMap<String, String>();
    HashMap<String, String> smileyhmap = new HashMap<String, String>();
    HashMap<String, String> likehmap = new HashMap<String, String>();

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
        final TextView feedbackquestion = view.findViewById(R.id.submitfeedbackscreenlist_question);
        final TextView feedbackquestionclick = view.findViewById(R.id.submitfeedbackscreenlist_clickme);
        feedbackanswer = view.findViewById(R.id.submitfeedbackscreenlist_editext);
        final RatingBar feedbackrate = view.findViewById(R.id.submitfeedbackscreenlist_ratebar);
        final SmileRating smileRating = view.findViewById(R.id.smile_rating);
        final LikeButton likerating = view.findViewById(R.id.submitfeedbackscreenlist_like);
        final ImageView dislikeButton = view.findViewById(R.id.dislikeButton);

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
                    //feedbackratelist.add(rateVal);
                    ratehmap.put(rateVal, feedbackquestion.getText().toString());
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
            feedbackquestionclick.setVisibility(View.VISIBLE);
            feedbackquestionclick.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View view) {
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
                    alertbox.setMessage("Submit your Feedback");
                    alertbox.setTitle("Feedback");

                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.textview_dialog_layout, null, false);
                    alertbox.setView(promptsView);

                    final EditText userInput = promptsView
                            .findViewById(R.id.editTextDialogUserInput);
                    userInput.setTextColor(Color.parseColor("#d63d0a"));
                    alertbox.setNeutralButton("OK",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    // feedbacktextlist.add(userInput.getText().toString());
                                    // feedbacktextlist.add(feedbackquestion.getText().toString());
                                    texthmap.put(userInput.getText().toString(), feedbackquestion.getText().toString());
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
                            smileyVal = "1";
                            break;
                        case SmileRating.GOOD:
                            smileyVal = "3";
                            break;
                        case SmileRating.GREAT:
                            smileyVal = "4";
                            break;
                        case SmileRating.OKAY:
                            smileyVal = "2";
                            break;
                        case SmileRating.TERRIBLE:
                            smileyVal = "0";
                            break;

                    }
                    hero.setSmileyval(smileyVal);
                    hero.setSmileyStatus(true);
                    //feedbacksmileylist.add(smileyVal);
                    smileyhmap.put(smileyVal, feedbackquestion.getText().toString());
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
            dislikeButton.setVisibility(View.VISIBLE);

            likerating.setOnLikeListener(new OnLikeListener() {

                @Override
                public void liked(LikeButton likeButton) {
                    dislikeButton.setVisibility(View.INVISIBLE);
                    likeVal = "Like";
                    hero.setLikeval(likeVal);
                    hero.setLikeStatus(true);
                    //feedbacklikelist.add(likeVal);
                    likehmap.put(likeVal, feedbackquestion.getText().toString());
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
                    //feedbacklikelist.add(likeVal);
                }
            });
            dislikeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    likerating.setVisibility(View.INVISIBLE);
                    dislikeButton.setImageResource(R.drawable.dislike_color);
                    likeVal = "Dislike";
                    hero.setLikeval(likeVal);
                    hero.setLikeStatus(true);
                    // feedbacklikelist.add(likeVal);
                    likehmap.put(likeVal, feedbackquestion.getText().toString());
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

        //adding values to the list item
        feedbackquestion.setText(hero.getFeedbackquestion());
        //finally returning the view
        return view;
    }


    HashMap<String, String> getRateSubmittedFeedbackDetails() {
        //  ArrayList<String> feedback = new ArrayList<>();
        return ratehmap;
    }

    HashMap<String, String> getLikeSubmittedFeedbackDetails() {
        //  ArrayList<String> feedback = new ArrayList<>();
        return likehmap;
    }

    HashMap<String, String> getSmileySubmittedFeedbackDetails() {
        //  ArrayList<String> feedback = new ArrayList<>();
        return smileyhmap;
    }

    HashMap<String, String> getTextSubmittedFeedbackDetails() {
        //  ArrayList<String> feedback = new ArrayList<>();
        return texthmap;
    }


}