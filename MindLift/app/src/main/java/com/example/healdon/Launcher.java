package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Launcher extends AppCompatActivity {

    String u_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
            u_id=sharedPreferences.getString("id", "");
            
            if(u_id.isEmpty()){
                start_login();
            }else{
                //save u_id in class
                Save_del_var obj=new Save_del_var();
                obj.u_id=u_id;
                start_home();
            }
        }catch (Exception e){
            start_login();
        }

       
        
        
    }

    private void start_login() {
        Intent i=new Intent(Launcher.this, MainActivity.class);
        startActivity(i);
    }

    private void start_home() {
        Intent i=new Intent(Launcher.this, Homepage.class);
        startActivity(i);
    }
}