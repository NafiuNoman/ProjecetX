package com.example.projectx_foodmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText email, password;
     Button btnLogin;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        email = findViewById(R.id.IdEditTxtEmailLogIn);
        password = findViewById(R.id.IdEditTxtPasswordLogIn);

        btnLogin = findViewById(R.id.IdBtnLogIn);

        progressBar = findViewById(R.id.LogInprogressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    public void logInWork(View view) {

        String logInEmail = email.getText().toString().trim();
        String logInPassword = password.getText().toString().trim();


        if (logInEmail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(logInEmail).matches()) {
            email.setError("Invalid Email Id");
            email.requestFocus();
            return;

        }

        if (logInPassword.isEmpty()) {
            password.setError("Password is Required");
            password.requestFocus();

            return;
        }

        if (logInPassword.length() < 6) {
            password.setError("Min length should be 6 characters");
            password.requestFocus();

            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(logInEmail, logInPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d( "onComplete: ",""+task.isSuccessful()+".."+logInEmail+".."+logInPassword);
                        if (task.isSuccessful()) {


                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            DatabaseReference databaseReference = database.getReference(mAuth.getUid());
                            Log.d( "onComplete: ",""+mAuth.getUid());


                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    Log.d( "onDataChange: ","its inside");

//                                    ClsUserDetails clsUserDetails = snapshot.getValue(ClsUserDetails.class);

                                    String email =  mAuth.getCurrentUser().getEmail();
                                    String name =  mAuth.getCurrentUser().getDisplayName();

                                    Log.d("name",email);
                                    Log.d("name2",name);

                                    Intent intent = new Intent(LogIn.this, MainActivity.class);

                                    intent.putExtra("username",name);
                                    intent.putExtra("useremail",email);

                                    startActivity(intent);


//
                                    Toast.makeText(LogIn.this, " Successfully Logged in", Toast.LENGTH_SHORT).show();

                                    progressBar.setVisibility(View.GONE);


                                    password.setText("");

                                    finish();
//
//                                    startActivity(intent);


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                    Log.d( "onCancelled: ",""+error.getMessage());

                                }
                            });


                        }

                        else {
                            email.setError("");

                            password.setError("");

                            Toast.makeText(LogIn.this, "Invalid password/email", Toast.LENGTH_LONG).show();

                        }

                    }
                });


    }




    public void btnRegister(View view) {

        startActivity(new Intent(LogIn.this, Register.class));
    }
}