package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fam_upload_photo extends AppCompatActivity {
    DatabaseReference mDatabase;
    EditText f_u_id;
    String f_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fam_upload_photo);

        //add_btn
        //goto to add_mul_patient

        f_u_id=findViewById(R.id.f_u_id);

        Save_del_var save_obj = new Save_del_var();
        f_id=save_obj.f_id;




    }

    public void sign_out(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(fam_upload_photo.this, MainActivity.class);
        startActivity(i);
    }

    public void add_patient(View view){
        String fam_u_id=f_u_id.getText().toString();


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("family").child(f_id).child("patient").push().setValue(fam_u_id);

        //go back to fam_upload

    }
}