package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Update;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.makeramen.roundedimageview.RoundedImageView;

import tn.esprit.myapplication.database.AppDataBase;
import tn.esprit.myapplication.database.Evenement;
import tn.esprit.myapplication.database.UserEvenement;
import tn.esprit.myapplication.database.UserEvenementDao;

public class Reservation extends AppCompatActivity {
    ImageView imageEvenement;
    TextView NameEvent,description;
    EditText nombredereservation;
    int id,idUser;

    String dec,Nom;
    byte[] image;
    UserEvenementDao user;
    Button Reservation;
    private ImageView QrImage;
    int nombreP=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


       /* RoundedImageView riv = new RoundedImageView(getApplicationContext());
        riv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        riv.setCornerRadius((float) 10);
        riv.setBorderWidth((float) 2);
        riv.setBorderColor(Color.DKGRAY);
        riv.mutateBackground(true);
        riv.setOval(true);
        riv.setTileModeX(Shader.TileMode.REPEAT);
        riv.setTileModeY(Shader.TileMode.REPEAT);
*/
        QrImage = findViewById(R.id.QRimage);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
NameEvent=findViewById(R.id.EvenementName);
      //  =findViewById(R.id.NombredeReservation);
        imageEvenement =findViewById(R.id.big_image);
        description=findViewById(R.id.Description);
Reservation=findViewById(R.id.buttonReservation);
        nombredereservation=(EditText) findViewById(R.id.NombredeReservation);

        image=getIntent().getByteArrayExtra("image");
        id=getIntent().getIntExtra("idUser",2);
        dec=getIntent().getStringExtra("description");
        Nom=getIntent().getStringExtra("nomEvenement");
        idUser=getIntent().getIntExtra("idUser",0);
        AppDataBase db= AppDataBase.getDbInstance(getApplicationContext());
        UserEvenement userEvent=      db.userEvenementDao().getUser ( idUser);
NameEvent.setText(Nom);
description.setText(dec);
        Evenement event=db.EvenementDao().selectEventByName(Nom);
        imageEvenement.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));

        Reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreP= Integer.parseInt(nombredereservation.getText().toString());


                if(event.NombresEvenement==0){
                    Toast.makeText(Reservation.this, "Sorry Events is Full ", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(nombreP==0){
                        Toast.makeText(Reservation.this, "Vous avez oublié d'entrer le nombre de Participants", Toast.LENGTH_SHORT).show();

                    }
                    else {

                       event.setNombresEvenement(event.getNombresEvenement() - nombreP);
                       db.EvenementDao().updateEvent(event);
                        int d = event.getNombresEvenement();

                        Intent intent = new Intent(getApplicationContext(), QRCode.class);
                        intent.putExtra("UserName", db.userEvenementDao().getUser(idUser).getUsername());
                        intent.putExtra("EventName", Nom);
                        intent.putExtra("NombreReservation", d);


                        startActivity(intent);
                    }
                }

            }
        });

        }

}