package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 2/20/2018.
 */

public class NotificationScreenAdapter extends RecyclerView.Adapter<NotificationScreenAdapter.NotificationScreenViewHolder> {

    private Context ctx;
    private List<NotificationScreenDetails> notificationScreenDetails;

    public NotificationScreenAdapter(Context ctx, List<NotificationScreenDetails> notificationScreenDetails) {
        this.ctx = ctx;
        this.notificationScreenDetails = notificationScreenDetails;
    }

    @Override
    public NotificationScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.cardlayout_faculty_noticationscreen, null);
        return new NotificationScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationScreenViewHolder holder, int position) {

        NotificationScreenDetails notification=notificationScreenDetails.get(position);

        holder.msgTitle.setText(notification.getNotificationtitle());
        holder.msgTime.setText(notification.getNotificationtime());

        holder.msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newPosition = holder.getAdapterPosition();
                String notification_title=notificationScreenDetails.get(newPosition).getNotificationtitle();
                String notification_msg=notificationScreenDetails.get(newPosition).getNotificationmsg();
                String notification_date=notificationScreenDetails.get(newPosition).getNotificationtime();
                Intent intent = new Intent(view.getContext(),DetailedNotificationScreen.class);

                intent.putExtra("notification_title", notification_title);
                intent.putExtra("notification_msg", notification_msg);
                intent.putExtra("notification_date", notification_date);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationScreenDetails.size();
    }

    class NotificationScreenViewHolder extends RecyclerView.ViewHolder{

        TextView msgTitle,msgTime;
        Button msgButton;
        ImageView notificationImage;

        public NotificationScreenViewHolder(View itemView) {
            super(itemView);

            msgTitle=(TextView)itemView.findViewById(R.id.faculty_notificationscreen_msgtitle);
            msgTime=(TextView)itemView.findViewById(R.id.faculty_notificationscreen_msgtime);
            msgButton=(Button)itemView.findViewById(R.id.faculty_notificationscreen_msgbutton);
        }


    }
}
