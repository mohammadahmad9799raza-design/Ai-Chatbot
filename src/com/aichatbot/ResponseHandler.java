package com.aichatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * ResponseHandler contains the chatbot logic and decides how to answer each input.
 */
public class ResponseHandler {

    private final Map<String, String> faqResponses;
    private final Map<String, String> generalResponses;
    private static final String[] EXIT_COMMANDS = {"bye", "exit", "quit", "goodbye"};
    private static final String[] GREETINGS = {"hello", "hi", "hey", "good morning", "good afternoon", "good evening"};
    private static final String[] PROGRAMMING_KEYWORDS = {"java", "python", "programming", "code", "compiler", "debug", "algorithm", "syntax", "oop", "object oriented"};
    private static final String[] STUDENT_KEYWORDS = {"college", "university", "student", "exam", "assignment", "homework", "result", "project", "courses", "admission", "scholarship"};
    private static final String[] DATE_TIME_KEYWORDS = {"date", "time", "today", "day", "clock", "schedule"};
    private static final String[] GENERAL_KEYWORDS = {"science", "history", "math", "english", "career", "technology", "health", "motivation", "goal", "skill", "project", "education", "learning", "business", "finance", "art", "sports", "travel", "food"};
    private static final String[] QUESTION_WORDS = {"what", "how", "why", "who", "when", "where", "which", "do", "does", "did", "can", "should", "will", "is", "are", "am"};
    private static final String[] GENERAL_HELPERS = {"define", "describe", "explain", "tell me", "give me", "teach me"};

    public ResponseHandler() {
        faqResponses = new HashMap<>();
        generalResponses = new HashMap<>();
        initializeFaqResponses();
        initializeGeneralResponses();
    }

    /**
     * Initializes the FAQ map with smart responses.
     */
    private void initializeFaqResponses() {
        faqResponses.put("how are you", "I am a virtual assistant, ready to help you with programming and college questions.");
        faqResponses.put("what can you do", "I can answer programming questions, help with college-related queries, show the current date/time, and respond to FAQs.");
        faqResponses.put("who created you", "I was built as a beginner-friendly Java console chatbot using object-oriented design.");
        faqResponses.put("help", "Ask me about programming, college, the current date and time, or say 'bye' to exit.");
        faqResponses.put("thank you", "You're welcome! If you have more questions, feel free to ask.");
    }

    private void initializeGeneralResponses() {
        generalResponses.put("history", "History is the study of past events and people. It helps us learn from earlier successes and mistakes.");
        generalResponses.put("sachin tendulkar", "Sachin Tendulkar is a legendary Indian cricketer known for his record-breaking batting career and sportsmanship.");
        generalResponses.put("java", "Java is a powerful object-oriented language used for building desktop, web, and mobile applications.");
        generalResponses.put("python", "Python is a flexible language known for clear syntax and is commonly used in data science, web development, and automation.");
        generalResponses.put("artificial intelligence", "Artificial intelligence uses computers to solve tasks that usually need human intelligence, like learning and reasoning.");
        generalResponses.put("ai", "AI stands for artificial intelligence and helps machines learn from data and make decisions.");
        generalResponses.put("computer", "A computer is a machine that processes information and helps people solve complicated tasks faster.");
        generalResponses.put("internet", "The internet is a global network that connects computers and devices so people can share information around the world.");
        generalResponses.put("health", "Good health comes from eating well, staying active, and taking care of your mental and physical well-being.");
        generalResponses.put("motivation", "Motivation helps you stay focused on your goals. Set small targets, celebrate progress, and keep moving forward.");
        generalResponses.put("career", "A strong career plan begins with understanding your strengths, learning new skills, and choosing goals that match your interests.");
        generalResponses.put("study", "Good study habits include planning, regular revision, practice, and asking questions when you don't understand something.");
        generalResponses.put("coding", "Coding means writing instructions for a computer to follow. Start with small programs and build up step by step.");
        generalResponses.put("programmer", "Becoming a programmer takes patience, practice, and learning languages like Java or Python. Start building small projects.");
        generalResponses.put("math", "Math helps you think logically and solve problems. Practice regularly and focus on understanding the ideas, not just formulas.");
        generalResponses.put("learning", "Learning is the process of practicing, asking questions, and understanding new ideas over time.");
        generalResponses.put("education", "Education helps you build knowledge and skills for your future. It grows with effort and curiosity.");
    }

