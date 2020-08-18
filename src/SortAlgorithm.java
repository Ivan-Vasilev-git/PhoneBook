import java.util.ArrayList;
import java.util.List;

public class SortAlgorithm {

  public static List<Entry> bubbleSort(Phonebook phonebook, long time) {
    long start = System.currentTimeMillis();

    List<Entry> result = new ArrayList<>(phonebook.getEntries());
    boolean swapped;
    for (int i = 0; i < result.size() - 1; i++) {
      swapped = false;
      for (int j = 0; j < result.size() - i - 1; j++) {
        if (result.get(j).compareTo(result.get(j + 1)) > 0) {
          Entry temp = result.get(j);
          result.set(j, result.get(j + 1));
          result.set(j + 1, temp);
          swapped = true;
        }
        if (System.currentTimeMillis() - start > time * 10) {
          return result;
        }
      }
      if (!swapped) {
        return result;
      }
    }
    return result;
  }

  public static List<Entry> bubbleSort(Phonebook phonebook) {
    return bubbleSort(phonebook, Long.MAX_VALUE / 20);
  }

  public static void quickSort(List<Entry> phonebook, int left, int right) {
    if (left < right) {
      int pivotIndex = partition(phonebook, left, right); // the pivot is already on its place
      quickSort(phonebook, left, pivotIndex - 1);  // sort the left subarray
      quickSort(phonebook, pivotIndex + 1, right); // sort the right subarray
    }
  }

  private static int partition(List<Entry> phonebook, int left, int right) {
    Entry pivot = phonebook.get(right);  // choose the rightmost element as the pivot
    int partitionIndex = left; // the first element greater than the pivot

    /* move large values into the right side of the array */
    for (int i = left; i < right; i++) {
      if (phonebook.get(i).compareTo(pivot) <= 0) { // may be used '<' as well
        swap(phonebook, i, partitionIndex);
        partitionIndex++;
      }
    }

    swap(phonebook, partitionIndex, right); // put the pivot on a suitable position

    return partitionIndex;
  }

  private static void swap(List<Entry> phonebook, int i, int j) {
    Entry temp = phonebook.get(i);
    phonebook.set(i, phonebook.get(j));
    phonebook.set(j, temp);
  }

  public static HashTable<String, Integer> createHashTable(Phonebook phonebook) {
    HashTable<String, Integer> result = new HashTable<>((int) phonebook.size());
    for (Entry entry : phonebook.getEntries()) {
      result.put(entry.getFullName(), entry.getPhoneNumber());
    }
    return result;
  }

}
