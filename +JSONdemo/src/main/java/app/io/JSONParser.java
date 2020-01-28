package app.io;

import app.domain.dto.PersonDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JSONParser {
    private Gson gson;
    private FileIOUtil fileIOUtil;

    public JSONParser() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.fileIOUtil = new FileIOUtil();
    }

    public <T> T importJson(Class<T> tClass, String fileName)
            throws IOException {
        String content = this.fileIOUtil.read(fileName);
        T mapped = gson.fromJson(content, tClass);
        return mapped;
    }

    public void outputJson(PersonDto dto,String fileName) throws IOException {
        String content = gson.toJson(dto);
        this.fileIOUtil.write(content, fileName);
    }
}
