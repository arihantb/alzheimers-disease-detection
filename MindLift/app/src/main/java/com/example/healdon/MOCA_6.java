package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MOCA_6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_o_c_a_6);

        ImageButton nextbtn=findViewById(R.id.finish);
        final TextView ans1=findViewById(R.id.sub1);
        final TextView ans2=findViewById(R.id.sub2);
        final TextView ans3=findViewById(R.id.sub3);
        final TextView ans4=findViewById(R.id.sub4);
        final TextView ans5=findViewById(R.id.sub5);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count_corr=0;
                MOCA_Score mark_obj=new MOCA_Score();
                mark_obj.que5=0;
                if(ans1.getText().toString().equals("93")){
                    count_corr++;
                }
                if(ans2.getText().toString().equals("86")){
                    count_corr++;
                }
                if(ans3.getText().toString().equals("79")){
                    count_corr++;
                }
                if(ans4.getText().toString().equals("72")){
                    count_corr++;
                }
                if(ans5.getText().toString().equals("65")){
                    count_corr++;
                }

                if(count_corr==4 || count_corr==5){
                    mark_obj.que5=3;
                }else if(count_corr==2 || count_corr==3){
                    mark_obj.que5=2;
                }else if(count_corr==1){
                    mark_obj.que5=1;
                }

                Log.d("test","que1="+mark_obj.que1+" que2="+mark_obj.que2+ " que3="+mark_obj.que3 + " que4="+mark_obj.que4+ " que5="+mark_obj.que5);

                Intent intent = new Intent(MOCA_6.this, MOCA_7.class);
                startActivity(intent);
            }
        });
    }
}