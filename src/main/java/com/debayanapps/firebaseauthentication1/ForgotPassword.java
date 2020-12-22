package com.debayanapps.firebaseauthentication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    Button next;
    EditText forgot;
    FirebaseAuth auth;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

    forgot=findViewById(R.id.email1);
    next=findViewById(R.id.next);
    auth=FirebaseAuth.getInstance();
    log=findViewById(R.id.lo);

      next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String em=forgot.getText().toString();

              auth.sendPasswordResetEmail(em).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
                      Toast.makeText(ForgotPassword.this, "Password Link Send Check your Email!", Toast.LENGTH_SHORT).show();
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(ForgotPassword.this, "Error! Email not sent as "+e.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              });
          }
      });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this,SignIn.class));
            }
        });
    }
}