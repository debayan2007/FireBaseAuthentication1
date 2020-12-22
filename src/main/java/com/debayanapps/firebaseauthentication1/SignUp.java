package com.debayanapps.firebaseauthentication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText password;
    TextInputEditText repet;
    TextInputEditText phone;
    Button signup;
    TextView textin;
    FirebaseAuth auth;
    FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        repet=findViewById(R.id.repet1);
        signup=findViewById(R.id.signin1);
        textin=findViewById(R.id.textin1);
        final String[] userID = new String[0];

        auth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1=username.getText().toString();
                String pass=password.getText().toString().trim();
                String repeat=repet.getText().toString().trim();

                if (user1.isEmpty()||pass.isEmpty()||repeat.isEmpty()){
                    Toast.makeText(getApplicationContext(), "No field cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(repeat)){
                        auth.createUserWithEmailAndPassword(user1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user=auth.getCurrentUser();
                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(SignUp.this, "Please Check your Email and verify", Toast.LENGTH_LONG).show();


                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignUp.this, "Error:! "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                Toast.makeText(SignUp.this, "User Successfully Created Now Login!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }else {
                        Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,SignIn.class));
            }
        });
    }
}