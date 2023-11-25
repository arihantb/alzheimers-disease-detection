package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MOCA_7 extends AppCompatActivity {
    int Mark1=0,Mark2=0, wrong1=0,wrong2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_o_c_a_7);

        ImageButton nextbtn=findViewById(R.id.finish);
        Button ans1=findViewById(R.id.corr_btn1);
        Button ans2=findViewById(R.id.corr_btn2);
        Button btn1=findViewById(R.id.btn1);
        Button btn2=findViewById(R.id.btn2);
        Button btn3=findViewById(R.id.btn3);
        Button btn4=findViewById(R.id.btn4);
        Button btn5=findViewById(R.id.btn5);
        Button btn6=findViewById(R.id.btn6);
        Button btn7=findViewById(R.id.btn7);
        Button btn8=findViewById(R.id.btn8);
        Button btn9=findViewById(R.id.btn9);
        Button btn10=findViewById(R.id.btn10);

        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mark1=1;
            }
        });
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mark2=1;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong1=1;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong1=1;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong1=1;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong1=1;
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong1=1;
            }
        });


        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong2=1;
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong2=1;
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong2=1;
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong2=1;
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong2=1;
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(wrong1==1){
                    Mark1=0;
                }
                if(wrong2==1){
                    Mark2=0;
                }

                MOCA_Score mark_obj=new MOCA_Score();
                mark_obj.que6=Mark1+Mark2;
                Log.d("test","que1="+mark_obj.que1+" que2="+mark_obj.que2+ " que3="+mark_obj.que3 + " que4="+mark_obj.que4+ " que5="+mark_obj.que5
                        + " que6="+mark_obj.que6);
                Intent intent = new Intent(MOCA_7.this, MOCA_8.class);
                startActivity(intent);
            }
        });
    }
}