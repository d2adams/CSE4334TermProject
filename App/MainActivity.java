package com.example.musicrecommender;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mLogin;
    private UserInfo usr;
    private EditText userNameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogin = findViewById(R.id.Main_Login_Buttonid);
        usr = new UserInfo();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.Main_Login_Buttonid:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);


        }
    }
}
