package edu.thapar.newindialms;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreen extends AppCompatActivity {

    private String ProgramName;
    private TextView Studentpic_program_title;
    private Toolbar studentpic_toolbar;

    //a List of type hero for holding list items
    List<ProgramScreenListItems> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen);
        ProgramName = getIntent().getStringExtra("programname");

        studentpic_toolbar = findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView studentpic_title = findViewById(R.id.studentpic_title);
        studentpic_title.setText(ProgramName);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_program_title = findViewById(R.id.Studentpic_program_title);
        Studentpic_program_title.setText(ProgramName);

        //initializing objects
        heroList = new ArrayList<>();
        listView = findViewById(R.id.studentpic_programscreenlist_ListView);

        ProgramScreenListItems pglist = new ProgramScreenListItems(ProgramName);
        pglist.setProgramname(ProgramName);

        heroList.add(new ProgramScreenListItems("Year of Joining", "Specialization", "Courses", "Core Courses", pglist.getProgramname()));

        //creating the adapter
        ProgramScreenAdapter adapter = new ProgramScreenAdapter(this, R.layout.activity_program_screen_listitems, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.setTitle(ProgramName);
    }
}
