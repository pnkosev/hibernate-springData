package pn.utils.api;

import java.util.Collection;

public interface Parser {
    <T> String toJSON(T entity);
    <T> T fromJSON(String path, Class<T> klass);
    <T> T objectFromJSON(String path, Class<T> klass);
    <T> void objectToJSON(String path, Collection<T> klass);
}
