package com.isettozeur.quizapp;

public class QuestionAnswer {

    public static String question[] ={
            "Which programming language is commonly used for system-level development?",
            "What is the primary purpose of a database management system?",
            "In object-oriented programming, what is encapsulation?",
            "Which company developed the Java programming language?"
    };

    public static String choices[][] = {
            {"C++", "Java", "Python", "Assembly"},
            {"Store and retrieve data", "Play video games", "Send emails", "Draw graphics"},
            {"Hiding the implementation details", "Building user interfaces", "Handling exceptions", "Creating algorithms"},
            {"Microsoft", "Google", "Oracle", "IBM"}
    };

    public static String correctAnswers[] = {
            "C++",
            "Store and retrieve data",
            "Hiding the implementation details",
            "Oracle"
    };
}
