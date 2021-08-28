package com.example.projectx_foodmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {


    EditText sEmail, sPassword,sName;
    Button registerBtn;

    private FirebaseAuth mAuth;

    ProgressBar progressBar;

    UserModel model;
    ImageView logoImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        sEmail = findViewById(R.id.IdEditTxtEmailRegs);
        sPassword = findViewById(R.id.IdEditTextPasswordRegs);
        registerBtn = findViewById(R.id.IdBtnRegisterUser);
        sName = findViewById(R.id.IdEditTextNameRegs);
        progressBar = findViewById(R.id.RegisterProgressBar);

        logoImage = findViewById(R.id.IdRegisterLogo);


        mAuth = FirebaseAuth.getInstance();

    }

    public void DoRegisterUser(View view) {



        String email,password,name;

        password = sPassword.getText().toString().trim();
        email = sEmail.getText().toString().trim();
        name = sName.getText().toString().trim();

        if(name.isEmpty())
        {
            sName.setError("Name is required");
            sName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            sEmail.setError("Email is required");
            sEmail.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            sEmail.setError("Invalid Email Id");
            sEmail.requestFocus();
            return;

        }


        if (password.isEmpty()) {
            sPassword.setError("Password is Required");
            sPassword.requestFocus();

            return;
        }

        if (password.length() < 6) {
            sPassword.setError("Min length should be 6 characters");
            sPassword.requestFocus();

            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            model = new UserModel(name,email,password);

                            FirebaseUser user = mAuth.getCurrentUser();


                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            user.updateProfile(profileUpdates);
                            finish();




//                            String email = user.getEmail();



                            Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Register.this,MainActivity.class);

                            intent.putExtra("username",name);
                            intent.putExtra("useremail",email);

                            startActivity(intent);

                            progressBar.setVisibility(View.GONE);


                        } else {

                            Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();


                        }

                    }
                });








    }
}