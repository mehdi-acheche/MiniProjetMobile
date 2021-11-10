package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminHomePage extends AppCompatActivity {
    ImageView bntsignup,bntChekAllUser,bntchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        bntchart=(ImageView) findViewById(R.id.chart);
        bntChekAllUser=(ImageView) findViewById(R.id.bt_user);
        bntChekAllUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdminHomePage.this,AllUser.class);
                startActivity(intent);
            }
        });
        bntchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdminHomePage.this,chart.class);
                startActivity(intent);
            }
        });




        bntsignup= (ImageView) findViewById(R.id.bt_register);

        bntsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AdminHomePage.this,signupEvenementActivity.class);
                startActivity(i);
            }
        });


    }


}