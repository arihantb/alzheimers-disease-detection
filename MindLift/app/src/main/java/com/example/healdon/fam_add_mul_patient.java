package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fam_add_mul_patient extends AppCompatActivity {
    DatabaseReference mDatabase;

    TextView f_u_id;
    String f_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fam_add_mul_patient);

        f_u_id=findViewById(R.id.f_u_id);

        Save_del_var save_obj = new Save_del_var();
        f_id=save_obj.f_id;

        //textview
        //add_btn


    }


    public void add_patient(View view){
        String fam_u_id=f_u_id.getText().toString();


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("family").child(f_id).child("patient").push().setValue(fam_u_id);

        //go back to fam_upload

    }
}