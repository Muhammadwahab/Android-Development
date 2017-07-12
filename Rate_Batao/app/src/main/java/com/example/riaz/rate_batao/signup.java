package com.example.riaz.rate_batao;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener {


    EditText email,password,confirmpasword;
    Button signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        confirmpasword= (EditText) findViewById(R.id.confirmpassword);
        signup= (Button) findViewById(R.id.signup);
        signup.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(password.getText().toString().equals(confirmpasword.getText().toString()))
        {
            final String emails = email.getText().toString();
            final String passwords = password.getText().toString();
            mAuth.createUserWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("sign up", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "failed",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Invalid password match",Toast.LENGTH_LONG).show();
        }

    }
}
