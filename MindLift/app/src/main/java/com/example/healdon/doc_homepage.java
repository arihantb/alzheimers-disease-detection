package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class doc_homepage extends AppCompatActivity {

    TextView search_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_homepage);

        search_bar=findViewById(R.id.search_bar);

    }
    public void pendingAppointment(View view){
        Intent pending=new Intent(doc_homepage.this,doc_recieved_appointment.class);
        startActivity(pending);
    }
    public void acceptedAppointment(View view){
        Intent accepted=new Intent(doc_homepage.this,doc_accepted_appointment.class);
        startActivity(accepted);
    }

    public void signout_goto_signin(View view) {
        FirebaseAuth.getInstance().signOut();

        SharedPreferences settings =getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
        settings.edit().clear().commit();

        Intent accepted=new Intent(doc_homepage.this,doc_sign_in.class);
        startActivity(accepted);
    }

    String u_id;
    public void search_patient(View view){
        u_id=search_bar.getText().toString();

        if(u_id.isEmpty()){
            Toast.makeText(doc_homepage.this, "Enter patient id!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Intent i=new Intent(doc_homepage.this,doc_patient_game_details.class);
            i.putExtra("u_id",u_id);
            startActivity(i);
        }


    }
}