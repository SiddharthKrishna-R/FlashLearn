package com.example.flashlearn;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class FlashcardDetailsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView questionTextView;
    private TextView answerTextView;
    private String flashcardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_details);

        // Initialize views
        questionTextView = findViewById(R.id.questionTextView);
        answerTextView = findViewById(R.id.answerTextView);

        // Get the flashcard ID from the intent
        flashcardId = getIntent().getStringExtra("flashcardId");

        // Fetch the flashcard details from Firestore
        if (flashcardId != null) {
            db = FirebaseFirestore.getInstance();
            fetchFlashcardDetails(flashcardId);
        } else {
            Toast.makeText(this, "Flashcard ID is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchFlashcardDetails(String flashcardId) {
        db.collection("flashcards")
                .document(flashcardId)  // Use the provided flashcard ID
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Flashcard flashcard = documentSnapshot.toObject(Flashcard.class);
                        if (flashcard != null) {
                            questionTextView.setText(flashcard.getQuestion());
                            answerTextView.setText(flashcard.getAnswer());
                        } else {
                            Toast.makeText(FlashcardDetailsActivity.this, "Flashcard not found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(FlashcardDetailsActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(FlashcardDetailsActivity.this, "Error fetching details", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                });
    }
}
