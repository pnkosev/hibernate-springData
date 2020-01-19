package pn.springdataintroexercise.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public String[] readFile(String path) throws IOException {

        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = new ArrayList<>();

        String line = reader.readLine();

        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }

        return lines.stream().filter(l -> !l.equals("")).toArray(String[]::new);
    }
}
