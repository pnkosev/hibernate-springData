package car_dealer.util;

public interface XMLParser {
    <T> T fromXML(Class<T> klass, String path);
    <T> void toXML(T entity, String path);
}
