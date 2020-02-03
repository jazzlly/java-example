import com.Foo;

import java.io.*;

public class Wahaha {

    public static void main(String[] args) {
        new Foo().msg("wahaha");
        new Bar().msg("wahaha!");

        InputStream inputStream = Wahaha.class.getResourceAsStream("text.txt");
        try {
            InputStreamReader streamReader = new InputStreamReader(inputStream, "utf8");
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
        System.out.println("wuheihei...");

        InputStream inputStream1 = Wahaha.class.getResourceAsStream("res/file.txt");
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
        System.out.println("wuheihei...");
    }
}
