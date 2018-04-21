package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/10/2017.
 */

public class Feedback {
    private String feedback_title, feedback_question, feedback_type, id;

    public Feedback(String id, String feedback_title, String feedback_question, String feedback_type) {
        this.id = id;
        this.feedback_title = feedback_title;
        this.feedback_question = feedback_question;
        this.feedback_type = feedback_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedback_type() {
        return feedback_type;
    }

    public String getFeedback_title() {
        return feedback_title;
    }

    public String getFeedback_question() {
        return feedback_question;
    }

}
