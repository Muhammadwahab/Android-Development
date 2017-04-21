package com.example.abdull.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity implements View.OnClickListener {

    Button signup;
    EditText email,password,confirmPassword;
     FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_signup);
        signup= (Button) findViewById(R.id.sigupButton);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        confirmPassword= (EditText) findViewById(R.id.confirm_password);
        mAuth= FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                }
                // ...
            }
        };


        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==signup.getId())
        {
          //  startActivity(new Intent(getApplicationContext(),start_login.class));
            //finish();
            boolean isValidate=isConfirm();
            final String emails = email.getText().toString();
            final String passwords = password.getText().toString();
            if(isValidate)
            {
                mAuth.createUserWithEmailAndPassword(emails, passwords)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("task", "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(signup.this, "authentication failed"+emails+passwords,
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
            else
            {

                Toast.makeText(getApplicationContext(),"PAssword Mismath",Toast.LENGTH_LONG).show();
            }

        }

    }

    public boolean isConfirm() {
        String Password=password.getText().toString();
        String ConfirmPassword=confirmPassword.getText().toString();
        if(Password.equals(ConfirmPassword))
        {
            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
