package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.thapar.newindialms.LoginScreen;
import edu.thapar.newindialms.SharedPrefManager;
import edu.thapar.newindialms.R;

import static android.app.WallpaperManager.getInstance;

public class Dashboard extends AppCompatActivity {
    Toolbar toolbar_main_screen;
    MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            //not logged in
            finish();
            startActivity(new Intent(this,LoginScreen.class));
        }

    }



}
