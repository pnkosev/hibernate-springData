package pn.utils.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import pn.utils.Parser;
import java.util.Collection;

@Component
public class JsonParser implements Parser {
    static Gson gson;

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public <T> String toString(T klass) {
        return gson.toJson(klass);
    }

    @Override
    public <T> T fromString(String string, Class<T> klass) {
        return gson.fromJson(string, klass);
    }

    @Override
    public <T> T objectFromFile(Class<T> klass, String path) {
        return gson.fromJson(TextFileUtils.read(path), klass);
    }

    @Override
    public <T> void objectToFile(Collection<T> list, String path) {
        String json = gson.toJson(list);
        TextFileUtils.write(path, json);
    }
}
