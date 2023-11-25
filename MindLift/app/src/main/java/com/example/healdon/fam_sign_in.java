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

public class fam_sign_in extends AppCompatActivity {
    TextView create_acc,txt_email,txt_pass;
    Button signIn_btn;
    String fam_email,fam_pass,verified;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fam_sign_in);

        mAuth = FirebaseAuth.getInstance();

        create_acc=findViewById(R.id.fam_create_account_btn);
        signIn_btn=findViewById(R.id.fam_sign_in_btn);
        txt_email=findViewById(R.id.editTextEmail_fam);
        txt_pass=findViewById(R.id.editTextPassword_fam);

        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccount=new Intent(fam_sign_in.this,fam_register.class);
                startActivity(createAccount);
            }
        });
        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn_fam_auth();
            }
        });

    }



    private void signIn_fam_auth() {
        fam_email=txt_email.getText().toString();
        fam_pass=txt_pass.getText().toString();

        if (fam_email.equals("") || fam_pass.equals("")) {
            Toast.makeText(fam_sign_in.this, "Fill all details!",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(fam_email, fam_pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Log", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                fetching_did_db_using_mail_verification(fam_email);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Log", "signInWithEmail:failure", task.getException());
                                Toast.makeText(fam_sign_in.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }

    }

    private void updateUI() {
//        Intent createAccount = new Intent(fam_sign_in.this, fam_upload_photo.class);
//        startActivity(createAccount);
    }

    String fetched_f_id;
    private void fetching_did_db_using_mail_verification(final String email) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("family");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item_snapshot:dataSnapshot.getChildren()) {

                    String fetched_email=item_snapshot.child("email").getValue().toString();

                    if(email.equals(fetched_email)){
                        fetched_f_id=item_snapshot.child("f_id").getValue().toString();
                        Log.d("Log","f_id from sign in page="+fetched_f_id);


                            SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("f_id", fetched_f_id);
                            editor.commit(); //done

                            //saving u_id in static class
                            Save_del_var obj = new Save_del_var();
                            obj.f_id = fetched_f_id;

                            updateUI();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fam_sign_in.this, "Unable to set U_id",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }



//    public void go_to_upload_pic(View view) {
//        Intent i=new Intent(fam_sign_in.this, fam_upload_photo.class);
//        startActivity(i);
//    }

    public void go_to_fam_register(View view) {
        Intent i=new Intent(fam_sign_in.this, fam_register.class);
        startActivity(i);
    }
}