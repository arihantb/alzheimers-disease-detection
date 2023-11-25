package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class User_request_appoinment extends AppCompatActivity {



    FirebaseDatabase database;
    private DatabaseReference mDatabase,mDatabase1;
    String d_id,u_id;

    TextView name,address,clinic_time;
    ImageButton request_appoit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_appoinment);

        name=findViewById(R.id.d_name_txt);
        address=findViewById(R.id.d_address_txt);
        clinic_time=findViewById(R.id.d_clinic_txt);
        request_appoit_btn=findViewById(R.id.request_appointment_btn);

        Save_del_var save_obj=new Save_del_var();
        d_id= save_obj.apply_appointmet_d_id;
        u_id=save_obj.u_id;
        Log.d("Log","clicked d_id="+d_id);

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("doctors").child(d_id);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String d_name=(String) dataSnapshot.child("name").getValue();
                String d_address=(String) dataSnapshot.child("address").getValue();
                String d_clinic=(String) dataSnapshot.child("clinic_day").getValue();

                name.setText(d_name);
                address.setText("Address: \n"+d_address);
                clinic_time.setText("Clinic Timing: \n"+d_clinic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        request_appoit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase = FirebaseDatabase.getInstance().getReference(); //db connectivity
                mDatabase1 = FirebaseDatabase.getInstance().getReference(); //db connectivity

                mDatabase=mDatabase.child("users").child(u_id).child("appointment");

                mDatabase.child("status").setValue("0");
                mDatabase.child("d_id").setValue(d_id);
                mDatabase.child("date").setValue(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
                mDatabase.child("time").setValue(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));

                mDatabase1=mDatabase1.child("doctors").child(d_id).child("pending_appointment");
                mDatabase1.push().setValue(u_id);


                Intent intent = new Intent(User_request_appoinment.this, Homepage.class);
                startActivity(intent);

            }
        });

    }
}