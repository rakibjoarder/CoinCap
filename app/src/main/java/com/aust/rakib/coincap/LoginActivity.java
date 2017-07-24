package com.aust.rakib.coincap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText passet;
    EditText useret;
    TextView signuptv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passet=(EditText) findViewById(R.id.password_edittext);
        useret=(EditText) findViewById(R.id.username_edittext);
        signuptv=(TextView) findViewById(R.id.signuptext);

        signuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent RegisterIntert=new Intent(LoginActivity.this,RegisterActivity.class);
               startActivity(RegisterIntert);
            }
        });


    }
    public void LogIn(View view)
    {
        String etusername=useret.getText().toString();
        String etpassword=passet.getText().toString();
        String username="rakibjoarder";
        String password="123456";

        if(etusername.equals(username) && etpassword.equals(password))
        {
            Toast.makeText(this,"loged In", Toast.LENGTH_SHORT).show();
        }
        else if(!etusername.equals(username))
        {
            useret.setError("The user name that you've entered doesn't match any account");
        }
        else if(!etpassword.equals(password))
        {
            passet.setError("The password that you've entered is incorrect.");
        }
    }


}
