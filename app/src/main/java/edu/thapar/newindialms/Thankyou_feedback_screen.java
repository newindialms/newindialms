package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kamalshree on 10/27/2017.
 */

public class Thankyou_feedback_screen extends AppCompatActivity {

    private Button close_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanksfeedback_screen);
        close_button=(Button)findViewById(R.id.thankyoubutton);

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.finishAffinity(Thankyou_feedback_screen.this);
            }
        });
    }

}