package com.hadiseh.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBar;
    private UsersAdapter usersAdapter;
    UsersAdapter.OnUserClickListener onUserClickListener;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_friends);


        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        users = new ArrayList<>();


        onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClicked(int position) {
               /*
                startActivity(new Intent(FriendsActivity.this,MessageActivity.class)
                        .putExtra("username_of_roommate",users.get(position).getUsername())
                        .putExtra("email_of_roommate",users.get(position).getEmail())
                        .putExtra("img_of_roommate",users.get(position).getProfilePicture())
                        .putExtra("my_img",myImageUrl)

                );
                */

            }
        };
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
            }
        });
        swipeRefreshLayout.setRefreshing(false);
        getUsers();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_item_profile){
            startActivity(new Intent(FriendsActivity.this,Profile.class));
        }
        return super.onOptionsItemSelected(item);
    }

    void getUsers()
    {
        users.clear();
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    users.add(dataSnapshot.getValue(User.class));
                }

                usersAdapter = new UsersAdapter(users , FriendsActivity.this ,  onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}