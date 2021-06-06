package com.eis.traderUI.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eis.traderUI.MainActivity;
import com.eis.traderUI.R;
import com.eis.traderUI.dto.Msg;
import com.eis.traderUI.dto.UserInfo;
import com.eis.traderUI.service.UserService;
import com.eis.traderUI.util.Constant;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "loginActivity";
    private EditText companyInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private Button submitButton;

    private UserService userService;
    private SharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        companyInput=findViewById(R.id.companyInput);
        usernameInput=findViewById(R.id.usernameInput);
        passwordInput=findViewById(R.id.passwordInput);
        submitButton=findViewById(R.id.loginSubmit);
        userService= Constant.RETROFIT.create(UserService.class);

        mySharedPreferences= getSharedPreferences("userInfo",
                Activity.MODE_PRIVATE);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        UserInfo userInfo=new UserInfo(companyInput.getText().toString(),usernameInput.getText().toString(),passwordInput.getText().toString());
        Log.i(TAG,"userInfo: "+userInfo.toString());
        Call<Msg<UserInfo>> blotterCall = userService.login(userInfo);
        blotterCall.enqueue(new Callback<Msg<UserInfo>> () {
            @Override
            public void onResponse(@NonNull Call<Msg<UserInfo>>  call, @NonNull Response<Msg<UserInfo>>  response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "response not success");
                    return;
                }
                if (response.body() == null) {
                    Log.e(TAG, "receive null body");
                    return;
                }
                final UserInfo data= response.body().getData();
                Log.i(TAG,"receive data: "+data.toString());
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putInt("userId", data.getUserId());
                editor.putString("token",data.getToken());
                editor.apply();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(@NonNull Call<Msg<UserInfo>> call, @NonNull Throwable t) {
                Log.e(TAG, "fetch order blotter failed");
                Snackbar.make(submitButton, "接收失败", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
