package com.personal.proyectodba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView tvemail,tvpassword;
    private Button btnLogin;

    private String correo = "";
    private String contraseña = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        tvemail = (TextView)findViewById(R.id.editTexEmail);
        tvpassword = (TextView)findViewById(R.id.editTexPassword);
        btnLogin = (Button) findViewById(R.id.btn_signIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = tvemail.getText().toString().trim();
                contraseña = tvpassword.getText().toString();
                //String de como deberia ser el correo
                String compruebaCorreo = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";
                //Comprueba si los campos estan vacios
                if(!correo.isEmpty() && !contraseña.isEmpty()){
                    //Comprueba de que sea de la forma del correo
                    if (!correo.matches(compruebaCorreo))
                    {
                        Toast.makeText(Login.this, "Por favor, introduce bien su email", Toast.LENGTH_LONG).show();
                    }else{
                        loginUser();
                    }

                }else{
                    Toast.makeText(Login.this, "complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this,MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(Login.this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
    }
}