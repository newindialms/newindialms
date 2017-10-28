package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.thapar.newindialms.R;

import static edu.thapar.newindialms.R.id.view;


public class MainScreen extends AppCompatActivity implements View.OnClickListener {
    Context context;
    Button register_button;
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            // logged in
            finish();
            startActivity(new Intent(this, Dashboard.class));
            return;
        }

        login_button = (Button) findViewById(R.id.mainscreen_login);
        register_button = (Button) findViewById(R.id.mainscreen_register);

        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.mainscreen_login:
                Intent intent_login = new Intent(MainScreen.this, LoginScreen.class);
                startActivity(intent_login);
                break;

            case R.id.mainscreen_register:
                Intent intent_register = new Intent(MainScreen.this, RegisterScreen.class);
                startActivity(intent_register);
                break;
        }
    }

}
