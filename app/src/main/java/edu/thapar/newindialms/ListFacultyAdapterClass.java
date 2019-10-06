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

public class ListFacultyAdapterClass extends BaseAdapter {
    Context context;
    List<Faculty> valueList;

    public ListFacultyAdapterClass(List<Faculty> listValue, Context context)
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
        ViewItemFaculty viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItemFaculty();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listview_faculty_item, null);

            viewItem.TextViewFacultyName = convertView.findViewById(R.id.faculty_text_view);
            viewItem.TextViewFacultyProgram = convertView.findViewById(R.id.facultyprogram_text_view);
            viewItem.TextViewFacultyCode = convertView.findViewById(R.id.facultyprogram_code);
            viewItem.TextViewFacultySpecialization = convertView.findViewById(R.id.facultyprogram_specialization);

            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItemFaculty) convertView.getTag();
        }

        viewItem.TextViewFacultyName.setText(valueList.get(position).FacultyName);
        viewItem.TextViewFacultyProgram.setText(valueList.get(position).FacultyID);
        viewItem.TextViewFacultyCode.setText(valueList.get(position).FacultyCode);
        viewItem.TextViewFacultySpecialization.setText(valueList.get(position).FacultySpecialization);

        return convertView;
    }
}

class ViewItemFaculty
{
    TextView TextViewFacultyName,TextViewFacultyProgram,TextViewFacultyCode,TextViewFacultySpecialization;

}