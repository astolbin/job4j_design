package file;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                addToZip(zip, source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToZip(ZipOutputStream zip, Path source) throws IOException {
        zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
        try (BufferedInputStream out = new BufferedInputStream(
                new FileInputStream(source.toFile()))) {
            zip.write(out.readAllBytes());
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            addToZip(zip, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Zip().packSingleFile(
                Paths.get("./chapter_005/pom.xml"),
                Paths.get("./chapter_005/pom.zip")
        );
    }
}
