package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class RemoveCourseListItems {
    private String removecoursename, removecoursecode;

    public RemoveCourseListItems(String removecoursename, String removecoursecode) {
        this.removecoursename = removecoursename;
        this.removecoursecode = removecoursecode;
    }

    public String getRemovecoursecode() {
        return removecoursecode;
    }


    public String getRemovecoursename() {
        return removecoursename;
    }


}
