package edu.ucu.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText Username,Pass,CPass, Name, Address, Status;
    Button btnregister, btnlogin;
    RadioGroup Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        Username=(EditText)findViewById(R.id.username);
        Pass=(EditText)findViewById(R.id.pass);
        CPass=(EditText)findViewById(R.id.cpass);
        Name =(EditText)findViewById(R.id.name);
        Address=(EditText)findViewById(R.id.address);
        Status=(EditText)findViewById(R.id.status);
        Gender = findViewById(R.id.gender);

        btnlogin=(Button)findViewById(R.id.buttonlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);

            }
        });

        btnregister=(Button)findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString();
                String password = Pass.getText().toString();
                String cpassword = CPass.getText().toString();
                String name = Name.getText().toString();
                String address = Address.getText().toString();
                String status = Status.getText().toString();
                RadioButton checkedBtn = findViewById(Gender.getCheckedRadioButtonId());
                String genderValue = checkedBtn.getText().toString();

                if(username.equals("")||password.equals("")||cpassword.equals("")||name.equals("")||address.equals("")||status.equals("")||genderValue.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    //inserting password and confirm password
                    if(password.equals(cpassword)){
                        //confirming user
                        Boolean chkusername = db.chkusername(username);
                        if(chkusername==true){
                            //inserting user
                            Boolean insert = db.insert(username,password,name,address,genderValue,status);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Register Successfully", Toast.LENGTH_SHORT).show();

                                //if done inserting data will display none in register page
                                Username.setText("");
                                Pass.setText("");
                                CPass.setText("");
                                Name.setText("");
                                Address.setText("");
                                Status.setText("");
                                checkedBtn.setText("");
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Username Already exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}