package pn.utils.impl;

import org.springframework.stereotype.Component;
import pn.utils.api.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileUtilsImpl implements FileUtils {
    @Override
    public String read(String path) {
        String result = "";
        try {
            result = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void write(String path, String json) {
        try {
            Files.writeString(Paths.get(path), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
