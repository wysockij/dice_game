import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        String filePath = "scores_log.txt";
        Path path = Paths.get(filePath);
        try {
            if(Files.exists(path)) {
                FileWriter fileWriter = new FileWriter(filePath);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print("");
                printWriter.close();
            }
            else {
                throw new IOException("Missing path: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Missing path:" + filePath);
        }

        MenuView menu = new MenuView();
    }
}
