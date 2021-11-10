package tn.esprit.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



import tn.esprit.myapplication.database.AppDataBase;
import tn.esprit.myapplication.database.DataConverter;
import tn.esprit.myapplication.database.Evenement;


public class signupEvenementActivity extends AppCompatActivity {
    ImageView mImageView;
    Button mChooseBtn,bntsinup;
    Bitmap BmpImage=null;
    AppDataBase db;
    EditText Eventname,lieu,description,nombre,image,nombrePersone;
    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_evenement);
        Eventname =(EditText) findViewById(R.id.id_EvenementName);
        lieu =(EditText) findViewById(R.id.id_lieu);
        description =(EditText) findViewById(R.id.id_Description);
        nombre =(EditText) findViewById(R.id.id_password);
        image=(EditText) findViewById(R.id.id_nombredepersone);
        bntsinup =(Button) findViewById(R.id.id_signin);
        nombrePersone=(EditText) findViewById(R.id.id_nombredepersone);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bntsinup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String Evenementname = Eventname.getText().toString();

                //int phon= Integer.parseInt(PhoneNumberUtils.formatNumber(phone.getText().toString()));

                String place= lieu.getText().toString();
                String desc = description.getText().toString();
                String img= image.getText().toString();
                int nombreP= Integer.parseInt(nombrePersone.getText().toString());

                if(Evenementname.equals("")||place.equals("")||desc.equals(""))

                    Toast.makeText(signupEvenementActivity.this, "Please enter all the fields",Toast.LENGTH_SHORT).show();

                else{
                   SaveEvent (Evenementname,place,nombreP,desc);



/*
                    if(SaveEvent==true){
                        Toast.makeText(signupEvenementActivity.this,"Registred successfully",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getApplicationContext(),SignupActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(signupEvenementActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
*/




                }

                Intent intent = new Intent(getApplicationContext(), signupEvenementActivity.class);
                startActivity(intent);
            }



        });










        mImageView=findViewById(R.id.imageView);
        mChooseBtn= findViewById(R.id.button_choose_image);
        mChooseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);

                    }
                    else{
                        PickImageFromGallery();

                    }
                }
                else{
                    PickImageFromGallery();

                }
            }
        });
    }

    private void PickImageFromGallery() {
       Intent intent=new Intent(Intent.ACTION_PICK);
       intent.setType("image/*");



        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    PickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE_PICK_CODE:

        if (resultCode == RESULT_OK) {
           // BmpImage = (Bitmap) data.getExtras().get("data");





    mImageView.setImageURI(data.getData());



            // assert data != null;
            // BmpImage = (Bitmap) data.getExtras().get("data");

        }
        break;
    }


    }




    private void SaveEvent(String NameEvenement,String lieu, int nombres, String description){
        AppDataBase db= AppDataBase.getDbInstance(this.getApplicationContext());
        Bitmap image = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();


        Evenement Event= new Evenement();
        Event.setNameEvenement(NameEvenement);
      Event.setImage(DataConverter.cinverImage2byteArray(image));
        Event.setNombresEvenement(nombres);
        Event.setDescriptionEvenement(description);
        Event.setLieuEvenement(lieu);


        db.EvenementDao().insertOne(Event);
        finish();

    }


}