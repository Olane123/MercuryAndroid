package com.olaneteam.MercuryAndroid;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView emailInput = findViewById(R.id.input_email_login);
        TextView passwordInput = findViewById(R.id.input_password_login);
        Button loginConfirm = findViewById(R.id.btn_login_confirm);
        Button switchPage = findViewById(R.id.btn_switchPageToRegister);

        loginConfirm.setOnClickListener(v -> {
            if (emailInput.getText().toString().isEmpty())
            {
                Toast.makeText(LoginActivity.this, "Не заполнены все поля", Toast.LENGTH_SHORT).show();
            }
            else
            {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailInput.getText().toString(),passwordInput.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Заход на аккаунт", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        switchPage.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}