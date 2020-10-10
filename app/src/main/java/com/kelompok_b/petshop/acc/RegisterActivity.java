package com.kelompok_b.petshop.acc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
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

    private TextInputEditText input_Email, input_password;
    private MaterialButton btn_register;
    private MaterialTextView text_login;

    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    String CHANNEL_ID = "Channel 1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input_Email = findViewById(R.id.input_email_register);
        input_password = findViewById(R.id.input_password_register);
        btn_register = findViewById(R.id.btn_register);
        text_login = findViewById(R.id.text_login);


        firebaseAuth = firebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_Email.getText().toString().trim();
                String password = input_password.getText().toString().trim();

//                if (firebaseAuth.getCurrentUser() != null){
//                    startActivity( new Intent(MainActivity.this,LoginActivity.class));
//                    finish();
//                }
                if (TextUtils.isEmpty(email)){
//                    input_Email.setError("Input Email");
                    Toast.makeText(RegisterActivity.this, "Email Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
//                    input_password.setError("Input Password");
                    Toast.makeText(RegisterActivity.this, "Password Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6){
//                    input_password.setError("Password too short");
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            firebaseAuth.signOut();
                            createNotificationChannel();
                            addNotification();
                            Toast.makeText(RegisterActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed, " +task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int important = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name, important);
            channel.setDescription(description);
            //Register the channel with the system; you can't change the importance
            //Or other notification behavior after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification() {
        //konstruktor NotificationCompat.Builder harus diberi CHANNEL_ID untuk api level 26+
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("Hello")
                .setContentText("Welcome Back, Please Enjoy your stay...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //Membuat intent yang menampilkan notifikasi
        Intent notificationIntent = new Intent(this, RegisterActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        //Menampilkan notifikasi
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}