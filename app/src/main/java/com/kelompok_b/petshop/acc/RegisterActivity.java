package com.kelompok_b.petshop.acc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kelompok_b.petshop.R;

public class RegisterActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton_gender;
    ProgressBar progressBar;
    MaterialButton btn_register;
    TextInputEditText input_email, input_password, input_fullname;
    TextView text_login;

    //FIREBASE
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_register = findViewById(R.id.btn_register);
        text_login = findViewById(R.id.text_login);

        input_email = findViewById(R.id.input_email_register);
        input_password = findViewById(R.id.input_password_register);
        firebaseAuth = firebaseAuth.getInstance();
        input_fullname = findViewById(R.id.input_fullname_register);
        RadioGroup radioGroup = findViewById(R.id.radioGroup_register);

        // memanggil fungsi
        checkGender();
        registration();
        backButton();
    }

    public void backButton() {
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    public void checkGender() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkRadio = radioGroup.getCheckedRadioButtonId();
                radioButton_gender = findViewById(checkRadio);

//                Toast.makeText(this,"Select Radio Button " + radioButton_gender.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void registration() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString().trim();
                String password = input_password.getText().toString().trim();
                String fullname = input_fullname.getText().toString().trim();
                if (TextUtils.isEmpty(fullname)) {
                    input_email.setError("Input Fullname");
                    Toast.makeText(RegisterActivity.this, "Fullname Is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    input_email.setError("Input Email");
                    Toast.makeText(RegisterActivity.this, "Email Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    input_password.setError("Input Password");
                    Toast.makeText(RegisterActivity.this, "Password Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    input_password.setError("Password too short");
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            firebaseAuth.signOut();
                            Toast.makeText(RegisterActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed, " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


}

