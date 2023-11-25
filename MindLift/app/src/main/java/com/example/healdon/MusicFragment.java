package com.example.healdon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MusicFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_music, container, false);
        TextView music_text1=(TextView)view.findViewById(R.id.textView31);
        TextView music_text2=(TextView)view.findViewById(R.id.textView32);
        TextView music_text3=(TextView)view.findViewById(R.id.textView33);
        TextView music_text4=(TextView)view.findViewById(R.id.textView34);
        TextView music_text5=(TextView)view.findViewById(R.id.music_text_chill);
        TextView music_text6=(TextView)view.findViewById(R.id.music_text_spiritual);
        TextView music_text7=(TextView)view.findViewById(R.id.music_text_indie);
        TextView music_text8=(TextView)view.findViewById(R.id.music_text_pop);
        music_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=M98QHc3kGAU")));
                Log.i("Video", "Video Playing....");
            }
        });
        music_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rac1lpOC4YA")));
                Log.i("Video", "Video Playing....");
            }
        });
        music_text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=G3t4uNaEShE")));
                Log.i("Video", "Video Playing....");
            }
        });
        music_text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=W2iQ38TG6qs")));
                Log.i("Video", "Video Playing....");
            }
        });
        music_text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=SQ1ED8-tBpE")));
                Log.i("Video", "Video Playing....");
            }
        });
        music_text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=n7BgglQtvx4")));
                Log.i("Video", "Video Playing....");
            }
        });
        music_text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9-cPonXJd_c")));
                Log.i("Video", "Video Playing....");
            }
        });
        music_text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Y_AzpTlSWk0")));
                Log.i("Video", "Video Playing....");
            }
        });
        return view;
    }
}