package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import tn.esprit.myapplication.database.AppDataBase;
import tn.esprit.myapplication.database.Evenement;

public class HomePage extends AppCompatActivity implements EvenementListener {
private Button buttonAdd;
    AppDataBase db;
int  id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhomepage);
        RecyclerView EvenementRecyclerView=findViewById(R.id.EventrecyclerView);
       // ListView listView=(ListView) findViewById(R.id.EventrecyclerView);
        AppDataBase db= AppDataBase.getDbInstance(this.getApplicationContext());
buttonAdd=findViewById(R.id.addEvenement);
        List<Evenement>dolist=  db.EvenementDao().getAllEvenement();
        System.out.println(db.EvenementDao().getAllEvenement());
       final EvenementAdapter evenementAdapter=new EvenementAdapter(dolist,this);
       EvenementRecyclerView.setAdapter(evenementAdapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=getIntent().getIntExtra("idUser",1);
              Evenement selectedEvenement= evenementAdapter.getSlectedEvenement();
                StringBuilder evenement=new StringBuilder();
                Intent intent = new Intent(getApplicationContext(), Reservation.class);
                intent.putExtra("image",selectedEvenement.getImage());
                intent.putExtra("nomEvenement",selectedEvenement.getNameEvenement());
                intent.putExtra("description",selectedEvenement.getDescriptionEvenement());
                intent.putExtra("idUser",id);

                startActivity(intent);

                Toast.makeText(HomePage.this,evenement.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onEvnementAction(Boolean isSelected) {
        if(isSelected){
            buttonAdd.setVisibility(View.VISIBLE);


        }else{
            buttonAdd.setVisibility(View.GONE);
        }

    }
}