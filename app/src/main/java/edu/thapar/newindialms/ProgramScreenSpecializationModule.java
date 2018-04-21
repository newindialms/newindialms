package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/25/2017.
 */

public class ProgramScreenSpecializationModule extends AppCompatActivity {

    private String SpecializationName;
    private TextView Studentpic_program_specialization_title;
    private Toolbar studentpic_toolbar;
    //a List of type hero for holding list items
    List<ProgramScreenSpecializationModuleListItems> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_specializationmodule);
        SpecializationName = getIntent().getStringExtra("specializationname");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);

        TextView studentpic_title = (TextView) findViewById(R.id.studentpic_title);
        studentpic_title.setText(SpecializationName);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_program_specialization_title = (TextView) findViewById(R.id.Studentpic_program_specializationmodule_title);
        Studentpic_program_specialization_title.setText(SpecializationName);

        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.studentpic_programscreenspecializationmodulelist_ListView);

        ProgramScreenSpecializationModuleListItems pglist = new ProgramScreenSpecializationModuleListItems(SpecializationName);
        pglist.setSpecializationname(SpecializationName);

        heroList.add(new ProgramScreenSpecializationModuleListItems("Courses", pglist.getSpecializationname()));

        //creating the adapter
        ProgramScreenSpecializationModuleAdapter adapter = new ProgramScreenSpecializationModuleAdapter(this, R.layout.activity_program_screen_specializationmodule_listitems, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.setTitle(SpecializationName);
    }
}