    /**
     * Checks whether the input is an exit command.
     *
     * @param input The normalized user input.
     * @return true if the input should end the conversation.
     */
    public boolean isExitCommand(String input) {
        String normalized = input.toLowerCase();
        for (String command : EXIT_COMMANDS) {
            if (normalized.equals(command)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a goodbye message for the exit command.
     *
     * @return A polite exit response.
     */
    public String getExitResponse() {
        return "Goodbye! Best of luck with your studies and coding practice.";
    }

    /**
     * Returns an appropriate chatbot response for the user input.
     *
     * @param input Raw user input.
     * @return Response text.
     */
    public String getResponse(String input) {
        String normalized = input.toLowerCase();

        if (containsAny(normalized, GREETINGS)) {
            return "Hello! I am here to help. Ask me about programming, college, or the current date and time.";
        }

        String faqResponse = getFaqResponse(normalized);
        if (faqResponse != null) {
            return faqResponse;
        }

        if (containsAny(normalized, DATE_TIME_KEYWORDS)) {
            return getDateTimeResponse(normalized);
        }

        if (containsAny(normalized, PROGRAMMING_KEYWORDS)) {
            return getProgrammingResponse(normalized);
        }

        if (containsAny(normalized, STUDENT_KEYWORDS)) {
            return getStudentResponse(normalized);
        }

        if (isQuestion(normalized) || containsAny(normalized, GENERAL_KEYWORDS) || containsAny(normalized, GENERAL_HELPERS)) {
            return getGeneralResponse(normalized);
        }

        return getUnknownResponse();
    }

    /**
     * Finds a matching FAQ response from the stored map.
     *
     * @param input Normalized input text.
     * @return Matching FAQ response, or null if none found.
     */
    private String getFaqResponse(String input) {
        for (Map.Entry<String, String> entry : faqResponses.entrySet()) {
            if (input.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Returns a date/time answer when the user asks about the current date or time.
     *
     * @param input Normalized input text.
     * @return Date or time response.
     */
    private String getDateTimeResponse(String input) {
        LocalDateTime now = LocalDateTime.now();
        if (input.contains("time") || input.contains("clock")) {
            String timeText = now.format(DateTimeFormatter.ofPattern("hh:mm a"));
            return "The current time is " + timeText + ".";
        }
        if (input.contains("date") || input.contains("today") || input.contains("day")) {
            String dateText = now.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"));
            return "Today is " + dateText + ".";
        }
        return "The current date and time is " + now.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy hh:mm a")) + ".";
    }

    /**
     * Returns a programming-related answer.
     *
     * @param input Normalized input text.
     * @return Programming response.
     */
    private String getProgrammingResponse(String input) {
        return switch (detectProgrammingTopic(input)) {
            case "java" -> "Java is a popular object-oriented language. Use classes, methods, and strong typing to write clean code.";
            case "python" -> "Python is beginner friendly and great for scripting, data science, and automation.";
            case "compiler" -> "A compiler translates source code into machine code. In Java, javac compiles .java files into .class files.";
            case "algorithm" -> "An algorithm is a step-by-step solution to a problem. Practice by solving small problems and tracing each step.";
            default -> "Programming is a skill you build with practice. Ask me specific questions about Java, Python, code, or debugging.";
        };
    }

    /**
     * Detects a programming topic from the input text.
     *
     * @param input Normalized input text.
     * @return Topic key to use in switch-case.
     */
    private String detectProgrammingTopic(String input) {
        if (input.contains("java")) {
            return "java";
        }
        if (input.contains("python")) {
            return "python";
        }
        if (input.contains("compiler") || input.contains("compile") || input.contains("javac")) {
            return "compiler";
        }
        if (input.contains("algorithm") || input.contains("data structure") || input.contains("debug")) {
            return "algorithm";
        }
        return "general";
    }

    /**
     * Returns a student or college-related answer.
     *
     * @param input Normalized input text.
     * @return Student response.
     */
    private String getStudentResponse(String input) {
        if (input.contains("exam") || input.contains("assignment") || input.contains("homework")) {
            return "Create a study plan, start early, and ask questions whenever you are unsure. That helps you perform better in exams.";
        }
        if (input.contains("college") || input.contains("university") || input.contains("project")) {
            return "Stay organized, manage your time, and collaborate with classmates to succeed in college projects and classes.";
        }
        if (input.contains("result") || input.contains("grade") || input.contains("marks")) {
            return "Focus on consistent learning and ask your teachers for feedback. That is the best way to improve your grades.";
        }
        return "College and student life can be busy. Balance study, rest, and practice so you can achieve your goals.";
    }

    /**
     * Returns a response for broader general questions.
     *
     * @param input The normalized user input.
     * @return Generated general response.
     */
    private String getGeneralResponse(String input) {
        String subject = extractSubject(input);
        if (subject == null || subject.isEmpty()) {
            subject = determineGeneralSubject(input);
        }
        if (subject == null || subject.isEmpty()) {
            subject = "your question";
        }

        if (generalResponses.containsKey(subject)) {
            return generalResponses.get(subject);
        }

        if (input.contains("why should i")) {
            return "Studying or learning " + subject + " can help you build skills, improve your future, and reach your goals.";
        }
        if (input.contains("should i") || input.contains("can i") || input.contains("do i") || input.contains("does it") || input.contains("would it")) {
            return "That depends on your goals, but focusing on " + subject + " can help you improve and make better decisions.";
        }
        if (input.contains("what is") || input.contains("what are") || input.startsWith("what")) {
            return "It looks like you're asking about " + subject + ". In simple terms, " + subject + " is a topic that can help you grow your knowledge.";
        }
        if (input.contains("how to") || input.contains("how do i") || input.contains("how can i") || input.startsWith("how")) {
            return "To learn " + subject + ", start with the basics, break it into small steps, and practice regularly.";
        }
        if (input.startsWith("why should i") || input.contains("should i")) {
            return "Studying " + subject + " can help you improve your skills, make better choices, and reach your long-term goals.";
        }
        if (input.startsWith("why")) {
            return "Often the reason behind " + subject + " is related to learning, improvement, and better results.";
        }
        if (input.startsWith("who")) {
            return "That usually depends on the people or experts connected with " + subject + ".";
        }
        if (input.startsWith("where") || input.startsWith("when") || input.startsWith("which")) {
            return "For " + subject + ", consider the context and choose the answer that fits your goal.";
        }
        if (input.contains("define") || input.contains("describe") || input.contains("explain") || input.contains("tell me")) {
            return "Here is a simple explanation of " + subject + ": learn the main idea, review examples, and ask follow-up questions to understand it better.";
        }
        return "That is a thoughtful question about " + subject + ". I may not know every detail, but focusing on the main idea will help you find the answer.";
    }

    /**
     * Determines a general subject from the input.
     *
     * @param input The normalized user input.
     * @return A subject phrase.
     */
    private String determineGeneralSubject(String input) {
        for (String keyword : GENERAL_KEYWORDS) {
            if (input.contains(keyword)) {
                return keyword;
            }
        }
        for (String keyword : PROGRAMMING_KEYWORDS) {
            if (input.contains(keyword)) {
                return keyword;
            }
        }
        for (String keyword : STUDENT_KEYWORDS) {
            if (input.contains(keyword)) {
                return keyword;
            }
        }
        return "your topic";
    }

    private String extractSubject(String input) {
        String lower = input.toLowerCase().trim();

        String[] patterns = {"tell me about ", "what is ", "what are ", "who is ", "who was ", "how do i ", "how can i ", "how to ", "should i ", "can i ", "do i ", "would it ", "where can i ", "where can ", "where should i ", "when should i ", "which ", "define ", "describe ", "explain ", "tell me "};
        for (String pattern : patterns) {
            if (lower.startsWith(pattern)) {
                return cleanupSubject(lower.substring(pattern.length()));
            }
            int index = lower.indexOf(pattern);
            if (index >= 0) {
                return cleanupSubject(lower.substring(index + pattern.length()));
            }
        }

        if (lower.startsWith("why ")) {
            return cleanupSubject(lower.substring(4));
        }
        if (lower.startsWith("where ")) {
            return cleanupSubject(lower.substring(6));
        }
        if (lower.startsWith("when ")) {
            return cleanupSubject(lower.substring(5));
        }
        if (lower.startsWith("which ")) {
            return cleanupSubject(lower.substring(6));
        }
        return "your topic";
    }

    private String cleanupSubject(String text) {
        text = text.trim();
        if (text.endsWith("?")) {
            text = text.substring(0, text.length() - 1).trim();
        }
        if (text.endsWith(".") || text.endsWith("!")) {
            text = text.substring(0, text.length() - 1).trim();
        }
        text = text.replaceAll("^(should i |can i |do i |would it |how do i |how can i |where can i |where should i |when should i |what is |what are |who is |who was |tell me about |tell me |about |to |learn |study |become a |become |a |an |the |my |your )", "").trim();
        if (text.startsWith("a ")) {
            text = text.substring(2).trim();
        }
        return text.isEmpty() ? "your topic" : text;
    }

    /**
     * Checks whether the input is phrased as a question.
     *
     * @param input The normalized user input.
     * @return true when the input looks like a question.
     */
    private boolean isQuestion(String input) {
        if (input.contains("?")) {
            return true;
        }
        return containsAny(input, QUESTION_WORDS);
    }

    /**
     * Returns a response for unrecognized input.
     *
     * @return Default unknown response.
     */
    private String getUnknownResponse() {
        return "I am not sure about that yet. Try asking about programming, college, study tips, or general knowledge questions.";
    }

    /**
     * Checks whether any keyword exists in the input.
     *
     * @param input The text to search.
     * @param keywords Keyword array.
     * @return true when input contains at least one keyword.
     */
    private boolean containsAny(String input, String[] keywords) {
        String[] words = input.split("[^a-zA-Z0-9]+?");
        for (String keyword : keywords) {
            if (keyword.contains(" ")) {
                if (input.contains(keyword)) {
                    return true;
                }
            } else {
                for (String word : words) {
                    if (keyword.equals(word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
