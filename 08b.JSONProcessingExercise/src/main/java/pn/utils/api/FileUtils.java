package pn.utils.api;

public interface FileUtils {
    String read(String path);
    void write(String path, String json);
}
