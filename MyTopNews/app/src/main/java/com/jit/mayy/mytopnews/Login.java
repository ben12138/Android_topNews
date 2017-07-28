package com.jit.mayy.mytopnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jit.mayy.mytopnews.fragment.Page4Fragment;
import com.jit.mayy.mytopnews.service.UserService;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private void findViews() {
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        register=(Button) findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                Log.i("TAG",name+"_"+pass);
                UserService uService=new UserService(Login.this);
                boolean flag=uService.login(name, pass);
                if(flag){
                    Log.i("TAG","登录成功");
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_LONG).show();
                    //跳转到page4
                    Intent show = new Intent(Login.this,MainActivity.class);
                    show.putExtra("username",name);
                    startActivity(show);
                }else{
                    Log.i("TAG","登录失败");
                    Toast.makeText(Login.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
