import java.util.List;

public class SearchAlgorithm {

  public static String linearSearch(Phonebook phonebook, Phonebook names) {
    int namesMatched = 0;
    for (Entry name : names.getEntries()) {
      for (Entry entry : phonebook.getEntries()) {
        if (entry.compareTo(name) == 0) {
          namesMatched++;
          break;
        }
      }
    }
    return "Found " + namesMatched + " / " + names.getEntries().size() + " entries. ";
  }

  public static int jumpSearch(List<Entry> names, String name) {
    int currRight = 0; // right border of the current block
    int prevRight = 0; // right border of the previous block

    /* If array is empty, the element is not found */
    if (names.size() == 0) {
      return -1;
    }

    /* Check the first element */
    if (names.get(currRight).getFullName().equals(name)) {
      return 0;
    }

    /* Calculating the jump length over list elements */
    int jumpLength = (int) Math.sqrt(names.size());

    /* Finding a block where the element may be present */
    while (currRight < names.size() - 1) {

      /* Calculating the right border of the following block */
      currRight = Math.min(names.size() - 1, currRight + jumpLength);

      if (names.get(currRight).getFullName().compareTo(name) >= 0) {
        break; // Found a block that may contain the name element
      }

      prevRight = currRight; // update the previous right block border
    }

    /* If the last block is reached and it cannot contain the name value => not found */
    if ((currRight == names.size() - 1) && names.get(currRight).getFullName().compareTo(name) < 0) {
      return -1;
    }

    /* Doing linear search in the found block */
    return backwardSearch(names, name, prevRight, currRight);
  }

  private static int backwardSearch(List<Entry> names, String name, int prevRight, int currRight) {
    for (int i = currRight; i > prevRight; i--) {
      if (names.get(i).getFullName().equals(name)) {
        return i;
      }
    }
    return -1;
  }

  public static boolean binarySearch(List<Entry> phonebook, Entry name) {
    int left = 0;
    int right = phonebook.size() - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (phonebook.get(mid).compareTo(name) == 0) {
        return true;
      } else if (phonebook.get(mid).compareTo(name) < 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return false;
  }

  public static int binarySearchRecursive(int[] array, int left, int right, int value) {
    if (left > right) {
      return -1;
    }
    int mid = left + (right - left) / 2;
    if (array[mid] == value) {
      return mid;
    } else if (array[mid] < value) {
      return binarySearchRecursive(array, mid + 1, right, value);
    } else {
      return binarySearchRecursive(array, left, mid - 1, value);
    }
  }

  public static String hashTableSearch(HashTable<String, Integer> table, Phonebook names) {
    int namesMatched = 0;
    for (Entry name : names.getEntries()) {
      if (table.get(name.getFullName()) != null) {
        namesMatched++;
      }
    }
    return "Found " + namesMatched + " / " + names.getEntries().size() + " entries. ";
  }

}
