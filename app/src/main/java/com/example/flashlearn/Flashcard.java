package com.example.flashlearn;

public class Flashcard {
    private String id;  // ID of the flashcard (Firestore document ID)
    private String question;
    private String answer;

    // Default constructor for Firestore
    public Flashcard() {
    }

    // Constructor for creating Flashcard objects
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getter and Setter for the question
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    // Getter and Setter for the answer
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Getter and Setter for the id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
