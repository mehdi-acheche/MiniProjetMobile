package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tn.esprit.myapplication.database.AppDataBase;
import tn.esprit.myapplication.database.UserEvenement;
import tn.esprit.myapplication.database.UserEvenementDao;

public class Retrouver_compte extends AppCompatActivity {
    Button button_back,btn_chercher;
    TextView input_email;
    AppDataBase db;
    String output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrouver_compte);

        //déclarer les inputs
        input_email = findViewById(R.id.id_email1);
        btn_chercher = findViewById(R.id.button_send1);





        //vérifier email existe ou pas

        btn_chercher.setOnClickListener(view -> {
            AppDataBase db= AppDataBase.getDbInstance(getApplicationContext());
            if(db.userEvenementDao().existe_email(input_email.getText().toString()) ==null)
            {
                Log.d("**************","********************************");
                Log.d("user est",input_email.getText().toString());
                Log.d("**************","********************************");
                alert("email n'existe pas");
            }
            else
            {
                /* Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                 intent.setType("text/plain");
                 intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
                 intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                 startActivity(intent);*/


                final String username = "moncef.mansour@esprit.tn";
                final String password = "s d t b t b d h l g g n a b y w";

                Properties properties  = new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");
                Session session =Session.getInstance(properties,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });

                try {
                    final Random numRandom = new Random();
                    int n = numRandom.nextInt((10000 - 1000) +1) + 1000;
                    output=String.valueOf(n);
                    Message msg = new MimeMessage( session );

                    msg.setFrom(new InternetAddress(username));
                    msg.setRecipients( javax.mail.Message.RecipientType.TO, InternetAddress.parse(input_email.getText().toString()) );
                    msg.setSubject("YOUR CODE" );
                    msg.setText( "your code is "+ output);
                    Transport.send(msg);

                }catch (MessagingException e)
                {
                    throw new RuntimeException(e);

                }
                /*db.userEvenementDao().update_password(output,input_email.getText().toString());
                Toast.makeText(Retrouver_compte.this,"check your email ",Toast.LENGTH_LONG).show();
                UserEvenement u = db.userEvenementDao().existe_email(input_email.getText().toString());
                Log.d("user est modifier",u.toString());*/
                Intent intent = new Intent(Retrouver_compte.this,Reinitialiser_mdp.class);
                intent.putExtra("email",input_email.getText().toString());
                intent.putExtra("number",output);
                startActivity(intent);
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);








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