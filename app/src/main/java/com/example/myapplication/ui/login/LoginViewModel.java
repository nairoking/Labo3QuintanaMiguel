package com.example.myapplication.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.modelo.Usuario;
import com.example.myapplication.request.ApiClient;
import com.example.myapplication.ui.registro.RegistroActivity;

public class LoginViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> mensaje;
    private ApiClient apiClient;





    public LoginViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        apiClient=new ApiClient();
    }

    public LiveData<String> getMensaje(){
        if (mensaje == null){
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }
    public void autenticar(String mail, String pass){
        Usuario usuario=apiClient.login(context,mail,pass);
        if (usuario != null){
            mensaje.setValue("");
            Intent intent=new Intent(context, RegistroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usuario", usuario);
            context.startActivity(intent);
        }
        else {
            mensaje.setValue("Email o Password incorrecto ");
        }
    }

    public void aRegistrar(){
        Intent intent=new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
