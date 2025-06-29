package com.chatbot;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChatBot bot = new ChatBot();

        
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│     Welcome to ChatBot 🤖     |");
        System.out.println("│   Type your message below     │");
        System.out.println("│   Type 'exit' to end chat     │");
        System.out.println("└───────────────────────────────┘");


        //System.out.println("Hi! I’m ChatBot. Type 'exit' to end the chat.");

        while (true) {
            System.out.print(ConsoleColors.BLUE + "👤 You: " + ConsoleColors.RESET);
            String input = sc.nextLine().toLowerCase(); 
            //System.out.print("You: ");
            //String input = sc.nextLine();

                    
            if (input.equals("exit")) {
                System.out.println(ConsoleColors.GREEN + "🤖 ChatBot: Goodbye! 👋" + ConsoleColors.RESET);
                break;
            }

            if (input.trim().isEmpty()) {
                System.out.println(ConsoleColors.RED + "🤖 ChatBot: Please say something!" + ConsoleColors.RESET);
                continue; 
            }
                        
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                
            }


            String response = bot.getResponse(input);
            System.out.println(ConsoleColors.GREEN + "🤖 ChatBot: " + response + ConsoleColors.RESET);
            System.out.println();
        }

        sc.close();
    }
}
