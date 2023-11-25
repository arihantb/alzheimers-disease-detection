package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MOCA_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_o_c_a_2);

        final TextView answer1=findViewById(R.id.text1);
        final TextView answer2=findViewById(R.id.text2);
        final TextView answer3=findViewById(R.id.text3);

        ImageButton nextbtn=findViewById(R.id.finish);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MOCA_Score mark_obj=new MOCA_Score();
                mark_obj.que2=0;


                if(answer1.getText().toString().equalsIgnoreCase("lion")){
                    Log.d("test","correct lion");
                    mark_obj.que2++;
                }

                if(answer2.getText().toString().equalsIgnoreCase("camel")){
                    Log.d("test","correct camel");
                    mark_obj.que2++;
                }

                if((answer3.getText().toString().equalsIgnoreCase("riono") || answer3.getText().equals("rionoceros"))){
                    Log.d("test","correct riono");
                    mark_obj.que2++;
                }
                Log.d("test","que1="+mark_obj.que1+" que2="+mark_obj.que2);

                Intent intent = new Intent(MOCA_2.this, MOCA_3.class);
                startActivity(intent);
            }
        });
    }
}