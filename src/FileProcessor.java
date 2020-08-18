import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileProcessor {

  public static List<String> readFile(String filePath) {
    List<String> lines;
    try {
      lines =
          Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return lines;
  }

  public static void saveListInFile(String filePath, List<Entry> names) {
    try (FileWriter writer = new FileWriter(filePath)) {
      for (Entry entry : names) {
        writer.write(entry.toString() + '\n');
      }
      System.out.println("Successfully wrote to the file.");
    } catch (
        IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
