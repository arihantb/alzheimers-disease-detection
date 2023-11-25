package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class doc_patient_game_details extends AppCompatActivity {

    String game,voice,moca,name;
    TextView game_txt,voice_txt,moca_txt,name_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_patient_game_details);

        Intent intent = getIntent();
        String u_id = intent.getExtras().getString("u_id");

        game_txt=findViewById(R.id.game_txt);
        voice_txt=findViewById(R.id.voice_txt);
        moca_txt=findViewById(R.id.moca_txt);
        name_txt=findViewById(R.id.name);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users").child(u_id);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name=(String) dataSnapshot.child("name").getValue();
                game=(String) dataSnapshot.child("test").child("game_score").child("model_prediction").getValue();
                voice=(String) dataSnapshot.child("test").child("voice").child("model_prediction").getValue();
                moca=(String) dataSnapshot.child("test").child("moca_score").child("total_marks").getValue();

                name_txt.setText("Patient Name: "+name);

                if(game.equals("1")) {
                    game_txt.setText("Game Model Prediction: Positive");
                }else{
                    game_txt.setText("Game Model Prediction: Negative");
                }

                if(voice.equals("1")) {
                    voice_txt.setText("Voice Model Prediction: Positive");
                }else{
                    voice_txt.setText("Voice Model Prediction: Negative");
                }

                moca_txt.setText("MoCA Score: "+moca);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}