package com.example.myapplication.ui.registro;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.modelo.Usuario;

import java.util.Observable;


public class RegistroActivity extends AppCompatActivity {

    private EditText dni,apellido,nombre,email,password;
    private Button guardar;
    private RegistroViewModel vm;
    private TextView mensaje;
    private Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Usuario usuarioActual = (Usuario) getIntent().getSerializableExtra("usuario");

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);

        inicializarVista();

        vm.getUsuario().observe(this, usuario ->{
            dni.setText(usuario.getDni() + "");
            apellido.setText(usuario.getApellido());
            nombre.setText(usuario.getNombre());
            email.setText(usuario.getMail());
            password.setText(usuario.getPassword());

        });

        vm.getMensaje().observe(this, elMensaje -> {
            mensaje.setText(elMensaje);
            mensaje.setVisibility(View.VISIBLE);
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.registrar(Long.parseLong(dni.getText().toString()),
                        apellido.getText().toString(),
                        nombre.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        usuarioActual);

                // para cerrar el teclado virtual
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(password.getWindowToken(), 0);

                guardar.setVisibility(View.GONE);
            }
        });
        vm.mostrar(usuarioActual);


    }


        public void  inicializarVista(){
        dni = findViewById(R.id.etDni);
        apellido = findViewById(R.id.etApellido);
        nombre = findViewById(R.id.etNombre);
        email = findViewById(R.id.etMailRegistro);
        password = findViewById(R.id.etPasswordRegistro);
        guardar = findViewById(R.id.btGuardar);
        mensaje = findViewById(R.id.textView_mensaje);




    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        System.exit(0);
    }

}
