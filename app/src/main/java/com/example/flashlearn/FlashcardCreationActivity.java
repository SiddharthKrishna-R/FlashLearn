package com.example.flashlearn;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class FlashcardCreationActivity extends AppCompatActivity {

    private EditText questionEditText, answerEditText;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_creation);

        questionEditText = findViewById(R.id.questionEditText);
        answerEditText = findViewById(R.id.answerEditText);
        Button saveButton = findViewById(R.id.saveButton);

        db = FirebaseFirestore.getInstance();

        saveButton.setOnClickListener(v -> {
            String question = questionEditText.getText().toString().trim();
            String answer = answerEditText.getText().toString().trim();

            // Check if both fields are filled
            if (question.isEmpty() || answer.isEmpty()) {
                Toast.makeText(FlashcardCreationActivity.this, "Please enter both question and answer.", Toast.LENGTH_SHORT).show();
            } else {
                Flashcard flashcard = new Flashcard(question, answer);

                // Save the flashcard to Firestore
                db.collection("flashcards").add(flashcard)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(FlashcardCreationActivity.this, "Flashcard added successfully!", Toast.LENGTH_SHORT).show();
                            finish();  // Close this activity and return to HomeScreenActivity
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(FlashcardCreationActivity.this, "Error adding flashcard. Please try again.", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
