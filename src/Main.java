import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        FileWriter fileWriter = new FileWriter("scores_log.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("");
        printWriter.close();

        MenuView menu = new MenuView();
    }
}
