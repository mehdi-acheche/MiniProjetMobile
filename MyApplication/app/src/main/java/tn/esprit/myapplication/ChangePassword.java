package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import tn.esprit.myapplication.database.AppDataBase;

public class ChangePassword extends AppCompatActivity {
    Button btn_confirme;
    TextView input_pass,input_pass1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent i = getIntent();
        String email = i.getStringExtra("email");
        input_pass = findViewById(R.id.id_pass);
        input_pass1 = findViewById(R.id.id_pass2);
        btn_confirme = findViewById(R.id.button_pass);

        btn_confirme.setOnClickListener(view -> {
            AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
            if(input_pass.getText().toString().equals(input_pass1.getText().toString())&&((!input_pass.getText().toString().isEmpty()) && (!input_pass1.getText().toString().isEmpty())))
            {
                db.userEvenementDao().update_password(input_pass.getText().toString(),email);
                alert("password changed");
                Intent intent = new Intent(ChangePassword.this,MainActivity.class);
                startActivity(intent);
            }
            else if(input_pass.getText().toString().isEmpty() || input_pass1.getText().toString().isEmpty())
            {
                alert("Text empty ,try again");
            }
            else
            {
                alert("confirm passowrd must be the same as password");
            }
        });
    }
    private void alert(String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("Message").setMessage(message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }
}