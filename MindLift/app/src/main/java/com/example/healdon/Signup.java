package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import static android.provider.Telephony.Mms.Part.TEXT;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView name_text, email_text, pass_text, repass_text,login_btn;
    String name, email, pass, repass, u_id;
    Button signup_btn;
    public static final String SHARED_PREFS = "sharedPrefs";
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);




        mAuth = FirebaseAuth.getInstance();
        name_text=findViewById(R.id.name_text);
        email_text=findViewById(R.id.email_text);
        pass_text=findViewById(R.id.pass_text);
        repass_text=findViewById(R.id.repass_text);
        signup_btn = findViewById(R.id.signup_btn);

        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            login_start(view);
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_auth();
            }
        });
    }


    public void signup_auth(){
        name=name_text.getText().toString();
        pass=pass_text.getText().toString();
        repass=repass_text.getText().toString();
        email=email_text.getText().toString();

        Random rand = new Random();

        u_id = name.replace(" ","")+Integer.toString(rand.nextInt(88)+10)+Integer.toString(rand.nextInt(88)+10);
        if (name.equals("") || pass.equals("") || repass.equals("") || email.equals("")) {
            Toast.makeText(Signup.this, "Fill all details!",
                    Toast.LENGTH_SHORT).show();
        }
        else if(pass.equals(repass)){



            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Log", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //function_shared_pref
                                write_new_user_db (name,pass,email, u_id);
                                write_activity_db();
                                shared_pref_storage(u_id);


                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Log", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Signup.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
            });

        }
        else{
            Toast.makeText(Signup.this, "Password doesn't match!",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void shared_pref_storage(String u_id)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", u_id);
        editor.commit(); //done
        Log.d("Log1", "Login"+u_id);

    }

    private void write_new_user_db(String name, String pass, String email, String u_id) {
        Signup_info obj = new Signup_info(name,email,pass,u_id);

        mDatabase = FirebaseDatabase.getInstance().getReference();

         mDatabase.child("users").child(u_id).setValue(obj);
    }

    public void write_activity_db() {
        Initialize_activity init_act_obj = new Initialize_activity();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(u_id).child("activity").setValue(init_act_obj);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Intent i=new Intent(Signup.this, Homepage.class);
        startActivity(i);
    }

    public void login_start(View view) {
        Intent i=new Intent(Signup.this, MainActivity.class);
        startActivity(i);
    }
}
