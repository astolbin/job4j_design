package file;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }

            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                boolean isEven = isEven(Integer.parseInt(line));
                System.out.println(line + " is even: " + isEven);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isEven(int num) {
        return num % 2 == 0;
    }
}
