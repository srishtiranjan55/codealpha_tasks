package com.chatbot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class ChatBot {
    private Map<String, String> responses = new HashMap<>();
    private Map<String, String> keywordResponses = new HashMap<>();


    public ChatBot() {
        loadResponses();
        loadKeywordResponses(); 
    }

    private void loadResponses() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/chatbot/responses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    responses.put(parts[0].toLowerCase(), parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading responses: " + e.getMessage());
        }
    }

    private String preprocess(String input) {
        return input.toLowerCase().replaceAll("[^a-zA-Z ]", "").trim();
    }

    private void loadKeywordResponses() {
        keywordResponses.put("java", "Java is a high-level programming language.");
        keywordResponses.put("python", "Python is a versatile scripting language.");
        keywordResponses.put("object", "You can create an object using the 'new' keyword. Like: MyClass obj = new MyClass();");
        keywordResponses.put("inheritance", "Inheritance allows a class to use properties and methods of another class.");
        keywordResponses.put("polymorphism", "Polymorphism allows objects to be treated as instances of their parent class.");
    }

    public String getResponse(String input) {
        input = preprocess(input);
        if (responses.containsKey(input)) {
            return responses.get(input);
        }
        for (String keyword : keywordResponses.keySet()) {
            if (input.contains(keyword)) {
                return keywordResponses.get(keyword);
            }
        }        
     return getSmartResponse(input);
    }

    
    private String getSmartResponse(String input) {
        if (input.contains("your name") || input.contains("who are you")) {
            return "I'm ChatBot, your virtual assistant!";
        }

        if (input.contains("how are you") || input.contains("how you doing")) {
            return "I'm doing great! Thanks for asking. How about you?";
        }

        if (input.contains("hello") || input.contains("hi") || input.contains("hey")) {
            return "Hello there! 👋";
        }

        if (input.contains("thank you") || input.contains("thanks") || input.contains("thanku")) {
            return "You're welcome! 😊";
        }

        if (input.contains("what's up") || input.contains("whats up") || input.contains("sup")) {
            return "Not much, just hanging out in code! What about you?";
        }

        if (input.contains("good morning")) {
            return "Good morning! ☀️ Hope you have a fantastic day!";
        }

        if (input.contains("good night")) {
            return "Good night! 🌙 Sweet dreams!";
        }

        if (input.contains("who made you") || input.contains("who created you")) {
            return "I was created by a Java developer just like you!";
        }

        if (input.contains("help") || input.contains("what can you do")) {
            return "I can chat with you, answer simple questions, and make you smile 😊";
        }

        if (input.contains("time")) {
            LocalTime time = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            return "The current time is " + time.format(formatter);
        }

        if (input.contains("date") || input.contains("today")) {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            return "Today's date is " + date.format(formatter);
        }
        if (input.contains("day") || input.contains("what day is today")) {
            DayOfWeek day = LocalDate.now().getDayOfWeek();
            return "Today is " + day;
        }
        if (input.contains("i am happy") || input.contains("i'm happy") || input.contains("feeling happy")) {
            return "Yay! I'm happy too 😄 Let's keep the good vibes going!";
        } 
        if (input.contains("i am sad") || input.contains("i'm sad") || input.contains("feeling sad")) {
            return "Oh no 😢 I'm here for you. Want to talk about it?";
        }    
        if (input.contains("i am excited") || input.contains("i'm excited")) {
            return "Woohoo! I’m excited too! 🚀 Tell me what's going on!";
        }
        if (input.contains("are you happy")) {
            return "Totally! Chatting with you always makes me happy 😄";
        }
        if (input.contains("are you angry")) {
            return "Nope! I'm as calm as a cucumber 🥒";
        }
        if (input.contains("it's my birthday") || input.contains("birthday today")) {
            return "Happy Birthday! 🎉🎂 Wishing you a fantastic year ahead!";
        }

        return "Sorry, I didn't understand that.";
    }
}
