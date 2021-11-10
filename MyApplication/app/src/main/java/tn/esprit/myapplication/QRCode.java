package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCode extends AppCompatActivity {
    private EditText QrText;
    private ImageView QrImage;
    String nomEvent,nomUser;
    int nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);


        QrImage = findViewById(R.id.QRimage);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            nomEvent=getIntent().getStringExtra("EventName");
            nomUser=getIntent().getStringExtra("UserName");
            nombre=getIntent().getIntExtra("NombreReservation",1);
            BitMatrix bitMatrix = qrCodeWriter.encode("Nom d'evenement: "+nomEvent+"\n Nom du client: "+nomUser+"\n Nombre de Reservation"+nombre, BarcodeFormat.QR_CODE, 200, 200);
            Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x<200; x++){
                for (int y=0; y<200; y++){
                    bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);
                }
            }
            QrImage.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();


        }


    }

}