package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kamalshree on 11/7/2017.
 */

public class Success extends AppCompatActivity implements View.OnClickListener {


    private Button login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        login_button = (Button) findViewById(R.id.success_login_button);
        login_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
        Intent login_intent = new Intent(Success.this, LoginScreen.class);
        startActivity(login_intent);
    }
}