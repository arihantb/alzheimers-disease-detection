package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MOCA_1 extends AppCompatActivity {
    String answer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_o_c_a_1);

        ImageButton c_1,c_2,c_3,c_4,c_5,c_a,c_b,c_c,c_d,c_e;

        c_1=findViewById(R.id.c_1);
        c_2=findViewById(R.id.c_2);
        c_3=findViewById(R.id.c_3);
        c_4=findViewById(R.id.c_4);
        c_5=findViewById(R.id.c_5);
        c_a=findViewById(R.id.c_a);
        c_b=findViewById(R.id.c_b);
        c_c=findViewById(R.id.c_c);
        c_d=findViewById(R.id.c_d);
        c_e=findViewById(R.id.c_e);


        ImageButton nextbtn1=findViewById(R.id.finish);
        nextbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MOCA_Score mark_obj=new MOCA_Score();

                Log.d("test","string="+answer);
                if(answer.equals("1a2b3c4d5e")){
                    Log.d("test","matched");
                    mark_obj.que1=1;
                }else{
                    mark_obj.que1=0;
                }
                Intent intent = new Intent(MOCA_1.this, MOCA_2.class);
                startActivity(intent);
            }
        });

        c_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"1";
            }
        });

        c_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"2";
            }
        });

        c_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"3";
            }
        });

        c_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"4";
            }
        });

        c_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"5";
            }
        });

        c_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"a";
            }
        });

        c_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"b";
            }
        });

        c_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"c";
            }
        });

        c_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"d";
            }
        });

        c_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answer+"e";
            }
        });

    }
}