package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class doc_patient_details extends AppCompatActivity {

    String u_id,d_id;
    TextView name_txt,appointm_date,appointm_time;
    String name,apointm_date,apointm_time,key;
    private DatabaseReference mDatabase,mDatabase1;
    Save_del_var save_obj=new Save_del_var();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_patient_details);


        name_txt=findViewById(R.id.patient_name);
        appointm_date=findViewById(R.id.app_date);
        appointm_time=findViewById(R.id.app_time);

        key = getIntent().getStringExtra("key");
        Log.d("Log","key="+key);


        u_id=save_obj.apply_appointmet_d_id;
        d_id=save_obj.d_id;


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users").child(u_id);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name=(String) dataSnapshot.child("name").getValue();
                name_txt.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void confirm_appointment(View view) {
        apointm_date=appointm_date.getText().toString();
        apointm_time=appointm_time.getText().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(u_id).child("appointment").child("status").setValue("1");
        mDatabase.child("users").child(u_id).child("appointment").child("app_date").setValue(apointm_date);
        mDatabase.child("users").child(u_id).child("appointment").child("app_time").setValue(apointm_time);

        mDatabase1 = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("doctors").child(d_id).child("pending_appointment").child(key).removeValue();
        mDatabase.child("doctors").child(d_id).child("accepted_appoinment").push().setValue(u_id);

        save_obj.apply_appointmet_d_id=null;

        go_to_doc_home();

    }

    private void go_to_doc_home() {
        Intent intent = new Intent(doc_patient_details.this, doc_homepage.class);
        startActivity(intent);
    }
}