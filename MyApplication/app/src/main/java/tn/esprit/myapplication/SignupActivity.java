package tn.esprit.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import tn.esprit.myapplication.database.AppDataBase;
import tn.esprit.myapplication.database.UserEvenement;

public class SignupActivity extends AppCompatActivity {
    EditText username, password, repassword, phone, email;
    Button bntsinup;
   AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        phone = (EditText) findViewById(R.id.id_phone);
        email = (EditText) findViewById(R.id.id_email);
        username = (EditText) findViewById(R.id.id_username);
        password = (EditText) findViewById(R.id.id_password);
        repassword = (EditText) findViewById(R.id.id_repassword);
        bntsinup = (Button) findViewById(R.id.id_signin);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bntsinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                validationEmailAddress (email);
              //  int phon= Integer.parseInt(PhoneNumberUtils.formatNumber(phone.getText().toString()));

                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignupActivity.this, "Please enter all the fields",Toast.LENGTH_SHORT).show();
                else {

                    if (pass.equals(repass)) {
                        SaveNewUser(user, pass, mail, 25810661);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(SignupActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                    }




            }

        });


    }


    public boolean validationEmailAddress (EditText email){
        String emailInput = email.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(this, "Email Validated Seccessfully", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Ivalid Email Address!!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveNewUser(String username,String password,String email,int phone){
        AppDataBase db= AppDataBase.getDbInstance(this.getApplicationContext());
        UserEvenement user= new UserEvenement();
        user.setUsername(username);

        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        db.userEvenementDao().insertOne(user);
        finish();
    }

}



