package com.example.flashlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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
        loadFlashcards();  // Load flashcards when activity is created

        // Open FlashcardCreationActivity to add new flashcards
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreenActivity.this, FlashcardCreationActivity.class);
            startActivity(intent);
        });
    }

    // Method to load flashcards from Firestore
    private void loadFlashcards() {
        db.collection("flashcards")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        flashcardList.clear();  // Clear existing data before adding new ones
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Flashcard flashcard = document.toObject(Flashcard.class);
                            if (flashcard != null) {
                                flashcardList.add(flashcard);
                            }
                        }
                        adapter.notifyDataSetChanged();  // Notify the adapter to refresh the RecyclerView
                    } else {
                        Toast.makeText(HomeScreenActivity.this, "Error fetching flashcards.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
