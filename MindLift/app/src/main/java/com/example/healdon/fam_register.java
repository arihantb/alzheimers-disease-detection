package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class fam_register extends AppCompatActivity {

    Button confirm_btn;
    TextView txt_name,txt_email,txt_u_id ,txt_pass,txt_confirm_pass;
    String fam_name,fam_email,fam_u_id,fam_pass,fam_conf_pass,f_id;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase,mDatabase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fam_register);
        mAuth = FirebaseAuth.getInstance();
        confirm_btn=findViewById(R.id.fam_reg_confitm_btn);


        txt_name=findViewById(R.id.fam_reg_name);
        txt_email=findViewById(R.id.fam_reg_email);
        txt_u_id =findViewById(R.id.fam_reg_u_id1);
        txt_pass=findViewById(R.id.fam_reg_password);
        txt_confirm_pass=findViewById(R.id.fam_reg_confirmPassword);


        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sign_up_fam_auth();

            }
        });

    }


    private void sign_up_fam_auth() {
        fam_name = txt_name.getText().toString();
        fam_email = txt_email.getText().toString();
        fam_u_id = txt_u_id .getText().toString();
        fam_pass = txt_pass.getText().toString();
        fam_conf_pass = txt_confirm_pass.getText().toString();

        Random rand = new Random();
        f_id = (fam_name.replace(" ","")).replace(".","")+Integer.toString(rand.nextInt(88)+10)+Integer.toString(rand.nextInt(88)+10);

        if(fam_name.equals("") || fam_email.equals("") || fam_u_id.equals("") || fam_pass.equals("") || fam_conf_pass.equals("")){

            Toast.makeText(fam_register.this, "Fill all details!",
                    Toast.LENGTH_SHORT).show();

        } else if(fam_pass.equals(fam_conf_pass)) {

            mAuth.createUserWithEmailAndPassword(fam_email, fam_pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Log", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                write_new_user_db ();

                                Save_del_var save_obj = new Save_del_var();
                                save_obj.f_id=f_id;


                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Log", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(fam_register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });

        }else{
            Toast.makeText(fam_register.this, "Password doesn't match!",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void write_new_user_db() {
        Log.d("Log","enterd write_new_user");
        //fetching patients u_id and connecting family member and user


        mDatabase = FirebaseDatabase.getInstance().getReference();
        Fam_register_info doc_obj=new Fam_register_info(fam_name,fam_pass,fam_email,f_id);
        mDatabase.child("family").child(f_id).setValue(doc_obj);

        mDatabase.child("family").child(f_id).child("patient").push().setValue(fam_u_id);


        mDatabase1 = FirebaseDatabase.getInstance().getReference();
        mDatabase1.child("users").child(fam_u_id).child("f_id").setValue(f_id);



    }

    private void updateUI(FirebaseUser user) {
//        Intent i=new Intent(fam_register.this, fam_upload_photo.class);
//        startActivity(i);
    }



}