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

        // Save button click listener
        saveButton.setOnClickListener(v -> {
            String question = questionEditText.getText().toString().trim();
            String answer = answerEditText.getText().toString().trim();

            // Check if the question and answer are not empty
            if (question.isEmpty() || answer.isEmpty()) {
                // Show a toast message to notify the user that both fields are required
                Toast.makeText(FlashcardCreationActivity.this, "Please enter both question and answer.", Toast.LENGTH_SHORT).show();
            } else {
                // Create a new Flashcard object
                Flashcard flashcard = new Flashcard(question, answer);

                // Add flashcard to Firestore
                db.collection("flashcards").add(flashcard)
                        .addOnSuccessListener(documentReference -> {
                            // Show a success message
                            Toast.makeText(FlashcardCreationActivity.this, "Flashcard added successfully!", Toast.LENGTH_SHORT).show();

                            // Finish the activity and return to the home screen
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            // Show an error message if the flashcard creation fails
                            Toast.makeText(FlashcardCreationActivity.this, "Error adding flashcard. Please try again.", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
