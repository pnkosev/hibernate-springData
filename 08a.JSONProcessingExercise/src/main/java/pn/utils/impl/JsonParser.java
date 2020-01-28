package pn.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
}
