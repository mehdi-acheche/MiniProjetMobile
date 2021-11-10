package tn.esprit.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.AppDataBase;
import tn.esprit.myapplication.database.UserEvenement;

public class AllUser extends AppCompatActivity {
    private UserListAdapter userListAdapter;
    ArrayList<UserEvenement> userList= new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);

        initRecyclerView();
        loadUserList();



    }

    private void initRecyclerView(){

        RecyclerView recyclerView= findViewById(R.id.EventrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        userListAdapter= new UserListAdapter(this);

        recyclerView.setAdapter(userListAdapter);



        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    }
    private void loadUserList(){
        AppDataBase db= AppDataBase.getDbInstance(this.getApplicationContext());
        List<UserEvenement> userList=db.userEvenementDao().getAlluser();
        userListAdapter.SetUserList(userList);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==100){
            loadUserList();
        }


        super.onActivityResult(requestCode, resultCode, data);

       }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


            AppDataBase db= AppDataBase.getDbInstance(getApplicationContext());
            UserEvenement User;

        //int id=    userList.get(viewHolder.getAdapterPosition()).getUid();

            int d= viewHolder.getAdapterPosition();
            List<UserEvenement> userList=db.userEvenementDao().getAlluser();
            System.out.println(userList.get(0));
            System.out.println(d);
            System.out.println("ddddddddddddddddddddddddddddddddddd");
           //System.out.println(id);
           User= db.userEvenementDao().getUser(d);
           User =userList.get(d);
            db.userEvenementDao().delete(User);

           //User=userList.get(viewHolder.getAdapterPosition());
            initRecyclerView();
            loadUserList();




        }
    };
    @SuppressLint("NotifyDataSetChanged")
    private void deleteUser(UserEvenement User) {

        AppDataBase db= AppDataBase.getDbInstance(getApplicationContext());

       db.userEvenementDao().delete(User);
        //List<UserEvenement> userList=db.userEvenementDao().getAlluser();


    }

}





