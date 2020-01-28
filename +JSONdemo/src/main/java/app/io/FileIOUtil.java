package app.io;

import java.io.*;

public class FileIOUtil {
    public String read(String fileName) throws IOException {
        InputStream str = getClass().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(str));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null){
            sb.append(line);
        }
        return sb.toString();
    }
    public void write(String content, String fileName) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(fileName);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){
            writer.write(content);
        }
    }
}
