package file;

import java.io.FileOutputStream;

public class Multiplication {
    private static final byte[] SEPARATOR = System.lineSeparator().getBytes();

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Таблица умножения".getBytes());
            out.write(SEPARATOR);

            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    String rsl = " " + (i * j);
                    out.write(rsl.getBytes());
                }
                out.write(SEPARATOR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
