import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    Phonebook phonebook = new Phonebook();
    phonebook.loadEntriesFromFile("data/directory.txt", true);

    Phonebook names = new Phonebook();
    names.loadEntriesFromFile("data/find.txt", false);

    //*****************       LINEAR SEARCH      ***********************************
    System.out.println("Start searching (linear search)...");
    long start = System.currentTimeMillis();
    String result = SearchAlgorithm.linearSearch(phonebook, names);
    long stop = System.currentTimeMillis();
    long linearSearchTime = stop - start;
    System.out.println(result + convertMilliseconds(linearSearchTime) + '\n');

    //*****************        JUMP SEARCH       ***********************************
    System.out.println("Start searching (bubble sort + jump search)...");

    start = System.currentTimeMillis();
    List<Entry> sortedNames = SortAlgorithm.bubbleSort(phonebook, linearSearchTime);
    stop = System.currentTimeMillis();
    long bubbleSortTime = stop - start;

    result = SearchAlgorithm.linearSearch(phonebook, names);
    linearSearchTime = System.currentTimeMillis() - start;

    System.out.println(result + convertMilliseconds(linearSearchTime));
    System.out.println("Sorting time: " + convertMilliseconds(bubbleSortTime) + " - STOPPED, moved to linear search");
    System.out.println("Searching time: " + convertMilliseconds(linearSearchTime - bubbleSortTime) + '\n');

    //*****************QUICK SORT + BUBBLE SEARCH***********************************
    List<Entry> sortedDirectory = new ArrayList<>(phonebook.getEntries());
    System.out.println("Start searching (quick sort + binary search)...");
    start = System.currentTimeMillis();
    SortAlgorithm.quickSort(sortedDirectory, 0, sortedDirectory.size() - 1);
    long sortTime = System.currentTimeMillis() - start;
    int count = 0;
    for (Entry name : names.getEntries()) {
      if (SearchAlgorithm.binarySearch(sortedDirectory, name)) {
        count++;
      }
    }
    long allTime = System.currentTimeMillis() - start;
    System.out.println("Found " + count + " / " + names.size() + ". " + convertMilliseconds(allTime));
    System.out.println("Sorting time: " + convertMilliseconds(sortTime));
    System.out.println("Searching time: " + convertMilliseconds(allTime - sortTime) + '\n');


    //*****************       HashTable create and search      ***********************************
    System.out.println("Start searching (hash table)...");
    long startCreateTable = System.currentTimeMillis();
    HashTable<String, Integer> table = SortAlgorithm.createHashTable(phonebook);
    long timeCreateTable = System.currentTimeMillis() - startCreateTable;
    String resultSearchInTable = SearchAlgorithm.hashTableSearch(table, names);
    long searchAndSort = System.currentTimeMillis();
    System.out.println(resultSearchInTable + convertMilliseconds(searchAndSort - startCreateTable));
    System.out.println("Creating time: " + convertMilliseconds(timeCreateTable));
    System.out.println("Searching time: " + convertMilliseconds(searchAndSort - startCreateTable - timeCreateTable));
  }


  /**
   * Convert milliseconds to String : X min. Y sec. Z ms.
   *
   * @param milliseconds
   * @return String
   */
  public static String convertMilliseconds(long milliseconds) {
    Duration d = Duration.ofMillis(milliseconds);
    int min = d.toMinutesPart();
    int sec = d.toSecondsPart();
    int ms = d.toMillisPart();
    return min + " min. " + sec + " sec. " + ms + " ms.";
  }
}

