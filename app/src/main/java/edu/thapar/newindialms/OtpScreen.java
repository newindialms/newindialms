package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import static edu.thapar.newindialms.R.layout.toolbar_main_screen;

/**
 * Created by kamalshree on 9/21/2017.
 */

public class OtpScreen extends AppCompatActivity  {
    Toolbar toolbar_otp_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_screen);
        toolbar_otp_screen = (Toolbar) findViewById(R.id.toolbar_login_screen);
        toolbar_otp_screen.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(toolbar_otp_screen);
        getSupportActionBar().setTitle("Enter your OTP");
        toolbar_otp_screen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
