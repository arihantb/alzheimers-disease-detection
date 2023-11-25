package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class doc_register extends AppCompatActivity {

    Button confirm_btn;
    TextView txt_name,txt_email,txt_reg_no,txt_adds,txt_day_time,txt_pass,txt_confirm_pass;
    String doc_name,doc_email,doc_regno,doc_adds,doc_clinic_day,doc_pass,doc_conf_pass,d_id;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_register);
        mAuth = FirebaseAuth.getInstance();
        confirm_btn=findViewById(R.id.confitm_btn);

        txt_name=findViewById(R.id.editTextName);
        txt_email=findViewById(R.id.editTextEmail);
        txt_reg_no=findViewById(R.id.editTextRNumber);
        txt_adds=findViewById(R.id.editTextlAddress);
        //txt_day=findViewById(R.id.editTextDays);
        txt_day_time=findViewById(R.id.editTextDayTime);
        txt_pass=findViewById(R.id.editTextPassword);
        txt_confirm_pass=findViewById(R.id.editTextConfirmPassword);


        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sign_up_doc_auth();

            }
        });
    }

    private void sign_up_doc_auth() {
        doc_name = txt_name.getText().toString();
        doc_email = txt_email.getText().toString();
        doc_regno = txt_reg_no.getText().toString();
        doc_adds = txt_adds.getText().toString();
        doc_clinic_day = txt_day_time.getText().toString();
        doc_pass = txt_pass.getText().toString();
        doc_conf_pass = txt_confirm_pass.getText().toString();

        Random rand = new Random();
        d_id = (doc_name.replace(" ","")).replace(".","")+Integer.toString(rand.nextInt(88)+10)+Integer.toString(rand.nextInt(88)+10);

        if(doc_name.equals("") || doc_email.equals("") || doc_regno.equals("") ||
                doc_adds.equals("") || doc_clinic_day.equals("") || doc_pass.equals("") || doc_conf_pass.equals("")){

            Toast.makeText(doc_register.this, "Fill all details!",
                    Toast.LENGTH_SHORT).show();

        } else if(doc_pass.equals(doc_conf_pass)) {

            mAuth.createUserWithEmailAndPassword(doc_email, doc_pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Log", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //function_shared_pref
                                write_new_user_db ();
                                shared_pref_storage();


                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Log", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(doc_register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });

        }else{
            Toast.makeText(doc_register.this, "Password doesn't match!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void shared_pref_storage() {
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("d_id", d_id);
        editor.commit(); //done
        Log.d("Log1", "Login"+d_id);
    }

    private void write_new_user_db() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Doc_register_info doc_obj=new Doc_register_info(doc_name,doc_pass,doc_email,d_id,doc_clinic_day,doc_adds,doc_regno);
        mDatabase.child("doctors").child(d_id).setValue(doc_obj);
    }

    private void updateUI(FirebaseUser user) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(doc_register.this, "Account verification started!", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(doc_register.this, doc_sign_in.class);
        startActivity(i);
    }

    public void go_to_doc_signin(View view) {
        Intent i=new Intent(doc_register.this, doc_sign_in.class);
        startActivity(i);
    }
}