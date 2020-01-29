package pn.utils.api;

public interface Parser {
    <T> String toJSON(T entity);
    <T> T fromJSON(String path, Class<T> klass);
    <T> T objectFromJSON(String path, Class<T> klass);
}
