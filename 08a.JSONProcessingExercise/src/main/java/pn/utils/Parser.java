package pn.utils;

import java.util.Collection;

public interface Parser {
    <T> String toString(T klass);
    <T> T fromString(String string, Class<T> klass);
    <T> T objectFromFile(Class<T> klass, String path);
    <T> void objectToFile(T klass, String path);
    <T> void objectToFile(Collection<T> collection, String path);
}
