package com.aichatbot;

/**
 * Main entry point for the AI Chatbot application.
 * This class launches the chatbot and displays the animated welcome screen.
 */
public class Main {

    public static void main(String[] args) {
        showAnimatedWelcome();
        ChatBot chatBot = new ChatBot();
        chatBot.start();
    }

    /**
     * Displays an animated welcome screen with a loading spinner.
     */
    private static void showAnimatedWelcome() {
        animateText("===========================================");
        animateText("          AI Chatbot Console Program       ");
        animateText("===========================================");
        animateText("Welcome to the advanced AI chatbot experience.");
        animateText("I can help with programming, student life, tips, and date/time queries.");
        animateText("Type any question, ask for 'tips', or say 'hi' to begin.");
        animateText("Enter 'bye', 'exit', or 'quit' to end the session.");
        animateText("-------------------------------------------");
        animateText("Preparing your virtual assistant...");
        showLoadingSpinner(30, 80);
        System.out.println();
    }

    /**
     * Animates a line of text by printing one character at a time.
     *
     * @param text The text to animate.
     */
    private static void animateText(String text) {
        for (char letter : text.toCharArray()) {
            System.out.print(letter);
            sleep(15);
        }
        System.out.println();
    }

    /**
     * Displays a simple loading spinner effect in the console.
     *
     * @param cycles Number of spinner rotations.
     * @param delay  Milliseconds delay per frame.
     */
    private static void showLoadingSpinner(int cycles, int delay) {
        char[] spinner = {'|', '/', '-', '\\'};
        for (int i = 0; i < cycles; i++) {
            System.out.print("\rLoading " + spinner[i % spinner.length]);
            sleep(delay);
        }
        System.out.print("\rReady to chat!        \n");
    }

    /**
     * Pauses execution for the requested number of milliseconds.
     *
     * @param milliseconds Duration of the pause.
     */
    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
