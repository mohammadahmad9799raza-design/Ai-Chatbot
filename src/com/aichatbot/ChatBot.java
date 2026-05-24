package com.aichatbot;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * ChatBot manages the conversation loop and controls user interactions.
 */
public class ChatBot {

    private final Scanner scanner;
    private final ResponseHandler responseHandler;
    private boolean isRunning;

    public ChatBot() {
        scanner = new Scanner(System.in);
        responseHandler = new ResponseHandler();
        isRunning = true;
    }

    /**
     * Starts the chatbot conversation loop.
     */
    public void start() {
        try {
            while (isRunning) {
                System.out.print("You: ");
                String userInput = scanner.nextLine();
                handleUserInput(userInput);
            }
        } catch (NoSuchElementException exception) {
            printAnimatedResponse("Sorry, I had trouble reading your input. Please try again.");
        } catch (Exception exception) {
            printAnimatedResponse("An unexpected error occurred. Please restart the program.");
        } finally {
            closeScanner();
        }
    }

    /**
     * Processes the user's input and prints the chatbot response.
     *
     * @param input Raw input from the user.
     */
    private void handleUserInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            printAnimatedResponse("Please type a question or say hi to start the chat.");
            return;
        }

        String normalizedInput = input.trim();
        if (responseHandler.isExitCommand(normalizedInput)) {
            printAnimatedResponse(responseHandler.getExitResponse());
            isRunning = false;
            return;
        }

        String response = responseHandler.getResponse(normalizedInput);
        printAnimatedResponse(response);
    }

    /**
     * Prints chatbot messages with a typing animation.
     *
     * @param response ChatBot text response.
     */
    private void printAnimatedResponse(String response) {
        System.out.print("ChatBot: ");
        typingEffect(response);
        System.out.println();
    }

    /**
     * Simulates typing by printing characters with a short delay.
     *
     * @param text Text to display.
     */
    private void typingEffect(String text) {
        for (char character : text.toCharArray()) {
            System.out.print(character);
            sleep(20);
        }
    }

    /**
     * Pauses execution for the requested number of milliseconds.
     *
     * @param milliseconds Duration of the pause.
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Closes the scanner resource when the chat ends.
     */
    private void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
