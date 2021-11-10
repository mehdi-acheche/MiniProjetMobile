package tn.esprit.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tn.esprit.myapplication.database.AppDataBase;


public class Reinitialiser_mdp extends AppCompatActivity {
    Button  btn_confirme;
    TextView input_code;
    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reinitialiser_mdp);
// déclarer les input
        btn_confirme = findViewById(R.id.button_send2);
        input_code = findViewById(R.id.id_Code);



        //récuperer email a partie de activity retrouver_compte
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String number =intent.getStringExtra("number");

//vérifier le code
        btn_confirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (input_code.getText().toString().isEmpty()) {
                    alert("saisir votre code");
                } else if (input_code.getText().toString().equals(number)) {
                    alert("your code is correct");
                    Intent intent = new Intent(Reinitialiser_mdp.this,ChangePassword.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
                else
                {
                    alert("verifie your code");
                }


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