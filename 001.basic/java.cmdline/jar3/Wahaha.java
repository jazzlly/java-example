import com.Foo;

import java.io.*;

public class Wahaha {

    public static void main(String[] args) {
        new Foo().msg("wahaha");
        new Bar().msg("wahaha!");

        readResourceFile("text.txt");
        System.out.println("tototo...");

        readResourceFile("res/file.txt");
        System.out.println("wuheihei...");
    }

    public static void readResourceFile(String path) {
        InputStream inputStream1 = Wahaha.class.getResourceAsStream(path);
        try {
            InputStreamReader streamReader = new InputStreamReader(inputStream1, "utf8");
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
