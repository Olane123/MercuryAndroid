package com.olaneteam.MercuryAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView emailInput = findViewById(R.id.input_email_register);
        TextView passwordInput = findViewById(R.id.input_password_register);
        TextView passwordInputConfirm = findViewById(R.id.input_password_confirm);
        Button registerConfirm = findViewById(R.id.btn_register_confirm);
        Button switchPage = findViewById(R.id.btn_switchPageToLogin);

        registerConfirm.setOnClickListener(v -> {
            if (passwordInput.getText().toString() == passwordInputConfirm.getText().toString())
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailInput.getText().toString(), passwordInputConfirm.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
                                HashMap<String, String> userinfo =  new HashMap<>();
                                userinfo.put("email", emailInput.getText().toString());
                                FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                        .setValue(userinfo);
                            }
                        });
            }
        });

        switchPage.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
}