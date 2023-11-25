package com.example.healdon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment implements View.OnClickListener {

    public ExerciseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_exercise, container, false);
        ImageView imageViewA1=(ImageView) view.findViewById(R.id.imageViewA1);
        imageViewA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=_ocdRy-1v3A")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewA1=(TextView) view.findViewById(R.id.textViewA1);
        textViewA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=_ocdRy-1v3A")));
                Log.i("Video", "Video Playing....");
            }
        });
        ImageView imageViewA2=(ImageView) view.findViewById(R.id.imageViewA2);
        imageViewA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=59eActUvH-s")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewA2=(TextView) view.findViewById(R.id.textViewA2);
        textViewA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=59eActUvH-s")));
                Log.i("Video", "Video Playing....");
            }
        });
        ImageView imageViewA3=(ImageView) view.findViewById(R.id.imageViewA3);
        imageViewA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=M42FMsHi1ds")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewA3=(TextView) view.findViewById(R.id.textViewA3);
        textViewA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=M42FMsHi1ds")));
                Log.i("Video", "Video Playing....");
            }
        });
        ImageView imageViewA4=(ImageView) view.findViewById(R.id.imageViewA4);
        imageViewA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=0mPNlC0vD6s")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewA4=(TextView) view.findViewById(R.id.textViewA4);
        textViewA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=0mPNlC0vD6s")));
                Log.i("Video", "Video Playing....");
            }
        });
        ImageView imageViewB1=(ImageView) view.findViewById(R.id.imageViewB1);
        imageViewB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=04zZLGcrN5Y")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewB1=(TextView) view.findViewById(R.id.textViewB1);
        textViewB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=04zZLGcrN5Y")));
                Log.i("Video", "Video Playing....");
            }
        });
       ImageView imageViewB2=(ImageView) view.findViewById(R.id.imageViewB2);
        imageViewB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=751E9kAdkwg")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewB2=(TextView) view.findViewById(R.id.textViewB2);
        textViewB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=751E9kAdkwg")));
                Log.i("Video", "Video Playing....");
            }
        });
      ImageView imageViewB3=(ImageView) view.findViewById(R.id.imageViewB3);
        imageViewB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=VPiawRHRpmY")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewB3=(TextView) view.findViewById(R.id.textViewB3);
        textViewB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=VPiawRHRpmY")));
                Log.i("Video", "Video Playing....");
            }
        });
      ImageView imageViewB4=(ImageView) view.findViewById(R.id.imageViewB4);
        imageViewB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=B54sRn3f8S0")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewB4=(TextView) view.findViewById(R.id.textViewB4);
        textViewB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=B54sRn3f8S0")));
                Log.i("Video", "Video Playing....");
            }
        });
        ImageView imageViewC1=(ImageView) view.findViewById(R.id.imageViewC1);
        imageViewC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=FpIfMyfpCk0")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewC1=(TextView) view.findViewById(R.id.textViewC1);
        textViewC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=FpIfMyfpCk0")));
                Log.i("Video", "Video Playing....");
            }
        });
        ImageView imageViewC2=(ImageView) view.findViewById(R.id.imageViewC2);
        imageViewC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=CW0h60-PS4Y")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewC2=(TextView) view.findViewById(R.id.textViewC2);
        textViewC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=CW0h60-PS4Y")));
                Log.i("Video", "Video Playing....");
            }
        });
       ImageView imageViewC3=(ImageView) view.findViewById(R.id.imageViewC3);
        imageViewC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=87BeiyTFZyU")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewC3=(TextView) view.findViewById(R.id.textViewC3);
        textViewC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=87BeiyTFZyU")));
                Log.i("Video", "Video Playing....");
            }
        });
        ImageView imageViewC4=(ImageView) view.findViewById(R.id.imageViewC4);
        imageViewC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=WsIs87By-a8")));
                Log.i("Video", "Video Playing....");


            }
        });
        TextView textViewC4=(TextView) view.findViewById(R.id.textViewC4);
        textViewC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=WsIs87By-a8")));
                Log.i("Video", "Video Playing....");
            }
        });

        return view;

    }
    @Override
    public void onClick(View v) {

    }
}
