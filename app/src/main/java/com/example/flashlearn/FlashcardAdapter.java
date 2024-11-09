package com.example.flashlearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {

    private List<Flashcard> flashcardList;
    private Context context;

    public FlashcardAdapter(List<Flashcard> flashcardList, Context context) {
        this.flashcardList = flashcardList;
        this.context = context;
    }

    @NonNull
    @Override
    public FlashcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_flashcard, parent, false);
        return new FlashcardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashcardViewHolder holder, int position) {
        Flashcard flashcard = flashcardList.get(position);

        // Set the question text
        holder.questionTextView.setText(flashcard.getQuestion());
        holder.answerTextView.setText(flashcard.getAnswer());

        // Handle click event for the flip effect
        holder.flashcardFrame.setOnClickListener(v -> {
            if (holder.flashcardBack.getVisibility() == View.VISIBLE) {
                // Flip to the front (question)
                holder.flashcardBack.setVisibility(View.GONE);
                holder.flashcardFront.setVisibility(View.VISIBLE);
            } else {
                // Flip to the back (answer)
                holder.flashcardBack.setVisibility(View.VISIBLE);
                holder.flashcardFront.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return flashcardList.size();
    }

    public static class FlashcardViewHolder extends RecyclerView.ViewHolder {
        FrameLayout flashcardFrame;
        LinearLayout flashcardFront;
        LinearLayout flashcardBack;
        TextView questionTextView;
        TextView answerTextView;

        public FlashcardViewHolder(View itemView) {
            super(itemView);
            flashcardFrame = itemView.findViewById(R.id.flashcard_frame);
            flashcardFront = itemView.findViewById(R.id.flashcard_front);
            flashcardBack = itemView.findViewById(R.id.flashcard_back);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            answerTextView = itemView.findViewById(R.id.answerTextView);
        }
    }
}
