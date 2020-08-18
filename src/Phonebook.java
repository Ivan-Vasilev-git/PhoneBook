import java.util.ArrayList;
import java.util.List;

public class Phonebook {
  private List<Entry> entries;

  public Phonebook() {
    entries = new ArrayList<>();
  }

  public void addEntry(Entry entry) {
    if (entry != null) {
      entries.add(entry);
    }
  }

  public Entry getEntry(int index) {
    return entries.get(index);
  }

  public List<Entry> getEntries() {
    return List.copyOf(entries);
  }

  public void setEntries(List<Entry> entries) {
    this.entries = List.copyOf(entries);
  }

  public long size() {
    return entries.size();
  }

  public boolean loadEntriesFromFile(String filePath, boolean hasPhoneNumber) {
    List<String> list = FileProcessor.readFile(filePath);
    if (list != null) {
      for (String line : list) {
        String[] arr = line.split(" ");
        entries.add(hasPhoneNumber ? arr.length == 3 ?
            new Entry(Integer.parseInt(arr[0]), arr[1], arr[2]) : new Entry(Integer.parseInt(arr[0]), arr[1]) :
            arr.length == 2 ?
                new Entry(arr[0], arr[1]) : new Entry(arr[0]));
      }
      return true;
    }
    return false;
  }
}
