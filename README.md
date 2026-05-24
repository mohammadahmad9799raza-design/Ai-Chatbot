# AI Chatbot

A beginner-friendly Java console AI chatbot built with object-oriented programming concepts. This project is suitable for GitHub upload and internship submission.

## Project Structure

- `src/com/aichatbot/Main.java` - Application entry point and welcome screen.
- `src/com/aichatbot/ChatBot.java` - Conversation loop and user interaction logic.
- `src/com/aichatbot/ResponseHandler.java` - Response generation using OOP and smart keyword matching.

## Features

- Advanced chat loop with animated typing responses
- Animated welcome screen with a loading spinner
- Responds to greetings like `hello`, `hi`, and `hey`
- Answers programming-related questions about Java, Python, compilers, algorithms, OOP, and debugging
- Answers student/college-related questions about exams, assignments, projects, grades, admission, and study planning
- Provides random study and coding tips on request
- Shows the current date and time
- Handles FAQs with smart responses
- Supports `bye`, `exit`, `quit`, and `goodbye` commands
- Responds with a helpful unknown message for unsupported queries
- Uses clean OOP design in separate classes

## Requirements

- Java 14 or newer
- VS Code with Java extension pack recommended

## Run the Project in VS Code

1. Open the `Ai-Chatbot` folder in VS Code.
2. Ensure the Java extension is installed.
3. Open `src/com/aichatbot/Main.java`.
4. Use the Run button or press `F5` to start the `Launch AI Chatbot` configuration.

### Or run manually from project root

```bash
cd "Ai-Chatbot"
javac src/com/aichatbot/*.java
java -cp src com.aichatbot.Main
```

> Do not run `java Main` from the package folder. Use the package-qualified class name `com.aichatbot.Main` with `-cp src`.

## Sample Output

```
===========================================
          AI Chatbot Console Program
===========================================
Welcome to the advanced AI chatbot experience.
I can help with programming, student life, tips, and date/time queries.
Type any question, ask for 'tips', or say 'hi' to begin.
Enter 'bye', 'exit', or 'quit' to end the session.
-------------------------------------------
Preparing your virtual assistant...
Loading |
Ready to chat!
You: hi
ChatBot: Hello! I'm ready to help you with programming, student life, leadership tips, and more.
You: what is java
ChatBot: Java is a powerful, platform-independent language used for desktop, web, and mobile applications.
You: give me study tips
ChatBot: Break your study sessions into focused 25-minute intervals and review notes after each session.
You: show me the time
ChatBot: The current time is 10:24 AM.
You: bye
ChatBot: Goodbye! Keep learning, and come back whenever you want more help.
```

## Professional LinkedIn Project Description

**AI Chatbot Console Application** - A Java-based console chatbot built using object-oriented programming principles. This project demonstrates:

- Clean package structure and modular design
- User interaction with `Scanner` and continuous loops
- Smart response handling via `HashMap`, conditionals, and helper methods
- Exception handling for robust user input processing
- Real-world features such as date/time queries, programming guidance, student support, and exit commands

This project is ideal for showcasing core Java skills, OOP design, and beginner-friendly software development on GitHub.
