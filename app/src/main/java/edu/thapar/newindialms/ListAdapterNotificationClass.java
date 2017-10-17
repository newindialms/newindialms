package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/4/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapterNotificationClass extends BaseAdapter {

    Context context;
    List<Student> valueList;

    public ListAdapterNotificationClass(List<Student> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItemNotification viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItemNotification();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listview_item_notificiation, null);

            viewItem.TextViewStudentName = (TextView)convertView.findViewById(R.id.student_text_view_name);
            viewItem.TextViewStudentEmail = (TextView)convertView.findViewById(R.id.student_text_view_email);

            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItemNotification) convertView.getTag();
        }

        viewItem.TextViewStudentName.setText(valueList.get(position).StudentName);
        viewItem.TextViewStudentEmail.setText(valueList.get(position).StudentEmail);

        return convertView;
    }
}

class ViewItemNotification
{
    TextView TextViewStudentName,TextViewStudentEmail;

}