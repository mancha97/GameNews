package com.valle00018316.gamenews;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.valle00018316.gamenews.GameNApi.Api;
import com.valle00018316.gamenews.GameNApi.Deserializer.Token;

import java.net.SocketTimeoutException;

import butterknife.ButterKnife;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";


    public boolean booly;
    ProgressDialog progressDialog;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void login() {
        Log.d(TAG, "Login");



        _loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

       validate();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed

                        // onLoginFailed();

                    }
                }, 3000);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_SIGNUP) {
//            if (resultCode == RESULT_OK) {
//
//                // TODO: Implement successful signup logic here
//                // By default we just finish the Activity and log them in automatically
//                this.finish();
//            }
//        }
//    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Log.d(TAG, "onLoginSuccess: Vacil");
        progressDialog.dismiss();
//        finish();
    }

    public void onLoginFailed() {


        _loginButton.setEnabled(true);
        progressDialog.dismiss();

    }

    public void validate() {


        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new Token()).create();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Api.END_POINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();

        Api apiG = retrofit.create(Api.class);

        Call<String> stringCall = apiG.login(email, password);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.code());
                if (response.isSuccessful()){
                     setPreferences(response.body());
                     onLoginSuccess();
                     Toast.makeText(LoginActivity.this, "LOGUEADO", Toast.LENGTH_SHORT).show();
                     starthome();

                }else if (response.code() == 401) {
                    onLoginFailed();

                    Toast.makeText(LoginActivity.this, "¡Ups! \n Usuario o Password incorrecto.", Toast.LENGTH_SHORT).show();
                }else {
                    onLoginFailed();
                    Toast.makeText(LoginActivity.this, "¡Ups! \n Algo salio mal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (t instanceof SocketTimeoutException){
                   onLoginFailed();
                    Toast.makeText(LoginActivity.this, "¡Error! \n Timed out", Toast.LENGTH_SHORT).show();
                }else if (t instanceof Exception){
                    onLoginFailed();
                    Toast.makeText(LoginActivity.this, "¡Error! \n Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void setPreferences(String token){
        SharedPreferences sharedPreferences = this.getSharedPreferences("logbait", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("logbait", token);
        editor.commit();
    }

    public void starthome(){
        Intent homeIntent= new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }



}