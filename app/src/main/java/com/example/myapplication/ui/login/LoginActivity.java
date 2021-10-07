package com.example.myapplication.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private TextView mensaje;
    private Button login, registrar;
    private LoginViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = new ViewModelProvider(this).get(LoginViewModel.class);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        inicializarVista();

        vm.getMensaje().observe(this, elMensaje -> {
            mensaje.setText(elMensaje);
            mensaje.setVisibility(View.VISIBLE);
            email.setText("");
            password.setText("");

        });
    }

    public void inicializarVista(){
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPass);
        mensaje = findViewById(R.id.tvMensaje);
        login = findViewById(R.id.btIngresar);
        registrar= findViewById(R.id.btRegistrar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.autenticar(email.getText().toString(),password.getText().toString());
                email.setText("");
                password.setText("");
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.aRegistrar();
            }
        });
    }
}
