package com.example.healdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Final_Score extends AppCompatActivity {

    TextView final_txt,game_score_text,voice_score_text,moca_score_text;
    String u_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final__score);

        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
        u_id=sharedPreferences.getString("id", "");

        Save_del_var save_obj=new Save_del_var();
        String game=save_obj.game_pred;
        String voice=save_obj.voice_pred;
        String moca_score=save_obj.moca_score;



        game_score_text=findViewById(R.id.game_score_textView);
        voice_score_text=findViewById(R.id.voice_score_textView);
        moca_score_text=findViewById(R.id.moca_score_textView);

        if(game.equals("0")){
            game_score_text.setText("Negative");
        }
        else {
            game_score_text.setText("Positive");
        }

        if(voice.equals("0")){

            voice_score_text.setText("Negative");
        }
        else{
            voice_score_text.setText("Positive");
        }
        moca_score_text.setText(moca_score);

        final_txt=findViewById(R.id.fscore_textView);

        try{

                final_txt.setText(save_obj.final_pred);

        }
        catch(Exception e){
                final_txt.setText("Negative");
        }






    }
    public void goto_home(){
        Intent i=new Intent(Final_Score.this,Homepage.class);
        startActivity(i);
    }
}