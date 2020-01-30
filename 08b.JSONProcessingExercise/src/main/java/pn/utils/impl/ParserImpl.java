package pn.utils.impl;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import pn.utils.api.FileUtils;
import pn.utils.api.Parser;

import java.util.Collection;

@Component
public class ParserImpl implements Parser {
    private final Gson gson;
    private final FileUtils fileUtils;

    public ParserImpl(Gson gson, FileUtils fileUtils) {
        this.gson = gson;
        this.fileUtils = fileUtils;
    }

    @Override
    public <T> String toJSON(T entity) {
        return this.gson.toJson(entity);
    }

    @Override
    public <T> T fromJSON(String path, Class<T> klass) {
        return this.gson.fromJson(path, klass);
    }

    @Override
    public <T> T objectFromJSON(String path, Class<T> klass) {
        return this.gson.fromJson(this.fileUtils.read(path), klass);
    }

    @Override
    public <T> void objectToJSON(String path, Collection<T> list) {
        this.fileUtils.write(path, this.gson.toJson(list));
    }
}
