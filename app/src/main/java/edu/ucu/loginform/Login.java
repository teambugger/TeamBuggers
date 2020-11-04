package edu.ucu.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper db;
    EditText Username,Password;
    Button BtnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        Username=(EditText)findViewById(R.id.usname);
        Password=(EditText)findViewById(R.id.uspass);
        BtnLogin=(Button)findViewById(R.id.btnlogin);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Susername = Username.getText().toString();
                String Spassword = Password.getText().toString();

                Boolean Chkuserpass = db.usernamepassword(Susername, Spassword);

                if (Chkuserpass==true){
                    Toast.makeText(getApplicationContext()," Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);

                    if (Susername.equals("")||Spassword.equals("")){
                        Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void reg(View view) {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }
}