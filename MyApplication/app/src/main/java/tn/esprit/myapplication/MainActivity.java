package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import tn.esprit.myapplication.database.AppDataBase;
import tn.esprit.myapplication.database.UserEvenement;
import tn.esprit.myapplication.database.UserEvenementDao;

public class MainActivity extends AppCompatActivity {
Button bntLogin;
Button bntsignup,button_forgot_password;
EditText username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.id_username);
        password=(EditText) findViewById(R.id.id_password);
        button_forgot_password=(Button) findViewById(R.id.button_forgot_password) ;
//db=new DBHelper(this);
        button_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(MainActivity.this,Retrouver_compte.class);
                startActivity(intent1);
            }
        });


            bntsignup= (Button) findViewById(R.id.button_signup);
        bntsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
        bntLogin=(Button) findViewById(R.id.id_signin);
        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase db= AppDataBase.getDbInstance(getApplicationContext());

                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")||pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all  the fields", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(username);
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(password);

                }

                else{



                        if(user.equals("admin")){
                            Intent intent= new Intent(getApplicationContext(), AdminHomePage.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Sing in seccessfull", Toast.LENGTH_SHORT).show();
                        }
                        else{

                              UserEvenement userEvent=      db.userEvenementDao().Login(user,pass);
                                    if(userEvent==null){
                                        Toast.makeText(getApplicationContext(), "Invalid !!!!!", Toast.LENGTH_SHORT).show();
                                        YoYo.with(Techniques.Shake)
                                                .duration(700)
                                                .repeat(1)
                                                .playOn(username);
                                        YoYo.with(Techniques.Shake)
                                                .duration(700)
                                                .repeat(1)
                                                .playOn(password);
                                    }
                                    else {

                                        Intent intent = new Intent(getApplicationContext(), HomePage.class);

                                        intent.putExtra("idUser",userEvent.getUid());
                                        startActivity(intent);
                                    }
                        }
                    }
                }

        });
    }
}