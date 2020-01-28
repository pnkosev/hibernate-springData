package pn.utils.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public final class TextFileUtils {
    private TextFileUtils() {}

    public static String read(String path) {
        // Solution 1
        //needs content root path - src/main/resources/json/users.json
        String result = "";
        try {
            result = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

        // Solution 2
        // needs source root path - json/users.json
//        File file = new ClassPathResource(path).getFile();
//        Scanner in = new Scanner(new FileReader(file));
//
//        StringBuilder json = new StringBuilder();
//
//        while (in.hasNext()) {
//            json.append(in.nextLine()).append(System.lineSeparator());
//        }
//
//        return json.toString();

        // Solution 3
        // needs content root path - src/main/resources/json/users.json
//        try (final InputStream inputStream = new FileInputStream(path)) {
//            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    public static void write(String path, String json) {
        try {
            Files.writeString(Paths.get(path), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
