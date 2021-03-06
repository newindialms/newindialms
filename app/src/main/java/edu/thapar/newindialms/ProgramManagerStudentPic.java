package edu.thapar.newindialms;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramManagerStudentPic extends Fragment {
    View rootview;
    private TextView studentPicProgram;

    public ProgramManagerStudentPic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_program_manager_student_pic, container, false);
        studentPicProgram = rootview.findViewById(R.id.student_PicProgram);

        studentPicProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentPicProgram.class);
                startActivity(intent);
            }
        });

        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.navigation_program_picbook));
    }

}
