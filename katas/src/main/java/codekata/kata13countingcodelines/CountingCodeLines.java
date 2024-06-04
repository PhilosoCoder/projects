package codekata.kata13countingcodelines;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountingCodeLines {

    public static void main(String[] args) throws IOException {
        String currentPath = "/Users/HU901163/Documents/projects-main/code-katas";
        Set<Path> files = listFilesUsingFileWalk(currentPath);
        List<String> codeLines = filterJavaFiles(files);

        System.out.println("\nSum of useful code lines:" + countCodeLines(codeLines));
        //
        //
        //
        /*
         *
         *
         *
         *    */


    }

    private static Integer countCodeLines(List<String> codeLines) {
        System.out.println("Useful lines to check:\n\n");
        int counter = 0;
        for (String line : codeLines) {
            if (!line.isEmpty() && !line.matches("^\\s+/.*") && !line.matches("^\\s+\\*.*")) {
                counter++;
                System.out.println(line);
            }
        }
        return counter;
    }

    public static Set<Path> listFilesUsingFileWalk(String dir) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir), 30)) {
            return stream.filter(file -> !Files.isDirectory(file)).map(x -> Paths.get(x.toUri())).collect(Collectors.toSet());
        }
    }

    public static List<String> filterJavaFiles(Set<Path> files) {
        List<String> codeLines = new ArrayList<>();

        for (Path filePath : files) {
            if (filePath.toString().endsWith(".java")) {
                try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        codeLines.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return codeLines;
    }

}
