package com.example.flashlearn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

            Flashcard flashcard = new Flashcard(question, answer);
            db.collection("flashcards").add(flashcard)
                    .addOnSuccessListener(documentReference -> finish());
        });
    }
}
