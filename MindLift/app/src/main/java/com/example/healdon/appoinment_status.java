package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class appoinment_status extends AppCompatActivity {

    String u_id;
    TextView name_txt,date_txt,time_txt,status_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment_status);

        name_txt=findViewById(R.id.name);
        date_txt=findViewById(R.id.date);
        time_txt=findViewById(R.id.time);
        status_txt=findViewById(R.id.status_txt);


        Save_del_var obj = new Save_del_var();
        u_id = obj.u_id;

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("users").child(u_id).child("appointment");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String d_id = (String) dataSnapshot.child("d_id").getValue();
                final String app_date = (String) dataSnapshot.child("app_date").getValue();
                final String app_time = (String) dataSnapshot.child("app_time").getValue();
                final String status = (String) dataSnapshot.child("status").getValue();


                final DatabaseReference myRef1 = database.getReference().child("doctors").child(d_id);
                myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String doctor_name=dataSnapshot.child("name").getValue().toString();

                        name_txt.setText(doctor_name);
                        date_txt.setText("Date:"+app_date);
                        time_txt.setText("Time:"+app_time);
                        if(status.equals("1")){
                            status_txt.setText("Accepted");
                        }else {
                            status_txt.setText("Pending");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}