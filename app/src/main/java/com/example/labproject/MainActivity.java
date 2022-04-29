package com.example.labproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText mail,pswrd;
    Button login;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        fAuth=FirebaseAuth.getInstance();

        mail=findViewById(R.id.mail);
        pswrd=findViewById(R.id.Password);
        login=findViewById(R.id.button);

       /* checkfield(mail);
        checkfield(pswrd);

        if (valid){
            fAuth.createUserWithEmailAndPassword(mail.getText().toString(),pswrd.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(MainActivity.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                }
            });
        }*/

        login.setOnClickListener(view -> {loginUser(); });

    }

    private void loginUser() {
        String email=mail.getText().toString();
        String password=pswrd.getText().toString();

        if(TextUtils.isEmpty(email)){
            pswrd.setError("Mail may not be Empty");
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            pswrd.setError("Password is necessary");
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "login Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}