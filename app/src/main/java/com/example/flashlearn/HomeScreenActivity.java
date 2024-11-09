package com.example.flashlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlashcardAdapter adapter;
    private List<Flashcard> flashcardList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab_add);
        flashcardList = new ArrayList<>();
        adapter = new FlashcardAdapter(flashcardList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadFlashcards();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreenActivity.this, FlashcardCreationActivity.class);
            startActivity(intent);
        });
    }

    private void loadFlashcards() {
        db.collection("flashcards").get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                flashcardList.clear();
                for (var document : task.getResult()) {
                    Flashcard flashcard = document.toObject(Flashcard.class);
                    flashcardList.add(flashcard);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
