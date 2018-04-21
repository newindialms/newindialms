package edu.thapar.newindialms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/28/2017.
 */

public class NotificationTabAdapter extends ArrayAdapter<NotificationTabListItems> {

    //the list values in the List of type hero
    List<NotificationTabListItems> notificationTabListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public NotificationTabAdapter(Context context, int resource, List<NotificationTabListItems> notificationTabListItems) {
        super(context, resource, notificationTabListItems);
        this.context = context;
        this.resource = resource;
        this.notificationTabListItems = notificationTabListItems;
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
        TextView notification_title = (TextView) view.findViewById(R.id.notification_title);
        TextView notification_message = (TextView) view.findViewById(R.id.notification_message);
        TextView notification_date = (TextView) view.findViewById(R.id.notification_date);

        //getting the hero of the specified position
        final NotificationTabListItems hero = notificationTabListItems.get(position);

        //adding values to the list item
        notification_title.setText(hero.getNotificationTitle());
        notification_message.setText(hero.getNotificationMessage());
        notification_date.setText(hero.getNotificationDate());

        //finally returning the view
        return view;
    }
}