package ar.edu.davinci.a262_am_av;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

import kotlin.Pair;
import kotlin.TuplesKt;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    private String validatedEmail (String email) {
        boolean validated = true;
        return validated ? email : "demo@davinci.edu.ar";
    }
    public void loginAction(View v) {
        EditText loginEmailInput = findViewById(R.id.loginEmail);
        EditText loginPasswordInput = findViewById(R.id.loginPassword);

        String loginEmail = validatedEmail(loginEmailInput.getText().toString());
        String loginPassword = validatedPassword(loginPasswordInput.getText().toString());

        this.mAuth
                .signInWithEmailAndPassword(loginEmail, loginPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete() && task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    private String validatedPassword(String password) {
        boolean validated = true;
        return validated ? password : "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAuth = FirebaseAuth.getInstance();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}