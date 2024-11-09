package com.example.flashlearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {

    private final List<Flashcard> flashcardList;
    private final Context context;

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
        holder.questionTextView.setText(flashcard.getQuestion());
        holder.itemView.setOnClickListener(v -> {
            // Handle click to open flashcard view screen
        });
    }

    @Override
    public int getItemCount() {
        return flashcardList.size();
    }

    static class FlashcardViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;

        public FlashcardViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
        }
    }
}
