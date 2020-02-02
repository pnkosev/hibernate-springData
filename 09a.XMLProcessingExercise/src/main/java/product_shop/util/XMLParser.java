package product_shop.util;

public interface XMLParser {
    <T> T fromXML(Class<T> klass, String path);
    <T> void toXML(T entity, String path);
}
