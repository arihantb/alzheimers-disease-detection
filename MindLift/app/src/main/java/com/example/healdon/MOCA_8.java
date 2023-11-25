package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MOCA_8 extends AppCompatActivity {
    int corr1=0,corr2=0,corr3=0,corr4=0,corr5=0,wrong1=0,wrong2=0,wrong3=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_o_c_a_8);

        ImageButton nextbtn=findViewById(R.id.finish);
        Button face_btn=findViewById(R.id.btn1);
        Button hands_btn=findViewById(R.id.btn2);
        Button paper_btn=findViewById(R.id.btn3);
        Button velvet_btn=findViewById(R.id.btn4);
        Button church_btn=findViewById(R.id.btn5);
        Button daisy_btn=findViewById(R.id.btn6);
        Button rec_btn=findViewById(R.id.btn7);
        Button dark_btn=findViewById(R.id.btn8);

        face_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                corr1=1;
            }
        });
        velvet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                corr2=1;
            }
        });
        church_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                corr3=1;
            }
        });
        daisy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                corr4=1;
            }
        });
        rec_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                corr5=1;
            }
        });

        hands_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong1=1;
            }
        });
        paper_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong2=1;
            }
        });
        dark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong3=1;
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MOCA_Score mark_obj=new MOCA_Score();
                int total=corr1+corr2+corr3+corr4+corr5-wrong1-wrong2-wrong3;

                if(total <1){
                    mark_obj.que7=0;
                }else {
                    mark_obj.que7=total;
                }

                Log.d("test","que1="+mark_obj.que1+" que2="+mark_obj.que2+ " que3="+mark_obj.que3 + " que4="+mark_obj.que4+ " que5="+mark_obj.que5
                        + " que6="+mark_obj.que6+ " que7="+mark_obj.que7);

                Intent intent = new Intent(MOCA_8.this, MOCA_9.class);
                startActivity(intent);
            }
        });
    }
}