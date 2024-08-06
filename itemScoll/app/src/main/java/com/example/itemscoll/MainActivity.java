package com.example.itemscoll;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AnimalAdaptor.MyClickInterface {
    RecyclerView recyclerView;
    ArrayList<Animal> animals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        animals = new ArrayList<>();

        animals.add(new Animal("Dolphin",R.drawable.dolphin));
        animals.add(new Animal("Lion",R.drawable.lion));
        animals.add(new Animal("Owl",R.drawable.owl));
        animals.add(new Animal("Owl",R.drawable.owl));
        animals.add(new Animal("Owl",R.drawable.owl));
        animals.add(new Animal("Owl",R.drawable.owl));
        animals.add(new Animal("Owl",R.drawable.owl));
        animals.add(new Animal("Owl",R.drawable.owl));


        AnimalAdaptor animalAdapter = new AnimalAdaptor(animals,this,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(animalAdapter);

    }

    @Override
    public void onItemClick(int postionOfTheAnimal) {
        Toast.makeText(this, "Clicked "+animals.get(postionOfTheAnimal).getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,Animallinfo.class);
        intent.putExtra("image",animals.get(postionOfTheAnimal).getImage());
        intent.putExtra("name",animals.get(postionOfTheAnimal).getName());
        startActivity(intent);
    }
}