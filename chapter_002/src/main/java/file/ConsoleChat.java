package file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private String state = CONTINUE;
    private final Logger logger = new Logger();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = getAnswers();

        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.print("Напишите что-нибудь: ");
            String input = log(scanner.nextLine());

            if (input.equals(OUT)) {
                run = false;
            } else if (input.equals(CONTINUE) && state.equals(STOP)) {
                state = CONTINUE;
                System.out.println(log("Чат продолжен"));
            } else if (state.equals(STOP) || input.equals(STOP)) {
                state = STOP;
                System.out.println(
                        log(
                                "Чат приостановлен, напишите \""
                                + CONTINUE + "\" для возобновления"
                        )
                );
            } else {
                int rand = (int) (Math.random() * answers.size());
                System.out.println("Ответ: " + log(answers.get(rand)));
            }
        }

        logger.save();
    }

    private List<String> getAnswers() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            rsl = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private String log(String message) {
        logger.log(message);
        return message;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "./data/chat.log",
                "./data/chat_answers.txt"
        );
        cc.run();
    }

    private class Logger {
        private final List<String> log = new ArrayList<>();

        public void log(String message) {
            log.add("- " + message);
        }

        public void save() {
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                    new FileOutputStream(path, true)), true, StandardCharsets.UTF_8)) {
                log.forEach(out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}